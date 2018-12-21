package Model;

public class Bateau {

	int ligneDebut;
	int colonneDebut;
	int longueur;
	String orientation;
	
	public Bateau(int ligneDebut, int colonneDebut, int longueur, String orientation) {
		this.ligneDebut = ligneDebut;
		this.colonneDebut = colonneDebut;
		this.longueur = longueur;
		this.orientation = orientation;
	}

	public Case[] getSurface() {
		Case[] surface = new Case [longueur];
		surface[0] = new Case(ligneDebut,colonneDebut);
		if(this.orientation.equals("H")) {
			for (int i = 1; i < longueur; i++) {
				surface[i] = new Case(ligneDebut,colonneDebut+i);
			}
		}
		if(this.orientation.equals("V")) {
			for (int i = 1; i < longueur; i++) {
				surface[i] = new Case(ligneDebut+i,colonneDebut);
			}
		}
		return surface;
		}

	
	public String toString() {
		return "[" + ligneDebut + "][" + colonneDebut + "] " + orientation;
	}
	
	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public int getLigneDebut() {
		return ligneDebut;
	}

	public void setLigneDebut(int ligneDebut) {
		this.ligneDebut = ligneDebut;
	}

	public int getColonneDebut() {
		return colonneDebut;
	}

	public void setColonneDebut(int colonneDebut) {
		this.colonneDebut = colonneDebut;
	}
	
	
}
