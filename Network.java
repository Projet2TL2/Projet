import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import model.Attaque;
import model.Bateau;
import model.Joueur;

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
			}
			catch (Exception e) {
				
			}
		}
		/////SI EST LE CLIENT\\\\\
		else {
			try {
				System.out.println("je suis client");
				socketClient= new Socket("127.0.0.1", 5000);
				in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
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
						if(input.length() == 3) {
							tab[0] = Integer.parseInt(input.split(" ")[1]);	
							tab[1] = Integer.parseInt(input.split(" ")[2]);	
							model.joueurEstAttaque(new Attaque(tab[0], tab[1]));
							input = " ";
						}
						if(input.length() > 5) {
							model.ordiPlacerBateau(new Bateau(Integer.parseInt(input.split(" ")[1]), Integer.parseInt(input.split(" ")[2]), Integer.parseInt(input.split(" ")[3]), input.split(" ")[4]));
							input = " ";
						}
					}
				}
			}
		}
		
		public void sendAttaque(Attaque attaque) {
			out.println(attaque.getLigne() + " " + attaque.getColonne());
			out.flush();
		}
		
		public void sendBateau(Bateau bateau) {
			out.println("B" + " " + bateau.getLigneDebut() + " " + bateau.getColonneDebut() + " " + bateau.getLongueur() + " " + bateau.getOrientation());
		}
	}

