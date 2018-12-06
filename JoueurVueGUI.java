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



public class JoueurVueGUI extends JoueurVue implements ActionListener{

	private JFrame joueurJFrame;
	private JTextField ligneAttaque = new JTextField(1);
	private JTextField colonneAttaque = new JTextField(1);
	private JButton attaqueButton = new JButton("Attaquer");
	private JButton attaqueHorizontaleButton = new JButton("Attaque Horizontale");
	private JButton placerBateauButton = new JButton("Placer Bateau");
	private JLabel message = new JLabel(" ");
	private JTable table;
	private JTable tableOrdi;
	JPanel textContent = new JPanel();
	
	private JButton grilleJoueur[];
	private JPanel plateauJoueur;
	private JButton grilleOrdi[];
	private JPanel plateauOrdi;
	
	private JPanel total;
	private JButton mesBoutons[] = new JButton[121];
	private JPanel grille;
	JFrame framePlateau = new JFrame();
	
	JFrame placementBateaux = new JFrame("Placement bateaux");
	JPanel textContentBateaux = new JPanel();
	JButton placerBateauFinal = new JButton("Placer Bateaux !");
	JTable tableBateaux;
	JPanel panelbuttons = new JPanel();
	
	
	
	boolean bateauxPlacés = false;

	public JoueurVueGUI(Joueur model, JoueurControl controller, int posX, int posY) {
		
		super(model, controller);
		
		joueurJFrame = new JFrame("Joueur MVC");	
		textContent.setLayout(new BoxLayout(textContent, BoxLayout.Y_AXIS));
		
		total = new JPanel();
		total.setLayout(new BorderLayout());
		grille = new JPanel();
        grille.setLayout(new GridLayout(11,11));
		
       
        
		
		grilleJoueur = new JButton[100];
		grilleOrdi = new JButton[100];
		plateauJoueur = new JPanel();
		plateauJoueur.setLayout(new GridLayout(10, 10, 0, 0));
		plateauOrdi = new JPanel();
		plateauOrdi.setLayout(new GridLayout(10, 10, 0, 0));
		
		for(int i =0 ; i<100 ; i++) {
			grilleJoueur[i] = new JButton(" ");
			grilleJoueur[i].setPreferredSize(new Dimension(10, 10));
			grilleOrdi[i] = new JButton(" ");
			grilleOrdi[i].setPreferredSize(new Dimension(10, 10));
			plateauJoueur.add(grilleJoueur[i]);
			plateauOrdi.add(grilleOrdi[i]);
		}
		
		updateTable();
		textContent.add(table.getTableHeader());
		textContent.add(table);
		textContent.add(tableOrdi.getTableHeader());
		textContent.add(tableOrdi);
		textContent.add(message);
		
		placerBateauFinal.addActionListener(this);
		panelbuttons.add(placerBateauFinal);
		textContentBateaux.setLayout(new BoxLayout(textContentBateaux, BoxLayout.Y_AXIS));
		
		joueurJFrame.add(textContent, BorderLayout.NORTH);
	
		JPanel fieldZone = new JPanel();
		fieldZone.setLayout(new BoxLayout(fieldZone, BoxLayout.X_AXIS));
		JLabel fieldLabel1 = new JLabel("Ligne de votre attaque ");
		fieldZone.add(fieldLabel1);
		fieldZone.add(ligneAttaque);
		JLabel fieldLabel2 = new JLabel("Colonne de votre attaque ");
		fieldZone.add(fieldLabel2);
		fieldZone.add(colonneAttaque);
		joueurJFrame.add(fieldZone, BorderLayout.CENTER);
		JPanel panelbuttons = new JPanel();
		panelbuttons.add(attaqueButton);
		panelbuttons.add(attaqueHorizontaleButton);
		panelbuttons.add(placerBateauButton);
		if(controller.aPlacerBateaux() == false) {
			attaqueButton.setVisible(false);
			attaqueHorizontaleButton.setVisible(false);
		}
		else {
			attaqueButton.setVisible(true);
			attaqueHorizontaleButton.setVisible(true);
		}
		
		
		joueurJFrame.add(panelbuttons, BorderLayout.SOUTH);
		joueurJFrame.pack();
		joueurJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		joueurJFrame.setSize(500, 400);
		joueurJFrame.setLocation(300, 400);
		joueurJFrame.setVisible(true);
		
		
		//DÃ©finition des actions sur les Ã©lÃ©ments de la GUI
		attaqueButton.addActionListener(this);
		attaqueHorizontaleButton.addActionListener(this);
		placerBateauButton.addActionListener(this);
		//joueurJFrame.pack();
		
		
		
		
		setSize(800,1000);
		setVisible(true);
		getContentPane().add(plateauJoueur,BorderLayout.NORTH);
		getContentPane().add(attaqueButton, BorderLayout.CENTER);
		getContentPane().add(placerBateauButton, BorderLayout.CENTER);
		getContentPane().add(plateauOrdi, BorderLayout.SOUTH);
		//this.setResizable(false);
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
		
		 int taille = 0, lettre = 65, numero = 0;
	        for(int i=0;i<11;i++) {
	            for(int j=0;j<11;j++) {
	                mesBoutons[taille] = new JButton("");
	                
	                if(i==0&&j==0) mesBoutons[taille].setBackground(Color.BLACK);
	                if(i==0&&j!=0) {
	                    mesBoutons[taille].setText(""+numero);
	                    mesBoutons[taille].setBackground(Color.ORANGE);
	                    mesBoutons[taille].setForeground(Color.red);
	                    mesBoutons[taille].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
	                    numero++;
	                }
	                if(j==0&&i!=0) {
	                    mesBoutons[taille].setText(""+(char)lettre);
	                    mesBoutons[taille].setBackground(Color.ORANGE);
	                    mesBoutons[taille].setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.red));
	                    lettre++;
	                }
	                if(i>0&&j>0) {
	                    mesBoutons[taille].setText(".");
	                }
	                
	                grille.add(mesBoutons[taille]); 
	                taille++;
	            }
	        }
	        
	        total.add(grille);
	        framePlateau.setTitle("test");
	        framePlateau.setContentPane(total);
	        framePlateau.pack();
	        framePlateau.setVisible(true);
		
		
		
		Object [][] data = new Object [10][10];
		Object [][] dataOrdi = new Object[10][10];
		String[] head = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		
		
		
		for(int ligne=0; ligne<data.length; ligne++){
			for (int colonne = 0; colonne < head.length; colonne++) {
				if(model.getPlateau().getPlateau()[ligne][colonne].estOccupee()) {
					if(model.getPlateau().getPlateau()[ligne][colonne].estTouchee()){
						data[ligne][colonne] = "[ X ]";
						plateauJoueur.remove(grilleJoueur[ligne*10 + colonne]);
						grilleJoueur[ligne*10 + colonne] = new JButton("[ X ]");
						grilleJoueur[ligne*10 + colonne].setPreferredSize(new Dimension(40, 40));
						grilleJoueur[ligne*10 + colonne].setBackground(Color.RED);
						grilleJoueur[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grilleJoueur[ligne*10 + colonne].setActionCommand("GrilleJoueur"+(ligne*10 + colonne));
						grilleJoueur[ligne*10 + colonne].addActionListener(this);
						plateauJoueur.add(grilleJoueur[ligne*10 + colonne]);
					}
					else{
						data[ligne][colonne] = "[ Y ]";
						plateauJoueur.remove(grilleJoueur[ligne*10 + colonne]);
						grilleJoueur[ligne*10 + colonne] = new JButton("[ Y ]");
						grilleJoueur[ligne*10 + colonne].setPreferredSize(new Dimension(40, 40));
						grilleJoueur[ligne*10 + colonne].setBackground(Color.ORANGE);
						grilleJoueur[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grilleJoueur[ligne*10 + colonne].setActionCommand("GrilleJoueur"+(ligne*10 + colonne));
						grilleJoueur[ligne*10 + colonne].addActionListener(this);
						plateauJoueur.add(grilleJoueur[ligne*10 + colonne]);
					}
				}
				else {
					if(model.getPlateau().getPlateau()[ligne][colonne].estTouchee()){
						data[ligne][colonne] = "[    ]";
						plateauJoueur.remove(grilleJoueur[ligne*10 + colonne]);
						grilleJoueur[ligne*10 + colonne] = new JButton("[   ]");
						grilleJoueur[ligne*10 + colonne].setPreferredSize(new Dimension(40, 40));
						grilleJoueur[ligne*10 + colonne].setBackground(Color.GRAY);
						grilleJoueur[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grilleJoueur[ligne*10 + colonne].setActionCommand("GrilleJoueur"+(ligne*10 + colonne));
						grilleJoueur[ligne*10 + colonne].addActionListener(this);
						plateauJoueur.add(grilleJoueur[ligne*10 + colonne]);
					}
					else{
						data[ligne][colonne] = "[ 0 ]";
						plateauJoueur.remove(grilleJoueur[ligne*10 + colonne]);
						grilleJoueur[ligne*10 + colonne] = new JButton("[ 0 ]");
						grilleJoueur[ligne*10 + colonne].setPreferredSize(new Dimension(40, 40));
						grilleJoueur[ligne*10 + colonne].setBackground(Color.CYAN);
						grilleJoueur[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grilleJoueur[ligne*10 + colonne].setActionCommand("GrilleJoueur"+(ligne*10 + colonne));
						grilleJoueur[ligne*10 + colonne].addActionListener(this);
						plateauJoueur.add(grilleJoueur[ligne*10 + colonne]);
					}
				}
			}
		}
		
		for(int ligne=0; ligne<data.length; ligne++){
			for (int colonne = 0; colonne < head.length; colonne++) {
				if(model.getPlateauOrdi().getPlateau()[ligne][colonne].estOccupee()) {
					if(model.getPlateauOrdi().getPlateau()[ligne][colonne].estTouchee()){
						dataOrdi[ligne][colonne] = "[ X ]";
						plateauOrdi.remove(grilleOrdi[ligne*10 + colonne]);
						grilleOrdi[ligne*10 + colonne] = new JButton("[ X ]");
						grilleOrdi[ligne*10 + colonne].setPreferredSize(new Dimension(30, 30));
						grilleOrdi[ligne*10 + colonne].setBackground(Color.RED);
						grilleOrdi[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grilleOrdi[ligne*10 + colonne].setActionCommand("grilleOrdi"+(ligne*10 + colonne));
						grilleOrdi[ligne*10 + colonne].addActionListener(this);
						plateauOrdi.add(grilleOrdi[ligne*10 + colonne]);
					}
					else{
						dataOrdi[ligne][colonne] = "[ Y ]";
						plateauOrdi.remove(grilleOrdi[ligne*10 + colonne]);
						grilleOrdi[ligne*10 + colonne] = new JButton("[ Y ]");
						grilleOrdi[ligne*10 + colonne].setPreferredSize(new Dimension(40, 40));
						grilleOrdi[ligne*10 + colonne].setBackground(Color.ORANGE);
						grilleOrdi[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grilleOrdi[ligne*10 + colonne].setActionCommand("grilleOrdi"+(ligne*10 + colonne));
						grilleOrdi[ligne*10 + colonne].addActionListener(this);
						plateauOrdi.add(grilleOrdi[ligne*10 + colonne]);
					}
				}
				else {
					if(model.getPlateauOrdi().getPlateau()[ligne][colonne].estTouchee()){
						dataOrdi[ligne][colonne] = "[    ]";
						plateauOrdi.remove(grilleOrdi[ligne*10 + colonne]);
						grilleOrdi[ligne*10 + colonne] = new JButton("[   ]");
						grilleOrdi[ligne*10 + colonne].setPreferredSize(new Dimension(40, 40));
						grilleOrdi[ligne*10 + colonne].setBackground(Color.GRAY);
						grilleOrdi[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grilleOrdi[ligne*10 + colonne].setActionCommand("grilleOrdi"+(ligne*10 + colonne));
						grilleOrdi[ligne*10 + colonne].addActionListener(this);
						plateauOrdi.add(grilleOrdi[ligne*10 + colonne]);
					}
					else{
						dataOrdi[ligne][colonne] = "[ 0 ]";
						plateauOrdi.remove(grilleOrdi[ligne*10 + colonne]);
						grilleOrdi[ligne*10 + colonne] = new JButton("[ 0 ]");
						grilleOrdi[ligne*10 + colonne].setPreferredSize(new Dimension(40, 40));
						grilleOrdi[ligne*10 + colonne].setBackground(Color.CYAN);
						grilleOrdi[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grilleOrdi[ligne*10 + colonne].setActionCommand("grilleOrdi"+(ligne*10 + colonne));
						grilleOrdi[ligne*10 + colonne].addActionListener(this);
						plateauOrdi.add(grilleOrdi[ligne*10 + colonne]);
					}
				}
			}
		}
		table = new JTable(data, head);
		tableOrdi = new JTable(dataOrdi,head);
		

	}
	
	
	/*
	 * Lors d'un changement dans le controller, actualise l'affichage
	 * si le joueur n'a pas encore placé ses bateaux, les bouttons d'attaque ne sont pas visibles
	 */
	public void update(Observable o, Object arg) {
		updateTable();
		if(controller.aPlacerBateaux() == false) {
			attaqueButton.setVisible(false);
			attaqueHorizontaleButton.setVisible(false);
		}
		else {
			attaqueButton.setVisible(true);
			attaqueHorizontaleButton.setVisible(true);
		}
		textContent.remove(1);
		textContent.add(table, 1);
		textContent.remove(3);
		textContent.add(tableOrdi, 3);	
		joueurJFrame.pack();
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
		 String [][] data = new String [5][4];
		 
		 if(source == placerBateauButton) {
			
			 
			 
		 }
		 
		 if(source == placerBateauFinal) {
			 for (int ligne = 0; ligne < 5; ligne++) {
				 	 for (int colonne = 0; colonne < 4; colonne++) {
						if(data[ligne][colonne].equals("5")) {
							affiche("Erreur, ceci n'est pas une attaque valide "); 
							return;
						}
					}
				 	Bateau current = new Bateau(Integer.parseInt(data[ligne][0]), Integer.parseInt(data[ligne][1]), Integer.parseInt(data[ligne][2]), data[ligne][3]);
				 	controller.joueurPlacerBateau(current);
			 }
			 System.out.println("test");
			 attaqueButton.setVisible(true);
		 }
		 
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
