
public class AttaqueHorizontale extends Attaque{

	/*
	 * Crée une Attaque horizontale
	 * @param : nom represente "attaque horizontale"
	 * @param : surface represente la surface de l'attaque (3 cases)
	 * @param : cout represente son cout (3)
	 * @param : centre le centre de l'attaque
	 */
	public AttaqueHorizontale(String nom, Case[] surface, int cout, Case centre) {
		super(nom, surface, 3,centre);
	}

	/*
	 * Crée une Attaque horizontale
	 * @param : nom represente "attaque horizontale"
	 * @param : cout represente son cout (3)
	 * @param : centre le centre de l'attaque
	 */
	public AttaqueHorizontale(String nom, int cout, Case centre) {
		super(nom,cout,centre);
		calculSurface(centre);
	}
	
	public void calculSurface(Case centre) {
		this.surface = new Case[3];
		surface[0] = new Case(centre.getColonne()-1,centre.getLigne());
		surface[1] = centre;
		surface[2] = new Case(centre.getColonne()+1,centre.getLigne());
	}
}
