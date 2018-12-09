import java.util.ArrayList;
import java.util.Observable;

public class Joueur extends Observable{

	Plateau plateau;
	Plateau plateauOrdi;
	ArrayList<Case> caseDeBateauxJoueur = new ArrayList<Case>();
	ArrayList<Case> caseDeBateauxOrdi = new ArrayList<Case>();
	
	boolean joueurAPlacerBateaux = false;
	boolean ordiAPlacerBateaux = false;
	
	int argent = 10;
	int bateauxAPlacer = 2;
	
	/*
	 * Crée un Joueur qui possède un plateau sur lequelle il placera ses bateaux et un plateauOrdi qui représente le plateau sur lequel il attaque
	 */
	public Joueur() {
		 plateau = new Plateau();
		 plateauOrdi = new Plateau();
	}
	
	/*
	 * @return true : si l'attaque touche un bateau de l'ordi
	 * @return false : si l'attaque ne touche pas de bateau 
	 */
	public boolean ordiEstAttaque(Attaque attaque) {
		setChanged();
		notifyObservers();
		return plateauOrdi.estAttaque(attaque);
	}
	
	/*
	 * @return true : si l'attaque touche un bateau du joueur
	 * @return false : si l'attaque ne touche pas de bateau 
	 */
	public boolean joueurEstAttaque(Attaque attaque) {
		setChanged();
		notifyObservers();
		return plateau.estAttaque(attaque);
	}
	
	
	/*
	 * @return true : le bateau b a été placé sur le plateau du joueur
	 * @retrun false: si le bateau n'a pas été placé car la surface était déja occupée
	 */
	public boolean joueurPlacerBateau(Bateau b) {
		setChanged();
		notifyObservers();
		for (int i = 0; i < b.getLongueur(); i++) {
			for (int j = 0; j < caseDeBateauxJoueur.size(); j++) {
				if(b.getSurface()[i].equals(caseDeBateauxJoueur.get(j))) {
					return false;
				}
			}
		}
		for (int i = 0; i < b.getLongueur(); i++) {
			plateau.getPlateau()[b.getSurface()[i].getLigne()][b.getSurface()[i].getColonne()].setOccupee();
			caseDeBateauxJoueur.add(b.getSurface()[i]);
		}
		return true;
	}

	/*
	 * @return true : le bateau b a été placé sur le plateau de l'ordi
	 * @retrun false: si le bateau n'a pas été placé car la surface était déja occupée
	 */
	public boolean ordiPlacerBateau(Bateau b) {
		setChanged();
		notifyObservers();
		for (int i = 0; i < b.getLongueur(); i++) {
			for (int j = 0; j < caseDeBateauxOrdi.size(); j++) {
				if(b.getSurface()[i].equals(caseDeBateauxOrdi.get(j))) {
					return false;
				}
			}
		}
		for (int i = 0; i < b.getLongueur(); i++) {
			plateauOrdi.getPlateau()[b.getSurface()[i].getLigne()][b.getSurface()[i].getColonne()].setOccupee();
			caseDeBateauxOrdi.add(b.getSurface()[i]);
		}
		return true;
	}
	
	/*
	 * @return true: le joueur a déja placé ses bateaux 
	 * @return false: le joueur n'a pas encore placé ses bateaux
	 */
	public boolean joueurAPlacerBateaux() {
		return joueurAPlacerBateaux;
	}
	
	/*
	 * @return true: le joueur a déja placé ses bateaux 
	 * @return false: le joueur n'a pas encore placé ses bateaux
	 */
	public boolean odriAPlacerBateaux() {
		return ordiAPlacerBateaux;
	}
	//GETTERS 
	public Plateau getPlateau() {
		return plateau;
	}
	
	public Plateau getPlateauOrdi() {
		return plateauOrdi;
	}
	
	public int getArgent() {
		return argent;
	}
	
	public void setArgent(int a) {
		this.argent = a;
	}
	
	public int getbateauAPlacer() {
		return bateauxAPlacer;
	}
	
	public void bateauAPlacerMoins1 () {
		this.bateauxAPlacer = bateauxAPlacer-1;
	}
	
	public boolean ordiAGagne() {
		boolean boo= true;
		for (int i = 0; i < 10; i++) {
			for(int j = 0 ; j<10 ; j++) {
				if (plateau.getPlateau()[i][j].estOccupee() && !plateau.getPlateau()[i][j].estTouchee()) {
					boo = false;
				}
			}
		}
		return boo;
	}
	
	public boolean joueurAGagne() {
		boolean boo= true;
		for (int i = 0; i < 10; i++) {
			for(int j = 0 ; j<10 ; j++) {
				if (plateauOrdi.getPlateau()[i][j].estOccupee() && !plateauOrdi.getPlateau()[i][j].estTouchee()) {
					boo = false;
				}
			}
		}
		return boo;
	}
}
