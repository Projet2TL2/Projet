
public class Tour {

	Joueur enCours;
	int argent;
	
	public Tour(Joueur enCours, int argent) {
		this.enCours = enCours;
		this.argent = argent;
	}

	public Joueur getEnCours() {
		return enCours;
	}

	public void setEnCours(Joueur enCours) {
		this.enCours = enCours;
	}

	public int getArgent() {
		return argent;
	}

	public void setArgent(int argent) {
		this.argent = argent;
	}
	
	
}
