import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Sudoku Generator.
 * 
 * @author André Dalwigk
 * @version 1.0
 *
 */
public final class SudokuGenerator {

	/**
	 * Number of columns.
	 */
	private static final int COLUMNS = 9;

	/**
	 * Number of rows.
	 */
	private static final int ROWS = COLUMNS;

	/**
	 * Container for Sudoku puzzles.
	 */
	int[][] grid;

	/**
	 * Custom Ctor for a Sudoku generator (Singleton!).
	 */
	public SudokuGenerator() {
		grid = new int[COLUMNS][ROWS];
	}

	/**
	 * Crate a Sudoku puzzle.
	 * 
	 * @param difficulty
	 *            How difficult is the puzzle?
	 * @return Sudoku puzzle.
	 */
	public final int[][] createPuzzle(final Difficulty difficulty) {

		int toDelete = 0;
		int tmp = 0;

		final Random randomGenerator = new Random();
		switch (difficulty) {
		case EASY:
			tmp = randomGenerator.nextInt(36);
			if (tmp < 20) {
				toDelete = 20; // 20 <= x <= 35
			} else {
				toDelete = tmp; // 20 <= x <= 35
			}
			break;
		case MEDIUM:
			tmp = randomGenerator.nextInt(46);
			if (tmp < 37) {
				toDelete = 37; // 37 <= x <= 45
			} else {
				toDelete = tmp; // 37 <= x <= 45
			}
			break;
		case HARD:
			tmp = randomGenerator.nextInt(56);
			if (tmp < 47) {
				toDelete = 47; // 47 <= x <= 55
			} else {
				toDelete = tmp; // 47 <= x <= 55
			}
			break;
		default:
			throw new IllegalArgumentException("Kein gültiger Schwierigkeitsgrad!");
		}

		grid = new int[COLUMNS][ROWS];

		nextField(0, 0);

		erase(toDelete);
		return grid;
	}

	/**
	 * Check validity of a move.
	 * 
	 * @param xPos
	 *            x pos of the value.
	 * @param yPos
	 *            y pos of the value.
	 * @param curr
	 *            Current value to check.
	 * @return Valid (true) or invalid (false).
	 */
	private final boolean isValid(final int xPos, final int yPos, int curr) {
		for (int yPosTmp = 0; yPosTmp < COLUMNS; yPosTmp++) {
			if (curr == grid[xPos][yPosTmp])
				return false;
		}
		for (int i = 0; i < 9; i++) {
			if (curr == grid[i][yPos])
				return false;
		}

		int borderX = 0;
		int borderY = 0;

		if (yPos > ROWS - 7) {
			if (yPos > ROWS - 4) {
				borderY = ROWS - 3;
			} else {
				borderY = ROWS - 6;
			}
		}

		if (xPos > COLUMNS - 7) {
			if (xPos > COLUMNS - 4) {
				borderX = COLUMNS - 3;
			} else {
				borderX = COLUMNS - 6;
			}
		}

		for (int i = borderX; i < 10 && i < borderX + 3; i++) {
			for (int j = borderY; j < 10 && j < borderY + 3; j++) {
				if (curr == grid[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Field to check next.
	 * 
	 * @param xCurr
	 *            x pos of the current field.
	 * @param yCurr
	 *            y pos of the current field.
	 * @return true (solvable), false (else).
	 */
	public final boolean nextField(final int xCurr, final int yCurr) {
		int nextX = xCurr;
		int nextY = yCurr;

		List<Integer> needToBeChecked = new ArrayList<>();
		for (int counter = 1; counter < 10; counter++) {
			needToBeChecked.add(counter);
		}

		final Random randomGenerator = new Random();

		int at = 0;
		int tmp = 0;
		int top = needToBeChecked.size();

		for (int i = top - 1; i > 0; i--) {
			at = randomGenerator.nextInt(i);
			tmp = needToBeChecked.get(at);
			needToBeChecked.set(at, needToBeChecked.get(i));
			needToBeChecked.set(i, tmp);
		}

		for (int counter = 0; counter < needToBeChecked.size(); counter++) {
			if (isValid(xCurr, yCurr, needToBeChecked.get(counter))) {
				grid[xCurr][yCurr] = needToBeChecked.get(counter);
				if (xCurr == ROWS - 1) {
					if (yCurr == COLUMNS - 1) {
						return true;
					} else {
						nextX = 0;
						nextY = yCurr + 1;
					}
				} else {
					nextX = xCurr + 1;
				}
				// Recursion ... I like recursion :-)
				if (nextField(nextX, nextY)) {
					return true;
				}
			}
		}
		grid[xCurr][yCurr] = 0;
		return false;
	}

	/**
	 * Erase fields.
	 * 
	 * @param fieldsToErase
	 *            Number of fields to erase.
	 */
	private final void erase(final int fieldsToErase) {
		double rSquares = COLUMNS * ROWS;
		double rFieldsToErase = (double) fieldsToErase;

		double eraseProbability = 0;

		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLUMNS; col++) {
				eraseProbability = rFieldsToErase / rSquares;
				if (Math.random() <= eraseProbability) {
					grid[row][col] = 0;
					rFieldsToErase--;
				}
				rSquares--;
			}
	}

	/**
	 * Enum encoding the difficulty.
	 * 
	 * @author André Dalwigk
	 * @version 1.0
	 *
	 */
	public enum Difficulty {
		EASY, MEDIUM, HARD
	}

}