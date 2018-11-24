import java.util.Observer;


public abstract class JoueurVue implements Observer{
	
	protected Joueur model;
	protected JoueurControl controller;
	
	JoueurVue(Joueur model, JoueurControl controller) {
		this.model = model; 
		this.controller = controller;
		model.addObserver(this);
	}
 
	public abstract void affiche(String string) ;
}

