
import javax.swing.JOptionPane;

public class Main{
	
	Case[][] plateau = new Case [10][10];

	public static void main(String[] args) {
		Case[] tab = new Case[6];
		tab[0] = new Case(0,0);
		tab[1] = new Case(0,1);
		tab[2] = new Case(0,2);
		tab[3] = new Case(0,3);
		tab[4] = new Case(0,4);
		tab[5] = new Case(0,5);
		Bateau bateau1 = new Bateau("catamaran",tab);
		Joueur a = new Joueur("A");
		a.ajouteBateau(bateau1);
		a.generePlateau();
		
		int x = Integer.parseInt(JOptionPane.showInputDialog("Entrez une colonne"));
		int y = Integer.parseInt(JOptionPane.showInputDialog("Entrez une ligne"));
		
		a.estAttque(new Case(x,y));
		a.affichePlateau();
	}
}
