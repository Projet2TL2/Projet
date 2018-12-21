package Controleur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Model.Attaque;
import Model.AttaqueHorizontale;
import Model.AttaqueVerticale;
import Model.Bateau;
import Model.Joueur;

public class Network {

	
	ServerSocket socketserver  ;
	Socket socketClient ;
	Socket socketduServeur;
	BufferedReader in;
	PrintWriter out;
	Scanner sc = null;
	String input = "";
	private Thread networking = new Thread(new Continue());
	Joueur model;
	int nbrBateauPlaceDeAdversaire = 0;
	int tour;
	String message;
	boolean hasChanged = false;
	
	public Network(Joueur model, boolean isServer) {
		this.model = model;
		if(isServer) {
			try {
				System.out.println("je suis serveur");
				socketserver = new ServerSocket(5000);
				socketduServeur = socketserver.accept();
				System.out.println("Connexion effectuée !");
				in = new BufferedReader(new InputStreamReader(socketduServeur.getInputStream()));
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketduServeur.getOutputStream())), true);
				tour = 0;
			}
			catch (Exception e) {
				
			}
		}
		/////SI EST LE CLIENT\\\\\
		else {
			try {
				System.out.println("je suis client");
				socketClient= new Socket("192.168.6.1", 5000);
				in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
				tour = 1;
			}
			catch (Exception e) {
				
			}
		}
		
		networking.start();
	}
		
		public class Continue implements Runnable{
			public void run() {
				int tab[] = new int[2];
				String input = " ";
				while (true) {
					try {
						input = in.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(! input.equals(" ")) {
						hasChanged = true;
						if(input.split(" ")[0].equals("attaqueSimple")) {
							tab[0] = Integer.parseInt(input.split(" ")[1]);	
							tab[1] = Integer.parseInt(input.split(" ")[2]);	
							model.joueurEstAttaque(new Attaque(tab[0], tab[1]));
							input = " ";
							message = "Attaque simple";
						}
						else if(input.split(" ")[0].equals("B")) {
							model.ordiPlacerBateau(new Bateau(Integer.parseInt(input.split(" ")[1]), Integer.parseInt(input.split(" ")[2]), Integer.parseInt(input.split(" ")[3]), input.split(" ")[4]));
							input = " ";
							nbrBateauPlaceDeAdversaire++;
						}
						else if(input.split(" ")[0].equals("attaqueHorizontale")) {
							tab[0] = Integer.parseInt(input.split(" ")[1]);	
							tab[1] = Integer.parseInt(input.split(" ")[2]);	
							model.joueurEstAttaque(new AttaqueHorizontale(tab[0], tab[1]));
							input = " ";
						}
						else if(input.split(" ")[0].equals("attaqueVerticale")) {
							tab[0] = Integer.parseInt(input.split(" ")[1]);	
							tab[1] = Integer.parseInt(input.split(" ")[2]);	
							model.joueurEstAttaque(new AttaqueVerticale(tab[0], tab[1]));
							input = " ";
						}
						else if(input.equals("finDeTour")) {
							if(tour == 0) {
								tour = 1;
							}
							else {
								tour = 0;
							}
						}
					}
				}
			}
		}
		
		public void sendAttaque(Attaque attaque) {
			out.println("attaqueSimple" + " " + attaque.getLigne() + " " + attaque.getColonne());
			out.flush();
		}
		public void sendAttaqueHorizontale(AttaqueHorizontale attaque) {
			out.println("attaqueHorizontale" + " " + attaque.getLigne() + " " + attaque.getColonne());
			out.flush();
		}
		public void sendAttaqueVerticale(AttaqueVerticale attaque) {
			out.println("attaqueVerticale" + " " + attaque.getLigne() + " " + attaque.getColonne());
			out.flush();
		}
		
		public void sendBateau(Bateau bateau) {
			out.println("B" + " " + bateau.getLigneDebut() + " " + bateau.getColonneDebut() + " " + bateau.getLongueur() + " " + bateau.getOrientation());
			//nbrBateauPlaceDeAdversaire++;
		}
		
		public void finDeTour () {
			out.println("finDeTour");
			if(tour == 0) {
				tour = 1;
			}
			else {
				tour = 0;
			}
		}
		
		public boolean serveurAPlacerTousSesBateaux () {
			if(nbrBateauPlaceDeAdversaire >=3 ) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public boolean hasChanged() {
			if(hasChanged == true) {
				hasChanged = false;
				return true;
			}
			else {
				return false;
			}
		}
		
		public int getTour() {
			return tour;
		}
		public void setTour(int tour) {
			this.tour = tour;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
		
	}

