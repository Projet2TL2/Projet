

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;



public class JoueurVueGUIVersion2 extends JoueurVue implements ActionListener{
	
	private JTextField ligneAttaque = new JTextField(1);
	private JTextField colonneAttaque = new JTextField(1);
	private JButton attaqueButton = new JButton("Attaquer");
	private JButton attaqueHorizontaleButton = new JButton("Attaque Horizontale");
	private JButton placerBateauButton = new JButton("Placer Bateau");
	private JLabel message = new JLabel(" ");
	
	private JPanel jpanelJoueur;
	private JPanel jpanelOrdi;
	private JButton mesBoutonsJoueur[][] = new JButton[11][11];
	private JButton mesBoutonsOrdi[][] = new JButton[11][11];
	private JPanel grilleJoueur;
	private JPanel grilleOrdi;
	private JFrame framePlateau = new JFrame();
	private JFrame framePlateauOrdi = new JFrame();
	private JPanel commandes;
	
	
	boolean bateauxPlacés = false;

	public JoueurVueGUIVersion2(Joueur model, JoueurControl controller, int posX, int posY) {
		
		super(model, controller);
		
		jpanelJoueur = new JPanel();
		jpanelOrdi = new JPanel();
		commandes = new JPanel();
		commandes.add(attaqueButton);
		commandes.add(attaqueHorizontaleButton);
		commandes.add(placerBateauButton);
		
		jpanelJoueur.setLayout(new BorderLayout());
		jpanelOrdi.setLayout(new BorderLayout());
		
		grilleJoueur = new JPanel();
		grilleOrdi = new JPanel();
		
        grilleJoueur.setLayout(new GridLayout(11,11));
        grilleOrdi.setLayout(new GridLayout(11, 11));
		
		updateTable();
		
		framePlateau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePlateauOrdi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * Affiche le msg en argument dans le Jlabel de la GUI
	 */
	public void affiche(String msg){
		message.setText(msg);
	}
	
	/*
	 * Affiche dans le JTable le plateau du model
	 * Affiche [0] si cette case n'est pas attaquée
	 * Affiche [X] si cette case est attaquée et contient un bateau
	 * Affiche [ ] si cette case est attaquée mais ne contient pas de bateau
	 */
	public void updateTable(){
		
			//REINITIALISATION DES GRILLES
		 grilleJoueur.removeAll();
		 grilleOrdi.removeAll();
		 
		 int numeroLignes = 0;
		 int numeroColonnes = 0;
	        for(int ligne=0;ligne<11;ligne++) {
	            for(int colonne=0;colonne<11;colonne++) {
	                mesBoutonsJoueur[ligne][colonne] = new JButton("");
	                mesBoutonsOrdi[ligne][colonne] = new JButton("");
	                
	                if(ligne==0&&colonne==0) {
	                	mesBoutonsJoueur[ligne][colonne].setBackground(Color.BLACK);
	                	mesBoutonsOrdi[ligne][colonne].setBackground(Color.BLACK);
	                	
	                	mesBoutonsJoueur[ligne][colonne].setEnabled(false);
	                	mesBoutonsOrdi[ligne][colonne].setEnabled(false);
	                }
	                if(ligne==0&&colonne!=0) {
	                	mesBoutonsJoueur[ligne][colonne].setText(""+numeroColonnes);
	                	mesBoutonsJoueur[ligne][colonne].setBackground(Color.ORANGE);
	                	mesBoutonsJoueur[ligne][colonne].setForeground(Color.red);
	                	mesBoutonsJoueur[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
	                	
	                	mesBoutonsOrdi[ligne][colonne].setText(""+numeroColonnes);
	                	mesBoutonsOrdi[ligne][colonne].setBackground(Color.ORANGE);
	                	mesBoutonsOrdi[ligne][colonne].setForeground(Color.red);
	                	mesBoutonsOrdi[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
	                	
	                	mesBoutonsJoueur[ligne][colonne].setEnabled(false);
	                	mesBoutonsOrdi[ligne][colonne].setEnabled(false);
	                	
	                    numeroColonnes++;
	                }
	                if(colonne==0&&ligne!=0) {
	                	mesBoutonsJoueur[ligne][colonne].setText(""+numeroLignes);
	                	mesBoutonsJoueur[ligne][colonne].setBackground(Color.ORANGE);
	                	mesBoutonsJoueur[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
	                	
	                	mesBoutonsOrdi[ligne][colonne].setText(""+numeroLignes);
	                	mesBoutonsOrdi[ligne][colonne].setBackground(Color.ORANGE);
	                	mesBoutonsOrdi[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
	                	
	                	mesBoutonsJoueur[ligne][colonne].setEnabled(false);
	                	mesBoutonsOrdi[ligne][colonne].setEnabled(false);
	                	
	                    numeroLignes++;
	                }
	                if(ligne>0&&colonne>0) {
	                	
	                	/////////////// Remplis Plateau du joueur \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	                	if(model.getPlateau().getPlateau()[ligne-1][colonne-1].estOccupee()) {
	    					if(model.getPlateau().getPlateau()[ligne-1][colonne-1].estTouchee()){
	    						mesBoutonsJoueur[ligne][colonne].setBackground(Color.RED);
	    					}
	    					else{
	    						mesBoutonsJoueur[ligne][colonne].setBackground(Color.GREEN);
	    					}
	    				}
	    				else {
	    					if(model.getPlateau().getPlateau()[ligne-1][colonne-1].estTouchee()){
	    						mesBoutonsJoueur[ligne][colonne].setBackground(Color.GRAY);
	    					}
	    					else{
	    						mesBoutonsJoueur[ligne][colonne].setBackground(Color.CYAN);
	    					}
	    				}
	                	
	                	
	                	/////////////// Remplis Plateau de l'ordi \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	                	if(model.getPlateauOrdi().getPlateau()[ligne-1][colonne-1].estOccupee()) {
	    					if(model.getPlateau().getPlateau()[ligne-1][colonne-1].estTouchee()){
	    						mesBoutonsOrdi[ligne][colonne].setBackground(Color.RED);
	    					}
	    					else{
	    						mesBoutonsOrdi[ligne][colonne].setBackground(Color.GREEN);
	    					}
	    				}
	    				else {
	    					if(model.getPlateauOrdi().getPlateau()[ligne-1][colonne-1].estTouchee()){
	    						mesBoutonsOrdi[ligne][colonne].setBackground(Color.GRAY);
	    					}
	    					else{
	    						mesBoutonsOrdi[ligne][colonne].setBackground(Color.CYAN);
	    					}
	    				}
	                }
	             
	                grilleJoueur.add(mesBoutonsJoueur[ligne][colonne]); 
	                grilleOrdi.add(mesBoutonsOrdi[ligne][colonne]);
	            }
	        }
	        
	        	//MISE EN PLACE DES GRILLES
	        jpanelJoueur.add(grilleJoueur);
	        jpanelJoueur.add(commandes,BorderLayout.SOUTH);
	        jpanelOrdi.add(grilleOrdi);
	        	//TITRE DE LA JFRAME
	        framePlateau.setTitle("joueur");
	        framePlateauOrdi.setTitle("ordi");
	        	//AJOUT DU CONTENUE DANS LA JFRAME
	        framePlateau.setContentPane(jpanelJoueur);
	        framePlateauOrdi.setContentPane(jpanelOrdi);
	        	//PACK TOUTE LA JFRAME
	        framePlateau.pack();
	        framePlateauOrdi.pack();
	        	//DEFINITION TAILLE OPTIMALE
	        framePlateau.setSize(500, 500);
	        framePlateauOrdi.setSize(500, 500);
	        	//LOCALISATION D'APPARITION
	        framePlateau.setLocation(100, 100);
	        framePlateauOrdi.setLocation(1200, 100);
	        	//SET VISIBLE
	        framePlateau.setVisible(true);
	        framePlateauOrdi.setVisible(true);
	}
	
	
	/*
	 * Lors d'un changement dans le controller, actualise l'affichage
	 * si le joueur n'a pas encore placé ses bateaux, les bouttons d'attaque ne sont pas visibles
	 */
	public void update(Observable o, Object arg) {
		updateTable();
	}
	
	/*
	 * si l'action e est:
	 * 		-le bouton "Placer bateaux":
	 * 			Ouvre une autre Jframe pour placer les bateaux
	 * 			TODO
	 * 		-le bouton "Placer!":
	 * 			Ferme la Jframe du placement des bateaux et a effectué le placement
	 * 			TODO
	 * 		-le bouton "Attaque":
	 * 			Prends la valeur des 2 JTextField et crée une attaque appliqué sur le plateau de l'ordi du controller/joueur
	 * 			réactualise le plateau
	 * 		-le bouton "Attaque Horizontale":
	 * 			Prends la valeur des 2 JTextField et crée une attaque Horizontale appliqué sur le plateau de l'ordi du controller/joueur
	 * 			réactualise le plateau
	 */
	public void actionPerformed(ActionEvent e) {
		 Object  source=e.getSource();
		 
		 if(source == attaqueButton) {
			 if(getColonneAttaque() < 0 || getLigneAttaque() < 0 || getColonneAttaque() > 10 || getLigneAttaque() > 10){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 Attaque attaque = new Attaque(getLigneAttaque(), getColonneAttaque());
			 this.controller.ordiEstAttaque(attaque);
			 updateTable();
		 }
		 
		 if(source == attaqueHorizontaleButton) {
			 if(getColonneAttaque() < 0 || getLigneAttaque() < 0 || getColonneAttaque() > 10 || getLigneAttaque() > 10){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 Attaque attaque = new AttaqueHorizontale(getLigneAttaque(), getColonneAttaque());
			 this.controller.ordiEstAttaque(attaque);
			 updateTable();

		 }
		 
		
	}
	
	/*
	 * Return le nombre du premier JTextField
	 */
	public int getLigneAttaque() {
		int result = 0;
		try {
			result = Integer.valueOf(ligneAttaque.getText()).intValue();
		}
		catch (NumberFormatException e){
			result = -1;
		}
		return result;
	}
	
	/*
	 * Return le nombre du second JTextField
	 */
	public int getColonneAttaque() {
		int result = 0;
		try {
			result = Integer.valueOf(colonneAttaque.getText()).intValue();
		}
		catch (NumberFormatException e){
			result = -1;
		}
		return result;
	}
}
