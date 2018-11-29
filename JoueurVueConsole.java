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
		printPlateau();
	}
	
	/*
	 * Affiche en console le plateau du joueur( en premier) et le plateau de l'ordi ( en second)
	 * Affiche [0] si cette case n'est pas attaqu�e
	 * Affiche [X] si cette case est attaqu�e et contient un bateau
	 * Affiche [ ] si cette case est attaqu�e mais ne contient pas de bateau 
	 */
	public void printPlateau() {
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
						System.out.print("[0]");
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
	 * Affiche en console les r�gles
	 */
	private void printHelp(){
		affiche("Pour faire une attaque simple (1 case) : A + num�ro de la ligne a attaqu�e + num�ro de la colonne a attaqu�e.");
		affiche("Pour faire une attaque horizontale (3 cases) : AH + num�ro de la ligne du centre a attaqu�e + num�ro de la colonne du centre a attaqu�e.");
	}
	
	/*
	 * Gere tous ce qui est entr� comme input en console:
	 * 		-A = attaque
	 * 		-AH = attaque horizontale
	 * 		-P = placer bateau
	 * 		-fin = fin du tour (simule attaque de l'ordi sur notre plateau)
	 */
	private class ReadInput implements Runnable{
		public void run() {
			
			int compteur = 1;
			while(controller.aPlacerBateaux() == false  && compteur != 0) {
				try {
					affiche("\nEncore " + compteur + " bateaux a placer !" );
					affiche("Pour placer un bateau : P + num�ro de la ligne + num�ro de la colonne + taille du bateau + orientation (H - V).");
					String a = sc.next();
					switch(a) {
						case "P":
							int i = sc.nextInt();
							if(i<0 || i> 9){
								affiche("Num�ro de ligne incorrect");
							}
							int j = sc.nextInt();
							if(j<0 || j> 9){
								affiche("Num�ro de colonne incorrect");
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
							printPlateau();
							printHelp();
							}
							else {
								affiche("\nEncore " + compteur + " bateaux a placer !" );
								printHelp();
							}
							break;
						default : 
							affiche("Op�ration incorrecte");
							printHelp();
					}
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
					printHelp();
				}
			}
			
			controller.ordiPlacerBateau(new Bateau(aleatoire(0, 6),aleatoire(0, 6),aleatoire(2, 5),"H"));
			
			while(true){
				try{
					String c = sc.next();
						if(c.equals("fin")) {
							Random n = new Random();
							if(aleatoire(0, 2) ==1) {
								controller.joueurEstAttaque(new Attaque(aleatoire(0, 10),aleatoire(0, 10)));
							}
							else {
								controller.joueurEstAttaque(new AttaqueHorizontale(aleatoire(0, 10),aleatoire(1, 9)));
							}
							printPlateau();
						}
					int i = sc.nextInt();
					if(i<0 || i> 9){
						affiche("Num�ro de ligne incorrect");
					}
					
					int j = sc.nextInt();
					if(j<0 || j> 9){
						affiche("Num�ro de colonne incorrect");
					}
					
					switch(c){
						case "A" :
							controller.ordiEstAttaque(new Attaque(i,j));
							printPlateau();
							break;
						case "AH" :
							controller.ordiEstAttaque(new AttaqueHorizontale(i,j));
							printPlateau();
							break;
						case "Fin" :
							//controller.joueurEstAttaque(new Attaque((int)Math.random()*10,(int)Math.random()*10));
							printPlateau();
						default : 
							affiche("Op�ration incorrecte");
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