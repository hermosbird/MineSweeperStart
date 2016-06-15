import java.util.Random;

import java.util.Stack;

/**
 * This class represents the model for a game of MineSweeper. It has a
 * constructor that takes a preset boolean 2D array where true means there is a
 * mine. This first constructor (you'll need 2) is for testing the methods of
 * this class.
 * 
 * The second constructor that takes the number of rows, the number of columns,
 * and the number of mines to be set randomly in that sized mine field. Do this
 * last.
 * 
 * @author YOUR NAME
 */
public class MineSweeper implements MineSweeperModel {

	private class GameSquare {

		private boolean isMine;
		private int row;
		private int col;
		private boolean isVisible;
		private boolean isFlagged;
		private int mineNeighbors;

		// Construct a GameSquare object with all values initialized except
		// mineNeighbors, which is an instance variables that can only be set
		// after
		// all
		// GameSquare objects have been constructed in the 2D array.
		public GameSquare(boolean isMine, int row, int col) {
			this.isMine = isMine;
			this.row = row;
			this.col = col;
			isVisible = false; // Default until someone starts clicking
			isFlagged = false; // Default until someone starts clicking
			// call setAdjacentMines() from both constructors
			// to set this for each new GameSquare.
			mineNeighbors = 0;
		}
	}

	// The instance variable represents all GameSquare objects where each knows
	// its row,
	// column, number of mines around it, if it is a mine, flagged, or visible
	private GameSquare[][] board;

	/**
	 * Construct a MineSweeper object using a given mine field represented by an
	 * array of boolean values: true means there is mine, false means there is
	 * not a mine at that location.
	 * 
	 * @param mines
	 *            A 2D array to represent a mine field so all methods can be
	 *            tested with no random placements.
	 */
	public MineSweeper(boolean[][] mines) {
		//
		// (later on you will need to write another constructor for random
		// boards).
		// new GameSquare objects store all info about one square on the board
		// such
		// as its row, column, if it's flagged, visible, or is a mine.

		board = new GameSquare[mines.length][mines[0].length];

		// Example construction of one GameSquare stored in row 2, column 4:
		// /// board[2][4] = new GameSquare(mines[2][4], 2, 4);
		// Use a nested for loop to change all board array elements
		// from null to a new GameSquare

		// You will need to call private void setAdjacentMines() to set
		// mineNeighbors for all GameSquare objects because each GameSquare
		// object
		// must first know if it is a mine or not. Set mineNeighbors for each.

		for (int i = 0; i < mines.length; i++) {
			for (int j = 0; j < mines[0].length; j++) {
				GameSquare a = new GameSquare(mines[i][j], i, j);
				board[i][j] = a;
			}
		}
		setAdjacentMines();
	}

	/**
	 * Use the almost initialized 2D array of GameSquare objects to set the
	 * instance variable mineNeighbors for every 2D array element (even if that
	 * one GameSquare has a mine). This is similar to GameOfLife neighborCount.
	 */
	private void setAdjacentMines() {
		// Example to set the instance variable mineNeighbors of the one
		// GameSquare
		// object stored in row 2, column 4 to 8:
		// /// board[2][4].mineNeighbors = 8;
		// Use a nested for loop to set mineNeighbors for ALL GameSquare objects

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int boom = 0;
				if (i + 1 <= board.length - 1 && j - 1 >= 0
						&& board[i + 1][j - 1].equals(true))
					boom++;
				if (i + 1 <= board.length - 1 && board[i + 1][j].equals(true))
					boom++;
				if (i + 1 <= board.length - 1 && j + 1 <= board[0].length - 1
						&& board[i + 1][j + 1].equals(true))
					boom++;
				if (j - 1 >= 0 && board[i][j - 1].equals(true))
					boom++;
				if (j + 1 <= board[0].length - 1
						&& board[i][j + 1].equals(true))
					boom++;
				if (i - 1 >= 0 && j - 1 >= 0
						&& board[i - 1][j - 1].equals(true))
					boom++;
				if (i - 1 >= 0 && board[i - 1][j].equals(true))
					boom++;
				if (i - 1 >= 0 && j + 1 <= board[0].length - 1
						&& board[i - 1][j + 1].equals(true))
					boom++;
				board[i][j].mineNeighbors = boom;
			}
		}
	}

	/**
	 * This method returns the number of mines surrounding the requested
	 * GameSquare (the mineNeighbors value of the square). A square with a mine
	 * may return the number of surrounding mines, even though it will never
	 * display that information.
	 * 
	 * @param row
	 *            - An int value representing the row in board.
	 * @param column
	 *            - An int value representing the column in board.
	 * @return The number of mines surrounding to this GameSquare
	 *         (mineNeighbors)
	 * 
	 *         Must run O(1)
	 */
	public int getAdjacentMines(int row, int column) {
		int wow = 0;
		int i = row;
		int j = column;
		if (i + 1 <= board.length - 1 && j - 1 >= 0
				&& board[i + 1][j - 1].isMine == true)
			wow++;
		if (i + 1 <= board.length - 1 && board[i + 1][j].isMine == true)
			wow++;
		if (i + 1 <= board.length - 1 && j + 1 <= board[0].length - 1
				&& board[i + 1][j + 1].isMine == true)
			wow++;
		if (j - 1 >= 0 && board[i][j - 1].isMine == true)
			wow++;
		if (j + 1 <= board[0].length - 1 && board[i][j + 1].isMine == true)
			wow++;
		if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1].isMine == true)
			wow++;
		if (i - 1 >= 0 && board[i - 1][j].isMine == true)
			wow++;
		if (i - 1 >= 0 && j + 1 <= board[0].length - 1
				&& board[i - 1][j + 1].isMine == true)
			wow++;

		return wow;
	}

	/**
	 * Construct a MineSweeper of any size that has numberOfMines randomly set
	 * so we get different games.
	 * 
	 * @param rows
	 *            Height of the board
	 * @param columns
	 *            Width of the board
	 * @param numberOfMines
	 *            How m any mines are to randomly placed
	 */
	public MineSweeper(int rows, int columns, int numberOfMines) {
		//
		//
		// Consider using class Random with its nextInt(int) method
		//
		Random generator = new Random();

		board = new GameSquare[rows][columns];
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				GameSquare a = new GameSquare(false, i, j);
				board[i][j] = a;
			}
		}
		int n = numberOfMines;
		while (n != 0) {
			int r = generator.nextInt(rows);
			int c = generator.nextInt(columns);
			if (r < rows && c < columns && !isMine(r, c)) {
				board[r][c].isMine = true;
				n--;
			}
			setAdjacentMines();
		}
	}

	/**
	 * This method returns the number of mines found in the game board.
	 * 
	 * @return The number of mines.
	 */
	public int getTotalMineCount() {
		int result = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].isMine == true)
					result++;
			}
		}
		return result;
	}

	/**
	 * This method returned whether or not the square has been flagged by the
	 * user. Flags are a tool used by players to quickly tell which squares they
	 * think contain mines as well as prevent accidental clicking on those
	 * squares.
	 * 
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 * @return A boolean value representing the flagged state of this location.
	 */
	public boolean isFlagged(int row, int column) {

		return board[row][column].isFlagged;
	}

	public void toggleFlagged(int row, int column) {
		if (isFlagged(row, column) == true) {
			board[row][column].isFlagged = false;
		} else {
			board[row][column].isFlagged = true;
		}
	}

	/**
	 * This method determines if the square in question is a mine.
	 * 
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 * @return A boolean representing the mine status of the square.
	 */
	public boolean isMine(int row, int column) {
		if (board[row][column].isMine == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method gets the visibility of the square in question. Visibilty is
	 * initially defined for all squares to be false and uncovered when the
	 * click method checks the square.
	 * 
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 * @return A boolean representing whether or not the square is set to be
	 *         visible.
	 */
	public boolean isVisible(int row, int column) {
		if (board[row][column].isVisible) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method determines if the player has lost on the current board. A
	 * player loses if and only if they have clicked on a mine.
	 * 
	 * @return A boolean representing player failure.
	 */
	public boolean lost() {
		boolean r = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].isMine && board[i][j].isVisible) {
					r = true;
				}
			}
		}
		return r;
	}

	/**
	 * Returns a textual representation of the GameBoard. Squares will be
	 * represented by one character followed by a space, except the last
	 * character which will have no following space. Visible squares will either
	 * be the number of mines next to the square, a blank space if no mines are
	 * adjacent, or a '*' character for a mine. Newlines will separate each row
	 * of the game board.
	 * 
	 * @return A String representation of the game board data.
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (j == board[0].length - 1) {
					if (board[i][j].isMine) {
						result += "*";
					} else if (board[i][j].isVisible
							&& board[i][j].mineNeighbors > 0
							&& !board[i][j].isMine) {

					}
				}

			}
			result += "/n";
		}
		return result;
	}

	/**
	 * This method determines if a player has won the game. Winning means all
	 * non-mine squares are visible and no mines have been detonated.
	 * 
	 * @return A boolean representing whether or not the player has won.
	 */
	public boolean won() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {

				if (!board[i][j].isMine && !board[i][j].isVisible) {
					return false;
				}

			}
		}

		return true;

	}

	/**
	 * This method alerts the Game Board the user has clicked on the square at
	 * the given row/column. There are five possibilities for updating the board
	 * during the click messages to your MineSweeper. The GameSquare object
	 * stored at the just clicked row and column
	 * 
	 * 1. is a mine (player looses)
	 * 
	 * 2. is visible already (do nothing)
	 * 
	 * 3. is flagged (do nothing)
	 * 
	 * 
	 * 4. has mineNeighbors >- 1 (simply mark that visible)
	 * 
	 * 5. is not adjacent to any mines with mineNeighbors == 0 (mark many
	 * visible)
	 * 
	 * Because MineSweeper automatically clears all squares adjacent to any
	 * blank square connected to the square clicked, a special algorithm is
	 * needed to set the proper part of the board visible. This pseudo-code
	 * shows the suggested algorithm.
	 */
	// Check special cases first, there may be nothing to do or the user clicked
	// a mine
	// if the clicked GameSquare is a mine
	// change the state of this object to lost (when this click method finishes,
	// the GUI will refresh with a loss)
	// else if the clicked GameSquare is flagged or the clicked GameSquare
	// isVisible
	// return, there is nothing to do
	// else if the clicked GameSquare has 1 or more neighboring mines
	// set the square to be visible, which applies only when mineNeighbors is
	// 1..8
	// else clear all possible GameSquares up to the border or GameSquares with
	// numbers 1..8
	// mark the clicked GameSquare as visible
	// push the GameSquare onto the stack
	// while the stack is not empty:
	// pop the stack and mark GameSquare as the current GameSquare
	// if the current square must has no neighboring mines (not 1..8)
	// for each adjacent square
	// if it's not visible and not flagged
	// push adjacent GameSquare on stack and set isVisible to true
	/**
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 */
	public void click(int row, int column) {
		int r = row;
		int c = column;
		if (board[r][c].isMine) {
			board[r][c].isVisible = true;
			this.lost();
		} else if (board[r][c].isFlagged || board[r][c].isVisible) {

		} else if (getAdjacentMines(r, c) >= 1) {
			board[r][c].isVisible = true;
		} else {
			Stack<GameSquare> my = new Stack<GameSquare>();
			board[r][c].isVisible = true;
			my.push(board[r][c]);
			while (!my.isEmpty()) {
				GameSquare a = my.pop();
				if (a.mineNeighbors == 0) {
					for (int i = a.row - 1; i <= a.row + 1; i++) {
						for (int j = a.col - 1; j <= a.col + 1; j++) {

							if ((i >= 0 && i < board.length)
									&& (j >= 0 && j < board[0].length)) {
								if (!board[i][j].isFlagged
										&& !board[i][j].isVisible) {
									my.push(board[i][j]);
									board[i][j].isVisible = true;
								}
							}
						}
					}
				}
			}

		}
	}
}