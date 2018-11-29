
public class Attaque {

	int ligne;
	int colonne;
	Case[] surface;
	
	/*
	 * Construit une attaque compos�e d'une ligne et d'une colonne ainsi que d'une surface repr�sentant un tableau de case 
	 * @param : ligne la ligne de la case
	 * @param : colonne la colonne de la case
	 */
	public Attaque(int ligne, int colonne) { 
		this.ligne = ligne;
		this.colonne = colonne;
	}

	/*
	 * renvoi la ligne et la colonne de l'attaque entre []
	 */
	public String toString() {
		return "[" + getLigne() + getColonne() + "]" ;
	}
	
	//GETTERS AND SETTERS
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
}
