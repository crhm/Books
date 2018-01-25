package ordering;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import main.Book;
import main.Genre;

/**
 * 
 */

/**
 * @author Laurene
 *
 */
public class OrderGenresBy {
	
	/** This method sorts genres by the number of books assigned to it in the list.
	 * It can return the sorted list in ascending order (flag = true) or descending 
	 * order (flag = false).<br>Works for both libraries and subsets of libraries.
	 * @param listOfBooks The list of books whose genres are to be sorted
	 * @param flag determines ascending or descending order of sort
	 * @return A string with one genre per line, followed by the number of books 
	 * found for it in the list in parenthesis.
	 */
	public static String numberOfBooks(HashMap<String, Book> listOfBooks, Boolean flag) {
		String toPrint = "";
		HashMap<Genre, Integer> listGenres = new HashMap<Genre, Integer>();
		for (Book b : listOfBooks.values()) {
			for (Genre g : b.getListGenres().keySet()) {
				if (listGenres.containsKey(g)) {
					int temp = listGenres.get(g);
					listGenres.put(g, temp+1);
				} else {
					listGenres.put(g, 1);
				}
			}
		}
		List<Genre> orderedList = new ArrayList<Genre>(listGenres.keySet()); 
		Collections.sort(orderedList, new Comparator<Genre>() {
			public int compare(Genre g1, Genre g2) {
				if (flag == true) {
					return listGenres.get(g1) - listGenres.get(g2);
				} else {
					return listGenres.get(g2) - listGenres.get(g1);
				}
			}
		});
		if (flag == true) {
			toPrint = toPrint.concat("Genres found for books in this list, sorted by number of "
					+ "books found for each (least to most):\n\n");
		} else {
			toPrint = toPrint.concat("Genres found for books in this list, sorted by number of "
					+ "books found for each (most to least):\n\n");			
		}
		for (Genre g : orderedList) {
			toPrint = toPrint.concat(g.getName() + " (" + listGenres.get(g) + ")\n");
		}
		return toPrint;
	}

}
