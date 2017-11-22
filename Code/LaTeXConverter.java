import java.util.HashMap;

/**
 * Utility class for LaTeX commands.
 * 
 * @author André Dalwigk
 * @version 1.0
 *
 */
public final class LaTeXConverter {

	/**
	 * Imports.
	 */
	public static final String IMPORTS = "\\documentclass[a4paper,12pt]{scrartcl}\n" + "\\usepackage[ngerman]{babel}\n"
			+ "\\usepackage[T1]{fontenc}\n" + "\\usepackage{amssymb}\n" + "\\usepackage{enumerate}\n"
			+ "\\usepackage{tikz}\n" + "\\usepackage{mathpazo}\n" + "\\usepackage[utf8]{inputenc}\n"
			+ "\\usepackage{CJKutf8}\n" + "\\usepackage{rotating}" // Für
																	// Deckblatt
			+ "\\usetikzlibrary{positioning}"; // Für Deckblatt

	/**
	 * Define Kanji characters.
	 */
	public static final String J_CHARS = "\\newcommand{\\jone}[0]{\\begin{CJK}{UTF8}{min}一\\end{CJK}}\n"
			+ "\\newcommand{\\jtwo}[0]{\\begin{CJK}{UTF8}{min}二\\end{CJK}}\n"
			+ "\\newcommand{\\jthree}[0]{\\begin{CJK}{UTF8}{min}三\\end{CJK}}\n"
			+ "\\newcommand{\\jfour}[0]{\\begin{CJK}{UTF8}{min}四\\end{CJK}}\n"
			+ "\\newcommand{\\jfive}[0]{\\begin{CJK}{UTF8}{min}五\\end{CJK}}\n"
			+ "\\newcommand{\\jsix}[0]{\\begin{CJK}{UTF8}{min}六\\end{CJK}}\n"
			+ "\\newcommand{\\jseven}[0]{\\begin{CJK}{UTF8}{min}七\\end{CJK}}\n"
			+ "\\newcommand{\\jeight}[0]{\\begin{CJK}{UTF8}{min}八\\end{CJK}}\n"
			+ "\\newcommand{\\jnine}[0]{\\begin{CJK}{UTF8}{min}九\\end{CJK}}\n"
			+ "\\newcommand{\\jten}[0]{\\begin{CJK}{UTF8}{min}十\\end{CJK}}\n"
			+ "\\newcommand{\\sudoku}[0]{\\begin{CJK}{UTF8}{min}数独\\end{CJK}}\n"
			+ "\\newcommand{\\jgreeting}[0]{\\begin{CJK}{UTF8}{min}ようこそ\\end{CJK}}\n"
			+ "\\newcommand{\\sudokuorder}[0]{\\begin{CJK}{UTF8}{min}数字は独身に限る\\end{CJK}}\n"
			+ "\\newcommand{\\jkanji}[0]{\\begin{CJK}{UTF8}{min}漢字\\end{CJK}}\n"
			+ "\\newcommand{\\jhundred}[0]{\\begin{CJK}{UTF8}{min}百\\end{CJK}}\n"
			+ "\\newcommand{\\jthousand}[0]{\\begin{CJK}{UTF8}{min}千\\end{CJK}}\n";

	/**
	 * HashMap that is used to convert numbers to Kanji character commands.
	 */
	public static final HashMap<Integer, String> J_NUMBER_COMMANDS;
	static {
		J_NUMBER_COMMANDS = new HashMap<>();
		J_NUMBER_COMMANDS.put(0, " ");
		J_NUMBER_COMMANDS.put(1, "\\jone");
		J_NUMBER_COMMANDS.put(2, "\\jtwo");
		J_NUMBER_COMMANDS.put(3, "\\jthree");
		J_NUMBER_COMMANDS.put(4, "\\jfour");
		J_NUMBER_COMMANDS.put(5, "\\jfive");
		J_NUMBER_COMMANDS.put(6, "\\jsix");
		J_NUMBER_COMMANDS.put(7, "\\jseven");
		J_NUMBER_COMMANDS.put(8, "\\jeight");
		J_NUMBER_COMMANDS.put(9, "\\jnine");
		J_NUMBER_COMMANDS.put(10, "\\jten");
		J_NUMBER_COMMANDS.put(100, "\\jhundred");
		J_NUMBER_COMMANDS.put(1000, "\\jthousand");
	}

	/**
	 * TeX-Commands for a Sudoku grid.
	 */
	public static final String COMMANDS = "\\newcounter{row}\n" + "\\newcounter{col}\n" + "\\newcommand\\setrow[9]{\n"
			+ "\\setcounter{col}{1}\n" + "\\foreach \\n in {#1, #2, #3, #4, #5, #6, #7, #8, #9} {\n"
			+ "\\edef\\x{\\value{col} - 0.5}\n" + "\\edef\\y{9.5 - \\value{row}}\n"
			+ "\\node[anchor=center] at (\\x, \\y) {\\n};\n" + "\\stepcounter{col}\n" + "}\n" + "\\stepcounter{row}\n"
			+ "}\n";

	/**
	 * Begin a LaTeX document.
	 */
	public static final String BEGIN_DOC = "\\begin{document}\n";

	/**
	 * End a LaTeX document.
	 */
	public static final String END_DOC = "\\end{document}\n";

	/**
	 * Begin center.
	 */
	public static final String BEGIN_CENTER = "\\begin{center}\n";

	/**
	 * Begin center.
	 */
	public static final String END_CENTER = "\\end{center}\n";

	/**
	 * Add the title to the document.
	 */
	public static final String MAKE_TITLE = "\\maketitle\n";

	/**
	 * Start a new page.
	 */
	public static final String NEW_PAGE = "\\newpage\n";

	/**
	 * Start a new line.
	 */
	public static final String NEW_LINE = "\\\\\n";

	/**
	 * Start a new line (needed for formatting reasons).
	 */
	public static final String NEW_LINE_S = "$ $" + NEW_LINE;

	/**
	 * Front page image.
	 */
	public static final String COVER_IMAGE = BEGIN_CENTER + "\\fontsize{100}{100}\\sudoku\n" + NEW_LINE
			+ "\\fontsize{30}{30}\\sudokuorder\n" + END_CENTER;

	/**
	 * Start tabular.
	 */
	public static final String BEGIN_TABULAR = "\\begin{tabular}{ccc}\n";

	/**
	 * End tabular.
	 */
	public static final String END_TABULAR = "\\end{tabular}\n";

	/**
	 * New column.
	 */
	public static final String NEW_COLUMN = " & ";

	/**
	 * Line between grid pairs.
	 */
	public static final String LINE = "\\hrule\n";

	/**
	 * Start an item.
	 */
	public static final String ITEM = "\\item ";

	/**
	 * Start an enumeration.
	 */
	public static final String BEGIN_ENUMERATE = "\\begin{enumerate}\n";

	/**
	 * End an enumeration.
	 */
	public static final String END_ENUMERATE = "\\end{enumerate}\n";

	/**
	 * Preface.
	 */
	public static final String PREFACE = NEW_LINE_S + NEW_LINE + "\\fontsize{12}{12}" + "Bei dieser "
			+ bold("Sudoku-Variante")
			+ " können die Kanji (\\jkanji) von \\(1\\) bis \\(9\\) spielerisch gelernt werden. "
			+ "Statt des Arabischen wird eines der drei Japanischen Schriftsysteme zur Repräsentation der Zahlen von \\(1\\) bis \\(9\\) verwendet. "
			+ "Die Rätsel werden ebenfalls auf Japanisch durchnummeriert, wodurch zusätzlich das Zählen und die dahinterstehende Logik, welche sich von der Deutschen Zählweise unterscheidet, erlernt werden können."
			+ NEW_LINE + NEW_LINE + "Die Regeln zum Ausfüllen der Felder bleiben wie bei der klassischen Variante:"
			+ enumerate(3,
					"In jeder " + bold("Zeile")
							+ "  dürfen die Ziffern von \\(1\\) bis \\(9\\) nur einmal vorkommen.\n",
					"In jeder " + bold("Spalte")
							+ "  dürfen die Ziffern von \\(1\\) bis \\(9\\) nur einmal vorkommen.\n",
					"In jedem " + bold("Block")
							+ "  dürfen die Ziffern von \\(1\\) bis \\(9\\) nur einmal vorkommen.\n")
			+ "Die einzelnen Zahlen \\(1\\) bis \\(9\\) werden auf Japanisch folgendermaßen geschrieben:" + NEW_LINE
			+ NEW_LINE
			+ "\\(1=\\) \\jone, \\(2=\\) \\jtwo, \\(3=\\) \\jthree, \\(4=\\) \\jfour, \\(5=\\) \\jfive, \\(6=\\) \\jsix, \\(7=\\) \\jseven, \\(8=\\) \\jeight und \\(9=\\) \\jnine.";

	/**
	 * Add an author.
	 * 
	 * @param author
	 *            Author.
	 * @return LaTeX code to add an author.
	 */
	public static final String author(final String author) {
		return "\\author{" + author + "}\n";
	}

	/**
	 * Add a title.
	 * 
	 * @param title
	 *            Title to add.
	 * @return return LaTeX code to add a title.
	 */
	public static final String title(final String title) {
		return "\\title{" + title + "}\n";
	}

	/**
	 * Bold a text.
	 * 
	 * @param text
	 *            Text to bold.
	 * @return LaTeX code to bold a text.
	 */
	public static final String bold(final String text) {
		return "\\textbf{" + text + "} ";
	}

	/**
	 * Enumerate items.
	 * 
	 * @param amount
	 *            Amount of items to enumerate.
	 * @param items
	 *            Items to enumerate.
	 * @return LaTeX code containing the enumerated items.
	 */
	public static final String enumerate(final int amount, final String... items) {
		String result = "";
		result += BEGIN_ENUMERATE;
		for (int counter = 0; counter < items.length; counter++) {
			result += (ITEM + items[counter] + "\n");
		}
		result += END_ENUMERATE;
		return result;
	}

	/**
	 * Function to create filled Sudoku grids.
	 * 
	 * @param grid
	 *            Raw data.
	 * @param index
	 *            Number of the Sudoku puzzle.
	 * @return Code for PDF creation.
	 */
	public static final String sudokuGrid(final String[][] grid, final int index) {
		return "\\begin{center}\n" + "\\begin{tikzpicture}[scale=1]\n" + "\\begin{scope}\n"
				+ "\\draw (0, 0) grid (9, 9);\n" + "\\draw[line width=1mm, scale=3] (0, 0) grid (3, 3);\n"
				+ "\\setcounter{row}{1}\n" + "\\setrow {" + grid[0][0] + "}{" + grid[0][1] + "}{" + grid[0][2] + "}  {"
				+ grid[0][3] + "}{" + grid[0][4] + "}{" + grid[0][5] + "}  {" + grid[0][6] + "}{" + grid[0][7] + "}{"
				+ grid[0][8] + "}\n" + "\\setrow {" + grid[1][0] + "}{" + grid[1][1] + "}{" + grid[1][2] + "}  {"
				+ grid[1][3] + "}{" + grid[1][4] + "}{" + grid[1][5] + "}  {" + grid[1][6] + "}{" + grid[1][7] + "}{"
				+ grid[1][8] + "}\n" + "\\setrow {" + grid[2][0] + "}{" + grid[2][1] + "}{" + grid[2][2] + "}  {"
				+ grid[2][3] + "}{" + grid[2][4] + "}{" + grid[2][5] + "}  {" + grid[2][6] + "}{" + grid[2][7] + "}{"
				+ grid[2][8] + "}\n" + "\n" + "\\setrow {" + grid[3][0] + "}{" + grid[3][1] + "}{" + grid[3][2] + "}  {"
				+ grid[3][3] + "}{" + grid[3][4] + "}{" + grid[3][5] + "}  {" + grid[3][6] + "}{" + grid[3][7] + "}{"
				+ grid[3][8] + "}\n" + "\\setrow {" + grid[4][0] + "}{" + grid[4][1] + "}{" + grid[4][2] + "}  {"
				+ grid[4][3] + "}{" + grid[4][4] + "}{" + grid[4][5] + "}  {" + grid[4][6] + "}{" + grid[4][7] + "}{"
				+ grid[4][8] + "}\n" + "\\setrow {" + grid[5][0] + "}{" + grid[5][1] + "}{" + grid[5][2] + "}  {"
				+ grid[5][3] + "}{" + grid[5][4] + "}{" + grid[5][5] + "}  {" + grid[5][6] + "}{" + grid[5][7] + "}{"
				+ grid[5][8] + "}\n" + "\n" + "\\setrow {" + grid[6][0] + "}{" + grid[6][1] + "}{" + grid[6][2] + "}  {"
				+ grid[6][3] + "}{" + grid[6][4] + "}{" + grid[6][5] + "}  {" + grid[6][6] + "}{" + grid[6][7] + "}{"
				+ grid[6][8] + "}\n" + "\\setrow {" + grid[7][0] + "}{" + grid[7][1] + "}{" + grid[7][2] + "}  {"
				+ grid[7][3] + "}{" + grid[7][4] + "}{" + grid[7][5] + "}  {" + grid[7][6] + "}{" + grid[7][7] + "}{"
				+ grid[7][8] + "}\n" + "\\setrow {" + grid[8][0] + "}{" + grid[8][1] + "}{" + grid[8][2] + "}  {"
				+ grid[8][3] + "}{" + grid[8][4] + "}{" + grid[8][5] + "}  {" + grid[8][6] + "}{" + grid[8][7] + "}{"
				+ grid[8][8] + "}\n" + "\n" + "\\node[anchor=center] at (4.5, -0.5) {" + convertNumberToJapanese(index)
				+ "};\n" + "\\end{scope}\n" + "\\end{tikzpicture}\n" + "\\end{center}\n";
	}

	/**
	 * Function to print a Sudoku solution.
	 * 
	 * @param unsolved
	 *            Unsolved Sudoku puzzle.
	 * @param solved
	 *            Solved Sudoku puzzle (delta!).
	 * @param index
	 *            Number of the puzzle.
	 * @return Code for PDF creation.
	 */
	public static final String sudokuSolution(final String[][] unsolved, final String[][] solved, final String color,
			final int index) {
		return "\\begin{tikzpicture}[scale=0.5]\n" + "\\begin{scope}[xshift=12cm]\n" + "\\draw (0, 0) grid (9, 9);\n"
				+ "\\draw[very thick, scale=3] (0, 0) grid (3, 3);\n" + "\\setcounter{row}{1}\n" + "\\setrow {"
				+ unsolved[0][0] + "}{" + unsolved[0][1] + "}{" + unsolved[0][2] + "}  {" + unsolved[0][3] + "}{"
				+ unsolved[0][4] + "}{" + unsolved[0][5] + "}  {" + unsolved[0][6] + "}{" + unsolved[0][7] + "}{"
				+ unsolved[0][8] + "}\n" + "\\setrow {" + unsolved[1][0] + "}{" + unsolved[1][1] + "}{" + unsolved[1][2]
				+ "}  {" + unsolved[1][3] + "}{" + unsolved[1][4] + "}{" + unsolved[1][5] + "}  {" + unsolved[1][6]
				+ "}{" + unsolved[1][7] + "}{" + unsolved[1][8] + "}\n" + "\\setrow {" + unsolved[2][0] + "}{"
				+ unsolved[2][1] + "}{" + unsolved[2][2] + "}  {" + unsolved[2][3] + "}{" + unsolved[2][4] + "}{"
				+ unsolved[2][5] + "}  {" + unsolved[2][6] + "}{" + unsolved[2][7] + "}{" + unsolved[2][8] + "}\n"
				+ "\n" + "\\setrow {" + unsolved[3][0] + "}{" + unsolved[3][1] + "}{" + unsolved[3][2] + "}  {"
				+ unsolved[3][3] + "}{" + unsolved[3][4] + "}{" + unsolved[3][5] + "}  {" + unsolved[3][6] + "}{"
				+ unsolved[3][7] + "}{" + unsolved[3][8] + "}\n" + "\\setrow {" + unsolved[4][0] + "}{" + unsolved[4][1]
				+ "}{" + unsolved[4][2] + "}  {" + unsolved[4][3] + "}{" + unsolved[4][4] + "}{" + unsolved[4][5]
				+ "}  {" + unsolved[4][6] + "}{" + unsolved[4][7] + "}{" + unsolved[4][8] + "}\n" + "\\setrow {"
				+ unsolved[5][0] + "}{" + unsolved[5][1] + "}{" + unsolved[5][2] + "}  {" + unsolved[5][3] + "}{"
				+ unsolved[5][4] + "}{" + unsolved[5][5] + "}  {" + unsolved[5][6] + "}{" + unsolved[5][7] + "}{"
				+ unsolved[5][8] + "}\n" + "\n" + "\\setrow {" + unsolved[6][0] + "}{" + unsolved[6][1] + "}{"
				+ unsolved[6][2] + "}  {" + unsolved[6][3] + "}{" + unsolved[6][4] + "}{" + unsolved[6][5] + "}  {"
				+ unsolved[6][6] + "}{" + unsolved[6][7] + "}{" + unsolved[6][8] + "}\n" + "\\setrow {" + unsolved[7][0]
				+ "}{" + unsolved[7][1] + "}{" + unsolved[7][2] + "}  {" + unsolved[7][3] + "}{" + unsolved[7][4] + "}{"
				+ unsolved[7][5] + "}  {" + unsolved[7][6] + "}{" + unsolved[7][7] + "}{" + unsolved[7][8] + "}\n"
				+ "\\setrow {" + unsolved[8][0] + "}{" + unsolved[8][1] + "}{" + unsolved[8][2] + "}  {"
				+ unsolved[8][3] + "}{" + unsolved[8][4] + "}{" + unsolved[8][5] + "}  {" + unsolved[8][6] + "}{"
				+ unsolved[8][7] + "}{" + unsolved[8][8] + "}\n" + "\\node[anchor=center] at (4.5, -0.5) {"
				+ convertNumberToJapanese(index) + "};\n" + "\\begin{scope}[" + color + ", font=\\sffamily\\slshape]\n"
				+ "\\setcounter{row}{1}\n" + "\\setrow {" + solved[0][0] + "}{" + solved[0][1] + "}{" + solved[0][2]
				+ "}  {" + solved[0][3] + "}{" + solved[0][4] + "}{" + solved[0][5] + "}  {" + solved[0][6] + "}{"
				+ solved[0][7] + "}{" + solved[0][8] + "}\n" + "\\setrow {" + solved[1][0] + "}{" + solved[1][1] + "}{"
				+ solved[1][2] + "}  {" + solved[1][3] + "}{" + solved[1][4] + "}{" + solved[1][5] + "}  {"
				+ solved[1][6] + "}{" + solved[1][7] + "}{" + solved[1][8] + "}\n" + "\\setrow {" + solved[2][0] + "}{"
				+ solved[2][1] + "}{" + solved[2][2] + "}  {" + solved[2][3] + "}{" + solved[2][4] + "}{" + solved[2][5]
				+ "}  {" + solved[2][6] + "}{" + solved[2][7] + "}{" + solved[2][8] + "}\n" + "\n" + "\\setrow {"
				+ solved[3][0] + "}{" + solved[3][1] + "}{" + solved[3][2] + "}  {" + solved[3][3] + "}{" + solved[3][4]
				+ "}{" + solved[3][5] + "}  {" + solved[3][6] + "}{" + solved[3][7] + "}{" + solved[3][8] + "}\n"
				+ "\\setrow {" + solved[4][0] + "}{" + solved[4][1] + "}{" + solved[4][2] + "}  {" + solved[4][3] + "}{"
				+ solved[4][4] + "}{" + solved[4][5] + "}  {" + solved[4][6] + "}{" + solved[4][7] + "}{" + solved[4][8]
				+ "}\n" + "\\setrow {" + solved[5][0] + "}{" + solved[5][1] + "}{" + solved[5][2] + "}  {"
				+ solved[5][3] + "}{" + solved[5][4] + "}{" + solved[5][5] + "}  {" + solved[5][6] + "}{" + solved[5][7]
				+ "}{" + solved[5][8] + "}\n" + "\n" + "\\setrow {" + solved[6][0] + "}{" + solved[6][1] + "}{"
				+ solved[6][2] + "}  {" + solved[6][3] + "}{" + solved[6][4] + "}{" + solved[6][5] + "}  {"
				+ solved[6][6] + "}{" + solved[6][7] + "}{" + solved[6][8] + "}\n" + "\\setrow {" + solved[7][0] + "}{"
				+ solved[7][1] + "}{" + solved[7][2] + "}  {" + solved[7][3] + "}{" + solved[7][4] + "}{" + solved[7][5]
				+ "}  {" + solved[7][6] + "}{" + solved[7][7] + "}{" + solved[7][8] + "}\n" + "\\setrow {"
				+ solved[8][0] + "}{" + solved[8][1] + "}{" + solved[8][2] + "}  {" + solved[8][3] + "}{" + solved[8][4]
				+ "}{" + solved[8][5] + "}  {" + solved[8][6] + "}{" + solved[8][7] + "}{" + solved[8][8] + "}\n"
				+ "\\end{scope}\n" + "\\end{scope}\n" + "\\end{tikzpicture}";
	}

	/**
	 * Translate a number to Japanese.
	 * 
	 * @param toConvert
	 *            Number to translate.
	 * @return Japanese translation of the given number.
	 */
	public static final String convertNumberToJapanese(final int toConvert) {
		String translation = "";
		int ones = toConvert % 10;
		int tens = (toConvert / 10) % 10;
		int hundreds = (toConvert / 100) % 10;
		int thousands = (toConvert / 1000) % 10;

		if (thousands > 1) {
			translation += (J_NUMBER_COMMANDS.get(thousands) + J_NUMBER_COMMANDS.get(1000));
		} else {
			if (thousands == 1) {
				translation += J_NUMBER_COMMANDS.get(1000);
			}
		}
		if (hundreds > 1) {
			translation += (J_NUMBER_COMMANDS.get(hundreds) + J_NUMBER_COMMANDS.get(100));
		} else {
			if (hundreds == 1) {
				translation += J_NUMBER_COMMANDS.get(100);
			}
		}
		if (tens > 1) {
			translation += (J_NUMBER_COMMANDS.get(tens) + J_NUMBER_COMMANDS.get(10));
		} else {
			if (tens == 1) {
				translation += J_NUMBER_COMMANDS.get(10);
			}
		}
		if (ones > 0) {
			translation += J_NUMBER_COMMANDS.get(ones);
		}
		return translation;
	}
}
