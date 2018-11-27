import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;



public class JoueurVueGUI extends JoueurVue implements ActionListener{

	private JFrame joueurJFrame;
	private JTextField ligneAttaque = new JTextField(1);
	private JTextField colonneAttaque = new JTextField(1);
	private JButton attaqueButton = new JButton("Attaquer");
	private JButton placerBateauButton = new JButton("Placer Bateau");
	private JLabel message = new JLabel(" ");
	private JTable table;
	JPanel textContent = new JPanel();
	
	JFrame placementBateaux = new JFrame("Placement bateaux");
	JPanel textContentBateaux = new JPanel();
	JButton placerBateauFinal = new JButton("Placer Bateaux !");
	JTable tableBateaux;
	JPanel panelbuttons = new JPanel();
	
	
	
	
	
	boolean bateauxPlac�s = false;

	public JoueurVueGUI(Joueur model, JoueurControl controller, int posX, int posY) {
		
		super(model, controller);
		
		//Construction de la fenêtre
		joueurJFrame = new JFrame("Joueur MVC");	
		textContent.setLayout(new BoxLayout(textContent, BoxLayout.Y_AXIS));
		updateTable();
		if(bateauxPlac�s == false) {
			attaqueButton.setVisible(false);
		}
		textContent.add(table.getTableHeader());
		textContent.add(table);
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
		panelbuttons.add(placerBateauButton);
		
		joueurJFrame.add(panelbuttons, BorderLayout.SOUTH);
		joueurJFrame.pack();
		joueurJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		joueurJFrame.setSize(500, 400);
		joueurJFrame.setLocation(300, 400);
		joueurJFrame.setVisible(true);
		
		//Définition des actions sur les éléments de la GUI
		attaqueButton.addActionListener(this);
		placerBateauButton.addActionListener(this);
		joueurJFrame.pack();
	}
	
	public void affiche(String msg){
		message.setText(msg);
	}
	
	public void updateTable(){
		
		Object [][] data = new Object [10][10];
		String[] head = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		for(int ligne=0; ligne<data.length; ligne++){
			for (int colonne = 0; colonne < head.length; colonne++) {
				data[ligne][colonne] = "[ 0 ]";
				if(controller.getPlateau()[ligne][colonne].estTouchee()) {
					data[ligne][colonne] = "[ X ]";
				}
			}
		}
		table = new JTable(data, head);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		updateTable();
		textContent.remove(1);
		textContent.add(table, 1);	
		joueurJFrame.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 Object  source=e.getSource();
		 String [][] data = new String [5][4];
		 
		 if(source == placerBateauButton) {
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
				 	controller.placerBateau(current);
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
			 this.controller.estAttaque(attaque);
		 }
		 
		
	}
	
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
