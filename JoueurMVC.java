
public class JoueurMVC {

	public JoueurMVC() {
		
		Joueur model = new Joueur();
		
		JoueurControl ctrlGUI = new JoueurControl(model);
		JoueurControl ctrlConsole = new JoueurControl(model);
		
		JoueurVue gui = new JoueurVueGUI(model, ctrlGUI, 200, 200);
		JoueurVue console = new JoueurVueConsole(model, ctrlConsole);
		
		ctrlGUI.addView(gui);
		ctrlConsole.addView(console);
	}
	
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JoueurMVC();
			}
		});
	}
}
