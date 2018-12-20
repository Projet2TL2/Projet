package Vue;

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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.JoueurControl;
import Controller.Network;
import model.Attaque;
import model.AttaqueHorizontale;
import model.AttaqueVerticale;
import model.Bateau;
import model.Joueur;


public class JoueurVueGUIVersionReseau extends JoueurVue implements ActionListener, Runnable{
	
	/////DECLARATIONS DES JPANNELS DES 2 JOUEURS\\\\\
	private JPanel jpanelJoueur;
	private JPanel jpanelOrdi;
	private JPanel grilleJoueur;
	private JPanel grilleOrdi;
	private JPanel commandes, commandesJ2;
	private JPanel commandesInputs, commandesInputsJ2;
	private JPanel commandesLigne, commandesLigneJ2;
	private JPanel commandesColonne, commandesColonneJ2;
	private JPanel commandesOrientation, commandesOrientationJ2;
	private JPanel commandesTaille, commandesTailleJ2;
	private JPanel global, globalJ2;
	
	/////DECLARATION JBUTTONS DES 2 JOUEURS\\\\\
	private JButton attaqueButton = new JButton("Attaque Simple (3)");
	private JButton attaqueHorizontaleButton = new JButton("Attaque Horizontale (5)");
	private JButton attaqueVerticaleButton = new JButton("Attaque Verticale (5)");
	private JButton placerBateauButton = new JButton("Placer Bateau");
	private JButton finTourButton = new JButton("Fin du tour");
	private JButton aideButton = new JButton("Besoin d'aide ?");
	private JButton attaqueButtonJ2 = new JButton("Attaquer");
	private JButton attaqueHorizontaleButtonJ2 = new JButton("Attaque Horizontale");
	private JButton attaqueVerticaleButtonJ2 = new JButton("Attaque Verticale");
	private JButton placerBateauButtonJ2 = new JButton("Placer Bateau");
	private JButton finTourButtonJ2 = new JButton("Fin du tour");
	private JButton aideButtonJ2 = new JButton("Besoin d'aide ?");
	private JButton rafraichirButton = new JButton("Raffraichir");
	
	/////DECLARATIONS GRILLE DES 2 JOUEURS\\\\\
	private JButton mesBoutonsJoueur[][] = new JButton[11][11];
	private JButton mesBoutonsOrdi[][] = new JButton[11][11];
	
	/////DECLARATIONS DES JFRAMES DES 2 JOUEURS + AIDE\\\\\
	private JFrame framePlateau = new JFrame();
	private JFrame framePlateauOrdi = new JFrame();
	private JFrame frameAide = new JFrame();
	
	/////DECLARATIONS DES JLABEL DES 2 JOUEURS\\\\\
	private JLabel message = new JLabel("");
	private JLabel labelArgent = new JLabel("Il vous reste : " + model.getArgent() + " pièces pour ce tour !");
	private JLabel labelLigne = new JLabel(" Ligne de votre attaque ");
	private JLabel labelColonne = new JLabel(" Colonne de votre attaque ");
	private JLabel labelOrientation = new JLabel(" Orientation de votre bateau ");
	private JLabel labelTaille = new JLabel("Choisissez une taille : ");
	private JLabel messageJ2 = new JLabel("");
	private JLabel labelArgentJ2 = new JLabel("Il vous reste : " + model.getArgent() + " pièces pour ce tour !");
	private JLabel labelLigneJ2 = new JLabel(" Ligne de votre attaque ");
	private JLabel labelColonneJ2 = new JLabel(" Colonne de votre attaque ");
	private JLabel labelOrientationJ2 = new JLabel(" Orientation de votre bateau ");
	private JLabel labelTailleJ2 = new JLabel("Choisissez une taille : ");
	
	/////DECLARATION DU TEXTE DE LA JFRAME AIDEE\\\\\
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

	/////DECLARATIONS DES JTEXTFIELDS DES 2 JOUEURS\\\\\
	private JTextField ligneAttaque = new JTextField(1);
	private JTextField colonneAttaque = new JTextField(1);
	private JTextField ligneAttaqueJ2 = new JTextField(1);
	private JTextField colonneAttaqueJ2 = new JTextField(1);
	
	/////DECLARATIONS DES BUTTONGROUP DES 2 JOUEURS\\\\\
	private ButtonGroup bgTaille = new ButtonGroup();
	private ButtonGroup bgOrientaion = new ButtonGroup();
	private ButtonGroup bgTailleJ2 = new ButtonGroup();
	private ButtonGroup bgOrientaionJ2 = new ButtonGroup();
	
	/////DECLARATIONS DES JRADIOBUTTONS DES 2 JOUEURS\\\\\
	private JRadioButton tailleDeux = new JRadioButton("2");
	private JRadioButton tailleTrois = new JRadioButton("3");
	private JRadioButton tailleQuatre = new JRadioButton("4");
	private JRadioButton tailleCinq = new JRadioButton("5");
	private JRadioButton orientationH = new JRadioButton("H");
	private JRadioButton orientationV = new JRadioButton("V");
	private JRadioButton tailleDeuxJ2 = new JRadioButton("2");
	private JRadioButton tailleTroisJ2 = new JRadioButton("3");
	private JRadioButton tailleQuatreJ2 = new JRadioButton("4");
	private JRadioButton tailleCinqJ2 = new JRadioButton("5");
	private JRadioButton orientationHJ2 = new JRadioButton("H");
	private JRadioButton orientationVJ2 = new JRadioButton("V");
	
	/////DECLARATIONS VARIABLES UTILES\\\\\
	boolean bateauxPlacés = false;
	private int choixModeJeux;
	int tour = -1;
	boolean isServer;
	Network test;
	
	public JoueurVueGUIVersionReseau(Joueur model, JoueurControl controller, boolean boo) {
		
		super(model, controller);
		
		//this.test = test;
		
		////CHOIX MODE DE JEUX\\\\\
		String modeJeux = choixModeJeux();
		if(modeJeux.equals("Jouer contre l'ordinateur")) {
			choixModeJeux = 0;
		}
		if(modeJeux.equals("Jouer contre un autre joueur sur ce PC")) {
			choixModeJeux = 1;
		}
		if(modeJeux.equals("Jouer contre un autre joueur en réseau")) {
			choixModeJeux = 2;
			test = new Network(model, boo);
		}	
		
		isServer = boo;
		tour = boo==true? 0 : 1;
		
		/////INITIALISATION DES JBUTTONGROUPS DES 2 JOUEURS\\\\\
		bgTailleJ2.add(tailleDeuxJ2);
		bgTailleJ2.add(tailleTroisJ2);
		bgTailleJ2.add(tailleQuatreJ2);
		bgTailleJ2.add(tailleCinqJ2);
		bgTaille.add(tailleDeux);
		bgTaille.add(tailleTrois);
		bgTaille.add(tailleQuatre);
		bgTaille.add(tailleCinq);
		bgOrientaionJ2.add(orientationHJ2);
		bgOrientaionJ2.add(orientationVJ2);
		bgOrientaion.add(orientationH);
		bgOrientaion.add(orientationV);
		
		/////INITIALISATION DES ACTIONCOMMAND DES JRADIOBUTTONS DES 2 JOUEURS\\\\\
		tailleDeux.setActionCommand("2");
		tailleTrois.setActionCommand("3");
		tailleQuatre.setActionCommand("4");
		tailleCinq.setActionCommand("5");
		orientationH.setActionCommand("H");
		orientationV.setActionCommand("V");
		tailleDeuxJ2.setActionCommand("2");
		tailleTroisJ2.setActionCommand("3");
		tailleQuatreJ2.setActionCommand("4");
		tailleCinqJ2.setActionCommand("5");
		orientationHJ2.setActionCommand("H");
		orientationVJ2.setActionCommand("V");
		
		/////CHANGEMENT DE COULEUR DU LABEL ARGENT DES 2 JOUEURS\\\\\
		labelArgent.setForeground(Color.blue);
		labelArgentJ2.setForeground(Color.blue);
		
		/////INITIALISATION DES JPANEL DES 2 JOUEURS\\\\\
		jpanelJoueur = new JPanel();
		jpanelOrdi = new JPanel();
		commandes = new JPanel();
		commandesInputs = new JPanel();
		commandesLigne = new JPanel();
		commandesColonne = new JPanel();
		commandesOrientation = new JPanel();
		commandesTaille = new JPanel();
		global = new JPanel();
		jpanelOrdi = new JPanel();
		commandesJ2 = new JPanel();
		commandesInputsJ2 = new JPanel();
		commandesLigneJ2 = new JPanel();
		commandesColonneJ2 = new JPanel();
		commandesOrientationJ2 = new JPanel();
		commandesTailleJ2 = new JPanel();
		globalJ2 = new JPanel();
		
		/////AJOUT DE LISTENER AUX JBUTTONS DES 2 JOUEURS\\\\\
		attaqueButton.addActionListener(this);
		attaqueHorizontaleButton.addActionListener(this);
		attaqueVerticaleButton.addActionListener(this);
		placerBateauButton.addActionListener(this);
		finTourButton.addActionListener(this);
		aideButton.addActionListener(this);
		attaqueButtonJ2.addActionListener(this);
		attaqueHorizontaleButtonJ2.addActionListener(this);
		attaqueVerticaleButtonJ2.addActionListener(this);
		placerBateauButtonJ2.addActionListener(this);
		finTourButtonJ2.addActionListener(this);
		aideButtonJ2.addActionListener(this);
		rafraichirButton.addActionListener(this);
		
		/////INITIALISATION DES LAYOUT DES 2 JOUEURS\\\\\
		commandesLigne.setLayout(new BoxLayout(commandesLigne, BoxLayout.X_AXIS));
		commandesColonne.setLayout(new BoxLayout(commandesColonne, BoxLayout.X_AXIS));
		commandesOrientation.setLayout(new BoxLayout(commandesOrientation, BoxLayout.X_AXIS));
		commandesTaille.setLayout(new BoxLayout(commandesTaille, BoxLayout.X_AXIS));
		commandesInputs.setLayout(new BoxLayout(commandesInputs, BoxLayout.Y_AXIS));
		commandes.setLayout(new BoxLayout(commandes, BoxLayout.X_AXIS));
		global.setLayout(new BoxLayout(global, BoxLayout.Y_AXIS));
		commandesLigneJ2.setLayout(new BoxLayout(commandesLigneJ2, BoxLayout.X_AXIS));
		commandesColonneJ2.setLayout(new BoxLayout(commandesColonneJ2, BoxLayout.X_AXIS));
		commandesOrientationJ2.setLayout(new BoxLayout(commandesOrientationJ2, BoxLayout.X_AXIS));
		commandesTailleJ2.setLayout(new BoxLayout(commandesTailleJ2, BoxLayout.X_AXIS));
		commandesInputsJ2.setLayout(new BoxLayout(commandesInputsJ2, BoxLayout.Y_AXIS));
		commandesJ2.setLayout(new BoxLayout(commandesJ2, BoxLayout.X_AXIS));
		globalJ2.setLayout(new BoxLayout(globalJ2, BoxLayout.Y_AXIS));
		
		/////CONSTRUCTION DU JPANEL GLOBAL DU JOUEUR 1\\\\\
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
		commandes.add(rafraichirButton);
		
		global.add(message);
		global.add(labelArgent);
		global.add(commandesInputs);
		global.add(commandes);
		global.add(finTourButton);
		
		/////CONSTRUCTION DU JPANEL GLOBAL DU JOUEUR 2\\\\\
		commandesOrientationJ2.add(labelOrientationJ2);
		commandesOrientationJ2.add(orientationHJ2);
		commandesOrientationJ2.add(orientationVJ2);
		commandesOrientationJ2.setVisible(false);
		
		commandesLigneJ2.add(labelLigneJ2);
		commandesLigneJ2.add(ligneAttaqueJ2);
		
		commandesColonneJ2.add(labelColonneJ2);
		commandesColonneJ2.add(colonneAttaqueJ2);
		
		commandesTailleJ2.add(labelTailleJ2);
		commandesTailleJ2.add(tailleDeuxJ2);
		commandesTailleJ2.add(tailleTroisJ2);
		commandesTailleJ2.add(tailleQuatreJ2);
		commandesTailleJ2.add(tailleCinqJ2);
		commandesTailleJ2.setVisible(false);
		
		commandesInputsJ2.add(commandesLigneJ2);
		commandesInputsJ2.add(commandesColonneJ2);
		commandesInputsJ2.add(commandesOrientationJ2);
		commandesInputsJ2.add(commandesTailleJ2);
		
		commandesJ2.add(attaqueButtonJ2);
		commandesJ2.add(attaqueHorizontaleButtonJ2);
		commandesJ2.add(attaqueVerticaleButtonJ2);
		commandesJ2.add(placerBateauButtonJ2);
		
		globalJ2.add(messageJ2);
		globalJ2.add(labelArgentJ2);
		globalJ2.add(commandesInputsJ2);
		globalJ2.add(commandesJ2);
		globalJ2.add(finTourButtonJ2);
		
		/////CONSTRUCTION DES JFRAMES DES 2 JOUEURS\\\\\
		jpanelJoueur.setLayout(new BorderLayout());
		jpanelOrdi.setLayout(new BorderLayout());
		
		grilleJoueur = new JPanel();
		grilleOrdi = new JPanel();
		
        grilleJoueur.setLayout(new GridLayout(11,11));
        grilleOrdi.setLayout(new GridLayout(11, 11));
		
		updateTable();

    	//TITRE DE LA JFRAME
		framePlateau.setTitle("Plateau du Joueur");
		
		/////CONSTRUCTION DES JFRAMES EN FONCTION DU MODE DE JEU CHOISI\\\\\
		
		switch(choixModeJeux) {
			
			case 0 : 
					framePlateauOrdi.setTitle("Plateau de l'Ordi");
					break;
		
			/////DANS CE CAS_CI PLATEAU ORDI REPRESENTE LE PLATEAU DU JOUEUR 2\\\\\
			case 1 :
					framePlateauOrdi.setTitle("Plateau du Joueur 2");
					break;
			/////DANS CE CAS_CI ON JOUE EN RESEAU\\\\\		
			case 2 :
					framePlateauOrdi.setTitle("Plateau du Joueur 2");
					if(boo == false) {
						framePlateau.setTitle("Plateau du client");
					}
					break;
		
		
					
		}
		
		
		framePlateau.setVisible(true);
		framePlateauOrdi.setVisible(true);
		framePlateau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePlateauOrdi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		aideButton.setBackground(Color.GREEN);
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
		
		tour = test.getTour();
		
		
		 /////REINITIALISATION DES GRILLES\\\\\
		 grilleJoueur.removeAll();
		 grilleOrdi.removeAll();
		 labelArgent .setText("Il vous reste : " + controller.getArgent() + " pièces pour ce tour !");
		 labelArgentJ2 .setText("Il vous reste : " + controller.getArgent() + " pièces pour ce tour !");
		 
		 if(controller.joueurAPlacerBateaux()) {
			placerBateauButton.setVisible(false); 
			commandesTaille.setVisible(false);
			commandesOrientation.setVisible(false);
			attaqueButton.setVisible(true);
			attaqueHorizontaleButton.setVisible(true);
			attaqueVerticaleButton.setVisible(true);
			finTourButton.setVisible(true);
		 }
		 
		 if(controller.ordiAPlacerBateaux()) {
			placerBateauButtonJ2.setVisible(false); 
			commandesTailleJ2.setVisible(false);
			commandesOrientationJ2.setVisible(false);
			attaqueButtonJ2.setVisible(true);
			attaqueHorizontaleButtonJ2.setVisible(true);
			attaqueVerticaleButtonJ2.setVisible(true);
			finTourButtonJ2.setVisible(true);
		}
		 
		 /////TOUR DU JOUEUR 1\\\\\
		 if(tour == 0) {
			attaqueButtonJ2.setEnabled(false);
			attaqueHorizontaleButtonJ2.setEnabled(false);
			attaqueVerticaleButtonJ2.setEnabled(false);
			finTourButtonJ2.setEnabled(false);
			
			attaqueButton.setEnabled(true);
			attaqueHorizontaleButton.setEnabled(true);
			attaqueVerticaleButton.setEnabled(true);
			finTourButton.setEnabled(true);
		 }
		 
		/////TOUR DU JOUEUR 2\\\\\
		 if(tour == 1) {
			attaqueButton.setEnabled(false);
			attaqueHorizontaleButton.setEnabled(false);
			attaqueVerticaleButton.setEnabled(false);
			 finTourButton.setEnabled(false);
			
			attaqueButtonJ2.setEnabled(true);
			attaqueHorizontaleButtonJ2.setEnabled(true);
			attaqueVerticaleButtonJ2.setEnabled(true);
			finTourButtonJ2.setEnabled(true);
		 }
		
		 /////DEBUT DE PARTIE\\\\\
		 if (tour == -1) {
			attaqueButton.setEnabled(false);
			attaqueHorizontaleButton.setEnabled(false);
			attaqueVerticaleButton.setEnabled(false);
			finTourButton.setEnabled(false);
			attaqueButtonJ2.setEnabled(false);
			attaqueHorizontaleButtonJ2.setEnabled(false);
			attaqueVerticaleButtonJ2.setEnabled(false);
			finTourButtonJ2.setEnabled(false);
			//tour = 0;
		 }
		 
		 if(choixModeJeux == 2) {
			 if(controller.joueurAPlacerBateaux()) {
				 if(tour == 0) {
					 placerBateauButton.setVisible(false);
					 attaqueButton.setEnabled(true);
					 attaqueHorizontaleButton.setEnabled(true);
					 attaqueVerticaleButton.setEnabled(true);
					 finTourButton.setEnabled(true);
				 }
				 else {
					 placerBateauButton.setVisible(false);
					 attaqueButton.setEnabled(false);
					 attaqueHorizontaleButton.setEnabled(false);
					 attaqueVerticaleButton.setEnabled(false);
					 finTourButton.setEnabled(false);
				 }
			 }
			 else {
				 if(tour == 0) {
					 placerBateauButton.setEnabled(true);
					 placerBateauButton.setVisible(true);
				 }
				 else {
					 placerBateauButton.setEnabled(false);
					 placerBateauButton.setVisible(true);
				 }
				attaqueButton.setEnabled(false);
				attaqueHorizontaleButton.setEnabled(false);
				attaqueVerticaleButton.setEnabled(false);
				finTourButton.setEnabled(false);
			 }
		 }
		 
		 /*
		 if(test.getTour() == 0) {
			 placerBateauButton.setVisible(true);
			 attaqueButton.setEnabled(true);
			 attaqueHorizontaleButton.setEnabled(true);
			 attaqueVerticaleButton.setEnabled(true);
			 finTourButton.setEnabled(true);
		 }
		 */
		 
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
	    						mesBoutonsOrdi[ligne][colonne].setBackground(Color.CYAN);
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
	        
	        if( model.getCaseDeBateauxOrdi().size() != 0 && model.getCaseDeBateauxJoueur().size() != 0 && choixModeJeux!=2) {
	        	if(model.joueurAGagne()) {
	        		JOptionPane.showInputDialog("AIE AIE AIE, le joueur 1 a gagné !!!");
	        		//framePlateau.setEnabled(false);
	        		//framePlateauOrdi.setEnabled(false);
	        	}
	        	if(model.ordiAGagne()) {
	        		if(choixModeJeux == 0) {
	        			JOptionPane.showInputDialog("AIE AIE AIE, l'ordinateur a gagné !!!");
	        		}
	        		else {
	        			JOptionPane.showInputDialog("AIE AIE AIE, le joueur 2 a gagné !!!");
	        		}
	        		//framePlateau.setEnabled(false);
	        		//framePlateauOrdi.setEnabled(false);
	        	}
	        }
	        
	        if( model.getCaseDeBateauxOrdi().size() != 0 && model.getCaseDeBateauxJoueur().size() != 0 && choixModeJeux==2) {
	        	if(model.joueurAGagne() && isServer == true) {
	        		JOptionPane.showInputDialog("AIE AIE AIE, le joueur serveur a gagné !!!");
	        	}
	        	if(model.joueurAGagne() && isServer == false) {
	        		JOptionPane.showInputDialog("AIE AIE AIE, le joueur client a gagné !!!");
	        	}
	        	
	        }
	        
	        	//MISE EN PLACE DES GRILLES
	        jpanelJoueur.add(aideButton, BorderLayout.NORTH);
	        jpanelJoueur.add(grilleJoueur);
	        jpanelJoueur.add(global,BorderLayout.SOUTH);
	        
	        if(choixModeJeux == 0 || choixModeJeux == 2) {
	        	jpanelOrdi.add(grilleOrdi); 
	        	framePlateauOrdi.setContentPane(jpanelOrdi);
	        	framePlateauOrdi.pack();
		        framePlateauOrdi.setSize(600,500);
	        }
	        else {
	        	jpanelOrdi.add(aideButtonJ2, BorderLayout.NORTH);
	        	jpanelOrdi.add(grilleOrdi);
	        	jpanelOrdi.add(globalJ2,BorderLayout.SOUTH);
	        	framePlateauOrdi.setContentPane(jpanelOrdi);
	        	framePlateauOrdi.pack();
		        framePlateauOrdi.setSize(600,600);
	        }
	        	//AJOUT DU CONTENUE DANS LA JFRAME
	        framePlateau.setContentPane(jpanelJoueur);
	        
	        	//PACK TOUTE LA JFRAME
	        framePlateau.pack();
	        	//DEFINITION TAILLE OPTIMALE
	        framePlateau.setSize(600, 600);
	        	//LOCALISATION D'APPARITION
	        //framePlateau.setLocation(100, 100);
	        //framePlateauOrdi.setLocation(900, 100);
	        
	        message.setText(test.getMessage());
	}
	
	
	/*
	 * Lors d'un changement dans le controller, actualise l'affichage
	 * si le joueur n'a pas encore placé ses bateaux, les bouttons d'attaque ne sont pas visibles
	 */
	public void update(Observable o, Object arg) {
		if(!controller.joueurAPlacerBateaux() && isServer==false) {
			if(test.serveurAPlacerTousSesBateaux()) {
				tour = 0;
			}
		}
		
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
		 /////PLACEMENT BATEAUX JOUEUR 1\\\\\
		 if(source == placerBateauButton) {
			 framePlateauOrdi.setEnabled(false);
				for(int compteur = model.getbateauAPlacer(); compteur>0 ; compteur --) {
					 commandesOrientation.setVisible(true);
					 commandesTaille.setVisible(true);
					 labelLigne.setText(" Ligne de votre Bateau");
					 labelColonne.setText(" Colonne de votre Bateau");
					 labelArgent.setText("Il vous reste " + model.getbateauAPlacer() + " bateaux a placer");
					 attaqueButton.setVisible(false);
					 attaqueHorizontaleButton.setVisible(false);
					 attaqueVerticaleButton.setVisible(false);
					 finTourButton.setVisible(false);
					 
					 /////CHANGEMENT DES COULEURS D'AFFICHAGE\\\\\
					 grilleJoueur.removeAll();
					 int numeroLignes = 0;
					 int numeroColonnes = 0;
				        for(int ligne=0;ligne<11;ligne++) {
				            for(int colonne=0;colonne<11;colonne++) {
				                mesBoutonsJoueur[ligne][colonne] = new JButton("");
				                
				                if(ligne==0&&colonne==0) {
				                	mesBoutonsJoueur[ligne][colonne].setBackground(Color.BLACK);
				                	mesBoutonsJoueur[ligne][colonne].setEnabled(false);
				                }
				                if(ligne==0&&colonne!=0) {
				                	mesBoutonsJoueur[ligne][colonne].setText(""+numeroColonnes);
				                	mesBoutonsJoueur[ligne][colonne].setBackground(Color.ORANGE);
				                	mesBoutonsJoueur[ligne][colonne].setForeground(Color.red);
				                	mesBoutonsJoueur[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
				                	mesBoutonsJoueur[ligne][colonne].setEnabled(false);
				                	
				                    numeroColonnes++;
				                }
				                if(colonne==0&&ligne!=0) {
				                	mesBoutonsJoueur[ligne][colonne].setText(""+numeroLignes);
				                	mesBoutonsJoueur[ligne][colonne].setBackground(Color.ORANGE);
				                	mesBoutonsJoueur[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
				                	mesBoutonsJoueur[ligne][colonne].setEnabled(false);
				                	
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
				        
				        if( getColonneAttaque(colonneAttaque) < 0 || getLigneAttaque(ligneAttaque) < 0 || getColonneAttaque(colonneAttaque) >= 10 || getLigneAttaque(ligneAttaque) >= 10){
							 affiche("Erreur, ceci n'est pas une coordonée valide "); 
							 return;
						 }
				        
				        if(getOrientation(bgOrientaion).equals("H")) {
				        	if(getTaille(bgTaille) + getColonneAttaque(colonneAttaque) >= 10) {
				        		affiche("Impossible de placer ce bateau !");
				        		return;
				        	}
				        }
				        if(getOrientation(bgOrientaion).equals("V")) {
				        	if(getTaille(bgTaille) + getLigneAttaque(ligneAttaque) >= 10) {
				        		affiche("Impossible de placer ce bateau !");
				        		return;
				        	}
				        }
				        if(getOrientation(bgOrientaion).isEmpty()) {
				        	affiche("Pas d'orientation encodée");
				        	return;
				        }
				        if(getTaille(bgTaille) == 0) {
				        	affiche("Pas de taille encodée");
				        	return;
				        }
				        if(!getOrientation(bgOrientaion).equals(" ") ) {
					        Bateau bateau = new Bateau(getLigneAttaque(ligneAttaque), getColonneAttaque(colonneAttaque),getTaille(bgTaille), getOrientation(bgOrientaion));
					        if(choixModeJeux == 2) {
								test.sendBateau(bateau);
					        }
					        
					        
					        if(controller.joueurPlacerBateau(bateau)) {
					        	model.bateauAPlacerMoins1();
					        	bgTaille.getSelection().setEnabled(false);
					        	ligneAttaque.setText("");
					        	colonneAttaque.setText("");
					        	//update(null,null);
					        }
					        if(! controller.joueurPlacerBateau(bateau)) {
					        	affiche("Un bateaux ne peut pas chevaucher un autre !");
					        }
					        update(null,null);
				        }
				}
				framePlateauOrdi.setEnabled(true);
				if(model.getbateauAPlacer() <= 0) {
					 controller.setJoueurAPlacerBateaux(true);
					 labelLigne.setText(" Ligne de votre attaque");
					 labelColonne.setText(" Colonne de votre attaque");
					 labelArgent.setText("Il vous reste : " + model.getArgent() + " pièces pour ce tour !");
					 if(!controller.ordiAPlacerBateaux() && choixModeJeux == 0) {
						controller.ordiPlacerBateau(new Bateau(aleatoire(0, 6),aleatoire(0, 5),aleatoire(2, 5),"H"));
						controller.ordiPlacerBateau(new Bateau(aleatoire(4, 9),aleatoire(4, 6),aleatoire(2, 4),"V"));
						//controller.ordiPlacerBateau(new Bateau(0,0,5,"H"));
						//controller.ordiPlacerBateau(new Bateau(1,0,5,"V"));
						//controller.ordiPlacerBateau(new Bateau(5,5,2,"H"));
						//controller.ordiPlacerBateau(new Bateau(6,6,2,"V"));
						 update(null,null);
					 }
				}
				if(choixModeJeux == 0) {
					tour = 0;
				}
				else {
					tour = 1;
					test.finDeTour();
				}
				
				update(null,null);
				return;
		 }
		 
		/////PLACEMENT BATEAUX JOUEUR 2\\\\\
				 if(source == placerBateauButtonJ2) {
					 framePlateau.setEnabled(false);
						for( int compteur = model.getbateauAPlacerOrdi() ; compteur>0 ; compteur --) {
							 commandesOrientationJ2.setVisible(true);
							 commandesTailleJ2.setVisible(true);
							 labelLigneJ2.setText(" Ligne de votre Bateau");
							 labelColonneJ2.setText(" Colonne de votre Bateau");
							 labelArgentJ2.setText("Il vous reste " + model.getbateauAPlacerOrdi() + " bateaux a placer");
							 attaqueButtonJ2.setVisible(false);
							 attaqueHorizontaleButtonJ2.setVisible(false);
							 attaqueVerticaleButtonJ2.setVisible(false);
							 finTourButtonJ2.setVisible(false);
							 
							 /////CHANGEMENT DES COULEURS D'AFFICHAGE\\\\\
							 grilleOrdi.removeAll();
							 int numeroLignes = 0;
							 int numeroColonnes = 0;
						        for(int ligne=0;ligne<11;ligne++) {
						            for(int colonne=0;colonne<11;colonne++) {
						                mesBoutonsOrdi[ligne][colonne] = new JButton("");
						                
						                if(ligne==0&&colonne==0) {
						                	mesBoutonsOrdi[ligne][colonne].setBackground(Color.BLACK);
						                	mesBoutonsOrdi[ligne][colonne].setEnabled(false);
						                }
						                if(ligne==0&&colonne!=0) {
						                	mesBoutonsOrdi[ligne][colonne].setText(""+numeroColonnes);
						                	mesBoutonsOrdi[ligne][colonne].setBackground(Color.ORANGE);
						                	mesBoutonsOrdi[ligne][colonne].setForeground(Color.red);
						                	mesBoutonsOrdi[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
						                	mesBoutonsOrdi[ligne][colonne].setEnabled(false);
						                	
						                    numeroColonnes++;
						                }
						                if(colonne==0&&ligne!=0) {
						                	mesBoutonsOrdi[ligne][colonne].setText(""+numeroLignes);
						                	mesBoutonsOrdi[ligne][colonne].setBackground(Color.ORANGE);
						                	mesBoutonsOrdi[ligne][colonne].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
						                	mesBoutonsOrdi[ligne][colonne].setEnabled(false);
						                	
						                    numeroLignes++;
						                }
						                if(ligne>0&&colonne>0) {
						                	if(model.getPlateauOrdi().getPlateau()[ligne-1][colonne-1].estOccupee()) {
						                		mesBoutonsOrdi[ligne][colonne].setBackground(Color.GREEN);
						                	}
						                	else {
						                		mesBoutonsOrdi[ligne][colonne].setBackground(Color.GRAY);
						                	}
						                }
						                mesBoutonsOrdi[ligne][colonne].setActionCommand((ligne-1) + " " + (colonne-1));
						                mesBoutonsOrdi[ligne][colonne].addActionListener(this);
						                grilleOrdi.add(mesBoutonsOrdi[ligne][colonne]); 
						            }
						       }
						        
						        if( getColonneAttaque(colonneAttaqueJ2) < 0 || getLigneAttaque(ligneAttaqueJ2) < 0 || getColonneAttaque(colonneAttaqueJ2) >= 10 || getLigneAttaque(ligneAttaqueJ2) >= 10){
									 affiche("Erreur, ceci n'est pas une coordonée valide "); 
									 return;
								 }
						        
						        if(getOrientation(bgOrientaionJ2).equals("H")) {
						        	if(getTaille(bgTailleJ2) + getColonneAttaque(colonneAttaqueJ2) >= 10) {
						        		affiche("Impossible de placer ce bateau !");
						        		return;
						        	}
						        }
						        if(getOrientation(bgOrientaionJ2).equals("V")) {
						        	if(getTaille(bgTailleJ2) + getLigneAttaque(ligneAttaqueJ2) >= 10) {
						        		affiche("Impossible de placer ce bateau !");
						        		return;
						        	}
						        }
						        if(getOrientation(bgOrientaionJ2).isEmpty()) {
						        	affiche("Pas d'orientation encodée");
						        	return;
						        }
						        if(getTaille(bgTailleJ2) == 0) {
						        	affiche("Pas de taille encodée");
						        	return;
						        }
						        if(!getOrientation(bgOrientaionJ2).equals(" ") ) {
							        Bateau bateau = new Bateau(getLigneAttaque(ligneAttaqueJ2), getColonneAttaque(colonneAttaqueJ2),getTaille(bgTailleJ2), getOrientation(bgOrientaionJ2));
							        
							        if(controller.ordiPlacerBateau(bateau)) {
							        	model.bateauAPlacerOrdiMoins1();
							        	bgTailleJ2.getSelection().setEnabled(false);
							        	ligneAttaqueJ2.setText("");
							        	colonneAttaqueJ2.setText("");
							        	//update(null,null);
							        }
							        if(! controller.ordiPlacerBateau(bateau)) {
							        	affiche("Un bateaux ne peut pas chevaucher un autre !");
							        }
							        update(null,null);
						        }
						}
						framePlateau.setEnabled(true);
						if(model.getbateauAPlacerOrdi() <= 0) {
							 controller.setOrdiAPlacerBateaux(true);
							 labelLigneJ2.setText(" Ligne de votre attaque");
							 labelColonneJ2.setText(" Colonne de votre attaque");
							 labelArgentJ2.setText("Il vous reste : " + model.getArgent() + " pièces pour ce tour !");
							 if(!controller.ordiAPlacerBateaux()) {
								 update(null,null);
							 }
						}
						tour = 0;
						update(null,null);
						return;
				 }
		 
		 /////ATTAQUE 1 CASE DU JOUEUR 1 VERS JOUEUR 2 (OU ORDI)\\\\\
		 if(source == attaqueButton) {
			 if(getColonneAttaque(colonneAttaque) < 0 || getLigneAttaque(ligneAttaque) < 0 || getColonneAttaque(colonneAttaque) >= 10 || getLigneAttaque(ligneAttaque) >= 10){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 if(controller.getArgent() < 3) {
				 affiche("Vous n'avez plus suffisament d'argent !!"); 
				 return;
			 }
			 Attaque attaque = new Attaque(getLigneAttaque(ligneAttaque), getColonneAttaque(colonneAttaque));
			 controller.ordiEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 3);
			 
			 if(choixModeJeux == 2) {
				test.sendAttaque(attaque);
			}
			 
			 update(null,null);
			 return;
		 }
		 
		 /////ATTAQUE 1 CASE DU JOUEUR 2 VERS JOUEUR 1\\\\\
		 if(source == attaqueButtonJ2) {
			 if(getColonneAttaque(colonneAttaqueJ2) < 0 || getLigneAttaque(ligneAttaqueJ2) < 0 || getColonneAttaque(colonneAttaqueJ2) >= 10 || getLigneAttaque(ligneAttaqueJ2) >= 10){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 if(controller.getArgent() < 3) {
				 affiche("Vous n'avez plus suffisament d'argent !!"); 
				 return;
			 }
			 Attaque attaque = new Attaque(getLigneAttaque(ligneAttaqueJ2), getColonneAttaque(colonneAttaqueJ2));
			 controller.joueurEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 3);
			 update(null,null);
			 return;
		 }
		 
		 /////ATTAQUE 3 CASES HORIZONTALES DU JOUEUR 1 VERS JOUEUR 2 (OU ORDI)\\\\\
		 if(source == attaqueHorizontaleButton) {
			 if(getColonneAttaque(colonneAttaque) <1 || getLigneAttaque(ligneAttaque) < 0 || getColonneAttaque(colonneAttaque) >= 9 || getLigneAttaque(ligneAttaque) >= 10){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 if(controller.getArgent() < 5) {
				 affiche("Vous n'avez plus suffisament d'argent !!"); 
				 return;
			 }
			 AttaqueHorizontale attaque = new AttaqueHorizontale(getLigneAttaque(ligneAttaque), getColonneAttaque(colonneAttaque));
			 controller.ordiEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 5);
			 if(choixModeJeux == 2) {
					test.sendAttaqueHorizontale(attaque);
			}
			 update(null,null);
			 return;
		 }
		 
		 /////ATTAQUE 3 CASES HORIZONTALES DU JOUEUR 2 VERS JOUEUR 1\\\\\
		 if(source == attaqueHorizontaleButtonJ2) {
			 if(getColonneAttaque(colonneAttaqueJ2) <1 || getLigneAttaque(ligneAttaqueJ2) < 0 || getColonneAttaque(colonneAttaqueJ2) >= 9 || getLigneAttaque(ligneAttaqueJ2) >= 10){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 if(controller.getArgent() < 5) {
				 affiche("Vous n'avez plus suffisament d'argent !!"); 
				 return;
			 }
			 AttaqueHorizontale attaque = new AttaqueHorizontale(getLigneAttaque(ligneAttaqueJ2), getColonneAttaque(colonneAttaqueJ2));
			 controller.joueurEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 5);
			 update(null,null);
			 return;
		 }
		 
		 /////ATTAQUE 3 CASES VERTICALES DU JOUEUR 1 VERS JOUEUR 2 (OU ORDI)\\\\\
		 if(source == attaqueVerticaleButton) {
			 if(getColonneAttaque(colonneAttaque) < 0 || getLigneAttaque(ligneAttaque) < 1 || getColonneAttaque(colonneAttaque) >= 10 || getLigneAttaque(ligneAttaque) >= 9){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 if(controller.getArgent() < 5) {
				 affiche("Vous n'avez plus suffisament d'argent !!"); 
				 return;
			 }
			 AttaqueVerticale attaque = new AttaqueVerticale(getLigneAttaque(ligneAttaque), getColonneAttaque(colonneAttaque));
			 controller.ordiEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 5);
			 if(choixModeJeux == 2) {
					test.sendAttaqueVerticale(attaque);
			}
			 update(null,null);
			 return;
		 }
		 
		/////ATTAQUE 3 CASES VERTICALES DU JOUEUR 2 VERS JOUEUR 1\\\\\
		if(source == attaqueVerticaleButtonJ2) {
			if(getColonneAttaque(colonneAttaqueJ2) < 0 || getLigneAttaque(ligneAttaqueJ2) < 1 || getColonneAttaque(colonneAttaqueJ2) >= 10 || getLigneAttaque(ligneAttaqueJ2) >= 9){
				 affiche("Erreur, ceci n'est pas une attaque valide "); 
				 return;
			 }
			 if(controller.getArgent() < 5) {
				 affiche("Vous n'avez plus suffisament d'argent !!"); 
				 return;
			 }
			 AttaqueVerticale attaque = new AttaqueVerticale(getLigneAttaque(ligneAttaqueJ2), getColonneAttaque(colonneAttaqueJ2));
			 controller.joueurEstAttaque(attaque);
			 controller.setArgent(this.controller.getArgent() - 5);
			 update(null,null);
			 return;
		 }
		 
		/////FIN DE TOUR EN MODE DE JEUX CONTRE L'ORDI\\\\\
		 if(source == finTourButton && choixModeJeux == 0) {
			 for(int i = 0 ; i < 2 ; i++) { 
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
			 }
				controller.setArgent(10);
				update(null,null);
				return;
		 }
		 
		/////FIN DE TOUR EN MODE DE JEUX CONTRE UN AUTRE JOUEUR SUR LE MEME PC\\\\\
				 if((source == finTourButton || source == finTourButtonJ2) && choixModeJeux == 1) {
					 if(source == finTourButton) {
						 tour = 1;
					 }
					 else {
						 tour = 0;
					 }
					controller.setArgent(10);
					update(null,null);
					return;
				 }
				 
		/////FIN DE TOUR EN MODE DE JEUX CONTRE UN AUTRE JOUEUR EN RESEAU\\\\\
				 if(source == finTourButton  && choixModeJeux == 2) {
					//tour = 1;
					test.finDeTour();
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
		 
		 if(source == rafraichirButton) {
			 update(null,null);
		 }
		 
		else {
			JButton test = (JButton) source;
			ligneAttaque.setText(test.getActionCommand().substring(0,1));
			colonneAttaque.setText(test.getActionCommand().substring(2,3));
			ligneAttaqueJ2.setText(test.getActionCommand().substring(0,1));
			colonneAttaqueJ2.setText(test.getActionCommand().substring(2,3));
			return;
		}
		 
		 update(null,null);
}
	
	public String choixModeJeux() {
		JDialog.setDefaultLookAndFeelDecorated(true);
	    Object[] selectionValues = { "Jouer contre l'ordinateur", "Jouer contre un autre joueur sur ce PC", "Jouer contre un autre joueur en réseau" };
	    String initialSelection = "Dogs";
	    Object selection = JOptionPane.showInputDialog(null, "A quelle mode de jeu voullez vous jouer?",
	        "Mode BATAILLE NAVALE", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
	    return (String)selection;
	}
	
	/*
	 * Return le nombre du premier JTextField
	 */
	public int getLigneAttaque(JTextField input) {
		int result = -1;
		try {
			result = Integer.valueOf(input.getText()).intValue();
		}
		catch (NumberFormatException e){
			affiche(e.getMessage());
		}
		return result;
	}
	
	/*
	 * Return le nombre du second JTextField
	 */
	public int getColonneAttaque(JTextField input) {
		int result = -1;
		try {
			result = Integer.valueOf(input.getText()).intValue();
		}
		catch (NumberFormatException e){
			affiche(e.getMessage());
		}
		return result;
	}
	
	public String getOrientation(ButtonGroup input) {
		String result = "";
		try {
			result = input.getSelection().getActionCommand();
		}
		catch(Exception e) {
			result = " ";
		}
		return result;
	}
	
	public int getTaille(ButtonGroup input) {
		int result = 0;
		try {
			result = Integer.parseInt(input.getSelection().getActionCommand());
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
