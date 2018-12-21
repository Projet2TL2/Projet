package Model;

public class AttaqueHorizontale extends Attaque{

	
	
	/*
	 * Crée une Attaque horizontale
	 * @param : nom represente "attaque horizontale"
	 * @param : surface represente la surface de l'attaque (3 cases)
	 * @param : cout represente son cout (3)
	 * @param : centre le centre de l'attaque
	 */
	public AttaqueHorizontale(int ligne, int colonne) {
		super(ligne, colonne);
	}
	
	public void calculSurface() {
		this.surface = new Case[3];
		surface[0] = new Case(this.ligne,this.colonne-1);
		surface[1] = new Case(this.ligne,this.colonne);
		surface[2] = new Case(this.ligne,this.colonne+1);
	}
	
	public Case[] getSurface() {
		calculSurface();
		return surface;
	}
}
