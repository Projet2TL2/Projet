import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;


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
		//printPlateau();
	}
	
	/*
	 * Affiche en console le plateau du joueur( en premier) et le plateau de l'ordi ( en second)
	 * Affiche [0] si cette case n'est pas attaquée
	 * Affiche [X] si cette case est attaquée et contient un bateau
	 * Affiche [ ] si cette case est attaquée mais ne contient pas de bateau 
	 */
	public void printPlateau() {
		System.out.print("[0] = Case non découverte\n[Y] = Bateau non découvert\n[X] = Bateau touché\n[ ] = Case touchée mais vide\n");
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
		affiche("Il vous reste " + model.getArgent() + " pièces");
		affiche("3 piéces Pour faire une attaque simple (1 case) : A + numéro de la ligne a attaquée + numéro de la colonne a attaquée.");
		affiche("5 piéces Pour faire une attaque horizontale (3 cases) : AH + numéro de la ligne du centre a attaquée + numéro de la colonne du centre a attaquée.");
		affiche("5 piéces Pour faire une attaque verticale (3 cases) : AV + numéro de la ligne du centre a attaquée + numéro de la colonne du centre a attaquée.");
		affiche("0 piécesPour finir le tour et faire le tour de l'ordi : fin");
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
			
			int compteur = 2;
			while(controller.aPlacerBateaux() == false  && compteur != 0) {
				try {
					affiche("\nEncore " + compteur + " bateaux a placer !" );
					affiche("Pour placer un bateau : P + numéro de la ligne + numéro de la colonne + taille du bateau + orientation (H - V).");
					String a = sc.next();
					switch(a) {
						case "P":
							int i = sc.nextInt();
							if(i<0 || i> 9){
								affiche("Numéro de ligne incorrect");
							}
							int j = sc.nextInt();
							if(j<0 || j> 9){
								affiche("Numéro de colonne incorrect");
							}
							int taille = sc.nextInt();
							if(taille<2 || taille> 5){
								affiche("Taille incorrect");
							}
							String orientation = sc.next();
							if(orientation.length()!=1 && (!orientation.equals("V") || !orientation.equals("H"))){
								affiche("Format d'input incorrect");
								printHelp();
							}
							controller.joueurPlacerBateau(new Bateau(i,j,taille,orientation));
							compteur --;
							if(compteur == 0) {
							controller.setAPlacerBateaux(true);
							}
							break;
						default : 
							affiche("Opération incorrecte");
							printHelp();
					}
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
					printHelp();
				}
			}
			
			System.out.println("L'ordi a placé ses bateaux !! :");
			controller.ordiPlacerBateau(new Bateau(aleatoire(0, 6),aleatoire(0, 6),aleatoire(2, 5),"H"));
			controller.ordiPlacerBateau(new Bateau(aleatoire(4, 9),aleatoire(4, 6),aleatoire(2, 4),"V"));
			printHelp();
			
			while(true){
				try{
					String c = sc.next();
						if(c.equals("fin")) {
							int nbrAleatoire = aleatoire(0, 3);
							if(nbrAleatoire == 0) {
								controller.joueurEstAttaque(new Attaque(aleatoire(0, 10),aleatoire(0, 10)));
								printPlateau();
								model.setArgent(10);
							}
							else{
								if(nbrAleatoire == 1) {
									controller.joueurEstAttaque(new AttaqueHorizontale(aleatoire(0, 10),aleatoire(1, 9)));
								}
								else{
									controller.joueurEstAttaque(new AttaqueVerticale(aleatoire(1, 9),aleatoire(0, 10)));
								}
							}
							printPlateau();
						}
						while(model.getArgent()>0){
							int i = sc.nextInt();
							if(i<0 || i> 9){
								affiche("Numéro de ligne incorrect");
							}
							
							int j = sc.nextInt();
							if(j<0 || j> 9){
								affiche("Numéro de colonne incorrect");
							}
							
							switch(c){
								case "A" :
									controller.ordiEstAttaque(new Attaque(i,j));
									model.setArgent(model.getArgent()-3);
									break;
								case "AH" :
									controller.ordiEstAttaque(new AttaqueHorizontale(i,j));
									model.setArgent(model.getArgent()-5);
									break;
								case "AV" :
									controller.ordiEstAttaque(new AttaqueVerticale(i,j));
									model.setArgent(model.getArgent()-5);
									break;
								default : 
									affiche("Opération incorrecte");
							} 
							printPlateau();
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