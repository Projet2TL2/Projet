import java.util.Observer;

import javax.swing.JFrame;



public abstract class JoueurVue extends JFrame implements Observer{
	
	protected Joueur model;
	protected JoueurControl controller;
	
	JoueurVue(Joueur model, JoueurControl controller) {
		this.model = model; 
		this.controller = controller;
		model.addObserver(this);
	}
 
	public abstract void affiche(String string) ;
}

