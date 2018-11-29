
public class Plateau {

	Case[][] plateau;
	
	public Plateau() {
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
		boolean boo = false;
		for (int i = 0; i < attaque.getSurface().length; i++) {
			if(plateau[attaque.getSurface()[i].getLigne()][attaque.getSurface()[i].getColonne()].estOccupee) {
				boo = true;
			}
			this.plateau[attaque.getSurface()[i].getLigne()][attaque.getSurface()[i].getColonne()].setTouchee();
		}
		return boo;
	}
	
	public Case[][] getPlateau() {
		return plateau;
	}
}
