
public abstract class Printable {
		
	/** This returns a print-friendly String of all the contents of each shelf, 
	 * @return each shelf, to string, ending with a new line
	 */
	public static String libraryShelves(GoodReadsData library) {
		String printableList = "";
		for (Shelf sh : library.getListShelves().values()) {
			printableList = printableList.concat(sh + "\n");
		}
		return printableList;
	}
	
	/** This returns a print-friendly String of all the authors, 
	 * @return each author, to string, on a separate line
	 */
	public static String libraryAuthors(GoodReadsData library) {
		String printableList = "";
		for (Author au : library.getListAuthors().values()) {
			printableList = printableList.concat(au + "\n");
		}
		return printableList;
	}	
	
	/** This returns a print-friendly String of all the contents of each shelf, 
	 * @return each book, to string, on a separate line
	 */
	public static String libraryBooks(GoodReadsData library) {
		String printableList = "";
		for (Book bo : library.getListBooks().values()) {
			printableList = printableList.concat(bo + "\n");
		}
		return printableList;
	}
	
	/** This returns a print-friendly String of all the genres in the library
	 * @return Each genre's detailed information, on a separate line
	 */
	public static String libraryGenres(GoodReadsData library) {
		String printableList = "";
		for (Genre g : library.getListGenres().values()) {
			printableList = printableList.concat(g.toStringLong() + "\n");
		}
		return printableList;
	}	
}
