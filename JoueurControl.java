

public class JoueurControl {

	Joueur model;
	JoueurVue vue;
	
	public JoueurControl(Joueur model) {
		this.model = model;
	}
	
	public void estAttaque(Attaque attaque) { 
		if(model.estAttaque(attaque)) {
			vue.affiche("L'attaque: " + attaque.toString() + "a touch�e un bateau !");
	} 
		else vue.affiche("L'attaque: " + attaque.toString() + "n'a pas touch�e de bateau !");
	}
	
	public void placerBateau(Bateau b) {
		if(model.placerBateau(b)) {
			vue.affiche("Le bateau : " + b.toString() + "  a bien �t� plac� !");
	} 
		else vue.affiche("Le bateau : " + b.toString() + "  n'a pas �t� plac� !");
	}
	
	public Case[][] getPlateau(){
		return model.getPlateau();
	}
	
	public void addView(JoueurVue vue) {
		this.vue = vue;
	}
}
