import java.awt.BorderLayout;
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
	private JLabel message = new JLabel(" ");
	private JTable table;
	JPanel textContent = new JPanel();

	public JoueurVueGUI(Joueur model, JoueurControl controller, int posX, int posY) {
		
		super(model, controller);
		
		//Construction de la fenêtre
		joueurJFrame = new JFrame("Joueur MVC");	
		textContent.setLayout(new BoxLayout(textContent, BoxLayout.Y_AXIS));
		updateTable();
		textContent.add(table.getTableHeader());
		textContent.add(table);
		textContent.add(message);
		
		
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
		
		joueurJFrame.add(panelbuttons, BorderLayout.SOUTH);
		joueurJFrame.pack();
		joueurJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		joueurJFrame.setSize(500, 400);
		joueurJFrame.setLocation(300, 400);
		joueurJFrame.setVisible(true);
		
		//Définition des actions sur les éléments de la GUI
		attaqueButton.addActionListener(this);
		joueurJFrame.pack();
	}
	
	public void affiche(String msg){
		message.setText(msg);
	}
	
	public void updateTable(){
		
		String [][] data = new String [10][10];
		String[] head = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		for(int ligne=0; ligne<data.length; ligne++){
			for (int colonne = 0; colonne < head.length; colonne++) {
				data[ligne][colonne] = model.getPlateau()[ligne][colonne].toString();
				if(model.getPlateau()[ligne][colonne].estTouchee()) {
					data[ligne][colonne] = "yes";
				}
				//data[ligne][colonne] = model.getPlateau()[ligne][colonne].estTouchee()?"yes":"no";
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
		 if(getColonneAttaque() < 0 || getLigneAttaque() < 0 || getColonneAttaque() > 10 || getLigneAttaque() > 10){
			 affiche("Erreur, ceci n'est pas une attaque valide "); 
			 return;
		 }
		 if(source == attaqueButton) {
			 controller.estAttaque(new Attaque(getLigneAttaque(), getColonneAttaque()));
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
