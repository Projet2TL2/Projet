import java.util.Observable;

public class Joueur extends Observable{

	Case[][] plateau;
	
	public Joueur() {
		plateau = new Case[10][10];
		for(int ligne=0; ligne<10; ligne++){
			for (int colonne = 0; colonne < 10; colonne++) {
				plateau[ligne][colonne] = new Case(ligne,colonne);
			}
			plateau[ligne][ligne].setOccupee(); 
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

	public Case[][] getPlateau() {
		return plateau;
	}
	
}
