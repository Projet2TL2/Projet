
public class Case {
	
	int x; //colonne de la case ( 0 - 20 )
	int y; // ligne de la case ( 0 - 20 )
	boolean touchee;
	
	/*
	 * Construit une case de colonne x et ligne y
	 */
	public Case(int colonne, int ligne) {
		this.x = colonne;
		this.y = ligne;
		this.touchee = false;
	}
	
	public boolean isTouchee() {
		return touchee;
	}

	public void setTouchee(boolean touchee) {
		this.touchee = touchee;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
}
