

import javax.swing.JOptionPane;

import Controleur.JoueurControl;
import Model.Joueur;
import Vue.JoueurVue;
import Vue.JoueurVueConsole;
import Vue.JoueurVueGUIVersionReseau;

public class JoueurMVC {

	static boolean isServeur;
	public JoueurMVC(boolean arg) {
		
		Joueur model = new Joueur();
		
		JoueurControl ctrlGUI = new JoueurControl(model);
		JoueurControl ctrlConsole = new JoueurControl(model);
		
		JoueurVue gui = new JoueurVueGUIVersionReseau(model, ctrlGUI, isServeur);
		JoueurVue console = new JoueurVueConsole(model, ctrlConsole);
		
		ctrlGUI.addView(gui);
		ctrlConsole.addView(console);
	}
	
	public static void main(String args[]) {
			isServeur = false;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JoueurMVC(isServeur);
			}
		});
	}
}
