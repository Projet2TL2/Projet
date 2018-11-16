import java.util.ArrayList;

public class Joueur {

	String nom;
	ArrayList<Bateau> mesBateaux = new ArrayList<Bateau>();
	//Faction maFaction;
	Case[][] plateau = new Case [10][10];
	
	/*
	 * Cree un joueur avec un nom
	 * @param : nom represente son nom
	 */
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	/*
	 * Genere un plateau par joueur
	 */
	public void generePlateau() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				plateau[i][j] = new Case(i,j);
				//System.out.print("[0]");
			}
			//System.out.println("\n");
		}
	}
	
	
	/*
	 * 
	 */
	public void estAttque(Case c) {
		for(int i=0 ; i<mesBateaux.size() ; i++) {
			for (int j = 0; j < mesBateaux.get(i).getTaille(); j++) {
				if(mesBateaux.get(i).getSurface()[j].equals(c) == true) {
				System.out.println("TOUCHE"); 
					mesBateaux.get(i).getSurface()[j].setTouchee(true);
					plateau[i][j].setTouchee(true);
					break;
				}
				else {
					//PAS TOUCHE
					System.out.println("PAS TOUCHE"); 
				}
			}
		}
	}
	
	/*
	 * 
	 */
	public void affichePlateau() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(plateau[i][j].isTouchee() == true) {
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
	 * ajoute le bateau b au joueur
	 */
	public void ajouteBateau(Bateau b) {
		mesBateaux.add(b);
	}
	
	public int getNbrbateaux() {
		return mesBateaux.size();
	}
}
