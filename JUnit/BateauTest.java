package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Model.Bateau;

public class BateauTest {

	@Test
	public void testBateau() {
		Bateau bateau = new Bateau(5,5,3,"V");
		assertTrue(bateau.getLigneDebut() == 5);
		assertTrue(bateau.getColonneDebut() == 5);
		assertTrue(bateau.getLongueur() == 3);
		assertTrue(bateau.getOrientation() == "V");
	}

	@Test
	public void testGetSurface() {
		Bateau bateau = new Bateau(5,5,3,"V");
		assertTrue(bateau.getSurface().length == 3);
	}

	@Test
	public void testToString() {
		Bateau bateau = new Bateau(5,5,3,"V");
		assertEquals("[5][5] V", bateau.toString());
	}

	@Test
	public void testGetLongueur() {
		Bateau bateau = new Bateau(5,5,3,"V");
		assertTrue(bateau.getLongueur() == 3);
	}

	@Test
	public void testGetOrientation() {
		Bateau bateau = new Bateau(5,5,3,"V");
		assertTrue(bateau.getOrientation() == "V");
	}

}