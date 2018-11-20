import java.util.ArrayList;

public class Main{
	
	public static void main(String[] args) {
		Joueur b = new Joueur("B",new ArrayList<Bateau>());
		b.placerBateaux();
		b.generePlateau();
		Tour tourCurrent = new Tour(b,10);
		tourCurrent.action(b);
	}
}