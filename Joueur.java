import java.util.ArrayList;

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
	 */
	public Joueur(String nom, ArrayList<Bateau> mesBateaux) {
		this.nom = nom;
		this.mesBateaux = mesBateaux;
	}
	
	/*
	 * Genere un plateau par joueur
	 */
	public void generePlateau() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				plateau[j][i] = new Case(j,i);
				//System.out.print("[0]");
			}
			//System.out.println("\n");
		}
	}

	/*
	 * 
	 */
	public void estAttaque2(Attaque c) {
		for(int i=0 ; i<mesBateaux.size() ; i++) {
			for (int j = 0; j < mesBateaux.get(i).getTaille(); j++) {
				for (int j2 = 0; j2 < c.getSurface().length; j2++) {
					if(mesBateaux.get(i).getSurface()[j].equals(c.getSurface()[j2]) == true) {
						//TOUCHE
						mesBateaux.get(i).getSurface()[j].setTouchee(true);
						plateau[c.getSurface()[j2].getColonne()][c.getSurface()[j2].getLigne()].setTouchee(true);
						if(mesBateaux.get(i).estEnVie() == false){
							System.out.println("Bateau coulé !");
						}
					}
				}
			}
		}
	}
	
	/*
	 * 
	 */
	public void affichePlateau() {
		System.out.println("  0  1  2  3  4  5  6  7  8  9");
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
			for (int j = 0; j < 10; j++) {
				if(plateau[j][i].isTouchee() == true) {
					System.out.print("[X]");
				}
				else {
					System.out.print("[0]");
				}
			}
			System.out.println("\n");
		}
	}
	
	/*
	 * @return un ArrayList de Bateau composé des bateaux de taille imposée dans le tableau choix et de localisation choisie par le joueur
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
	 * ajoute le bateau b au joueur
	 */
	public void ajouteBateau(Bateau b) {
		mesBateaux.add(b);
	}
	
	public int getNbrbateaux() {
		return mesBateaux.size();
	}
}
