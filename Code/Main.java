import java.io.FileNotFoundException;

/**
 * Main program.
 * 
 * @author André Dalwigk
 * @version 1.0
 *
 */
public final class Main {
	/**
	 * Main function.
	 * 
	 * @param arguments
	 * @throws FileNotFoundException
	 */
	public static final void main(String... arguments) throws FileNotFoundException {
		final Document document = new Document("sudoku", "tex", Document.Language.JP);
		document.createSudokuPuzzles(12);
		document.add(LaTeXConverter.IMPORTS);
		document.add(LaTeXConverter.J_CHARS);
		document.add(LaTeXConverter.COMMANDS);
		document.addTitle("Sudoku auf Japanisch");
		document.addAuthor("F. André Dalwigk");
		document.beginDocument();
		document.add(LaTeXConverter.MAKE_TITLE);
		document.add(LaTeXConverter.COVER_IMAGE);
		document.add(LaTeXConverter.PREFACE);
		document.add(LaTeXConverter.NEW_PAGE);
		document.addSudokuPuzzles();
		document.add(LaTeXConverter.NEW_PAGE);
		document.addSudokuSolutions();
		document.endDocument();
		document.print();
	}
}
