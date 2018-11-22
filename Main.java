import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main{
	
	public static void main(String[] args) {
		Joueur b = new Joueur("B",new ArrayList<Bateau>());
		
		switch(b.choixModeJeux()) {
		case "console" :
			b.generePlateau();
			b.placerBateauxConsole();
			Tour tourCurrent = new Tour(b,10);
			tourCurrent.action(b);
		case "interface" : 
			MaJFrame2 plateauGraphique = new MaJFrame2();
			tourCurrent = new Tour(b,10);
			//tourCurrent.action(b);
		}
	}
}