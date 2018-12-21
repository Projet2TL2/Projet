package Model;

public class Case {

	int ligne;
	int colonne;
	boolean estOccupee = false;
	boolean estTouchee = false;
	
	/*
	 * Construit une case de ligne ligne, colonne colonne. EstOccupe et EstTouche sont initialisé a false
	 * @param : ligne la ligne de la case
	 * @param : colonne la colonne de la case
	 */
	public Case(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	/*
	 * return la ligne et la colonne sous forme de String tout attaché
	 */
	public String toString() {
		return this.getLigne() + "" + this.getColonne();
	}
	
	//GETTES AND SETTERS
	public boolean estOccupee() {
		return estOccupee;
	}
	
	public void setOccupee() {
		estOccupee = true;
	}
	
	public boolean estTouchee() {
		return estTouchee;
	}
	
	public void setTouchee() {
		estTouchee= true;
	}
	
	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

}
