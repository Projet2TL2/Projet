
public class AttaqueClassique extends Attaque{

	/*
	 * Crée une Attaque classique
	 * @param : nom represente "attaque classique"
	 * @param : surface represente la surface de l'attaque (1 case)
	 * @param : cout represente son cout (3)
	 * @param : centre le centre de l'attaque
	 */
	public AttaqueClassique(String nom, Case[] surface, int cout, Case centre) {
		super(nom, surface, 3,centre);
	}

	/*
	 * Crée une Attaque classique ou la surface equivaut au centre
	 * @param : nom represente "attaque classique"
	 * @param : cout represente son cout (3)
	 * @param : centre le centre de l'attaque
	 */
	public AttaqueClassique(String nom, int cout, Case centre) {
		super(nom,cout,centre);
		calculSurface(centre);
	}
	
	public void calculSurface(Case centre) {
		this.surface = new Case[1];
		surface[0] = centre;
	}
}
