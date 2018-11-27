

public class JoueurControl {

	Joueur model;
	JoueurVue vue;
	
	public JoueurControl(Joueur model) {
		this.model = model;
	}
	
	public void estAttaque(Attaque attaque) { 
		if(model.estAttaque(attaque)) {
			vue.affiche("L'attaque: " + attaque.toString() + "a touchée un bateau !");
	} 
		else vue.affiche("L'attaque: " + attaque.toString() + "n'a pas touchée de bateau !");
	}
	
	public void placerBateau(Bateau b) {
		if(model.placerBateau(b)) {
			vue.affiche("Le bateau : " + b.toString() + "  a bien été placé !");
	} 
		else vue.affiche("Le bateau : " + b.toString() + "  n'a pas été placé !");
	}
	
	public Case[][] getPlateau(){
		return model.getPlateau();
	}
	
	public void addView(JoueurVue vue) {
		this.vue = vue;
	}
}
