package model;

import Controller.JoueurControl;
import Vue.JoueurVue;
import Vue.JoueurVueConsole;
import Vue.JoueurVueGUIVersionReseau;

public class JoueurMVC {

	static boolean isServeur;
	public JoueurMVC(boolean arg) {
		
		Joueur model = new Joueur();
		
		JoueurControl ctrlGUI = new JoueurControl(model);
		JoueurControl ctrlConsole = new JoueurControl(model);
		
		//Network test = new Network(model, arg);
		JoueurVue gui = new JoueurVueGUIVersionReseau(model, ctrlGUI, isServeur);
		JoueurVue console = new JoueurVueConsole(model, ctrlConsole);
		
		ctrlGUI.addView(gui);
		ctrlConsole.addView(console);
	}
	
	public static void main(String args[]) {
		isServeur = (args[0].equals("true")? true : false);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JoueurMVC(isServeur);
			}
		});
	}
}
