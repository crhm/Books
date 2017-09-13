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
	 * the year ends up being zero, which could be excluded if I decided so... In the meantime, they get 
	 * displayed as empty parenthises, but compared as 0s.
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
		String toPrint = "Ordered by year of first publication:\n\n";
		for (Book b : orderedList) {
			toPrint = toPrint.concat(b + " (" + b.getYearPublished() + ")\n");
		}
		return toPrint;
	}

//	TODO Figure out if I want to avoid special characters such as accents to be counted with their actual 
//	Unicode or that of the equivalent non special character (i.e. É to E)
//	TODO Figure out if this is affected by capitalisation
//	TODO Figure out if you want to put books that start with The to be ordered by their second word or not
	/** This returns a print friendly string of all the books passed in the HashMap listOfBooks ordered by book
	 * title, alphabetically if flag = true and reverse-alphabetically if flag=false. Books with titles starting
	 * with numbers go before A, and books starting with "the" are under "T".
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean that determines whether order is normal or inverted.
	 * @return A string with one book per line, in their toString format, in the order specified by the flag
	 */
	public static String title(HashMap<String, Book> listOfBooks, Boolean flag) {
		List<Book> orderedList = new ArrayList<Book>(listOfBooks.values());
		Collections.sort(orderedList, new Comparator<Book>() {
			public int compare(Book b1, Book b2) {
				if (flag == true) {
					return b1.getTitle().compareTo(b2.getTitle());
				} else {
					return b2.getTitle().compareTo(b1.getTitle());
				}
			}
		});
		String toPrint = "Ordered by book title:\n\n";
		for (Book b : orderedList) {
			toPrint = toPrint.concat(b + "\n");
		}
		return toPrint;
	}
	
//	TODO Figure out if I want to avoid special characters such as accents to be counted with their actual 
//	Unicode or that of the equivalent non special character (i.e. É to E)
//	TODO Figure out if this is affected by capitalisation
//	TODO Figure out if you the returned string to not be the normal book.toString and place the author's name
//	first rather than the book title, so that it is more clear that it is ordered	
	/** This returns a print friendly string of all the books passed in the HashMap listOfBooks ordered by authors'
	 * last names, alphabetically if flag = true and reverse-alphabetically if flag=false.
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean that determines whether order is normal or inverted.
	 * @return A string with one book per line, in their toString format, in the order specified by the flag
	 */
	public static String lastName(HashMap<String, Book> listOfBooks, Boolean flag) {
		List<Book> orderedList = new ArrayList<Book>(listOfBooks.values());
		Collections.sort(orderedList, new Comparator<Book>() {
			public int compare(Book b1, Book b2) {
				if (flag == true) {
					return b1.getAuthor().getLastName().compareTo(b2.getAuthor().getLastName());
				} else {
					return b2.getAuthor().getLastName().compareTo(b1.getAuthor().getLastName());
				}
			}
		});
		String toPrint = "Ordered by authors last names:\n\n";
		for (Book b : orderedList) {
			toPrint = toPrint.concat(b + "\n");
		}
		return toPrint;
	}
	
}
