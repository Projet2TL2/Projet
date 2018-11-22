import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Joueur {

	String nom;
	ArrayList<Bateau> mesBateaux = new ArrayList<Bateau>();
	Case[][] plateau = new Case [10][10];
	//Faction maFaction;
	
	/*
	 * Cree un joueur avec un nom
	 * @param : nom represente son nom
	 */
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	/*
	 * Cree un joueur avec un nom
	 * @param : nom represente son nom
	 * @param : mesBateaux represente les bateaux du joueur
	 */
	public Joueur(String nom, ArrayList<Bateau> mesBateaux) {
		this.nom = nom;
		this.mesBateaux = mesBateaux;
	}
	
	/*
	 * @return le nom du mode de jeux choisi ( console ou interface )
	 */
	public String choixModeJeux(){
		String choix = "";
		choix = JOptionPane.showInputDialog("Voulez vous jouer en console ou en interface graphique ?	(console / interface)");
		return choix;
	}
	
	/*
	 * Cree un plateau de case par joueur 
	 */
	public void generePlateau() {
		for (int colonne = 0; colonne < 10; colonne++) {
			for (int ligne = 0; ligne < 10; ligne++) {
				plateau[colonne][ligne] = new Case(colonne,ligne);
			}
		}
	}

	/*
	 * Applique l'attaque c au joueur courant. Si un bateau est coulé, ecrit en console "Bateau coulé !"
	 */
	public void estAttaque2(Attaque c) {
		
		
		//Survol chaque bateau du joueur
		for(int i=0 ; i<mesBateaux.size() ; i++) {
			
			//Survol la surface de chacun des bateaux
			for (int j = 0; j < mesBateaux.get(i).getTaille(); j++) {
				
				//Attaque de tout la surface de l'attaque c
				for (int j2 = 0; j2 < c.getSurface().length; j2++) {
					
					plateau[c.getSurface()[j2].getColonne()][c.getSurface()[j2].getLigne()].setTouchee(true); 
					if(mesBateaux.get(i).getSurface()[j].equals(c.getSurface()[j2]) == true) {
						//TOUCHE
						mesBateaux.get(i).getSurface()[j].setTouchee(true);			//on mets le boolean de la case a true
						//plateau[c.getSurface()[j2].getColonne()][c.getSurface()[j2].getLigne()].setTouchee(true);
						plateau[c.getSurface()[j2].getColonne()][c.getSurface()[j2].getLigne()].setOccupe(true);
						if(mesBateaux.get(i).estEnVie() == false){
							System.out.println("Bateau coulé !");
						}
					}
				}
			}
		}
	}
	
	/*
	 * Affiche le plateau du joueur avec les numeros des colonnes et des lignes
	 * les [0] representent une case non decouverte
	 * les [X] representent une case touchée contenant un bateau
	 * les [ ] representent une case touchée mais ne contenant pas de bateau
	 */
	public void affichePlateau() {
		System.out.println("  0  1  2  3  4  5  6  7  8  9");
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
			for (int j = 0; j < 10; j++) {
				if(plateau[i][j].isOccupe() == true) {
					if(plateau[i][j].isTouchee() == true) {
						System.out.print("[X]");
					}
					else {
						System.out.print("[0]");
					}
				}
				else {
					if(plateau[i][j].isTouchee() == true) {
						System.out.print("[ ]");
					}
					else {
						System.out.print("[0]");
					}
				}
			}
			System.out.println("\n");
		}
	}
	
	/*
	 * Instancie l'ArrayList de Bateau du joueur sur base des ses choix de localisations
	 * le nombre de bateaux par joueurs et kleur taille est represente pars le tableau de string "choix"
	 */
	public void placerBateaux() {
		String[] choix = {"3","2"};	//Liste des taille de bateau( fixe pour tous les joueurs)	
		int nbrChoix = choix.length;
		int taille = 0;
		Case current;
		Case[] surfaceNewBateau;
		for (int i = 0; i < nbrChoix; i++) {
			int choixPane = JOptionPane.showOptionDialog(null, "Choisissez une taille", "Placement des Bateaux", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choix, choix[0]);
			
			if(choixPane == 0) {
				taille = Integer.parseInt(choix[0]);
			}
			if(choixPane == 1) {
				taille = Integer.parseInt(choix[1]);
			}
			if(choixPane == 2) {
				taille = Integer.parseInt(choix[2]);
			}
			
			int x = -1;
			int y = -1;
			while((x<0||x>=10)&&(y<0||y>=10)) {
				 x = Integer.parseInt(JOptionPane.showInputDialog("Entrez une colonne valide (0-9)"));
				 y = Integer.parseInt(JOptionPane.showInputDialog("Entrez une ligne valide (0-9)"));
			}
		    current = new Case(x,y);
			surfaceNewBateau = new Case [taille]; 
			surfaceNewBateau[0] = current;
				
			for (int j = 0; j < taille-1; j++) {
				JOptionPane jp = new JOptionPane();
				Case lesCases[]={ new Case(x,y-1), new Case(x+1,y), new Case(x,y+1), new Case(x-1,y)};
				String lesCasesToString[]={ new Case(x,y-1).toString(), new Case(x+1,y).toString(), new Case(x,y+1).toString(), new Case(x-1,y).toString()};
				int retour = jp.showOptionDialog(null, "Choisissez une case", "Placement du Bateaux de taille: " + taille, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, lesCasesToString, lesCasesToString[0]);
				if(retour == 0) {
					surfaceNewBateau[j+1] = lesCases[0];
					y--;
				}
				if(retour == 1) {
					surfaceNewBateau[j+1] = lesCases[1];
					x++;
				}
				if(retour == 2) {
					surfaceNewBateau[j+1] = lesCases[2];
					y++;
				}
				if(retour == 3) {
					surfaceNewBateau[j+1] = lesCases[3];
					x--;
				}
			}
			String nomNewBateau = JOptionPane.showInputDialog("Donnez un nom à votre ouveau bateau !");
			Bateau test = new Bateau(nomNewBateau,surfaceNewBateau);
			this.mesBateaux.add(test);	// ajoute a l'array le bateau créé
			System.out.println(test);	//imprime en console le bateau créé
			String [] choixReduit = new String [choix.length-1];
			for (int j = 0; j < choix.length; j++) {
				if(j == choixPane) {
					for (int j2 = j; j2 < choixReduit.length; j2++) {
						choixReduit[j2] = choix[j+1];
					}
					break;
				}
				else {
					choixReduit[j] = choix[j];
				}
			}
			choix = choixReduit;
		}
	}

	/*
	 * Instancie l'ArrayList de Bateau du joueur sur base des ses choix de localisations
	 * le nombre de bateaux par joueurs et kleur taille est represente pars le tableau de string "choix"
	 */
	public void placerBateauxConsole() {
		
		String[] choix = {"3","2"};	//Liste des taille de bateau( fixe pour tous les joueurs)	
		int taille = 0;
		int tailleChoisie = 0;
		int indexTailleChoisie=0;
		int nbrChoix = choix.length;
		Case current;
		Case[] surfaceNewBateau;
		
	
			for (int i = 0; i < nbrChoix; i++) {
				Scanner sc = new Scanner(System.in);
				while(taille==0) {
					System.out.println("\nVous devez encore placez ces bateaux:");
					for(int k=0; k<choix.length; k++) {
						  System.out.println(choix[k]);
					}
					System.out.println("Choisissez-en un en écrivant sa taille");
					tailleChoisie = sc.nextInt();
					
					for (int j = 0; j < choix.length; j++) {
						if(tailleChoisie == Integer.parseInt(choix[j])) {
							taille = Integer.parseInt(choix[j]);
							indexTailleChoisie = j;
						}
					}
				}
				
				int x = -1;
				int y = -1;
				while(x<0||x>=10||y<0||y>=10) {
					 System.out.println("Entrez une colonne valide (0-9)");
					 x = sc.nextInt();
					 System.out.println("Entrez une ligne valide (0-9)");
					 y = sc.nextInt();
				}
			    current = new Case(x,y,true);
			    plateau[x][y] = new Case(x,y,true);
				surfaceNewBateau = new Case [taille]; 
				surfaceNewBateau[0] = current;
				
				System.out.println("Entrez une orientation ( horizontale - verticale )");
				String orientation = "";
				orientation = sc.next();
				switch(orientation) {
				case "horizontale":
					for (int j = 0; j < taille-1; j++) {
						surfaceNewBateau[j+1] = new Case(x+1,y,true);
						plateau[x+1][y] = new Case(x+1,y,true);
						x++;
					}
				case "verticale":
					for (int j = 0; j < taille-1; j++) {
						surfaceNewBateau[j+1] = new Case(x,y+1,true);
						plateau[x][y+1] = new Case(x,y+1,true);
						y++;
					}
				}
				//System.out.println("Donnez un nom à votre bateau!");
				//String nomNewBateau = sc.next();
				Bateau test = new Bateau("nomNewBateau",surfaceNewBateau);
				this.mesBateaux.add(test);	// ajoute a l'array le bateau créé
				System.out.println(test);	//imprime en console le bateau créé
				String [] choixReduit = new String [choix.length-1];
				for (int j = 0; j < choix.length; j++) {
					if(j == indexTailleChoisie) {
						for (int j2 = j; j2 < choixReduit.length; j2++) {
							choixReduit[j2] = choix[j+1];
						}
						break;
					}
					else {
						choixReduit[j] = choix[j];
					}
				}
				choix = choixReduit;
				taille = 0;
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * ajoute le bateau b au joueur
	 */
	public void ajouteBateau(Bateau b) {
		mesBateaux.add(b);
	}
	
	public int getNbrbateaux() {
		return mesBateaux.size();
	}
}
