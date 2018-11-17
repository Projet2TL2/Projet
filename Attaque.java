
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
}
