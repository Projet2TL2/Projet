import static org.junit.Assert.*;

import org.junit.Test;

public class AttaqueVerticaleTest {

	@Test
	public void testGetSurface() {
		AttaqueVerticale attaqueVertical = new AttaqueVerticale(7,7);
		assertTrue(attaqueVertical.getSurface().length == 3);
	}

	@Test
	public void testAttaqueVerticale() {
		AttaqueVerticale attaque = new AttaqueVerticale(5,4);
		assertTrue(attaque.ligne == 5);
		assertTrue(attaque.colonne == 4);
	}

	
}
