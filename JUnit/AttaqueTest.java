import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AttaqueTest {

	@Test
	public void testAttaque() {
		Attaque attaque = new Attaque(7,8);
		assertTrue(attaque.ligne == 7);
		assertTrue(attaque.colonne == 8);
		assertTrue(attaque.cout == 3);
	}

	@Test
	public void testToString() {
		Attaque attaque = new Attaque(5,5);
		assertEquals("[55]", attaque.toString());
	}

	@Test
	public void testGetLigne() {
		Attaque attaque = new Attaque(7,9);
		assertTrue(attaque.getLigne() == 7);
	}

	@Test
	public void testGetColonne() {
		Attaque attaque = new Attaque(7,9);
		assertTrue(attaque.getColonne() == 9);
		assertFalse(attaque.getColonne() == 5);
	}

	@Test
	public void testGetSurface() {
		Attaque attaque = new Attaque(5,6);
		assertTrue(attaque.getSurface().length == 1);
		
	} 

	@Test
	public void testGetCout() {
		Attaque attaque = new Attaque(7,8);
		assertTrue(attaque.getCout() == 3);
		
	}

}
