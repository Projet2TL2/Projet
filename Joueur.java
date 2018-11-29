import java.util.ArrayList;
import java.util.Observable;

public class Joueur extends Observable{

	Plateau plateau;
	Plateau plateauOrdi;
	ArrayList<Case> caseDeBateauxJoueur = new ArrayList<Case>();
	ArrayList<Case> caseDeBateauxOrdi = new ArrayList<Case>();
	boolean aPlacerBateaux = false;
	
	/*
	 * Cr�e un Joueur qui poss�de un plateau sur lequelle il placera ses bateaux et un plateauOrdi qui repr�sente le plateau sur lequel il attaque
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
	 * @return true : le bateau b a �t� plac� sur le plateau du joueur
	 * @retrun false: si le bateau n'a pas �t� plac� car la surface �tait d�ja occup�e
	 */
	public boolean joueurPlacerBateau(Bateau b) {
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
	 * @return true : le bateau b a �t� plac� sur le plateau de l'ordi
	 * @retrun false: si le bateau n'a pas �t� plac� car la surface �tait d�ja occup�e
	 */
	public boolean ordiPlacerBateau(Bateau b) {
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
	 * @return true: le joueur a d�ja plac� ses bateaux 
	 * @return false: le joueur n'a pas encore plac� ses bateaux
	 */
	public boolean aPlacerBateaux() {
		return aPlacerBateaux;
	}
	
	//GETTERS 
	public Plateau getPlateau() {
		return plateau;
	}
	
	public Plateau getPlateauOrdi() {
		return plateauOrdi;
	}
}
