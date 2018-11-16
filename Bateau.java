
public class Bateau {

	String nom;
	Case[] surface;
	Case centre;
	
	/*
	 * Construit un Bateau avec un nom, un tableau de Case qui représente sa surface dans la grille et son centre
	 * @param : nom represente le nom du bateau
	 * @param : surface represente la surface de case que prends le bateau dans la grille
	 * @param : centre represente le centre de la surface ( si le nombre de case est pair, 
	 * le centre est la moitie de la surface) (exemple: bateau de 4, centre est la seconde case)
	 */
	public Bateau(String nom, Case[] surface) {
		this.nom = nom;
		this.surface = surface;
		setCentre();	//Calcul le centre
	}

	/*
	 * @return true si le bateau a encore au moins une case non touchée, false sinon
	 */
	public boolean estEnVie() {
		for(int i=0 ; i<surface.length ; i++) {
			if(! surface[i].isTouchee()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean estTouchee() {
		for(int i=0 ; i<surface.length ; i++) {
			if(surface[i].isTouchee()) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String result =  "nom: " + nom +"\nsurface: ";
		for(int i=0 ; i<surface.length ; i++) {
			result += surface[i].toString();
		}
		result +="\ncentre: " +  centre.toString();
		return result;
	}
	
	/*
	 * Set le centre a la bonne valeure en fonction de la surface
	 */
	public void setCentre() {
		if(surface.length % 2 == 0) {
			this.centre = surface[surface.length / 2 - 1];
		}
		else {
			this.centre = surface[surface.length / 2 ];
		}
	}
	
	public int getTaille() {
		return surface.length;
	}
	
	//GETTERS AND SETTERS
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Case[] getSurface() {
		return surface;
	}

	public void setSurface(Case[] surface) {
		this.surface = surface;
	}

	public Case getCentre() {
		return centre;
	}

	
	
	
}
