import java.util.ArrayList;
import java.util.Observable;

public class Joueur extends Observable{

	Case[][] plateau;
	ArrayList<Case> caseDeBateaux = new ArrayList<Case>();
	
	public Joueur() {
		plateau = new Case[10][10];
		for(int ligne=0; ligne<10; ligne++){
			for (int colonne = 0; colonne < 10; colonne++) {
				plateau[ligne][colonne] = new Case(ligne,colonne);
			}
		}
	}
	
	/*
	 * @return true : si l'attaque touche un bateau du joueur
	 * @return false : si l'attaque ne touche pas de bateau du joueur
	 */
	public boolean estAttaque(Attaque attaque) {
		if(plateau[attaque.getLigne()][attaque.getColonne()].estOccupee) {
			this.plateau[attaque.getLigne()][attaque.getColonne()].setTouchee();
			setChanged();
			notifyObservers();
			
			return true;
		}
		return false;
	}
	
	public boolean placerBateau(Bateau b) {
		for (int i = 0; i < b.getLongueur(); i++) {
			for (int j = 0; j < caseDeBateaux.size(); j++) {
				if(b.getSurface()[i].equals(caseDeBateaux.get(j))) {
					return false;
				}
			}
		}
		for (int i = 0; i < b.getLongueur(); i++) {
			plateau[b.getSurface()[i].getLigne()][b.getSurface()[i].getColonne()].setOccupee();
			caseDeBateaux.add(b.getSurface()[i]);
		}
		return true;
	}

	public Case[][] getPlateau() {
		return plateau;
	}
	
}
