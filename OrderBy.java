import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class OrderBy {

	// TODO figure out if I want to exclude the books without a year of publication...
	/** This returns a print friendly string consisting of the books in the HashMap listOfBooks ordered by year
	 * of first publication (increasing or decreasing depending on parameter flag). 
	 * Each book is on one line, with the year in parenthesis.
	 * Note: some books are in the goodreads database as having no year of first publication, so for those
	 * the year ends up being zero, which could be excluded if I decided so...
	 * @param listOfBooks 
	 * @param flag if true, order is increasing, if false, order is decreasing
	 */
	public static String publicationDate(HashMap<String, Book> listOfBooks, Boolean flag) {
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
				if (flag == true) {
					return (int) age1  - (int) age2;
				} else {
					return (int) age2  - (int) age1;
				}
				
			}
		});
		String toPrint = "Ordered by year of first publication:\n";
		for (Book b : orderedList) {
			toPrint = toPrint.concat(b + " (" + b.getYearPublished() + ")\n");
		}
		return toPrint;
	}
	
}
