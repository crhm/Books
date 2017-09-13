import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

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
	
	// TODO figure out if I want to exclude the books without a number of pages...
	/** This returns a print friendly string consisting of the books in the HashMap listOfBooks ordered by
	 * number of pages (increasing or decreasing depending on parameter flag). 
	 * Each book is on one line, with the number of pages in parenthesis.
	 * Note: some books are in the goodreads database as having no number of pages, so for those
	 * the number ends up being zero, which could be excluded if I decided so... In the meantime, they get 
	 * displayed and compared as -1s.
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean. If true, order is increasing, if false, order is decreasing
	 */
	public static String numberOfPages(HashMap<String, Book> listOfBooks, Boolean flag) {
		List<Book> orderedList = new ArrayList<Book>(listOfBooks.values());
		Collections.sort(orderedList, new Comparator<Book>() {
			public int compare(Book b1, Book b2) {
				if (flag == true) {
					return (int) (b1.getNumPages() - b2.getNumPages());
				} else {
					return (int) (b2.getNumPages() - b1.getNumPages());
				}
			}
		});
		String toPrint = "Ordered by number of pages:\n\n";
		for (Book b : orderedList) {
			toPrint = toPrint.concat(b + " (" + (int) b.getNumPages() + " pages)\n");
		}
		return toPrint;
	}
	
	
//	TODO Fix this ugly try catch	
	/** This returns a print friendly string of the books passed in listOfBooks order by date added to the
	 * library, from earliest to latest if flag = true and the opposite if flag = false. 
	 * At this stage the try catch is terrible terrible temporary fix but it works
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean. If true, order is earliest to latest, if false, order is latest to earliest
	 * @return A string with one book per line in its toString form, followed by the date it was added
	 * in parenthesis
	 */
	public static String dateAdded(HashMap<String, Book> listOfBooks, Boolean flag) {
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
					// TODO Auto-generated catch block
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
	
//	TODO Fix this ugly try catch	
	/** This returns a print friendly string of ONLY the books passed in listOfBooks that have a "read"
	 * date, (SO BOOKS READ BUT NOT GIVEN A READ DATE WILL NOT APPEAR) ordered by date read, 
	 * from earliest to latest if flag = true and the opposite if flag = false. 
	 * At this stage the try catch is terrible terrible temporary fix but it works
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean. If true, order is earliest to latest, if false, order is latest to earliest
	 * @return A string with one book per line in its toString form, followed by the date it was read
	 * in parenthesis
	 */
	public static String dateRead(HashMap<String, Book> listOfBooks, Boolean flag) {
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
					// TODO Auto-generated catch block
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
	
//	TODO decide if I want to do something for the "no ratings" books instead of counting as 0.0	
	/** This returns a print friendly string of books passed in listOfBooks ordered by their general, collective
	 * Goodreads rating, from lowest to highest if flag = true and the opposite if flag = false.
	 * So far, if a book has no ratings it is counted and displayed as zero.
	 * @param listOfBooks HashMap<String, Book> of books that need ordering
	 * @param flag Boolean. If true, order is lowest to highest, if false, order is highest to lowest
	 * @return a string with one book per line in its toString form, followed by their rating in parenthesis
	 */
	public static String generalRating(HashMap<String, Book> listOfBooks, Boolean flag) {
		List<Book> orderedList = new ArrayList<Book>(listOfBooks.values());
		Collections.sort(orderedList, new Comparator<Book>() {
			public int compare (Book b1, Book b2) {
				if (flag == true) {
					// The * 100 is because it needs to be cast to int, and if I don't do that the value
					// behind the coma get truncated by the cast and the ordering is meaningless
					// This does not impact the actual general rating value which get displayed as normal below
					return (int) ((b1.getGeneralRating() * 100) - (b2.getGeneralRating() * 100));
				} else {
					return (int) ((b2.getGeneralRating() * 100) - (b1.getGeneralRating() * 100));
				}
			}
		});
		String toPrint = "Books ordered by general rating:\n\n";
		for (Book b : orderedList) {
			toPrint = toPrint.concat(b + " (" + b.getGeneralRating() + " /5)\n");
		}
		return toPrint;
	}
	
}
