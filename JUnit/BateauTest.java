package JUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Bateau;
import Case;

class BateauTest {

	@Test
	void testBateau() {
		Bateau bateau = new Bateau(5,5,3,"vertical");
		assertTrue(bateau.ligneDebut == 5);
		assertTrue(bateau.colonneDebut == 5);
		assertTrue(bateau.longueur == 3);
		assertTrue(bateau.orientation == "vertical");
	}

	@Test
	void testGetSurface() {
		Case[] surface = new Case[3];
		assertTrue(surface.length == 3);
	}

	@Test
	void testToString() {
		Bateau bateau = new Bateau(5,5,3,"vertical");
		assertEquals("[5][5] vertical", bateau.toString());
	}

	@Test
	void testGetLongueur() {
		Bateau bateau = new Bateau(5,5,3,"vertical");
		assertTrue(bateau.getLongueur() == 3);
	}

	@Test
	void testGetOrientation() {
		Bateau bateau = new Bateau(5,5,3,"vertical");
		assertTrue(bateau.getOrientation() == "vertical");
	}

}
