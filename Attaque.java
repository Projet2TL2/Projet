
public class Attaque {

	String nom;
	Case[] surface;
	int cout;
	Case centre;
	
	/*
	 * Crée une Attaque 
	 * @param : nom represente le nom de l'attaque
	 * @param : surface represente la surface de l'attaque 
	 * @param : cout represente son cout 
	 * @param : centre represente le centre de l'attaque
	 */
	public Attaque(String nom, Case[] surface, int cout, Case centre) {
		this.nom = nom;
		this.surface = surface;
		this.cout = cout;
		this.centre = centre;
	}
	
	/*
	 * Crée une Attaque 
	 * @param : nom represente le nom de l'attaque
	 * @param : cout represente son cout 
	 * @param : centre represente le centre de l'attaque
	 */
	public Attaque(String nom, int cout, Case centre) {
		this.nom = nom;
		this.cout = cout;
		this.centre = centre;
	}

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

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public Case getCentre() {
		return centre;
	}

	public void setCentre(Case centre) {
		this.centre = centre;
	}
	
	
}
