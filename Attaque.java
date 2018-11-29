
public class Attaque {

	int ligne;
	int colonne;
	Case[] surface;
	
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

	public Case[] getSurface() {
		surface = new Case[1];
		surface[0] = new Case(this.ligne,this.colonne);
		return surface;
	}
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	public String toString() {
		return "[" + getLigne() + getColonne() + "]" ;
	}
}
