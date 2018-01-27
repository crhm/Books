package ordering;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import main.Author;
import main.Book;

/** This class holds methods that, given a Hashmap<String, Book>, output a String listing authors ordered 
 * according to a certain criteria (made explicit by the method name)
 * <br>Each method's order can be reversed using the second argument it is passed, the boolean. 
 * <br>Methods so far:
 * <br>Orber by last Name
 * <br>Order by first Name
 * <br>Order by number of books in lisst
 * <br>Order by average publication year
 * <br>Order by percentage of books in list read
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

	/** This method returns a string of the authors of the books on the list, ordered by 
	 * the number of books by them contained in the list (indicated in parenthesis next to their name in  
	 * the output).<br>Order can be ascending (flag = true) or descending (flag = false).
	 * @param listOfBooks Hashmap containing the books to be analysed. Cannot be null or empty
	 * @param flag Boolean, determines the order of the list (ascending or descending)
	 * @return A printable string with a context-dependent title, and then one author per 
	 * line, with the number of their books in the list in parenthesis.
	 */
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
					if (flag == true) {
						return calculateNumberOfBooksInList(listOfBooks, a1) - calculateNumberOfBooksInList(listOfBooks, a2);
					} else {
						return calculateNumberOfBooksInList(listOfBooks, a2) - calculateNumberOfBooksInList(listOfBooks, a1);
					}
				}
			});
			// Making sure label is appropriate to flag
			if (flag == true) {
				toPrint = toPrint.concat("Authors ordered by number of books in this list (smallest to largest):\n");
			} else {
				toPrint = toPrint.concat("Authors ordered by number of books in this list (largest to smallest):\n");
			}
			// Print friendly formatting
			for (Author a : orderedList) {
				toPrint = toPrint.concat(a + " (" + calculateNumberOfBooksInList(listOfBooks, a) + ")\n");
			}
			return toPrint;
		}
	}
	
	/** This method returns a string of the authors of the books on the list, ordered by 
	 * the year of publication of books by them contained in the list (indicated in parenthesis 
	 * next to their name in the output).<br>Order can be ascending (flag = true) or descending (flag = false).
	 * <br>Authors with no books with a publication date are sorted as -11111 and displayed as 'none found'.
	 * @param listOfBooks Hashmap containing the books to be analysed. Cannot be null or empty
	 * @param flag Boolean, determines the order of the list (ascending or descending)
	 * @return A printable string with a context-dependent title, and then one author per 
	 * line, with the average year of publication of their books in the list in parenthesis.
	 */
	public static String averagePublicationYear(HashMap<String, Book> listOfBooks, Boolean flag) {
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
					if (flag == true) {
						return calculateAveragePublicationYearInList(listOfBooks, a1) - calculateAveragePublicationYearInList(listOfBooks, a2);
					} else {
						return calculateAveragePublicationYearInList(listOfBooks, a2) - calculateAveragePublicationYearInList(listOfBooks, a1);
					}
				}
			});
			// Making sure label is appropriate to flag
			if (flag == true) {
				toPrint = toPrint.concat("Authors ordered by average publication year for their books in this list (earliest to latest):\n");
			} else {
				toPrint = toPrint.concat("Authors ordered by average publication year for their books in this list (latest to earliest):\n");
			}
			// Print friendly formatting
			for (Author a : orderedList) {
				if (calculateAveragePublicationYearInList(listOfBooks, a)==-11111) {
					toPrint = toPrint.concat(a + " (None found)\n");
				} else {
					toPrint = toPrint.concat(a + " (" + calculateAveragePublicationYearInList(listOfBooks, a) + ")\n");
				}
			}
			return toPrint;
		}
	}

	/** This method returns a string of the authors of the books on the list, ordered by 
	 * the percentage of books by them contained in the list that have been read (indicated in parenthesis 
	 * next to their name in the output).<br>Order can be ascending (flag = true) or descending (flag = false).
	 * @param listOfBooks Hashmap containing the books to be analysed. Cannot be null or empty
	 * @param flag Boolean, determines the order of the list (ascending or descending)
	 * @return A printable string with a context-dependent title, and then one author per 
	 * line, with the percentage of their books in the list that has been read in parenthesis.
	 */
	public static String readRatio(HashMap<String, Book> listOfBooks, Boolean flag) {
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
					if (flag == true) {
						return calculatePercentageBooksRead(listOfBooks, a1) - calculatePercentageBooksRead(listOfBooks, a2);
					} else {
						return calculatePercentageBooksRead(listOfBooks, a2) - calculatePercentageBooksRead(listOfBooks, a1);
					}
				}
			});
			// Making sure label is appropriate to flag
			if (flag == true) {
				toPrint = toPrint.concat("Authors ordered by percentage of books read (ascending):\n");
			} else {
				toPrint = toPrint.concat("Authors ordered by percentage of books read (descending):\n");
			}
			// Print friendly formatting
			for (Author a : orderedList) {
				toPrint = toPrint.concat(a + " (Read: " + calculatePercentageBooksRead(listOfBooks, a) + "% of books in list)\n");
			}
			return toPrint;
		}
	}
	
	/** Needed because behavior needs to be flexible between the case where listOfBooks is a full library,
	 *  and the case where it is not and the author's listofBooks needs to be restricted.<br>Works 
	 *  in both cases.<br>Returns -11111 if no publication years for any books by the author have been found
	 * @param listOfBooks
	 * @param a
	 * @return the average publication year of books by the author found in the list (int)
	 */
	private static int calculateAveragePublicationYearInList(HashMap<String, Book> listOfBooks, Author a) {
		BigDecimal numberOfBooksInList = new BigDecimal(0);
		BigDecimal sumPublicationYears = new BigDecimal(0);
		for (Book b : a.getListOfBooks().getList().values()) {
			if (listOfBooks.containsValue(b) && !b.getYearPublished().isEmpty()) {
				numberOfBooksInList =  numberOfBooksInList.add(new BigDecimal(1));
				sumPublicationYears = sumPublicationYears.add(new BigDecimal(b.getYearPublished()));
			}
		}
		if (numberOfBooksInList.doubleValue() != 0) { //Needed to avoid a 'divide by zero' error
			BigDecimal average = sumPublicationYears.divide(numberOfBooksInList, 1, RoundingMode.HALF_UP);
			return average.intValue();
		} else { 
			return -11111;
		}	
	}
	
	/** Needed for modularity and because behavior needs to be flexible between the case where listOfBooks is a full library,
	 *  and the case where it is not and the author's listofBooks needs to be restricted.
	 *  <br>Works in both cases.
	 * @param listOfBooks
	 * @param a
	 * @return the number of books the authors has in listOfBooks (int)
	 */
	private static int calculateNumberOfBooksInList(HashMap<String, Book> listOfBooks, Author a) {
		int numberOfBooksInList = 0;
		for (Book b : a.getListOfBooks().getList().values()) {
			if (listOfBooks.containsValue(b)) {
				numberOfBooksInList++;
			}
		}
		return numberOfBooksInList;
	}

	/**Needed for modularity
	 * 
	 * @param listOfBooks
	 * @param a
	 * @return the percentage of books read for the author among books of his in the list.
	 */
	private static int calculatePercentageBooksRead(HashMap<String, Book> listOfBooks, Author a) {
		BigDecimal numberOfBooksInList = new BigDecimal(0);
		BigDecimal numberOfBooksRead = new BigDecimal(0);
		for (Book b : a.getListOfBooks().getList().values()) {
			if (listOfBooks.containsValue(b)) {
				numberOfBooksInList =  numberOfBooksInList.add(new BigDecimal(1));
				if (b.getShelf().getName().equals("read")) {
					numberOfBooksRead = numberOfBooksRead.add(new BigDecimal(1));
				}
			}	
		}
		BigDecimal average = numberOfBooksRead.divide(numberOfBooksInList, 3, RoundingMode.HALF_UP);
		average = average.multiply(new BigDecimal(100));
		return average.intValue();
	}
	
}