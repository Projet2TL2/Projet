package JUnit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Attaque;
import Case;

class AttaqueTest {

	@Test
	void testAttaque() {
		Attaque attaque = new Attaque(7,8);
		assertTrue(attaque.ligne == 7);
		assertTrue(attaque.colonne == 8);
		assertTrue(attaque.cout == 3);
	}

	@Test
	void testToString() {
		Attaque attaque = new Attaque(5,5);
		assertEquals("[55]", attaque.toString());
	}

	@Test
	void testGetLigne() {
		Attaque attaque = new Attaque(7,9);
		assertTrue(attaque.getLigne() == 7);
	}

	@Test
	void testSetLigne() {
		Attaque attaque = new Attaque(7,9);
		assertTrue(attaque.getLigne() == 7);
	}

	@Test
	void testGetColonne() {
		Attaque attaque = new Attaque(7,9);
		assertTrue(attaque.getColonne() == 9);
		assertFalse(attaque.getColonne() == 5);
	}

	@Test
	void testGetSurface() {
		Case[] surface = new Case[1];
		assertTrue(surface.length == 1);
	}

	@Test
	void testSetColonne() {
		Attaque attaque = new Attaque(7,9);
		assertTrue(attaque.getColonne() == 9);
	}

	@Test
	void testGetCout() {
		Attaque attaque = new Attaque(7,8);
		assertTrue(attaque.getCout() == 3);
		
	}

}
