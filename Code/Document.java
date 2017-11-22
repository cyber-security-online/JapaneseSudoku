import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A document consists of a finite number of LaTeX commands.
 * 
 * @author André Dalwigk
 * @version 1.0
 *
 */
public final class Document {
	/**
	 * Content of the document.
	 */
	private String content = "";

	/**
	 * PrintWriter for writing to a file.
	 */
	private final PrintWriter output;

	/**
	 * Language for the Sudoku puzzle.
	 */
	private final Language lang;

	/**
	 * Sudoku gGenerator.
	 */
	private final SudokuGenerator sudokuGenerator;

	/**
	 * Sudoku solver.
	 */
	private final SudokuSolver sudokuSolver;

	/**
	 * List containing all Sudokus.
	 */
	private final List<Integer[][]> sudokuPuzzles;

	/**
	 * List containing solved Sudoku puzzles.
	 */
	private final List<Integer[][]> sudokuSolutions;

	/**
	 * Custom Ctor for a document.
	 * 
	 * @param name
	 *            Name of the document.
	 * @param type
	 *            Type of the document.
	 * @throws FileNotFoundException
	 *             fnfe.
	 */
	public Document(final String name, final String type, final Language lang) throws FileNotFoundException {
		sudokuGenerator = new SudokuGenerator();
		sudokuSolver = new SudokuSolver();
		sudokuPuzzles = new ArrayList<>();
		sudokuSolutions = new ArrayList<>();
		this.lang = lang;
		output = new PrintWriter(name + "." + type);
	}

	/**
	 * Method to add new elements to a document.
	 * 
	 * @param data
	 *            Data that needs to be added.
	 */
	public final void add(final String data) {
		content += data;
	}

	/**
	 * Method to add a title.
	 * 
	 * @param title
	 *            Title to add.
	 */
	public final void addTitle(final String title) {
		content += LaTeXConverter.title(title);
	}

	/**
	 * Method to add an author.
	 * 
	 * @param author
	 *            Author to add.
	 */
	public final void addAuthor(final String author) {
		content += LaTeXConverter.author(author);
	}

	/**
	 * Method to start a document.
	 */
	public final void beginDocument() {
		content += LaTeXConverter.BEGIN_DOC;
	}

	/**
	 * Method to end a document.
	 */
	public final void endDocument() {
		content += LaTeXConverter.END_DOC;
	}

	/**
	 * Method to print a Sudoku grid.
	 * 
	 * @param grid
	 *            Grid to print.
	 * @param index
	 *            Number of the Sudoku puzzle.
	 */
	private final void addSudokuPuzzle(final int[][] grid, final int index) {
		content += LaTeXConverter.sudokuGrid(convertGrid(grid), index);
	}

	/**
	 * Method to print a Sudoku.
	 * 
	 * @param unsolved
	 *            Unsolved Sudoku puzzle.
	 * @param solved
	 *            Solved Sudoku puzzle (Delta!).
	 * @param index
	 *            Number of the Sudoku puzzle.
	 */
	private final void addSudokuSolution(final int[][] unsolved, final int[][] solved, final int index) {
		content += LaTeXConverter.sudokuSolution(convertGrid(unsolved), convertGrid(solved), "orange", index);
	}

	/**
	 * Method to create a human-readable version of the Sudoku puzzle.
	 */
	public final void addSudokuPuzzles() {
		int tmp = 1;
		// Füge jedes erzeugte Sudoku-Rätsel dem Content hinzu.
		for (int counter = 0; counter < sudokuPuzzles.size(); counter++) {
			add(LaTeXConverter.LINE);
			add(LaTeXConverter.NEW_LINE_S);
			addSudokuPuzzle(integerToInt(sudokuPuzzles.get(counter)), counter + 1);
			// An unteres Rätsel auf einer Seite ebenfalls einen Bindestrich
			// anfügen.
			if (tmp % 2 == 0) {
				add(LaTeXConverter.LINE);
				add(LaTeXConverter.NEW_PAGE);
			}
			tmp++;
		}
	}

	/**
	 * Create Sudoku puzzle.
	 * 
	 * @param amount
	 *            Number of Sudoku puzzles.
	 */
	public final void createSudokuPuzzles(final int amount) {
		// TODO Different levels of difficulty.
		for (int counter = 0; counter < amount; counter++) {
			sudokuPuzzles.add(intToInteger(sudokuGenerator.createPuzzle(SudokuGenerator.Difficulty.HARD)));
		}
	}

	/**
	 * Convert int[][] to Integer[][] (shame on you, javac!)
	 * 
	 * @param toConvert
	 *            int-array to box.
	 * @return Boxed Array.
	 */
	private final Integer[][] intToInteger(final int[][] toConvert) {
		Integer[][] resultGrid = new Integer[toConvert.length][toConvert[0].length];
		for (int row = 0; row < resultGrid.length; row++) {
			for (int col = 0; col < resultGrid[0].length; col++) {
				resultGrid[row][col] = toConvert[row][col];
			}
		}
		return resultGrid;
	}

	/**
	 * Convert Integer[][] to int[][] (shame on you, javac!)
	 * 
	 * @param toConvert
	 *            int-array to unbox.
	 * @return unboxed array.
	 */
	private final int[][] integerToInt(final Integer[][] toConvert) {
		int[][] resultGrid = new int[toConvert.length][toConvert[0].length];
		for (int row = 0; row < resultGrid.length; row++) {
			for (int col = 0; col < resultGrid[0].length; col++) {
				resultGrid[row][col] = toConvert[row][col];
			}
		}
		return resultGrid;
	}

	/**
	 * Calculate the "delta" of the Sudoku puzzle and the solution.
	 * 
	 * @param unsolvedGrid
	 *            Unsolved Sudoku puzzle.
	 * @param solvedGrid
	 *            Solved Sudoku puzzle.
	 * @return Matrix containing only (!) the solved numbers.
	 */
	private final int[][] calculateDelta(final int[][] unsolvedGrid, final int[][] solvedGrid) {
		for (int row = 0; row < solvedGrid.length; row++) {
			for (int col = 0; col < solvedGrid[0].length; col++) {
				if (unsolvedGrid[row][col] != 0) {
					solvedGrid[row][col] = 0;
				}
			}
		}
		return solvedGrid;
	}

	/**
	 * Method to solve all Sudoku puzzles.
	 */
	private final void solveAllSudokuPuzzles() {
		int[][] sudokuPuzzle;
		int[][] toSolve;
		for (int counter = 0; counter < sudokuPuzzles.size(); counter++) {
			sudokuPuzzle = integerToInt(sudokuPuzzles.get(counter));
			toSolve = integerToInt(sudokuPuzzles.get(counter));
			// Löse Rätsel.
			sudokuSolver.solve(toSolve);
			// Delta berechnen und der Liste mit den Lösungen hinzufügen.
			sudokuSolutions.add(intToInteger(calculateDelta(sudokuPuzzle, toSolve)));
		}
	}

	/**
	 * Method to print all solutions to the document.
	 */
	public final void addSudokuSolutions() {
		solveAllSudokuPuzzles();
		int tmp = 1;
		add(LaTeXConverter.BEGIN_TABULAR);

		for (int counter = 0; counter < sudokuPuzzles.size(); counter++) {
			addSudokuSolution(integerToInt(sudokuPuzzles.get(counter)), integerToInt(sudokuSolutions.get(counter)),
					counter + 1);
			// Table fomat (3 columns).
			if (tmp % 12 != 0) {
				if (tmp % 3 == 0) {
					add(LaTeXConverter.NEW_LINE);
				} else {
					add(LaTeXConverter.NEW_COLUMN);
				}
			} else {
				if (counter != sudokuPuzzles.size() - 1) {
					// New page (each page has a maximum of 12 solutions.).
					add(LaTeXConverter.NEW_PAGE);
					add(LaTeXConverter.BEGIN_TABULAR);
				}
			}
			tmp++;
		}
		add(LaTeXConverter.END_TABULAR);
	}

	/**
	 * Convert int[][] to String[][].
	 * 
	 * @param toConvert
	 *            Grid to convert.
	 * @return Converted grid.
	 */
	private final String[][] convertGrid(final int[][] toConvert) {
		String[][] resultGrid = new String[toConvert.length][toConvert[0].length];
		int elem = 0;
		for (int row = 0; row < resultGrid.length; row++) {
			for (int col = 0; col < resultGrid[0].length; col++) {
				elem = toConvert[row][col];
				if (lang == Language.JP) {
					resultGrid[row][col] = toJapanese(elem);
				} else {
					if (elem == 0) {
						resultGrid[row][col] = " ";
					} else {
						resultGrid[row][col] = Integer.toString(elem);
					}
				}
			}
		}
		return resultGrid;
	}

	/**
	 * Convert numbers to Kanji characters.
	 * 
	 * @param toConvert
	 *            Number to convert.
	 * @return Converted number.
	 */
	private final String toJapanese(final int toConvert) {
		return LaTeXConverter.J_NUMBER_COMMANDS.get(toConvert);
	}

	/**
	 * End a LaTeX-document.
	 * 
	 * @throws FileNotFoundException
	 */
	public final void print() {
		output.write(content);
		output.close();
	}

	/**
	 * Enum to encode the languages.
	 * 
	 * @author André
	 *
	 */
	public enum Language {
		DE, JP
	}
}
