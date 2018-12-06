import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

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
	
	private JButton grille1[];
	private JPanel plat;
	
	JFrame placementBateaux = new JFrame("Placement bateaux");
	JPanel textContentBateaux = new JPanel();
	JButton placerBateauFinal = new JButton("Placer Bateaux !");
	JTable tableBateaux;
	JPanel panelbuttons = new JPanel();
	
	
	
	boolean bateauxPlacés = false;

	public JoueurVueGUI(Joueur model, JoueurControl controller, int posX, int posY) {
		
		super(model, controller);
		
		//Construction de la fenÃªtre
		joueurJFrame = new JFrame("Joueur MVC");	
		textContent.setLayout(new BoxLayout(textContent, BoxLayout.Y_AXIS));
		
		grille1 = new JButton[100];
		plat = new JPanel();
		plat.setLayout(new GridLayout(10, 10, 0, 0));
		
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
		joueurJFrame.pack();
		
		
		
		
		setSize(1170, 500);
		setVisible(true);
		getContentPane().add(plat, BorderLayout.CENTER);
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
		
		Object [][] data = new Object [10][10];
		Object [][] dataOrdi = new Object[10][10];
		String[] head = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		
		
		
		for(int ligne=0; ligne<data.length; ligne++){
			for (int colonne = 0; colonne < head.length; colonne++) {
				if(model.getPlateau().getPlateau()[ligne][colonne].estOccupee()) {
					if(model.getPlateau().getPlateau()[ligne][colonne].estTouchee()){
						data[ligne][colonne] = "[ X ]";
						grille1[ligne*10 + colonne] = new JButton("[ X ]");
						grille1[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grille1[ligne*10 + colonne].setActionCommand("Grille1"+(ligne*10 + colonne));
						grille1[ligne*10 + colonne].addActionListener(this);
						plat.add(grille1[ligne*10 + colonne]);
					}
					else{
						data[ligne][colonne] = "[ Y ]";
						grille1[ligne*10 + colonne] = new JButton("[ Y ]");
						grille1[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grille1[ligne*10 + colonne].setActionCommand("Grille1"+(ligne*10 + colonne));
						grille1[ligne*10 + colonne].addActionListener(this);
						plat.add(grille1[ligne*10 + colonne]);
					}
				}
				else {
					if(model.getPlateau().getPlateau()[ligne][colonne].estTouchee()){
						data[ligne][colonne] = "[    ]";
						grille1[ligne*10 + colonne] = new JButton("[   ]");
						grille1[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grille1[ligne*10 + colonne].setActionCommand("Grille1"+(ligne*10 + colonne));
						grille1[ligne*10 + colonne].addActionListener(this);
						plat.add(grille1[ligne*10 + colonne]);
					}
					else{
						data[ligne][colonne] = "[ 0 ]";
						grille1[ligne*10 + colonne] = new JButton("[ 0 ]");
						grille1[ligne*10 + colonne].setName(""+(ligne*10 + colonne));
						grille1[ligne*10 + colonne].setActionCommand("Grille1"+(ligne*10 + colonne));
						grille1[ligne*10 + colonne].addActionListener(this);
						plat.add(grille1[ligne*10 + colonne]);
					}
				}
			}
		}
		
		for(int ligne=0; ligne<data.length; ligne++){
			for (int colonne = 0; colonne < head.length; colonne++) {
				if(model.getPlateauOrdi().getPlateau()[ligne][colonne].estOccupee()) {
					if(model.getPlateauOrdi().getPlateau()[ligne][colonne].estTouchee()){
						dataOrdi[ligne][colonne] = "[ X ]";
					}
					else{
						dataOrdi[ligne][colonne] = "[ Y ]";
					}
				}
				else {
					if(model.getPlateauOrdi().getPlateau()[ligne][colonne].estTouchee()){
						dataOrdi[ligne][colonne] = "[    ]";
					}
					else{
						dataOrdi[ligne][colonne] = "[ 0 ]";
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
			 controller.setAPlacerBateaux(true);
			 String[] head = { "Taille", "ligne", "colonne", "orientation"};
			 tableBateaux = new JTable(data, head);
			 textContentBateaux.add(tableBateaux.getTableHeader());
			 textContentBateaux.add(tableBateaux);
			 textContentBateaux.add(tableBateaux, 1);	
			 placementBateaux.add(textContentBateaux, BorderLayout.NORTH);
			 placementBateaux.add(panelbuttons, BorderLayout.SOUTH);
			 placementBateaux.pack();
			 placementBateaux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 placementBateaux.setSize(500, 200);
			 placementBateaux.setLocation(800, 200);
			 placementBateaux.setVisible(true);
			 
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
