import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class OrderBy {

	/** This returns a print friendly string with the books in the HashMap listOfBooks ordered by year
	 * of first publication, from oldest to most recent, one on each line, with in parenthesis the year.
	 * Note: some books are in the goodreads database as having no year of first publication, so for those
	 * the year ends up being zero, which could be excluded if I decided so...
	 * @param listOfBooks 
	 */
	public static String publicationDate(HashMap<String, Book> listOfBooks) {
		List<Book> orderedList = new ArrayList<Book>(listOfBooks.values());
		Collections.sort(orderedList, new Comparator<Book>() {
			public int compare(Book b1, Book b2) {
				double age1 = 0;
				double age2 = 0;
				if (b1.getYearPublished()!= null && b1.getYearPublished().length() >0){
					age1 = Double.parseDouble(b1.getYearPublished());
				}
				if (b2.getYearPublished()!= null && b2.getYearPublished().length() >0){
					age2 = Double.parseDouble(b2.getYearPublished());
				}
				return (int) age1  - (int) age2;
			}
		});
		String toPrint = "Ordered by year of first publication:\n";
		for (Book b : orderedList) {
			toPrint = toPrint.concat(b + " (" + b.getYearPublished() + ")\n");
		}
		return toPrint;
	}
	
}
