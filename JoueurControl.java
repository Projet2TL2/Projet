import javafx.collections.SetChangeListener;

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
	
	public boolean joueurPlacerBateau(Bateau b) {
		if(model.joueurPlacerBateau(b)) {
			//vue.affiche("Le bateau : " + b.toString() + "  a bien �t� plac� !\n");
			return true;
		} 
		else{
			//vue.affiche("Le bateau : " + b.toString() + "  n'a pas �t� plac� !\n");
			return false;
		}
	}

	public boolean ordiPlacerBateau(Bateau b) {
		if(model.ordiPlacerBateau(b)) {
			vue.affiche("Le bateau : " + b.toString() + "  a bien �t� plac� !\n");
			return true;
	} 
		else vue.affiche("Le bateau : " + b.toString() + "  n'a pas �t� plac� !\n");
		return false;
	}
	
	public boolean joueurAPlacerBateaux() {
		return model.joueurAPlacerBateaux();
	}
	
	public void setJoueurAPlacerBateaux(boolean boo) {
		model.joueurAPlacerBateaux = boo;
	}
	
	public boolean ordiAPlacerBateaux() {
		return model.odriAPlacerBateaux();
	}
	
	public void setOrdiAPlacerBateaux(boolean boo) {
		model.ordiAPlacerBateaux = boo;
	}
	
	public void addView(JoueurVue vue) {
		this.vue = vue;
	}
	
	public int getArgent() {
		return model.getArgent();
	}
	
	public void setArgent( int a) {
		model.setArgent(a);
	}
}
