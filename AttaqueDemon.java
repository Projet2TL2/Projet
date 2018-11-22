public class AttaqueDemon extends Attaque{

	/*
	 * Crée une Attaque ultime des démons
	 * @param : nom represente "Z de l'enfer"
	 * @param : surface represente la surface de l'attaque (7 cases)
	 * @param : cout represente son cout (10)
	 * @param : centre le centre de l'attaque
	 */
	public AttaqueDemon(String nom, Case[] surface, int cout, Case centre) {
		super(nom, surface, 10,centre);
	}

	/*
	 * Crée une Attaque Ultime des démons
	 * @param : nom represente "Z de l'enfer"
	 * @param : cout represente son cout (10)
	 * @param : centre le centre de l'attaque
	 */
	public AttaqueDemon(String nom, int cout, Case centre) {
		super(nom,cout,centre);
		calculSurface(centre);
	}
	
	private void calculSurface(Case centre) {
		this.surface = new Case[7];
		surface[0] = new Case(centre.getColonne()-2,centre.getLigne()-1);
		surface[1] = new Case(centre.getColonne()-1,centre.getLigne()-1);
		surface[2] = new Case(centre.getColonne(),centre.getLigne()-1);
		surface[3] = new Case(centre);
		surface[4] = new Case(centre.getLigne()+1,centre.getColonne());
		surface[5] = new Case(centre.getLigne()+1,centre.getColonne()+1);
		surface[6] = new Case(centre.getLigne()+1,centre.getColonne()+2);
	}
}