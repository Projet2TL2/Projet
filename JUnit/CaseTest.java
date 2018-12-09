package JUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import Case;

class CaseTest {

	@Test
	void testCase() {
		Case i = new Case(5,5);
		assertTrue(i.ligne ==  5);
		assertTrue(i.colonne == 5);
	}

	@Test
	void testToString() {
		Case i = new Case(5,5);
		assertEquals("55", i.toString());
	}

	@Test
	void testEstOccupee() {
		fail("Not yet implemented");
	}
	

	@Test
	void testEstTouchee() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLigne() {
		Case i = new Case(7,9);
		assertTrue(i.getLigne() == 7);
	}

	@Test
	void testGetColonne() {
		Case i = new Case(7,9);
		assertTrue(i.getColonne() == 9);
	}

}
