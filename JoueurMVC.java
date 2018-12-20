package model;

import javax.swing.JOptionPane;

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
		int nbr = JOptionPane.showConfirmDialog(null, "Attendez-vous qqun?", "Etes vous serveur?", JOptionPane.YES_NO_OPTION);
		if(nbr == 0) {
			isServeur = true;
		}
		else {
			isServeur = false;
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JoueurMVC(isServeur);
			}
		});
	}
}
