
public class Attaque {

	int ligne;
	int colonne;
	
	public Attaque(int ligne, int colonne) { 
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	public String toString() {
		return "[" + getLigne() + getColonne() + "]" ;
	}
}
