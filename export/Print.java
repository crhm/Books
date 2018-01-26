package export;

import main.Genre;
import main.GoodReadsData;
import main.Shelf;

public abstract class Print {

	
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
