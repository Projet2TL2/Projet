package JUnit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Model.AttaqueHorizontale;

public class AttaqueHorizontaleTest {

	@Test
	public void testGetSurface() {
		AttaqueHorizontale attaqueHorizontale = new AttaqueHorizontale(7,7);
		assertTrue(attaqueHorizontale.getSurface().length == 3);
	}

	@Test
	public void testAttaqueHorizontale() {
		AttaqueHorizontale attaque = new AttaqueHorizontale(7,8);
		assertTrue(attaque.getLigne() == 7);
		assertTrue(attaque.getColonne() == 8);
	}


}