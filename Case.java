
public class Case {
	
	int colonne; //colonne de la case ( 0 - 20 )
	int ligne; // ligne de la case ( 0 - 20 )
	boolean touchee;
	boolean occupe;
	
	/*
	 * Construit une case de colonne x et ligne y
	 */
	public Case(int colonne, int ligne, boolean occupe) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.touchee = false;
		this.occupe = occupe;
	}
	
	/*
	 * Construit une case de colonne x et ligne y
	 */
	public Case(int colonne, int ligne) {
		this.colonne = colonne;
		this.ligne = ligne;
		this.touchee = false;
		this.occupe = false;
	}
	
	public String toString() {
		return "[" + colonne + "][" + ligne +"]; ";
	}
	
	
	public boolean equals(Case c) {
		if (this.getColonne() == c.getColonne() && this.getLigne() == c.getLigne()){
			return true;
		}
		else {
			return false;
		}
		
	}
	
	//GETTERS AND SETTERS
	
	
	public boolean isTouchee() {
		return touchee;
	}

	public boolean isOccupe() {
		return occupe;
	}

	public void setOccupe(boolean occupe) {
		this.occupe = occupe;
	}

	public void setTouchee(boolean touchee) {
		this.touchee = touchee;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	
}
