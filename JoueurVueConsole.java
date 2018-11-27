import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;


public class JoueurVueConsole extends JoueurVue implements Observer {
	protected Scanner sc;
	private boolean aPlacerBateaux = false;
	
	public JoueurVueConsole(Joueur model, JoueurControl controller) {
		super(model, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();	
	}

	@Override
	public void update(Observable o, Object arg) {
		printHelp();
		printPlateau();
	}
	
	public void printPlateau() {
		System.out.println("   0  1  2  3  4  5  6  7  8  9");
		for(int ligne = 0; ligne<10; ligne++){
			System.out.print(ligne + " ");
			for (int colonne = 0; colonne < 10; colonne++) {
				System.out.print(model.getPlateau()[ligne][colonne].estTouchee()?"[x]":"[0]");
			}
			System.out.println("\n");
		}
	}

	private void printHelp(){
		affiche("Pour attaquer : A + numéro de la ligne a attaquée + numéro de la colonne a attaquée.");
		affiche("Pour placer un bateau : P + numéro de la ligne a attaquée + numéro de la colonne a attaquée + taille du bateau + orientation (H - V).");
	}
	
	private class ReadInput implements Runnable{
		public void run() {
			
			while(aPlacerBateaux == false) {
				try {
					String a = sc.next();
					if(a.length()!=1){
						affiche("Format d'input incorrect");
						printHelp();
					}
					switch(a) {
						case "P":
							int i = sc.nextInt();
							if(i<0 || i> 9){
								affiche("Numéro de ligne incorrect");
								printHelp(); 
							}
							int j = sc.nextInt();
							if(j<0 || j> 9){
								affiche("Numéro de colonne incorrect");
								printHelp(); 
							}
							int taille = sc.nextInt();
							if(taille<0 || taille> 9){
								affiche("Taille incorrect");
								printHelp(); 
							}
							String orientation = sc.next();
							if(orientation.length()!=1){
								affiche("Format d'input incorrect");
								printHelp();
							}
							controller.placerBateau(new Bateau(i,j,taille,orientation));
							aPlacerBateaux = true;
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
			
			
			while(true){
				try{
					String c = sc.next();
					if(c.length()!=1){
						affiche("Format d'input incorrect");
						printHelp();
					}
						
					int i = sc.nextInt();
					if(i<0 || i> 9){
						affiche("Numéro de ligne incorrect");
						printHelp(); 
					}
					
					int j = sc.nextInt();
					if(j<0 || j> 9){
						affiche("Numéro de colonne incorrect");
						printHelp(); 
					}
					
					switch(c){
						case "A" :
							controller.estAttaque(new Attaque(i,j));
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
		}
	}
	
	@Override
	public void affiche(String string) {
		System.out.println(string);
		
	}
}