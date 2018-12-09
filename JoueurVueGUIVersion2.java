
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class JoueurVueGUIVersion2 extends JoueurVue implements ActionListener{
	
	private JButton attaqueButton = new JButton("Attaquer");
	private JButton attaqueHorizontaleButton = new JButton("Attaque Horizontale");
	private JButton attaqueVerticaleButton = new JButton("Attaque Verticale");
	private JButton placerBateauButton = new JButton("Placer Bateau");
	private JButton finTourButton = new JButton("Fin du tour");
	private JButton aideButton = new JButton("Besoin d'aide ?");
	
	private JPanel jpanelJoueur;
	private JPanel jpanelOrdi;
	private JPanel grilleJoueur;
	private JPanel grilleOrdi;
	private JPanel commandes;
	private JPanel commandesInputs;
	private JPanel commandesLigne;
	private JPanel commandesColonne;
	private JPanel commandesOrientation;
	private JPanel commandesTaille;
	private JPanel global;
	
	private JButton mesBoutonsJoueur[][] = new JButton[11][11];
	private JButton mesBoutonsOrdi[][] = new JButton[11][11];
	
	
	private JFrame framePlateau = new JFrame();
	private JFrame framePlateauOrdi = new JFrame();
	private JFrame frameAide = new JFrame();
	
	private JLabel message = new JLabel("");
	private JLabel labelArgent = new JLabel("Il vous reste : " + model.getArgent() + " pièces pour ce tour !");
	private JLabel labelLigne = new JLabel(" Ligne de votre attaque ");
	private JLabel labelColonne = new JLabel(" Colonne de votre attaque ");
	private JLabel labelOrientation = new JLabel(" Orientation de votre bateau ");
	private JLabel labelTaille = new JLabel("Choisissez une taille : ");
	private JLabel labelAide = new JLabel("<html>Voici comment se déroule une parite de notre BATAILLE NAVALE :<br><br> "
										+ "1)Cliquez sur Placer bateaux et placer vos différent bateaux;<br>	"
										+ "    Pour se faire cliquer dans la grille sur une case et les champs colonne et ligne de votre bateau se rempliront tous seuls,<br>"
										+ "    Il ne vous reste plus qu'à choisir l'orientation et la taille du bateau;<br><br>"
										+ "2)Attaquez votre adversaire<br>"
										+ "    Pour se faire cliquer dans la grille sur une case et les champs colonne et ligne de votre attaque se rempliront tous seuls,<br>"
										+ "    Ensuite sélectionnez quelle type d'attaque vous voulez infiliger à votre adversaire<br>"
										+ "    /!/, vous n'avez que 10 pièces par tour !<br><br>"
										+ "3)Fin de tour<br>"
										+ "    Quand vous avez fini votre tour, cliquez sur fin du tour et votre adversaire pourra ainsi jouer à son tour!</html>");

	private JTextField ligneAttaque = new JTextField(1);
	private JTextField colonneAttaque = new JTextField(1);
	
	private ButtonGroup bgTaille = new ButtonGroup();
	private ButtonGroup bgOrientaion = new ButtonGroup();
	
	private JRadioButton tailleDeux = new JRadioButton("2");
	private JRadioButton tailleTrois = new JRadioButton("3");
	private JRadioButton tailleQuatre = new JRadioButton("4");
	private JRadioButton tailleCinq = new JRadioButton("5");
	private JRadioButton orientationH = new JRadioButton("H");
	private JRadioButton orientationV = new JRadioButton("V");
	
	boolean bateauxPlacés = false;

	public JoueurVueGUIVersion2(Joueur model, JoueurControl controller, int posX, int posY) {
		
		super(model, controller);
		
		int choixModeJeux =  JOptionPane.showConfirmDialog(null,"A quelle mode de BATAILLE NAVALE voullez vous jouer ?", "Bonjour", JOptionPane.YES_NO_OPTION);
		
		bgTaille.add(tailleDeux);
		bgTaille.add(tailleTrois);
		bgTaille.add(tailleQuatre);
		bgTaille.add(tailleCinq);
		
		bgOrientaion.add(orientationH);
		bgOrientaion.add(orientationV);
		
		tailleDeux.setActionCommand("2");
		tailleTrois.setActionCommand("3");
		tailleQuatre.setActionCommand("4");
		tailleCinq.setActionCommand("5");
		orientationH.setActionCommand("H");
		orientationV.setActionCommand("V");
		
		labelArgent.setForeground(Color.blue);
		
		jpanelJoueur = new JPanel();
		jpanelOrdi = new JPanel();
		commandes = new JPanel();
		commandesInputs = new JPanel();
		commandesLigne = new JPanel();
		commandesColonne = new JPanel();
		commandesOrientation = new JPanel();
		commandesTaille = new JPanel();
		global = new JPanel();
		
		attaqueButton.addActionListener(this);
		attaqueHorizontaleButton.addActionListener(this);
		attaqueVerticaleButton.addActionListener(this);
		placerBateauButton.addActionListener(this);
		finTourButton.addActionListener(this);
		aideButton.addActionListener(this);
		
		commandesLigne.setLayout(new BoxLayout(commandesLigne, BoxLayout.X_AXIS));
		commandesColonne.setLayout(new BoxLayout(commandesColonne, BoxLayout.X_AXIS));
		commandesOrientation.setLayout(new BoxLayout(commandesOrientation, BoxLayout.X_AXIS));
		commandesTaille.setLayout(new BoxLayout(commandesTaille, BoxLayout.X_AXIS));
		commandesInputs.setLayout(new BoxLayout(commandesInputs, BoxLayout.Y_AXIS));
		commandes.setLayout(new BoxLayout(commandes, BoxLayout.X_AXIS));
		global.setLayout(new BoxLayout(global, BoxLayout.Y_AXIS));
		
		commandesOrientation.add(labelOrientation);
		commandesOrientation.add(orientationH);
		commandesOrientation.add(orientationV);
		commandesOrientation.setVisible(false);
		
		commandesLigne.add(labelLigne);
		commandesLigne.add(ligneAttaque);
		
		commandesColonne.add(labelColonne);
		commandesColonne.add(colonneAttaque);
		
		commandesTaille.add(labelTaille);
		commandesTaille.add(tailleDeux);
		commandesTaille.add(tailleTrois);
		commandesTaille.add(tailleQuatre);
		commandesTaille.add(tailleCinq);
		commandesTaille.setVisible(false);
		
		commandesInputs.add(commandesLigne);
		commandesInputs.add(commandesColonne);
		commandesInputs.add(commandesOrientation);
		commandesInputs.add(commandesTaille);
		
		commandes.add(attaqueButton);
		commandes.add(attaqueHorizontaleButton);
		commandes.add(attaqueVerticaleButton);
		commandes.add(placerBateauButton);
		
		global.add(message);
		global.add(labelArgent);
		global.add(commandesInputs);
		global.add(commandes);
		global.add(finTourButton);
		
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
		 labelArgent .setText("Il vous reste : " + controller.getArgent() + " pièces pour ce tour !");
		 if(controller.joueurAPlacerBateaux()) {
			placerBateauButton.setVisible(false); 
			commandesTaille.setVisible(false);
			commandesOrientation.setVisible(false);
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
	                	mesBoutonsJoueur[ligne][colonne].setBackground(Color.MAGENTA);
	                	mesBoutonsJoueur[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
	                	
	                	mesBoutonsOrdi[ligne][colonne].setText(""+numeroLignes);
	                	mesBoutonsOrdi[ligne][colonne].setBackground(Color.MAGENTA);
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
	                	
	                	mesBoutonsJoueur[ligne][colonne].setActionCommand((ligne-1) + " " + (colonne-1));
						mesBoutonsJoueur[ligne][colonne].addActionListener(this);
	                	
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
	                	mesBoutonsOrdi[ligne][colonne].setActionCommand((ligne-1) + " " + (colonne-1));
	                	mesBoutonsOrdi[ligne][colonne].addActionListener(this);
	                }
	             
	                grilleJoueur.add(mesBoutonsJoueur[ligne][colonne]); 
	                grilleOrdi.add(mesBoutonsOrdi[ligne][colonne]);
	            }
	        }
	        if( model.caseDeBateauxOrdi.size() != 0) {
	        	if(model.joueurAGagne()) {
	        		JOptionPane.showInputDialog("C'est GAGNE !!!");
	        	}
	        	if(model.ordiAGagne()) {
	        		JOptionPane.showInputDialog("AIE AIE AIE, c'est une DEFAITE !!!");
	        	}
	        }
	        
	        	//MISE EN PLACE DES GRILLES
	        jpanelJoueur.add(aideButton, BorderLayout.NORTH);
	        jpanelJoueur.add(grilleJoueur);
	        jpanelJoueur.add(global,BorderLayout.SOUTH);
	        jpanelOrdi.add(grilleOrdi);
	        	//TITRE DE LA JFRAME
	        framePlateau.setTitle("Plateau du Joueur");
	        framePlateauOrdi.setTitle("Plateau de l'Ordi");
	        	//AJOUT DU CONTENUE DANS LA JFRAME
	        framePlateau.setContentPane(jpanelJoueur);
	        framePlateauOrdi.setContentPane(jpanelOrdi);
	        	//PACK TOUTE LA JFRAME
	        framePlateau.pack();
	        framePlateauOrdi.pack();
	        	//DEFINITION TAILLE OPTIMALE
	        framePlateau.setSize(500, 600);
	        framePlateauOrdi.setSize(500, 500);
	        	//LOCALISATION D'APPARITION
	        framePlateau.setLocation(100, 100);
	        framePlateauOrdi.setLocation(900, 100);
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
				for(int compteur = model.getbateauAPlacer(); compteur>0 ; compteur --) {
					 commandesOrientation.setVisible(true);
					 commandesTaille.setVisible(true);
					 labelLigne.setText(" Ligne de votre Bateau");
					 labelColonne.setText(" Colonne de votre Bateau");
					 labelArgent.setText("Il vous reste " + model.getbateauAPlacer() + " bateaux a placer");
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
				                	if(model.getPlateau().getPlateau()[ligne-1][colonne-1].estOccupee()) {
				                		mesBoutonsJoueur[ligne][colonne].setBackground(Color.GREEN);
				                	}
				                	else {
				                		mesBoutonsJoueur[ligne][colonne].setBackground(Color.GRAY);
				                	}
				                }
				                mesBoutonsJoueur[ligne][colonne].setActionCommand((ligne-1) + " " + (colonne-1));
				                mesBoutonsJoueur[ligne][colonne].addActionListener(this);
				                grilleJoueur.add(mesBoutonsJoueur[ligne][colonne]); 
				            }
				       }
				        
				        if( getColonneAttaque() < 0 || getLigneAttaque() < 0 || getColonneAttaque() >= 10 || getLigneAttaque() >= 10){
							 affiche("Erreur, ceci n'est pas une coordonée valide "); 
							 return;
						 }
				        
				        if(getOrientation().equals("H")) {
				        	if(getTaille() + getColonneAttaque() >= 10) {
				        		affiche("Impossible de placer ce bateau !");
				        		return;
				        	}
				        }
				        if(getOrientation().equals("V")) {
				        	if(getTaille() + getLigneAttaque() >= 10) {
				        		affiche("Impossible de placer ce bateau !");
				        		return;
				        	}
				        }
				        
				        
				        
				        if(getOrientation().isEmpty()) {
				        	affiche("Pas d'orientation encodée");
				        	return;
				        }
				        if(getTaille() == 0) {
				        	affiche("Pas de taille encodée");
				        	return;
				        }
				        if(!getOrientation().equals(" ") ) {
					        Bateau bateau = new Bateau(getLigneAttaque(), getColonneAttaque(),getTaille(), getOrientation());
					        bgTaille.getSelection().setEnabled(false);
					        controller.joueurPlacerBateau(bateau);
					        model.bateauAPlacerMoins1();
					        ligneAttaque.setText("");
					        colonneAttaque.setText("");
					        update(null,null);
				        }
				}
				if(model.getbateauAPlacer() <= 0) {
					 controller.setJoueurAPlacerBateaux(true);
					 labelLigne.setText(" Ligne de votre attaque");
					 labelColonne.setText(" Colonne de votre attaque");
					 labelArgent.setText("Il vous reste : " + model.getArgent() + " pièces pour ce tour !");
					 if(!controller.ordiAPlacerBateaux()) {
						//controller.ordiPlacerBateau(new Bateau(aleatoire(0, 6),aleatoire(0, 6),aleatoire(2, 5),"H"));
						// controller.ordiPlacerBateau(new Bateau(aleatoire(4, 9),aleatoire(4, 6),aleatoire(2, 4),"V"));
						 controller.ordiPlacerBateau(new Bateau(0,0,5,"H"));
						 controller.ordiPlacerBateau(new Bateau(1,0,5,"V"));
						 controller.ordiPlacerBateau(new Bateau(5,5,2,"H"));
						 controller.ordiPlacerBateau(new Bateau(6,6,2,"V"));
						 update(null,null);
					 }
				}
				update(null,null);
				return;
		 }
		 
		 
		 /////ATTAQUE 1 CASE\\\\\
		 if(source == attaqueButton) {
			 if(getColonneAttaque() < 0 || getLigneAttaque() < 0 || getColonneAttaque() >= 10 || getLigneAttaque() >= 10){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 if(controller.getArgent() < 3) {
				 affiche("Vous n'avez plus suffisament d'argent !!"); 
				 return;
			 }
			 Attaque attaque = new Attaque(getLigneAttaque(), getColonneAttaque());
			 controller.ordiEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 3);
			 update(null,null);
			 return;
		 }
		 
		/////ATTAQUE 3 CASES HORIZONTALES\\\\\
		 if(source == attaqueHorizontaleButton) {
			 if(getColonneAttaque() <1 || getLigneAttaque() < 0 || getColonneAttaque() >= 9 || getLigneAttaque() >= 10){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 if(controller.getArgent() < 5) {
				 affiche("Vous n'avez plus suffisament d'argent !!"); 
				 return;
			 }
			 Attaque attaque = new AttaqueHorizontale(getLigneAttaque(), getColonneAttaque());
			 controller.ordiEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 5);
			 update(null,null);
			 return;
		 }
		 
		/////ATTAQUE 3 CASES VERTICALES\\\\\
		 if(source == attaqueVerticaleButton) {
			 if(getColonneAttaque() < 0 || getLigneAttaque() < 1 || getColonneAttaque() >= 10 || getLigneAttaque() >= 9){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 if(controller.getArgent() < 5) {
				 affiche("Vous n'avez plus suffisament d'argent !!"); 
				 return;
			 }
			 Attaque attaque = new AttaqueVerticale(getLigneAttaque(), getColonneAttaque());
			 controller.ordiEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 5);
			 update(null,null);
			 return;
		 }
		 
		/////FIN DE TOUR\\\\\
		 if(source == finTourButton) {
			 int nbrAleatoire = aleatoire(0, 3);
				if(nbrAleatoire == 0) {
					controller.joueurEstAttaque(new Attaque(aleatoire(0, 10),aleatoire(0, 10)));
				}
				else{
					if(nbrAleatoire == 1) {
						controller.joueurEstAttaque(new AttaqueHorizontale(aleatoire(0, 10),aleatoire(1, 9)));
					}
					else{
						controller.joueurEstAttaque(new AttaqueVerticale(aleatoire(1, 9),aleatoire(0, 10)));
					}
				}
				controller.setArgent(10);
				update(null,null);
				return;
		 }
		 
		/////AIDE\\\\\
		 if(source == aideButton) {
				frameAide.setTitle("Aide");
				frameAide.add(labelAide);
				frameAide.setSize(600,400);
				frameAide.setLocation(300, 300);
				frameAide.setAlwaysOnTop(true);
				frameAide.setVisible(true);
		 }
				 
		else {
			JButton test = (JButton) source;
			ligneAttaque.setText(test.getActionCommand().substring(0,1));
			colonneAttaque.setText(test.getActionCommand().substring(2,3));
			return;
		}
		 
		 update(null,null);
		
	}
	
	/*
	 * Return le nombre du premier JTextField
	 */
	public int getLigneAttaque() {
		int result = -1;
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
		int result = -1;
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
			result = bgOrientaion.getSelection().getActionCommand();
		}
		catch(Exception e) {
			result = " ";
		}
		return result;
	}
	
	public int getTaille() {
		int result = 0;
		try {
			result = Integer.parseInt(bgTaille.getSelection().getActionCommand());
		}
		catch(Exception e) {
			result = 0;
		}
		return result;
	}
	
	public int aleatoire(int min, int max) {
		Random r = new Random();
		int valeur = min + r.nextInt(max - min);
		return valeur;
	}
}
