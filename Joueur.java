import java.util.ArrayList;
import java.util.Observable;

public class Joueur extends Observable{

	Plateau plateau;
	Plateau plateauOrdi;
	ArrayList<Case> caseDeBateauxJoueur = new ArrayList<Case>();
	ArrayList<Case> caseDeBateauxOrdi = new ArrayList<Case>();
	boolean aPlacerBateaux = false;
	
	public Joueur() {
		 plateau = new Plateau();
		 plateauOrdi = new Plateau();
	}
	
	/*
	 * @return true : si l'attaque touche un bateau du joueur
	 * @return false : si l'attaque ne touche pas de bateau du joueur
	 */
	public boolean ordiEstAttaque(Attaque attaque) {
		setChanged();
		notifyObservers();
		return plateauOrdi.estAttaque(attaque);
	}
	
	/*
	 * @return true : si l'attaque touche un bateau du joueur
	 * @return false : si l'attaque ne touche pas de bateau du joueur
	 */
	public boolean joueurEstAttaque(Attaque attaque) {
		setChanged();
		notifyObservers();
		return plateau.estAttaque(attaque);
	}
	
	
	
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
	
	
	
	public boolean aPlacerBateaux() {
		return aPlacerBateaux;
	}
	
	public Plateau getPlateau() {
		return plateau;
	}
	
	public Plateau getPlateauOrdi() {
		return plateauOrdi;
	}
}
