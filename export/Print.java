package export;

import main.Author;
import main.Book;
import main.Genre;
import main.GoodReadsData;
import main.Shelf;

public abstract class Print {
		
	/** This prints a print-friendly String of all the contents of each shelf,  
	 */
	public static void libraryShelves(GoodReadsData library) {
		String printableList = "";
		for (Shelf sh : library.getListShelves().values()) {
			printableList = printableList.concat(sh + "\n");
		}
		System.out.println(printableList);
	}
	
	/** This prints a print-friendly String of all the authors, 
	 */
	public static void libraryAuthors(GoodReadsData library) {
		String printableList = "";
		for (Author au : library.getListAuthors().values()) {
			printableList = printableList.concat(au + "\n");
		}
		System.out.println(printableList);
	}	
	
	/** This prints a print-friendly String of all the contents of each shelf, 
	 */
	public static void libraryBooks(GoodReadsData library) {
		String printableList = "";
		for (Book bo : library.getListBooks().values()) {
			printableList = printableList.concat(bo + "\n");
		}
		System.out.println(printableList);
	}
	
	/** This prints a print-friendly String of all the genres in the library
	 */
	public static void libraryGenres(GoodReadsData library) {
		String printableList = "";
		for (Genre g : library.getListGenres().values()) {
			printableList = printableList.concat(g.toStringLong() + "\n");
		}
		System.out.println(printableList);
	}	
}
