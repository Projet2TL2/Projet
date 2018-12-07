

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
	
	private JButton attaqueButton = new JButton("Attaquer");
	private JButton attaqueHorizontaleButton = new JButton("Attaque Horizontale");
	private JButton attaqueVerticaleButton = new JButton("Attaque Verticale");
	private JButton placerBateauButton = new JButton("Placer Bateau");
	
	private JPanel jpanelJoueur;
	private JPanel jpanelOrdi;
	private JPanel grilleJoueur;
	private JPanel grilleOrdi;
	private JPanel commandes;
	private JPanel commandesInputs;
	private JPanel commandesLigne;
	private JPanel commandesColonne;
	private JPanel commandesOrientation;
	private JPanel global;
	
	private JButton mesBoutonsJoueur[][] = new JButton[11][11];
	private JButton mesBoutonsOrdi[][] = new JButton[11][11];
	
	
	private JFrame framePlateau = new JFrame();
	private JFrame framePlateauOrdi = new JFrame();
	
	private JLabel message = new JLabel(" ");
	private JLabel labelArgent = new JLabel("Il vous reste : " + model.getArgent() + " pièces pour ce tour !");
	private JLabel labelLigne = new JLabel(" Ligne de votre attaque ");
	private JLabel labelColonne = new JLabel(" Colonne de votre attaque ");
	private JLabel labelOrientation = new JLabel(" Orientation de votre bateau ");;

	private JTextField ligneAttaque = new JTextField(1);
	private JTextField colonneAttaque = new JTextField(1);
	private JTextField orientationBateau = new JTextField(1);
	
	
	boolean bateauxPlacés = false;

	public JoueurVueGUIVersion2(Joueur model, JoueurControl controller, int posX, int posY) {
		
		super(model, controller);
		
		labelArgent.setForeground(Color.blue);
		jpanelJoueur = new JPanel();
		jpanelOrdi = new JPanel();
		commandes = new JPanel();
		commandesInputs = new JPanel();
		commandesLigne = new JPanel();
		commandesColonne = new JPanel();
		global = new JPanel();
		commandesOrientation = new JPanel();
		
		attaqueButton.addActionListener(this);
		attaqueHorizontaleButton.addActionListener(this);
		attaqueVerticaleButton.addActionListener(this);
		placerBateauButton.addActionListener(this);
		
		commandesLigne.setLayout(new BoxLayout(commandesLigne, BoxLayout.X_AXIS));
		commandesColonne.setLayout(new BoxLayout(commandesColonne, BoxLayout.X_AXIS));
		commandesOrientation.setLayout(new BoxLayout(commandesOrientation, BoxLayout.X_AXIS));
		commandesInputs.setLayout(new BoxLayout(commandesInputs, BoxLayout.Y_AXIS));
		commandes.setLayout(new BoxLayout(commandes, BoxLayout.X_AXIS));
		global.setLayout(new BoxLayout(global, BoxLayout.Y_AXIS));
		
		commandesOrientation.add(labelOrientation);
		commandesOrientation.add(orientationBateau);
		commandesOrientation.setVisible(false);
		
		commandesLigne.add(labelLigne);
		commandesLigne.add(ligneAttaque);
		
		commandesColonne.add(labelColonne);
		commandesColonne.add(colonneAttaque);
		
		commandesInputs.add(commandesLigne);
		commandesInputs.add(commandesColonne);
		commandesInputs.add(commandesOrientation);
		
		commandes.add(attaqueButton);
		commandes.add(attaqueHorizontaleButton);
		commandes.add(attaqueVerticaleButton);
		commandes.add(placerBateauButton);
		
		global.add(labelArgent);
		global.add(commandesInputs);
		global.add(commandes);
		
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
		 labelArgent .setText("Il vous reste : " + controller.getArgent() + " pièces pour ce tour !" + model.getbateauAPlacer());
		 if(controller.aPlacerBateaux()) {
			placerBateauButton.setVisible(false); 
			attaqueButton.setVisible(true);
			 attaqueHorizontaleButton.setVisible(true);
			 attaqueVerticaleButton.setVisible(true);
		 }
		 
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
	    					if(model.getPlateauOrdi().getPlateau()[ligne-1][colonne-1].estTouchee()){
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
	        jpanelJoueur.add(global,BorderLayout.SOUTH);
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
		 
		 /////PLACEMENT BATEAUX\\\\\
		 if(source == placerBateauButton) {
			 
				for(int compteur = model.getbateauAPlacer()*2; compteur!=0 ; compteur --) {
					 commandesOrientation.setVisible(true);
					 labelLigne.setText(" Ligne de votre Bateau");
					 labelColonne.setText(" Colonne de votre Bateau");
					 attaqueButton.setVisible(false);
					 attaqueHorizontaleButton.setVisible(false);
					 attaqueVerticaleButton.setVisible(false);
					 
					 /////CHANGEMENT DES COULEURS D'AFFICHAGE\\\\\
					 grilleJoueur.removeAll();
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
				    				mesBoutonsJoueur[ligne][colonne].setBackground(Color.GRAY);
				    				
				                }
				                grilleJoueur.add(mesBoutonsJoueur[ligne][colonne]); 
				            }
				       }
				        
				        if( getColonneAttaque() < 0 || getLigneAttaque() < 0 || getColonneAttaque() >= 10 || getLigneAttaque() >= 10){
							 affiche("Erreur, ceci n'est pas une coordonée valide "); 
							 return;
						 }
				        if(getOrientation().isEmpty()) {
				        	
				        }
				        else {
					        Bateau bateau = new Bateau(getLigneAttaque(), getColonneAttaque(),3, getOrientation());
					        controller.joueurPlacerBateau(bateau);
					        controller.setAPlacerBateaux(true);
					        model.bateauAPlacerMoins1();
					        ligneAttaque.setText("");
					        colonneAttaque.setText("");
					        orientationBateau.setText("");
					        
				        }
				}
				if(model.getbateauAPlacer() <= 0) {
					updateTable();
					 commandesOrientation.setVisible(false);
					 labelLigne.setText(" Ligne de votre attaque");
					 labelColonne.setText(" Colonne de votre attaque");
				}
				
		 }
		 
		 
		 /////ATTAQUE 1 CASE\\\\\
		 if(source == attaqueButton) {
			 if(getColonneAttaque() < 0 || getLigneAttaque() < 0 || getColonneAttaque() >= 10 || getLigneAttaque() >= 10){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 Attaque attaque = new Attaque(getLigneAttaque(), getColonneAttaque());
			 controller.ordiEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 3);
			 updateTable();
		 }
		 
		/////ATTAQUE 3 CASES HORIZONTALES\\\\\
		 if(source == attaqueHorizontaleButton) {
			 if(getColonneAttaque() <1 || getLigneAttaque() < 0 || getColonneAttaque() >= 9 || getLigneAttaque() >= 10){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 Attaque attaque = new AttaqueHorizontale(getLigneAttaque(), getColonneAttaque());
			 controller.ordiEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 5);
			 updateTable();
		 }
		 
		/////ATTAQUE 3 CASES VERTICALES\\\\\
		 if(source == attaqueVerticaleButton) {
			 if(getColonneAttaque() < 0 || getLigneAttaque() < 1 || getColonneAttaque() >= 10 || getLigneAttaque() >= 9){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 Attaque attaque = new AttaqueVerticale(getLigneAttaque(), getColonneAttaque());
			 controller.ordiEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 5);
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
			affiche(e.getMessage());
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
			affiche(e.getMessage());
		}
		return result;
	}
	
	public String getOrientation() {
		String result = "";
		try {
			result = (orientationBateau.getText()).toUpperCase();
		}
		catch(StringIndexOutOfBoundsException e) {
			affiche(e.getMessage());
		}
		return result;
	}
}
