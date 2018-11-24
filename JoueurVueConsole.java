import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
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
		//affiche("Pour emprunter : E + numéro de livre.");
		//affiche("Pour rendre : R + numéro de livre.");
	}
	
	private class ReadInput implements Runnable{
		public void run() {
			while(true){
				try{
					String c = sc.next();
					if(c.length()!=1){
						affiche("Format d'input incorrect");
						printHelp();
					}
						
					int i = sc.nextInt();
					if(i<0 || i> 9){
						affiche("Numéro du livre incorrect");
						printHelp(); 
					}
					switch(c){
						case "R" :
							controller.estAttaque(new Attaque(i,i));
							break;
						case "E" : 
							//controller.emprunteLivre(i);
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