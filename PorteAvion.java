package batailleNavale;

public class PorteAvion extends Bateau{
	private String nom;
	private Case[] surface;
	private Case centre;
	
	/*
	 * crée un bateau
	 * @param : centre représente le centre du bateau
	 */
	public PorteAvion(Case centre) {
		super(centre);
		this.nom = "porte-avion";
		calculSurface(centre);
		
						
	}
    /**
     * donne la surface du bateau
     * @param centre
     */
	private void calculSurface(Case centre) {
		this.surface  = new Case[5];
		setCentre();
	}
	
}
