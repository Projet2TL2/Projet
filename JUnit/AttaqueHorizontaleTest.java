import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AttaqueHorizontaleTest {

	@Test
	public void testGetSurface() {
		AttaqueHorizontale attaqueHorizontale = new AttaqueHorizontale(7,7);
		assertTrue(attaqueHorizontale.getSurface().length == 3);
	}

	@Test
	public void testAttaqueHorizontale() {
		AttaqueHorizontale attaque = new AttaqueHorizontale(7,8);
		assertTrue(attaque.ligne == 7);
		assertTrue(attaque.colonne == 8);
	}


}