import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/** This class holds methods that, given a Hashmap<String, Book>, output a String listing authors ordered 
 * according to a certain criteria (made explicit by the method name)
 * <br>Each method's order can be reversed using the second argument it is passed, the boolean. 
 * <br>Methods so far:
 * <br>Orber by last Name
 * <br>Order by first Name
 * @author crhm
 *
 */
public class OrderAuthorsBy {
	
	/**Method that outputs a string of the authors of the books of list of books, sorted alphabetically
	 * by their last name if Boolean parameter flag is true, or in reverse alphabetical order if 
	 *  Boolean flag is false.<br>Different from OrderBooksBy.lastName in that it doesn't print
	 *  the books in question after the name of the author. 
	 */
	public static String lastName(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			// To avoid repetition of authors
			HashMap<String, Author> listAuthors = new HashMap<String, Author>();
			for (Book b : listOfBooks.values()) {
				listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
			}
			List<Author> orderedList = new ArrayList<Author>(listAuthors.values());
			Collections.sort(orderedList, new Comparator<Author>() {
				public int compare(Author a1, Author a2) {
					// This allows the comparison to treat accented characters (diacritical ones) like normal ones
					// And not place them in the sort according to their true unicode value
					String temp1 = Normalizer.normalize(a1.getLastName(), Normalizer.Form.NFD);
					String temp2 = Normalizer.normalize(a2.getLastName(), Normalizer.Form.NFD);
					// This is to make sure that comparisons will disregard capitalisation, which impacts unicode value
					temp1 = temp1.toLowerCase();
					temp2 = temp2.toLowerCase();
					if (flag == true) {
						return temp1.compareTo(temp2);
					} else {
						return temp2.compareTo(temp1);
					}
				}
			});
			String toPrint = "";
			if (flag == true) {
				toPrint = toPrint.concat("\nList of authors in alphabetical order by last name:\n\n");
			} else {
				toPrint = toPrint.concat("\nList of authors in reverse alphabetical order by last name:\n\n");
			}
			for (Author a : orderedList) {
				toPrint = toPrint.concat(a.getLastName() + ", " + a.getFirstName() + "\n");
			}
			return toPrint;
		}
	}
	
	/**Method that outputs a string of the authors of the books of list of books, sorted alphabetically
	 * by their first name if Boolean parameter flag is true, or in reverse alphabetical order if 
	 *  Boolean flag is false.<br>Uses author's last Name if they don't have a first name. 
	 */
	public static String firstName(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			// To avoid repetition of authors
			HashMap<String, Author> listAuthors = new HashMap<String, Author>();
			for (Book b : listOfBooks.values()) {
				listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
			}
			List<Author> orderedList = new ArrayList<Author>(listAuthors.values());
			Collections.sort(orderedList, new Comparator<Author>() {
				public int compare(Author a1, Author a2) {
					String temp1 = "";
					String temp2 = "";
					// This allows the comparison to treat accented characters (diacritical ones) like normal ones
					// And not place them in the sort according to their true unicode value
					//
					// And avoids the problems of authors with no first names by treating their last names as
					// their first for the purposes of this sort
					if (a1.getFirstName().length() == 0) {
						temp1 = Normalizer.normalize(a1.getLastName(), Normalizer.Form.NFD);
					} else {
						temp1 = Normalizer.normalize(a1.getFirstName(), Normalizer.Form.NFD);
					}
					if (a2.getFirstName().length() == 0) {
						temp2 = Normalizer.normalize(a2.getLastName(), Normalizer.Form.NFD);
					} else {
						temp2 = Normalizer.normalize(a2.getFirstName(), Normalizer.Form.NFD);
					}
					// This is to make sure that comparisons will disregard capitalisation, which impacts unicode value
					temp1 = temp1.toLowerCase();
					temp2 = temp2.toLowerCase();
					if (flag == true) {
						return temp1.compareTo(temp2);
					} else {
						return temp2.compareTo(temp1);
					}
				}
			});
			String toPrint = "";
			if (flag == true) {
				toPrint = toPrint.concat("\nList of authors in alphabetical order by first name:\n\n");
			} else {
				toPrint = toPrint.concat("\nList of authors in reverse alphabetical order by first name:\n\n");
			}
			for (Author a : orderedList) {
				toPrint = toPrint.concat(a + "\n");
			}
			return toPrint;
		}
	}

	
	public static String numberOfBooks(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			String toPrint = "";
			// To avoid repetition of authors
			HashMap<String, Author> listAuthors = new HashMap<String, Author>();
			for (Book b : listOfBooks.values()) {
				listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
			}
			// Sorting them
			List<Author> orderedList = new ArrayList<Author>(listAuthors.values());
			Collections.sort(orderedList, new Comparator<Author>() {
				public int compare(Author a1, Author a2) {
					// So that if the list is not the entire library, not books are counted that are outside the list
					int numberOfBooksInList1 = 0;
					int numberOfBooksInList2 = 0;
					for (Book b : a1.getListOfBooks().values()) {
						if (listOfBooks.containsValue(b)) {
							numberOfBooksInList1++;
						}
					}
					for (Book b : a2.getListOfBooks().values()) {
						if (listOfBooks.containsValue(b)) {
							numberOfBooksInList2++;
						}
					}
					if (flag == true) {
						return numberOfBooksInList1 - numberOfBooksInList2;
					} else {
						return numberOfBooksInList2 - numberOfBooksInList1;
					}
				}
			});
			// Making sure label is appropriate to flag
			if (flag == true) {
				toPrint = toPrint.concat("Authors ordered by number of books in this list (smallest to largest):\n");
			} else {
				toPrint = toPrint.concat("Authors ordered by number of books in this list (largest to smallest):\n");
			}
			
			for (Author a : orderedList) {
				// So that if the list is not the entire library, not books are counted that are outside the list
				int numberOfBooksInList = 0;
				for (Book b : a.getListOfBooks().values()) {
					if (listOfBooks.containsValue(b)) {
						numberOfBooksInList++;
					}
				}
				toPrint = toPrint.concat(a + " (" + numberOfBooksInList + ")\n");
			}
			return toPrint;
		}
	}
	
}
