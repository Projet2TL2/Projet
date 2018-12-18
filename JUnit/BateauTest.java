import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BateauTest {

	@Test
	public void testBateau() {
		Bateau bateau = new Bateau(5,5,3,"vertical");
		assertTrue(bateau.ligneDebut == 5);
		assertTrue(bateau.colonneDebut == 5);
		assertTrue(bateau.longueur == 3);
		assertTrue(bateau.orientation == "vertical");
	}

	@Test
	public void testGetSurface() {
		Bateau bateau = new Bateau(5,5,3,"vertical");
		assertTrue(bateau.getSurface().length == 3);
	}

	@Test
	public void testToString() {
		Bateau bateau = new Bateau(5,5,3,"vertical");
		assertEquals("[5][5] vertical", bateau.toString());
	}

	@Test
	public void testGetLongueur() {
		Bateau bateau = new Bateau(5,5,3,"vertical");
		assertTrue(bateau.getLongueur() == 3);
	}

	@Test
	public void testGetOrientation() {
		Bateau bateau = new Bateau(5,5,3,"vertical");
		assertTrue(bateau.getOrientation() == "vertical");
	}

}