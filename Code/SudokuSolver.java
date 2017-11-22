/**
 * SudokuSolver implements an object type capable of solving nxn sudoku puzzles.
 * 
 * @author André Dalwigk
 * @version 1.0
 */
public final class SudokuSolver {
	/**
	 * Dimension.
	 */
	private static final int COLUMNS = 9, ROWS = 9;

	/**
	 * Check validity of an entry.
	 * 
	 * @param grid
	 *            Sudoku puzzle.
	 * @param icol
	 *            Current column.
	 * @param jrow
	 *            Current row.
	 * @param val
	 *            Value at position (icol, jrow).
	 * @return true, if valid (false otherwise).
	 */
	private final boolean isValid(final int[][] grid, final int icol, final int jrow, final int val) {
		for (int row = 0; row < ROWS; ++row) {
			if (val == grid[row][jrow]) {
				return false;
			}
		}

		for (int col = 0; col < COLUMNS; ++col) {
			if (val == grid[icol][col]) {
				return false;
			}
		}

		int colOff = (jrow / 3) * 3;
		int rowOff = (icol / 3) * 3;

		for (int row = 0; row < ROWS / 3; ++row) {
			for (int col = 0; col < COLUMNS / 3; ++col) {
				if (val == grid[row + rowOff][col + colOff]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Recursive function to solve a Sudoku puzzle.
	 * 
	 * @param subGrid
	 *            SubGrid to solve.
	 * @param row
	 *            Current row.
	 * @param col
	 *            Current column.
	 * @return Solved subGrid (divide and conquer!).
	 */
	private final boolean solve(final int[][] subGrid, int row, int col) {
		if (row == ROWS) {
			row = 0;
			if (++col == COLUMNS) {
				return true;
			}
		}

		if (subGrid[row][col] != 0) {
			return solve(subGrid, row + 1, col);
		}

		for (int val = 1; val <= COLUMNS; ++val) {
			if (isValid(subGrid, row, col, val)) {
				subGrid[row][col] = val;

				if (solve(subGrid, row + 1, col)) {
					return true;
				}
			}
		}
		// Reset for backtracking.
		subGrid[row][col] = 0;
		return false;
	}

	/**
	 * Function to solve a Sudoku (publicly availabe).
	 * 
	 * @param toSolve
	 *            Sudoku to solve.
	 * @return Solution of the Sudoku.
	 */
	public final int[][] solve(final int[][] toSolve) {
		solve(toSolve, 0, 0);
		return toSolve;
	}
}