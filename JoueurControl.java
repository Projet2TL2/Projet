

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
	
	public void addView(JoueurVue vue) {
		this.vue = vue;
	}
}
