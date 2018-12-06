
public class AttaqueVerticale extends Attaque {
	/*
	 * Crée une Attaque verticale
	 * @param : nom represente "attaque horizontale"
	 * @param : surface represente la surface de l'attaque (3 cases)
	 * @param : cout represente son cout (3)
	 * @param : centre le centre de l'attaque
	 */
	public AttaqueVerticale(int ligne, int colonne) {
		super(ligne, colonne);
		this.cout = 5;
	}
	
	public void calculSurface() {
		this.surface = new Case[3];
		surface[0] = new Case(this.ligne-1,this.colonne);
		surface[1] = new Case(this.ligne,this.colonne);
		surface[2] = new Case(this.ligne+1,this.colonne);
	}
	
	public Case[] getSurface() {
		calculSurface();
		return surface;
	}
}
