package Vue;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Controleur.JoueurControl;
import Model.Attaque;
import Model.AttaqueHorizontale;
import Model.AttaqueVerticale;
import Model.Bateau;
import Model.Joueur;


public class JoueurVueConsole extends JoueurVue implements Observer {
	protected Scanner sc;
	
	
	public JoueurVueConsole(Joueur model, JoueurControl controller) {
		super(model, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();	
	}

	@Override
	public void update(Observable o, Object arg) {
		printPlateau();
	}
	
	/*
	 * Affiche en console le plateau du joueur( en premier) et le plateau de l'ordi ( en second)
	 * Affiche [0] si cette case n'est pas attaquée
	 * Affiche [X] si cette case est attaquée et contient un bateau
	 * Affiche [ ] si cette case est attaquée mais ne contient pas de bateau 
	 */
	public void printPlateau() {
		System.out.print("[0] = Case non decouverte\n[Y] = Bateau non decouvert\n[X] = Bateau touche\n[ ] = Case touchee mais vide\n");
		System.out.println("Votre plateau: ");
		System.out.println("   0  1  2  3  4  5  6  7  8  9");
		for(int ligne = 0; ligne<10; ligne++){
			System.out.print(ligne + " ");
			for (int colonne = 0; colonne < 10; colonne++) {
				if(model.getPlateau().getPlateau()[ligne][colonne].estOccupee()) {
					if(model.getPlateau().getPlateau()[ligne][colonne].estTouchee()){
						System.out.print("[X]");
					}
					else{
						System.out.print("[Y]");
					}
				}
				else {
					if(model.getPlateau().getPlateau()[ligne][colonne].estTouchee()){
						System.out.print("[ ]");
					}
					else{
						System.out.print("[0]");
					}
				}
			}
			System.out.print("\n");
		}
		
		System.out.println("\nPlateau de l'ordi: ");
		System.out.println("   0  1  2  3  4  5  6  7  8  9");
		for(int ligne = 0; ligne<10; ligne++){
			System.out.print(ligne + " ");
			for (int colonne = 0; colonne < 10; colonne++) {
				if(model.getPlateauOrdi().getPlateau()[ligne][colonne].estOccupee()) {
					if(model.getPlateauOrdi().getPlateau()[ligne][colonne].estTouchee()){
						System.out.print("[X]");
					}
					else{
						System.out.print("[0]");
					}
				}
				else {
					if(model.getPlateauOrdi().getPlateau()[ligne][colonne].estTouchee()){
						System.out.print("[ ]");
					}
					else{
						System.out.print("[0]");
					}
				}
			}
			System.out.print("\n");
		}
		System.out.println("\n");
		
		
	}

	/*
	 * Affiche en console les règles
	 */
	private void printHelp(){
		affiche("Il vous reste " + this.controller.getArgent() + " pieces");
		affiche("3 pieces Pour faire une attaque simple (1 case) : A + numero de la ligne a attaquee + numero de la colonne a attaquee.");
		affiche("5 pieces Pour faire une attaque horizontale (3 cases) : AH + numero de la ligne du centre a attaquee + numero de la colonne du centre a attaquee.");
		affiche("5 pieces Pour faire une attaque verticale (3 cases) : AV + numero de la ligne du centre a attaquee + numero de la colonne du centre a attaquee.");
		affiche("0 pieces Pour finir le tour et faire le tour de l'ordi : fin");
	}
	
	/*
	 * Gere tous ce qui est entré comme input en console:
	 * 		-A = attaque
	 * 		-AH = attaque horizontale
	 * 		-AV = attaque verticale
	 * 		-P = placer bateau
	 * 		-fin = fin du tour (simule attaque de l'ordi sur notre plateau)
	 */
	private class ReadInput implements Runnable{
		public void run() {
			
			int compteur = model.getbateauAPlacer();
			while(controller.joueurAPlacerBateaux() == false  && compteur != 0) {
				try {
					if(controller.joueurAPlacerBateaux() == false) {
						affiche("\nEncore " + controller.getBateauxAPlacer() + " bateaux a placer !" );
						affiche("Pour placer un bateau : P + numero de la ligne + numero de la colonne + taille du bateau + orientation (H - V).");
					}
					String a = sc.next();
					switch(a) {
						case "P":
							int i = sc.nextInt();
							if(i<0 || i> 9){
								affiche("Numero de ligne incorrect");
								break;
							}
							int j = sc.nextInt();
							if(j<0 || j> 9){
								affiche("Numero de colonne incorrect");
								break;
							}
							int taille = sc.nextInt();
							if(taille<2 || taille> 5){
								affiche("Taille incorrect");
								break;
							}
							String orientation = sc.next();
							if(orientation.length()!=1 && (!orientation.equals("V") || !orientation.equals("H") || !orientation.equals("v") || !orientation.equals("h"))){
								affiche("Format d'input incorrect");
								printHelp();
							}
							if(orientation.equals("H")) {
						        	if(taille + j > 10) {
						        		affiche("Impossible de placer ce bateau !");
						        		break;
						        	}
						    }
							if(orientation.equals("V")) {
					        	if(taille + i > 10) {
					        		affiche("Impossible de placer ce bateau !");
					        		break;
					        	}
					    }
							controller.joueurPlacerBateau(new Bateau(i,j,taille,orientation));
							compteur --;
							model.bateauAPlacerMoins1();
							if(compteur == 0) {
								controller.setJoueurAPlacerBateaux(true);
							}
							update(null,null);
							break;
						default : 
							affiche("Operation incorrecte");
							printHelp();
							break;
					}
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
					printHelp();
				}
			}
			
			if(controller.ordiAPlacerBateaux()) {
				System.out.println("L'ordi a place ses bateaux !! :");
			}
			else {
				controller.ordiPlacerBateau(new Bateau(aleatoire(0, 6),aleatoire(0, 6),aleatoire(2, 5),"H"));
				controller.ordiPlacerBateau(new Bateau(aleatoire(4, 9),aleatoire(4, 6),aleatoire(2, 4),"V"));
			}
			//update(null,null);
			printHelp();
			
			while(true){
				try{
					String c = sc.next();
						if(c.equals("fin")) {
							int nbrAleatoire = aleatoire(0, 3);
							if(nbrAleatoire == 0) {
								controller.joueurEstAttaque(new Attaque(aleatoire(0, 10),aleatoire(0, 10)));
								printPlateau();
								controller.setArgent(10);
							}
							else{
								if(nbrAleatoire == 1) {
									controller.joueurEstAttaque(new AttaqueHorizontale(aleatoire(0, 10),aleatoire(1, 9)));
								}
								else{
									controller.joueurEstAttaque(new AttaqueVerticale(aleatoire(1, 9),aleatoire(0, 10)));
								}
							}
							update(null,null);
						}
						while(model.getArgent()>0){
							int i = sc.nextInt();
							if(i<0 || i> 9){
								affiche("Numero de ligne incorrect");
							}
							
							int j = sc.nextInt();
							if(j<0 || j> 9){
								affiche("Numero de colonne incorrect");
							}
							
							switch(c){
								case "A" :
									controller.ordiEstAttaque(new Attaque(i,j));
									controller.setArgent(controller.getArgent()-3);
									update(null,null);
									break;
								case "AH" :
									controller.ordiEstAttaque(new AttaqueHorizontale(i,j));
									controller.setArgent(controller.getArgent()-5);
									update(null,null);
									break;
								case "AV" :
									controller.ordiEstAttaque(new AttaqueVerticale(i,j));
									controller.setArgent(controller.getArgent()-5);
									update(null,null);
									break;
								default : 
							} 
							update(null,null);
							printHelp();
						}
					}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
					printHelp();
				}
			}
		}
	}
	
	@Override
	public void affiche(String string) {
		System.out.println(string);
		
	}
	
	public int aleatoire(int min, int max) {
		Random r = new Random();
		int valeur = min + r.nextInt(max - min);
		return valeur;
	}
}
