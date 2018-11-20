package batailleNavale;

public class Torpilleur extends Bateau{
	private String nom;
	private Case[] surface;
	private Case centre;
	
	/*
	 * cr�e un bateau
	 * @param : centre repr�sente le centre du bateau
	 */
	public Torpilleur(Case centre) {
		super(centre);
		this.nom = "torpilleur";
		calculSurface(centre);
		
						
	}
    /**
     * Donne la surface du bateau
     * @param centre
     */
	private void calculSurface(Case centre) {
		this.surface  = new Case[2];
		setCentre();
	}
}