/**
 * The beginning of a unit test for MineSweeper.  
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class MineSweeperTest {

	@Test
	public void testGetAdjacentMinesWithAGivenTwodArrayOfBooleans() {

		boolean[][] b1 =

		{ { false, false, false, false, false },
				{ false, false, true, true, false },
				{ false, false, false, true, false }, };

		// Use the non-random constructor when testing to avoid random mine
		// placement.
		MineSweeper ms = new MineSweeper(b1);

		// Check adjacent mines around every possible GameSquare
		assertEquals(0, ms.getAdjacentMines(0, 0));
		assertEquals(1, ms.getAdjacentMines(0, 1));
		assertEquals(2, ms.getAdjacentMines(0, 2));
		assertEquals(2, ms.getAdjacentMines(0, 3));
		assertEquals(1, ms.getAdjacentMines(0, 4));

		assertEquals(0, ms.getAdjacentMines(1, 0));
		assertEquals(1, ms.getAdjacentMines(1, 1));
		assertEquals(2, ms.getAdjacentMines(1, 2)); // works even if it is a
													// mine
		assertEquals(2, ms.getAdjacentMines(1, 3));
		assertEquals(2, ms.getAdjacentMines(1, 4));

		assertEquals(0, ms.getAdjacentMines(2, 0));
		assertEquals(1, ms.getAdjacentMines(2, 1));
		assertEquals(3, ms.getAdjacentMines(2, 2));
		assertEquals(2, ms.getAdjacentMines(2, 3));
		assertEquals(2, ms.getAdjacentMines(2, 4));
	}

	@Test
	public void testgetTotalMineCount() {
		boolean[][] b1 =

		{ { false, false, false, false, false },
				{ false, false, true, true, false },
				{ false, false, false, true, false }, };
		MineSweeper ms = new MineSweeper(b1);
		assertEquals(3, ms.getTotalMineCount());

	}

	@Test
	public void testisMine() {
		boolean[][] b1 =

		{ { false, false, false, false, false },
				{ false, false, true, true, false },
				{ false, false, false, true, false }, };
		MineSweeper ms = new MineSweeper(b1);
		assertEquals(false, ms.isMine(0, 0));
		assertEquals(true, ms.isMine(1, 2));
	}

	@Test
	public void testIsFlaggeOrNot() {
		boolean[][] b1 =

		{ { false, false, false, false, false },
				{ false, false, true, true, false },
				{ false, false, false, true, false }, };

		MineSweeper a = new MineSweeper(b1);
		a.toggleFlagged(2, 2);
		a.toggleFlagged(1, 3);
		assertTrue(a.isFlagged(2, 2));
		assertTrue(a.isFlagged(1, 3));
		assertFalse(a.isFlagged(0, 0));
		assertFalse(a.isFlagged(0, 1));
	}

	@Test
	public void testWonTheGame() {
		boolean[][] b1 =

		{ { false, false, false, false, false },
				{ false, false, true, true, false },
				{ false, false, false, true, false }, };

		MineSweeper ms = new MineSweeper(b1);
		assertEquals(3, ms.getTotalMineCount());
		assertFalse(ms.isVisible(0, 0));
		ms.click(0, 4);
		assertTrue(ms.isVisible(0, 4));
		ms.click(2, 2);
		assertTrue(ms.isVisible(2, 2));
		ms.click(0, 2);
		assertTrue(ms.isVisible(0, 2));
		ms.click(2, 4);
		assertTrue(ms.isVisible(2, 4));
		ms.click(0, 3);
		assertTrue(ms.isVisible(0, 3));
		ms.click(1, 4);
		assertTrue(ms.isVisible(1, 4));
		System.out.println(ms.toString());
		System.out.println();
		ms.toggleFlagged(1, 2);
		ms.toggleFlagged(1, 3);
		ms.toggleFlagged(2, 3);
		assertFalse(ms.lost());
		
	}
	@Test
	public void testWonTheGamerando() {
		MineSweeper ms = new MineSweeper(16,16,12);
		System.out.print(ms.toString());
}}