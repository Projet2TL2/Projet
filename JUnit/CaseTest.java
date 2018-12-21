package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import Model.Case;


class CaseTest {

	Case i = new Case(5,5);
	
	@Test
	void testCase() {
		
		assertTrue(i.getLigne() ==  5);
		assertTrue(i.getColonne() == 5);
	}

	@Test
	void testToString() {
		assertEquals("55", i.toString());
	}

	
	
	@Test
	void testEstOccupee() {
		i.setOccupee();
		assertTrue(i.estOccupee() == true);
	}
	

	@Test
	void testEstTouchee() {
		assertTrue(i.estTouchee() == false);
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
