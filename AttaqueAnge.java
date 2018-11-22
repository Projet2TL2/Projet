public class AttaqueAnge extends Attaque{

	/*
	 * Crée une Attaque Ultime des anges
	 * @param : nom represente "Croix de Jesus"
	 * @param : surface represente la surface de l'attaque (6 cases)
	 * @param : cout represente son cout (10)
	 * @param : centre le centre de l'attaque
	 */
	public AttaqueAnge(String nom, Case[] surface, int cout, Case centre) {
		super(nom, surface, 10,centre);
	}

	/*
	 * Crée une Attaque Ultime des anges
	 * @param : nom represente "Croix de Jesus"
	 * @param : cout represente son cout (10)
	 * @param : centre le centre de l'attaque
	 */
	public AttaqueHorizontale(String nom, int cout, Case centre) {
		super(nom,cout,centre);
		calculSurface(centre);
	}
	
	private void calculSurface(Case centre) {
		this.surface = new Case[6];
		surface[0] = new Case(centre.getColonne()-1,centre.getLigne());
		surface[1] = centre;
		surface[2] = new Case(centre.getColonne()+1,centre.getLigne());
		surface[3] = new Case(centre.getLigne()-1,centre.getColonne());
		surface[4] = new Case(centre.getLigne()+1,centre.getColonne());
		surface[5] = new Case(centre.getLigne()+2,centre.getColonne());
	}
}