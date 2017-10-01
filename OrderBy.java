import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Date;



/** This class holds methods that, given a Hashmap<String, Book>, output a String listing books ordered 
 * according to a certain criteria (made explicit by the method name)
 * <br>Each method's order can be reversed using the second argument it is passed, the boolean. 
 * <br><br>Methods so far are: 
 * <br>Order by Publication year, 
 * <br>Order by Book title, 
 * <br>Order by Author's last name (not first name since some authors do not have one), 
 * <br>Order by Number of Pages, 
 * <br>Order by date added, 
 * <br>Order by date read, 
 * <br>Order by general rating, 
 * <br>Order by user rating
 * @author Laurene
 */
public class OrderBy {

	/** This returns a print friendly string consisting of the books in the HashMap listOfBooks ordered by year
	 * of first publication (increasing or decreasing depending on parameter flag). 
	 * <br>Each book is on one line, with the year in parenthesis.
	 * <br>Note: some books are in the goodreads database as having no year of first publication, so they are not
	 * ordered with the others but listed at the end, separately.
	 * @param listOfBooks HashMap containing the books to be ordered
	 * @param flag if true, order is increasing, if false, order is decreasing
	 */
	public static String publicationDate(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			List<Book> withPubDate = new ArrayList<Book>();
			List<Book> withoutPubDate = new ArrayList<Book>();
			String toPrint = "";
			
			for (Book b : listOfBooks.values()) {
				if (b.getYearPublished().isEmpty()) {
					withoutPubDate.add(b);
				} else {
					withPubDate.add(b);
				}
			}
			if (!withPubDate.isEmpty()) {
				Collections.sort(withPubDate, new Comparator<Book>() {
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
				toPrint = toPrint.concat("\nOrdered by year of first publication:\n\n");
				for (Book b : withPubDate) {
					toPrint = toPrint.concat(b + " (" + b.getYearPublished() + ")\n");
				}
			} else {
				toPrint = toPrint.concat("\nSorry, no books with recorded year of first publication were found.\n\n");
			}
			
			if (!withoutPubDate.isEmpty()) {
				toPrint = toPrint.concat("\nBooks with no recorded year of first publication:\n");
				for (Book b : withoutPubDate) {
					toPrint = toPrint.concat(b + "\n");
				}
			}	
			return toPrint;
		}
	}

	/** This returns a print friendly string of all the books passed in the HashMap listOfBooks ordered by book
	 * title, alphabetically if flag = true and reverse-alphabetically if flag=false. 
	 * <br>Books with titles starting with numbers go before A, and books starting with "the" are under "T".
	 * <br>Sorting disregards capitalization and accents.
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean that determines whether order is normal or inverted.
	 * @return A string with one book per line, in their toString format, in the order specified by the flag
	 */
	public static String title(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			List<Book> orderedList = new ArrayList<Book>(listOfBooks.values());
			Collections.sort(orderedList, new Comparator<Book>() {
				public int compare(Book b1, Book b2) {
					// To make sort insensitive to capitalisation (since unicode value is different)
					String temp1 = b1.getTitle().toLowerCase();
					String temp2 = b2.getTitle().toLowerCase();
					// To treat accented characters like non-accented ones (diacritic characters)
					temp1 = Normalizer.normalize(temp1, Normalizer.Form.NFD);
					temp2 = Normalizer.normalize(temp2, Normalizer.Form.NFD);
					if (flag == true) {
						return temp1.compareTo(temp2);
					} else {
						return temp2.compareTo(temp1);
					}
				}
			});
			String toPrint = "Ordered by book title:\n\n";
			for (Book b : orderedList) {
				toPrint = toPrint.concat(b + "\n");
			}
			return toPrint;

		}
	}
	
	/** This returns a print friendly string of all the books passed in the HashMap listOfBooks ordered by authors'
	 * last names, alphabetically if flag = true and reverse-alphabetically if flag=false.
	 * <br>It ignores capitalisation and treats accents (diacritic characters) like their non-accented version.
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean that determines whether order is normal or inverted.
	 * @return A string with one book per line, in their toString format, in the order specified by the flag
	 */
	public static String lastName(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			List<Book> orderedList = new ArrayList<Book>(listOfBooks.values());
			Collections.sort(orderedList, new Comparator<Book>() {
				public int compare(Book b1, Book b2) {
					// This allows the comparison to treat accented characters (diacritical ones) like normal ones
					// And not place them in the sort according to their true unicode value
					String temp1 = Normalizer.normalize(b1.getAuthor().getLastName(), Normalizer.Form.NFD);
					String temp2 = Normalizer.normalize(b2.getAuthor().getLastName(), Normalizer.Form.NFD);
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
			String toPrint = "Ordered by authors last names:\n\n";
			for (Book b : orderedList) {
				toPrint = toPrint.concat(b.getAuthor().getLastName() + ", " + b.getAuthor().getFirstName() 
						+ ", " + b.getTitle() + "\n");
			}
			return toPrint;
		}
	}
	
	/** This returns a print friendly string consisting of the books in the HashMap listOfBooks ordered by
	 * number of pages (increasing or decreasing depending on parameter flag). 
	 * <br>Each book is on one line, with the number of pages in parenthesis.
	 * <br>Note: some books are in the goodreads database as having no number of pages, so those are not ordered
	 * with the others but listed separately at the end.
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean. If true, order is increasing, if false, order is decreasing
	 */
	public static String numberOfPages(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			List<Book> withNumPage = new ArrayList<Book>();
			List<Book> withoutNumPage = new ArrayList<Book>();
			String toPrint = "";
			
			for (Book b : listOfBooks.values()) {
				if (b.getNumPages() > 0) {
					withNumPage.add(b);
				} else {
					withoutNumPage.add(b);
				}
			}
			
			if (!withNumPage.isEmpty()) {
				Collections.sort(withNumPage, new Comparator<Book>() {
					public int compare(Book b1, Book b2) {
						if (flag == true) {
							return (int) (b1.getNumPages() - b2.getNumPages());
						} else {
							return (int) (b2.getNumPages() - b1.getNumPages());
						}
					}
				});
				toPrint = toPrint.concat("\nOrdered by number of pages:\n\n");
				for (Book b : withNumPage) {
					toPrint = toPrint.concat(b + " (" + (int) b.getNumPages() + " pages)\n");
				}
			} else {
				toPrint = toPrint.concat("\nSorry, no books with recorded number of pages were found.\n\n");
			}
			
			if (!withoutNumPage.isEmpty()) {
				toPrint = toPrint.concat("\nBooks with no recorded number of pages:\n\n");
				for (Book b : withoutNumPage) {
					toPrint = toPrint.concat(b + "\n");
				}
			}			
			return toPrint;
		}
	}
	
	
//	TODO Fix this ugly try catch	
	/** This returns a print friendly string of the books passed in listOfBooks order by date added to the
	 * library, from earliest to latest if flag = true and the opposite if flag = false. 
	 * <br>At this stage the try catch is terrible terrible temporary fix but it works
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean. If true, order is earliest to latest, if false, order is latest to earliest
	 * @return A string with one book per line in its toString form, followed by the date it was added
	 * in parenthesis
	 */
	public static String dateAdded(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			List<Book> orderedList = new ArrayList<Book>(listOfBooks.values());
			Collections.sort(orderedList, new Comparator<Book>() {
				public int compare (Book b1, Book b2) {
					String[] temp1 = b1.getDateAdded().split("/");
					String[] temp2 = b2.getDateAdded().split("/");
					
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					
					try {
						Date d1 = sdf1.parse(temp1[0] + "-" + temp1[1] + "-" + temp1[2]);
						Date d2 = sdf2.parse(temp2[0] + "-" + temp2[1] + "-" + temp2[2]);
						if (flag == true) {
							return d1.compareTo(d2);
						} else {
							return d2.compareTo(d1);
						}
					} catch (ParseException e) {
						e.printStackTrace();
						return  0;
					}

				}
			});
			String toPrint = "Ordered by date it was added to Shelves:\n\n";
			for (Book b : orderedList) {
				toPrint = toPrint.concat(b + " (" + b.getDateAdded() + ")\n");
			}
			return toPrint;
		}
	}
	
//	TODO Fix this ugly try catch	
	/** This returns a print friendly string of ONLY the books passed in listOfBooks that have a "read"
	 * date, (SO BOOKS READ BUT NOT GIVEN A READ DATE WILL NOT APPEAR) ordered by date read, 
	 * from earliest to latest if flag = true and the opposite if flag = false. 
	 * <br>At this stage the try catch is terrible terrible temporary fix but it works
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean. If true, order is earliest to latest, if false, order is latest to earliest
	 * @return A string with one book per line in its toString form, followed by the date it was read
	 * in parenthesis
	 */
	public static String dateRead(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			ArrayList<Book> readBooks = new ArrayList<Book>();
			for (Book b : listOfBooks.values()) {
				if (b.getDateRead()!= null && b.getDateRead().length() >0) {
					readBooks.add(b);
				}
			}
			List<Book> orderedList = readBooks;
			Collections.sort(orderedList, new Comparator<Book>() {
				public int compare (Book b1, Book b2) {
					String[] temp1 = b1.getDateRead().split("/");
					String[] temp2 = b2.getDateRead().split("/");
					
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					
					try {
						Date d1 = sdf1.parse(temp1[0] + "-" + temp1[1] + "-" + temp1[2]);
						Date d2 = sdf2.parse(temp2[0] + "-" + temp2[1] + "-" + temp2[2]);
						if (flag == true) {
							return d1.compareTo(d2);
						} else {
							return d2.compareTo(d1);
						}
					} catch (ParseException e) {
						e.printStackTrace();
						return  0;
					}

				}
			});
			String toPrint = "Books read, ordered by date read:\n\n";
			for (Book b : orderedList) {
				toPrint = toPrint.concat(b + " (" + b.getDateRead() + ")\n");
			}
			return toPrint;
		}
	}
	
	/** This returns a print friendly string of books passed in listOfBooks ordered by their general, collective
	 * Goodreads rating, from lowest to highest if flag = true and the opposite if flag = false.
	 * <br>Books with no ratings (aka where rating !> 0) are listed separately, after the sorted list.
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean. If true, order is lowest to highest, if false, order is highest to lowest
	 * @return a string with one book per line in its toString form, followed by their rating in parenthesis
	 */
	public static String genRating(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			List<Book> noRating = new ArrayList<Book>();
			List<Book> withRating = new ArrayList<Book>();
			for (Book b : listOfBooks.values()) {
				if (b.getGenRating() > 0) {
					withRating.add(b);
				} else {
					noRating.add(b);
				}
			}
			
			Collections.sort(withRating, new Comparator<Book>() {
				public int compare (Book b1, Book b2) {
					if (flag == true) {
						// The * 100 is because it needs to be cast to int, and if I don't do that the value
						// behind the coma get truncated by the cast and the ordering is meaningless
						// This does not impact the actual general rating value which get displayed as normal below
						return (int) ((b1.getGenRating() * 100) - (b2.getGenRating() * 100));
					} else {
						return (int) ((b2.getGenRating() * 100) - (b1.getGenRating() * 100));
					}
				}
			});
			String toPrint = "\nBooks ordered by general rating:\n\n";
			for (Book b : withRating) {
				toPrint = toPrint.concat(b + " (" + b.getGenRating() + " /5)\n");
			}
			toPrint = toPrint.concat("\nBooks with no general goodreads rating:\n\n");
			for (Book b : noRating) {
				toPrint = toPrint.concat(b + "\n");
			}
			return toPrint;
		}
	}
	
	/** This returns a print friendly string of books passed in listOfBooks ordered by the rating the user
	 * gave to them, from lowest to highest if flag = true and the opposite if flag = false.
	 * <br>Books that the user has not rated are excluded.
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean. If true, order is lowest to highest, if false, order is highest to lowest
	 * @return a string with one book per line in its toString form, followed by their rating in parenthesis
	 */
	public static String userRating(HashMap<String, Book> listOfBooks, Boolean flag) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			ArrayList<Book> ratedBooks = new ArrayList<Book>();
			for (Book b : listOfBooks.values()) {
				if (b.getUserRating() > 0) {
					ratedBooks.add(b);
				}
			}
			List<Book> orderedList = ratedBooks;
			Collections.sort(orderedList, new Comparator<Book>() {
				public int compare (Book b1, Book b2) {
					if (flag == true) {
						return (int) (b1.getUserRating() - b2.getUserRating());
					} else {
						return (int) (b2.getUserRating() - b1.getUserRating());
					}
				}
			});
			String toPrint = "Books rated by user, ordered by user rating:\n\n";
			for (Book b : orderedList) {
				toPrint = toPrint.concat(b + " (" + (int) b.getUserRating() + " /5)\n");
			}
			return toPrint;
		}
	}
	
}
