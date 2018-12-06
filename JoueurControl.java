

public class JoueurControl {

	Joueur model;
	JoueurVue vue;
	
	public JoueurControl(Joueur model) {
		this.model = model;
	}
	
	public void ordiEstAttaque(Attaque attaque) { 
		if(model.ordiEstAttaque(attaque)) {
			vue.affiche("L'attaque: " + attaque.toString() + "a touch�e un bateau !\n");
	} 
		else vue.affiche("L'attaque: " + attaque.toString() + "n'a pas touch�e de bateau !\n");
	}
	
	public void joueurEstAttaque(Attaque attaque) { 
		if(model.joueurEstAttaque(attaque)) {
			vue.affiche("L'attaque: " + attaque.toString() + "de l'ordi a touch�e un de vos bateau !\n");
	} 
		else vue.affiche("L'attaque: " + attaque.toString() + "n'a pas touch�e un de vos bateau !\n");
	}
	
	public void joueurPlacerBateau(Bateau b) {
		if(model.joueurPlacerBateau(b)) {
			vue.affiche("Le bateau : " + b.toString() + "  a bien �t� plac� !\n");
	} 
		else vue.affiche("Le bateau : " + b.toString() + "  n'a pas �t� plac� !\n");
	}

	public void ordiPlacerBateau(Bateau b) {
		if(model.ordiPlacerBateau(b)) {
			vue.affiche("Le bateau : " + b.toString() + "  a bien �t� plac� !\n");
	} 
		else vue.affiche("Le bateau : " + b.toString() + "  n'a pas �t� plac� !\n");
	}
	
	public boolean aPlacerBateaux() {
		return model.aPlacerBateaux();
	}
	
	public void setAPlacerBateaux(boolean boo) {
		model.aPlacerBateaux = boo;
	}
	
	public void addView(JoueurVue vue) {
		this.vue = vue;
	}
}
