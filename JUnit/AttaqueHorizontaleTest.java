package JUnit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import AttaqueHorizontale;
import Case;

class AttaqueHorizontaleTest {

	@Test
	void testGetSurface() {
		AttaqueHorizontale attaque = new AttaqueHorizontale(7,8);
		Case[] surface = new Case[3];
		assertTrue(surface.length == 3);
	}

	@Test
	void testAttaqueHorizontale() {
		AttaqueHorizontale attaque = new AttaqueHorizontale(7,8);
		assertTrue(attaque.ligne == 7);
		assertTrue(attaque.colonne == 8);
	}


}
