package JUnit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import AttaqueVerticale;
import Case;

class AttaqueVerticaleTest {

	@Test
	void testGetSurface() {
		AttaqueVerticale attaque = new AttaqueVerticale(5,4);
		Case[] surface = new Case[3];
		assertTrue(surface.length == 3);
	}

	@Test
	void testAttaqueVerticale() {
		AttaqueVerticale attaque = new AttaqueVerticale(5,4);
		assertTrue(attaque.ligne == 5);
		assertTrue(attaque.colonne == 4);
	}

	
}
