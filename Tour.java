import java.util.Scanner;

import javax.swing.JOptionPane;

public class Tour {

	Joueur enCours;
	int argent;
	
	public Tour(Joueur enCours, int argent) {
		this.enCours = enCours;
		this.argent = argent;
	}

	/*
	 * Lance un tour pour un joueur, pdt ce tour il peut depenser ces 10 pieces en faisant diverses attaques
	 * @param : b, un Joueur
	 */
	public void action(Joueur b) {
		boolean veutRejouer = true;
		while(veutRejouer) {
			int x = -1;
			int y = -1;
			String[] choix = {"Attaque classique","Attaque Horizontale"};	//Liste des attaques disponibles
			int choixPane = JOptionPane.showOptionDialog(null, "Choisissez une Attaque", "Choix Attaque", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choix, choix[0]);
			
			
			if(choixPane == 0) {
				//ATTAQUE CLASSIQUE
				while((x<0||x>=10)&&(y<0||y>=10)) {
					 x = Integer.parseInt(JOptionPane.showInputDialog("Préparez-vus à ATTAQUER !!!!!!!!! \nEntrez une colonne valide (0-9)"));
					 y = Integer.parseInt(JOptionPane.showInputDialog("Préparez-vus à ATTAQUER !!!!!!!!! \nEntrez une ligne valide (0-9)"));
				}
				System.out.println(""+ x +" " + y);
				AttaqueClassique aC = new AttaqueClassique("horizontale",3, new Case(x,y));
				b.estAttaque2(aC);
				this.setArgent(this.getArgent() - aC.getCout());
			}
			
			
			if(choixPane == 1) {
				//ATTAQUE HORIZONTALE
				while((x<0||x>=10)&&(y<0||y>=10)) {
					 x = Integer.parseInt(JOptionPane.showInputDialog("Préparez-vus à ATTAQUER !!!!!!!!! \nEntrez une colonne valide (0-9)"));
					 y = Integer.parseInt(JOptionPane.showInputDialog("Préparez-vus à ATTAQUER !!!!!!!!! \nEntrez une ligne valide (0-9)"));
				}
				System.out.println(""+ x +" " + y);
				AttaqueHorizontale aH = new AttaqueHorizontale("horizontale",5, new Case(x,y));
				b.estAttaque2(aH);
				this.setArgent(this.getArgent() - aH.getCout());
			}
			
			
			b.affichePlateau();
			System.out.println(this.getArgent());
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Voullez vous réattaquer ?");
			String str = sc.nextLine();
			if( str.equals("o") == false) {
				veutRejouer = false;
				sc.close();
			}
		}
	}
	
	
	public Joueur getEnCours() {
		return enCours;
	}

	public void setEnCours(Joueur enCours) {
		this.enCours = enCours;
	}

	public int getArgent() {
		return argent;
	}

	public void setArgent(int argent) {
		this.argent = argent;
	}
	
	
}
