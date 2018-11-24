
public class Case {

	int ligne;
	int colonne;
	boolean estOccupee = false;
	boolean estTouchee = false;
	
	public Case(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

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
		estOccupee = true;
	}
	
	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}
	
	public String toString() {
		return this.getLigne() + "" + this.getColonne();
	}

}
