package Controleur;

import Model.Attaque;
import Model.Bateau;
import Model.Joueur;
import Vue.JoueurVue;

public class JoueurControl {

	Joueur model;
	JoueurVue vue;
	
	public JoueurControl(Joueur model) {
		this.model = model;
	}
	
	public void ordiEstAttaque(Attaque attaque) { 
		if(model.ordiEstAttaque(attaque)) {
			vue.affiche("L'attaque: " + attaque.toString() + "a touchée un bateau !\n");
	} 
		else vue.affiche("L'attaque: " + attaque.toString() + "n'a pas touchée de bateau !\n");
	}
	
	public void joueurEstAttaque(Attaque attaque) { 
		if(model.joueurEstAttaque(attaque)) {
			vue.affiche("L'attaque: " + attaque.toString() + "de l'ordi a touchée un de vos bateau !\n");
	} 
		else vue.affiche("L'attaque: " + attaque.toString() + "n'a pas touchée un de vos bateau !\n");
	}
	
	public boolean joueurPlacerBateau(Bateau b) {
		if(model.joueurPlacerBateau(b)) {
			//vue.affiche("Le bateau : " + b.toString() + "  a bien été placé !\n");
			return true;
		} 
		else{
			//vue.affiche("Le bateau : " + b.toString() + "  n'a pas été placé !\n");
			return false;
		}
	}

	public boolean ordiPlacerBateau(Bateau b) {
		if(model.ordiPlacerBateau(b)) {
			vue.affiche("Le bateau : " + b.toString() + "  a bien été placé !\n");
			return true;
	} 
		else vue.affiche("Le bateau : " + b.toString() + "  n'a pas été placé !\n");
		return false;
	}
	
	public boolean joueurAPlacerBateaux() {
		return model.joueurAPlacerBateaux();
	}
	
	public void setJoueurAPlacerBateaux(boolean boo) {
		model.setJoueurAPlacerBateaux(boo);
	}
	
	public boolean ordiAPlacerBateaux() {
		return model.odriAPlacerBateaux();
	}
	
	public void setOrdiAPlacerBateaux(boolean boo) {
		model.setOrdiAPlacerBateaux(boo);
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
	
	public int getBateauxAPlacer(){
		return model.getbateauAPlacer();
	}
}
