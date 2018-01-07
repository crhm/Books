import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

/** This class holds methods that return interesting data, indicated by their name. So far they are 
 * intended to be printer-friendly and hence most often return strings formatted for printing in console.
 * <br>Ideally in the future this would be used to display lists in a window graphically.
 * <br>Methods created so far:
 * <br>isShelf (for internal use only)
 * <br>Number of books
 * <br>Number of authors
 * <br>Average General Goodreads ratings of Books
 * <br>Average User Ratings of books
 * <br>Number of authors with more than one book
 * <br>Average number of books amongst authors with more than one book
 * <br>Author with most books
 * <br>Shortest book
 * <br>Longest Book
 * <br>Average page number
 * <br>List of books with a specific general rating
 * <br>List of books with a specific user rating
 * <br>Book with worst general rating
 * <br>Book with best general rating
 * <br>Difference between user rating average and general rating average
 * <br>All Data available to be printed
 * <br>Export all data to text file
 * @author crhm
 */
public class GetData {

	/** This method is for internal use at this point, but made it public just in case, 
	 * and is meant to distinguish between a HashMap being a single shelf and it not being so.
	 * <br>Note: even if it returns false, it does not mean it's the whole library.
	 * @param listOfBooks HashMap whose nature is to be elucidated
	 * @return Boolean true if listOfBooks is a single shelf, false if it is not
	 */
	public static Boolean isOneShelf(HashMap<String, Book> listOfBooks) {
		HashMap<String, Shelf> listOfShelves = new HashMap<String, Shelf>();
		
		for (Book b : listOfBooks.values()) {
			listOfShelves.put(b.getShelf().getName(), b.getShelf());
		}
		
		if (listOfShelves.values().size() > 1) {
			return false;
		} else {
			return true;
		}
	}
	
	/** Returns the number of books in the passed HashMap listOfBooks, as an int
	 * @param listOfBooks HashMap<String, Book> containing the books to be counted
	 * @return int number of books
	 */
	public static int numberOfBooks(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			return listOfBooks.values().size();
		}
	}
	
	
	/** Returns the number of unique authors in the passed HashMap listOfBooks, as an int
	 * @param listOfBooks HashMap<String, Book> containing the books whose authors are to be counted
	 * @return int. The number of unique authors
	 */
	public static int numberOfAuthors(HashMap<String, Book> listOfBooks) {
		HashMap<String, Author> listAuthors = new HashMap<String, Author>();
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			for (Book b : listOfBooks.values()) {
				// Not even checking that it doesn't exist in the HashMap yet because Maps don't allow
				// duplicates so if it exists already it gets overwritten, which is not clean but it works
				// whereas an if-check I tried here didn't...
				listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
			}
			return listAuthors.values().size();
		}
	}
	
	/** Returns a string containing the average general Goodreads rating of books in the HashMap listOfBooks
	 * rounded to the 3rd decimal (the nearest one, or the one up from the number if it is 5).
	 * @param listOfBooks HashMap<String, Book> That contains the books whose general ratings are to be averaged
	 * @return double - the average general rating of books in this HashMap
	 */
	public static String avgGenRating(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			double sum = 0;
			for (Book b : listOfBooks.values()) {
				sum = sum + b.getGenRating();
			}
			// For the rounding:
			BigDecimal sumBD = new BigDecimal(sum);
			BigDecimal totalNumber = new BigDecimal(numberOfBooks(listOfBooks));
			BigDecimal bd = sumBD.divide(totalNumber, 3, RoundingMode.HALF_UP);
			return "The average Goodreads rating of these " + totalNumber.intValue() + " books is " 
					+ bd.doubleValue() + "/5 stars.";	
		}
	}
	
	/** This method returns what is the average rating the user has given to books the user has 
	 * rated in the HashMap listOfBooks, rounded to the 3rd decimal (the nearest one, or up if it's 5).
	 * <br>If no user rating has been found, then it returns zero.
	 * @param listOfBooks HashMap<String, Book> That contains the books whose user ratings are to be averaged
	 * @return double - the average user rating of books rater by the user in this HashMap
	 */
	public static String avgUserRating(HashMap<String, Book> listOfBooks) {
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
			if (!ratedBooks.isEmpty()) {
				double sum = 0;
				for (Book b : ratedBooks) {
					sum = sum + b.getUserRating();
				}
				// For the rounding:
				BigDecimal totalNumber = new BigDecimal(ratedBooks.size());
				BigDecimal sum1 = new BigDecimal(sum);
				BigDecimal bd = sum1.divide(totalNumber, 3, RoundingMode.HALF_UP);
				return "For the " +  totalNumber.intValue() + " books which have one, the average User rating is " 
						+ bd.doubleValue() + "/5 stars.";
			} else {
				return "Sorry, no books with user ratings were found.";
			}
			
		}
	}
	
	/** This method returns the number of Authors with more than one book in listOfBooks in a string
	 * @param listOfBooks HashMap<String, Book> That contains the books whose authors with more than one books
	 * are to be counted
	 * @return int - the number of authors with more than one book in the listOfBooks
	 */
	public static String authorsMultipleBooks(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			// Initializing variables
			HashMap<String, Author> listAuthors = new HashMap<String, Author>();
			int numAuthorsMB = 0;
			for (Book b : listOfBooks.values()) {
				listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
			}
			
			// Checking whether listOfBooks is a shelf or the entire library;
			// behavior needs to be different depending on it.
			// Neither behavior works in the other case:
			// Because author.getListOfBooks returns all books the author has written, not just
			// Books they have written that are this in this HashMap,
			// And because if it is the whole library then the checking implemented
			// to make sure the book is in the right shelf does not work because the
			// HashMap is not a shelf.
			
			if (isOneShelf(listOfBooks)) { // This behavior is for when the listOfBooks is a single shelf
				
				for (Author a : listAuthors.values()) {
					int temp1 = 0; //counter of books in the shelf
					for (Book b : a.getListOfBooks().values()) {
						if (b.getShelf().getListOfBooks() == listOfBooks) {
							temp1++;
						}
					}
					if (temp1 > 1) {
						numAuthorsMB++;
					}
				}
				return numAuthorsMB + " authors have multiple books, out of " + listAuthors.values().size() 
						+ " authors in total.";	
			
			} else { // This behavior is for when the listOfBooks is the whole library
				
				for (Author a : listAuthors.values()) {
					if (a.getListOfBooks().values().size() > 1) {
						numAuthorsMB++;
					}
				}
				
				return numAuthorsMB + " authors have multiple books out of " + listAuthors.values().size() 
						+ " authors in total.";
			}
		}
	}
	
	
	/** Returns the average number of books amongst authors who have more than one book in listOfBooks
	 * , rounded to nearest 3rd decimal, in a string.
	 * <br>If no authors have more than one book, it returns a message saying so.
	 * @param listOfBooks HashMap<String, Book> That contains the books whose authors with more than one books's 
	 * number of books is to be averaged
	 * @return a double of the rounded average of number of books by Authors with more than 1 book
	 */
	public static String avgMultipleBooks(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			// Initializing variables
			HashMap<String, Author> listAuthors = new HashMap<String, Author>();
			double numAuthorsMB = 0;
			for (Book b : listOfBooks.values()) {
				listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
			}
			double sum = 0;
			
			// Checking whether listOfBooks is a shelf or the entire library;
			// behavior needs to be different depending on it.
			// Neither behavior works in the other case:
			// Because author.getListOfBooks returns all books the author has written, not just
			// Books they have written that are this in this HashMap,
			// And because if it is the whole library then the checking implemented
			// to make sure the book is in the right shelf does not work because the
			// HashMap is not a shelf.
			
			if (isOneShelf(listOfBooks)) { // This behavior is for when the listOfBooks is a single shelf
				
				for (Author a : listAuthors.values()) {
					int temp1 = 0; //counter of books in the shelf
					for (Book b : a.getListOfBooks().values()) {
						if (b.getShelf().getListOfBooks() == listOfBooks) {
							temp1++;
						}
					}
					if (temp1 > 1) {
						numAuthorsMB++;
						sum = sum + temp1;
					}
				}
				
			} else { // This behavior is for when the listOfBooks is the whole library
				
				for (Author a : listAuthors.values()) {
					if (a.getListOfBooks().values().size() > 1) {
						numAuthorsMB++;
						sum = sum + a.getListOfBooks().values().size();
					}
				}
			}
			
			if (numAuthorsMB > 0) { // to avoid dividing by zero
				// For the rounding and the calculation of the average:
				// Had to convert sum and total number to BigDecimal so that I could divide them as BigDecimals,
				// Otherwise it was always giving me a result of two because of the innacuracies of double and int
				BigDecimal sum1 = new BigDecimal(sum);
				BigDecimal totalNumber = new BigDecimal(numAuthorsMB);
				BigDecimal bd = sum1.divide(totalNumber, 3, RoundingMode.HALF_UP);
				return "Amongst the " + totalNumber.intValue() + " authors with more than one book, "
						+ "the average number of books is " + bd.doubleValue(); 
			} else {
				return "Sorry, no authors with more than one book were found.";
			}
			
		}
	}

	/** This method returns a string containing the name of the author with the largest number of books in the
	 * HashMap and in parenthesis, the number of books he or she has written that are in the HashMap
	 * followed by the number of books in total in the HashMap.
	 * @param listOfBooks HashMap<String, Book> containing the books whose authors will be checked
	 * @return a string of the format "Author Name (X books)"
	 */
	public static String authorMostBooks(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			// Creating a HashMap (to avoid duplicates) of authors
			HashMap<String, Author> listAuthors = new HashMap<String, Author>();
			for (Book b : listOfBooks.values()) {
				listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
			}
			
			// Converting the HashMap to an array to be able to use indexes in order to check
			// Value of current author's number of books against that of the next one.
			Author[] authorArray = new Author[(listAuthors.values().size())];
			int counter1 = 0;
			for (Author a : listAuthors.values()) {
				authorArray[counter1] = a;
				counter1++;
			}
			
			// Checking whether listOfBooks is a shelf or the entire library;
			// behavior needs to be different depending on it.
			// Neither behavior works in the other case:
			// Because author.getListOfBooks returns all books the author has written, not just
			// Books they have written that are this in this HashMap,
			// And because if it is the whole library then the checking implemented
			// to make sure the book is in the right shelf does not work because the
			// HashMap is not a shelf.
			
			if (isOneShelf(listOfBooks)) { // Behavior for listOfBooks being a single shelf
				
				// Initializing variables
				int counter2 = 0;
				int bookNumber = 0;
				Author largestAuthor = authorArray[counter2];	
				// To make sure the books counted are the books in that specific shelf, not all
				// of the author's books regardless of shelf.
				for (Book b : largestAuthor.getListOfBooks().values()) {  
					if (b.getShelf().getListOfBooks() == listOfBooks) {
						bookNumber++;
					}
				}
				
				// Moving on to compare with next author's num of books in this shelf only, using temp,
				// Assigning them as the largestAuthor if their number is higher than previously
				// Highest number, and trying the next author if not.
				while (counter2 < (listAuthors.values().size() - 1)) {
					int temp = 0;
					for (Book b : authorArray[counter2 + 1].getListOfBooks().values()) {  
						if (b.getShelf().getListOfBooks() == listOfBooks) {
							temp++;
						}
					}
					if (temp > bookNumber) {
						bookNumber = temp;
						largestAuthor = authorArray[counter2 + 1];
						counter2++;
					} else {
						counter2++;
					}
				}
				return "The author with most books is " + largestAuthor + " (Books: " + bookNumber 
						+ ", out of a total of " + numberOfBooks(listOfBooks) + ").";
			
			} else { // Behavior for listOfBooks being the whole library
				
				// Comparing all authors in the array in succession by their number of books, 
				// Starting outside the while with the first author in the array
				// This works even when there is only one author; does not cause
				// Out Of Index errors for the array, because it does not enter the if.
				int counter2 = 0;
				int bookNumber = numberOfBooks(authorArray[counter2].getListOfBooks());
				Author largestAuthor = authorArray[counter2];
				while (counter2 < (listAuthors.values().size() - 1)) {
					if (bookNumber < numberOfBooks(authorArray[counter2 + 1].getListOfBooks())) {
						bookNumber = numberOfBooks(authorArray[counter2 + 1].getListOfBooks());
						largestAuthor = authorArray[counter2 + 1];
						counter2++;
					} else {
						counter2++;
					}
				}
				return "The author with most books is " + largestAuthor + " (Books: " + bookNumber 
						+ ", out of a total of " + numberOfBooks(listOfBooks) + ").";	
			}
		}
	}
	
	/** This method returns a string detailing which book in the listOfBooks passed as argument is the shortest
	 *  in number of pages, and includes the number of pages in parenthesis.
	 *  <br>It excludes from its count books with a page number equal to or smaller than 0.
	 * @param listOfBooks HashMap<String, Book> containing the books whose length will be checked
	 * @return String following the format "The shortest book in that list is Title, by Author (x pages)"
	 */
	public static String shortestBook(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {		
			// Converting the HashMap to an array to be able to use indexes in order to check
			// number of pages of current book against that of the next one.
			Book[] bookArray = new Book[(numberOfBooks(listOfBooks))];
			int counter1 = 0;
			for (Book b : listOfBooks.values()) {
				bookArray[counter1] = b;
				counter1++;
			}
			
			// Comparing all books in the array in succession by their number of pages, 
			// Starting outside the while with the first author in the array
			int counter2 = 0;
			Book shortestBook = bookArray[counter2];
			while (counter2 < (numberOfBooks(listOfBooks) - 1)) {
				if (shortestBook.getNumPages() > (bookArray[counter2 + 1].getNumPages()) && (bookArray[counter2 + 1].getNumPages()) > 0) {
					shortestBook = bookArray[counter2 + 1];
					counter2 = counter2 + 1;
				} else {
					counter2 = counter2 + 1;
				}
			}
			return "The shortest book of these " + numberOfBooks(listOfBooks) + " is " + shortestBook.getTitle() 
				+ ", by " + shortestBook.getAuthor() + " (" + (int) shortestBook.getNumPages() + " pages)";
		}
	}
	
	/** This method returns a string detailing which book in the listOfBooks passed as argument is the longest
	 *  in number of pages, and includes the number of pages in parenthesis.
	 * @param listOfBooks HashMap<String, Book> containing the books whose length will be checked
	 * @return String following the format "The longest book in that list is Title, by Author (x pages)"
	 */
	public static String longestBook(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {		
			// Converting the HashMap to an array to be able to use indexes in order to check
			// number of pages of current book against that of the next one.
			Book[] bookArray = new Book[(numberOfBooks(listOfBooks))];
			int counter1 = 0;
			for (Book b : listOfBooks.values()) {
				bookArray[counter1] = b;
				counter1++;
			}
			
			// Comparing all books in the array in succession by their number of pages, 
			// Starting outside the while with the first author in the array
			int counter2 = 0;
			Book longestBook = bookArray[counter2];
			while (counter2 < (numberOfBooks(listOfBooks) - 1)) {
				if (longestBook.getNumPages() < (bookArray[counter2 + 1].getNumPages())) {
					longestBook = bookArray[counter2 + 1];
					counter2 = counter2 + 1;
				} else {
					counter2 = counter2 + 1;
				}
			}
			return "The longest book of these " + numberOfBooks(listOfBooks) + " is " 
				+ longestBook.getTitle() + ", by " + longestBook.getAuthor() + " (" 
				+ (int) longestBook.getNumPages() + " pages)";
		}
	}
	
	/** This method returns a string detailing the average length (in pages) of books in the HashMap 
	 * passed as argument. <br>It ignores books that have a number of pages lower than 1.
	 * @param listOfBooks HashMap<String, Book> of books whose length is to be averaged
	 * @return String of format "The average length of books in this list is x pages."
	 */
	public static String avgPageNum(HashMap<String, Book> listOfBooks){
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			double totalPages = 0;
			for (Book b : listOfBooks.values()) {
				if (b.getNumPages() > 0) {
					totalPages = totalPages + b.getNumPages();
				}
			}
	
			// For the rounding and the calculation of the average itself:
			// Had to convert sum and total number to BigDecimal so that I could divide them as BigDecimals,
			// otherwise the result is innacurate.
			BigDecimal sum = new BigDecimal(totalPages);
			BigDecimal totalNumber = new BigDecimal(numberOfBooks(listOfBooks));
			BigDecimal average = sum.divide(totalNumber, 3, RoundingMode.HALF_UP);
			return "These " + totalNumber.intValue() + " books have an average length of " 
					+ average.doubleValue() + " pages.";
		}
	}
	
	/** This method returns a string listing all the books whose general Goodreads rating is between 
	 * the lower and higher boundaries passed as argument, including the lower but EXCLUDING the upper one, 
	 * and how many there are. Boundaries have to be integer between 0 and 5.
	 * @param listOfBooks HashMap<String, Book> of books whose rating is to be checked
	 * @param lowerBoundary int that has to be either 0, 1, 2, 3 or 4 and has to be smaller than lowerBoundary
	 * @param upperBoundary int that has to be either 1, 2, 3, 4 or 5 and has to be greater than lowerBoundary
	 * @return String with a header, then each book on one line followed by its gen rating in parenthesis
	 */
	public static String genRatingSpecificList(HashMap<String, Book> listOfBooks, int lowerBoundary, int upperBoundary) {
		if (lowerBoundary < 0 || lowerBoundary >= upperBoundary || upperBoundary > 5) {
			throw new IllegalArgumentException("Sorry, there was a mistake with your rating-boundaries passed"
					+ " as arguments to the method. Check method docs for information.");
		} else if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			// I've put it in a new HashMap instead of adding it to toPrint directly in the for loop 
			// because I anticipate it will be useful in later versions of this when I'm not printing
			// to console all the time.
			HashMap<String, Book> ratingSpecific = new HashMap<String, Book>();
			for (Book b : listOfBooks.values()) {
				if (b.getGenRating() >= lowerBoundary && b.getGenRating() < upperBoundary) {
					ratingSpecific.put(b.getIsbn(), b);
				}
			}
			
			String toPrint = "List of the " + ratingSpecific.values().size() + " books which have a general Goodreads "
					+ "rating between " + lowerBoundary + " and " + upperBoundary + " stars out of " 
					+ numberOfBooks(listOfBooks) + " books:\n";
			for (Book b : ratingSpecific.values()) {
				toPrint = toPrint.concat(b + " (" + b.getGenRating() + ")\n");
			}
			return toPrint;
		}
	}
		
	/**This method is dependent on the number of arguments; there must be either 2 or 3. If the user 
	 * passes only one int, it returns all books with that exact star rating, and if the user passes two int, 
	 * it returns all the books with ratings between those values (including both boundary values).
	 * @param listOfBooks HashMap of books whose user rating is to be used
	 * @param ratings int Varargs: can be 1 or 2 arguments; if only 1 is given, books with that star rating are
	 * returned, and if 2 are given, books with a rating comprised between those 2 int boundaries (inlcuding
	 * them) are returned.
	 * @return String listing books according to parameters, with a header giving their number and criteria
	 */
	public static String userRatingSpecificList(HashMap<String, Book> listOfBooks, int... ratings) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			if (ratings.length > 1) { // If there are two int passed as arguments, then they are boundaries:
				
				if (ratings[0] < 1 || ratings[0] >= ratings[1] || ratings[1] > 5) {				
					throw new IllegalArgumentException("Sorry, there was a mistake with the rating-boundaries passed"
							+ " as arguments to the method. Check method docs for information.");
				} else {
					
					// I've put it in a new HashMap instead of adding it to toPrint directly in the for loop 
					// because I anticipate it will be useful in later versions of this when I'm not printing
					// to console all the time.
					HashMap<String, Book> ratingSpecific = new HashMap<String, Book>();
					for (Book b : listOfBooks.values()) {
						if (b.getUserRating() >= ratings[0] && b.getUserRating() <= ratings[1]) {
							ratingSpecific.put(b.getIsbn(), b);
						}
					}
					
					String toPrint = "List of the " + ratingSpecific.values().size() + " books which have a user "
							+ "rating between " + ratings[0] + " and " + ratings[1] + " stars out of " 
							+ numberOfBooks(listOfBooks) + " books:\n";
					for (Book b : ratingSpecific.values()) {
						toPrint = toPrint.concat(b + " (" + (int) b.getUserRating() + ")\n");
					}
					return toPrint;
				}
				
			} else { // If there is only one int passed as argument, then it is the exact number of stars required:
				
				// Checking parameter is valid and excluding zero because goodreads puts minimum user rating at 1
				if (ratings[0] > 5 || ratings[0] < 1) {  
					throw new IllegalArgumentException("Sorry, the rating passed as a method argument is "
							+ "invalid. Please follow rules laid out in method docs.");
				
				} else {
					
					// I've put it in a new HashMap instead of adding it to toPrint directly in the for loop 
					// because I anticipate it will be useful in later versions of this when I'm not printing
					// to console all the time.
					HashMap<String, Book> ratingSpecific = new HashMap<String, Book>();
					for (Book b : listOfBooks.values()) {
						if (b.getUserRating() == ratings[0]) {
							ratingSpecific.put(b.getIsbn(), b);
						}
					}
					
					String toPrint = "List of the " + ratingSpecific.values().size() + " books which have a user "
							+ "rating of " + ratings[0] + " stars out of " + numberOfBooks(listOfBooks) + " books:\n";
					for (Book b : ratingSpecific.values()) {
						toPrint = toPrint.concat(b + "\n");
					}
					return toPrint;
				}	
			}	
		}
	}
	
	/** This method returns a string detailing which is the book with the worst general Goodreads rating in the 
	 * HashMap listOfBooks, excluding books that have a rating of 0 or lower. 
	 * @param listOfBooks HashMap<String, Book> whose books are to be evaluated for worst rated
	 * @return String of the format "The worst-rated book in that list is Title, by Author (rating)"
	 */
	public static String worstGenRating(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {		
			// Converting the HashMap to an array to be able to use indexes in order to check
			// rating of current book against that of the next one.
			Book[] bookArray = new Book[(numberOfBooks(listOfBooks))];
			int counter1 = 0;
			for (Book b : listOfBooks.values()) {
				bookArray[counter1] = b;
				counter1++;
			}
			
			// Comparing all books in the array in succession by their rating of pages, 
			// Starting outside the while with the first book in the array
			int counter2 = 0;
			Book worstBook = bookArray[counter2];
			while (counter2 < (numberOfBooks(listOfBooks) - 1)) {
				if (worstBook.getGenRating() > (bookArray[counter2 + 1].getGenRating()) 
						&& (bookArray[counter2 + 1].getGenRating()) > 0) {
					worstBook = bookArray[counter2 + 1];
					counter2 = counter2 + 1;
				} else {
					counter2 = counter2 + 1;
				}
			}
			return "Out of " + numberOfBooks(listOfBooks) + " books, the worst-rated book is " 
				+ worstBook.getTitle() + ", by " + worstBook.getAuthor() + " (" + worstBook.getGenRating() + ")";
		}
	}
	
	/** This method returns a string detailing which is the book with the best general Goodreads rating in the 
	 * HashMap listOfBooks. 
	 * @param listOfBooks HashMap<String, Book> whose books are to be evaluated for best rated
	 * @return String of the format "The best-rated book in that list is Title, by Author (rating)"
	 */
	public static String bestGenRating(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {		
			// Converting the HashMap to an array to be able to use indexes in order to check
			// rating of current book against that of the next one.
			Book[] bookArray = new Book[(numberOfBooks(listOfBooks))];
			int counter1 = 0;
			for (Book b : listOfBooks.values()) {
				bookArray[counter1] = b;
				counter1++;
			}
			
			// Comparing all books in the array in succession by their rating, 
			// Starting outside the while with the first book in the array
			int counter2 = 0;
			Book bestBook = bookArray[counter2];
			while (counter2 < (numberOfBooks(listOfBooks) - 1)) {
				if (bestBook.getGenRating() < (bookArray[counter2 + 1].getGenRating())) {
					bestBook = bookArray[counter2 + 1];
					counter2 = counter2 + 1;
				} else {
					counter2 = counter2 + 1;
				}
			}
			return "Out of " + numberOfBooks(listOfBooks) + " books, the best-rated book is " 
				+ bestBook.getTitle() + ", by " + bestBook.getAuthor() + " (" + bestBook.getGenRating() + ")";
		}
	}
	
	/** This method returns a String comparing (on books that have a user rating, aka where the user rating
	 * is greater than zero) the average user rating to the average general Goodreads rating, and their 
	 * difference.
	 * @param listOfBooks HashMap<String, Book> that contains the books to be averaged and compared
	 * @return String of the format " The average User Rating is x, whereas the average General 
	 * Goodreads Rating (for those same books) is y, representing a difference of z."
	 */
	public static String avgRatingDiff(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			HashMap<String, Book> userRatedBooks = new HashMap<String, Book>();
			for (Book b : listOfBooks.values()) {
				if(b.getUserRating() > 0) {
					userRatedBooks.put(b.getIsbn(), b);
				}
			}
			
			if (!userRatedBooks.isEmpty()) {
				double genSum = 0;
				double userSum = 0;
				for (Book b : userRatedBooks.values()) {
					genSum = genSum + b.getGenRating();
					userSum = userSum + b.getUserRating();
				}
				
				BigDecimal genSumBD = new BigDecimal(genSum);
				BigDecimal userSumBD = new BigDecimal(userSum);
				BigDecimal totalDB = new BigDecimal(userRatedBooks.values().size());
				BigDecimal genAvgBD = genSumBD.divide(totalDB, 3, RoundingMode.HALF_UP);
				BigDecimal userAvgBD  = userSumBD.divide(totalDB, 3, RoundingMode.HALF_UP);
				BigDecimal diffBD = genAvgBD.subtract(userAvgBD);
				
				return totalDB + " books, out of " + numberOfBooks(listOfBooks) 
					+ ", were found to have both a user rating and a general goodreads rating.\n"
						+ "For those books:\nThe average User Rating is " + userAvgBD.doubleValue() 
						+ ",\nThe average General Goodreads"
						+ " Rating is " + genAvgBD.doubleValue() 
						+ ",\nRepresenting a difference of "
						+ diffBD.doubleValue() + ".";
			} else {
				return "Sorry, there were no books with user ratings found, so no comparison can be made.";
			}
		}
	}
	
	/** This method is meant to agregate all above methods's outputs into a string, with the purpose of being 
	 * passed as an argument to the exportToTxt() method. It selects only the methods appropriate to the 
	 * HashMap passed as argument; it avoids userRating methods if it is shelf currently-reading or to-read 
	 * for example. If the HashMap passed is a library rather than a shelf, it does a general analysis of the 
	 * library but not a detailed analysis of each shelf afterwards. (Could be changed, haven't decided).
	 * @param listOfBooks HashMap containing the list of books to be analysed.
	 * @return String detailing what the HashMap is and all relevant data.
	 */
	public static String allData(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {	
			String toPrint = "";
			if (!isOneShelf(listOfBooks)) { 
				HashMap<String, Shelf> listOfShelves = new HashMap<String, Shelf>();
				for (Book b : listOfBooks.values()) {
					listOfShelves.put(b.getShelf().getName(), b.getShelf());
				}
				toPrint = toPrint.concat("Data analysis for this library of several shelves:");
				toPrint = toPrint.concat("\n\nOVERALL LIBRARY DATA\n");
				toPrint = toPrint.concat("\nNumber of shelves: " + listOfShelves.values().size());
				toPrint = toPrint.concat("\nShelves: " + listOfShelves.keySet());
				toPrint = toPrint.concat("\n" + avgUserRating(listOfBooks));
				toPrint = toPrint.concat("\n" + avgRatingDiff(listOfBooks));
				
				// DO I WANT A COMPLETE DATA SET OR JUST GENERAL ONE? 
				// AKA DO I DO IT FOR EACH SHELF IN THERE TOO?
				// IF SO, CAN I RECYCLE STUFF I'D USE BELOW IN THE ELSE?
				
			} else {
				String shelfName = "";
				for (Book b : listOfBooks.values()) {
					shelfName = b.getShelf().getName();
				}
				if (shelfName.equals("read")) {
					toPrint = toPrint.concat("Data analysis for shelf 'Read':\n");
					toPrint = toPrint.concat("\n" + avgUserRating(listOfBooks));
					toPrint = toPrint.concat("\n" + avgRatingDiff(listOfBooks));
				} else if (shelfName.equals("currently-reading")) {
					toPrint = toPrint.concat("Data analysis for shelf 'Currently Reading':\n");
				} else if (shelfName.equals("to-read")) {
					toPrint = toPrint.concat("Data analysis for shelf 'To Read':\n");
				} else {
					toPrint = toPrint.concat("Data analysis for shelf '" + shelfName + "':\n");
					toPrint = toPrint.concat("\n" + avgUserRating(listOfBooks));
					toPrint = toPrint.concat("\n" + avgRatingDiff(listOfBooks));
				}
			}
			// General stuff, works whatever the listOfBooks is.
			toPrint = toPrint.concat("\n" + avgGenRating(listOfBooks));
			toPrint = toPrint.concat("\nNumber of books: " + numberOfBooks(listOfBooks));
			toPrint = toPrint.concat("\nNumber of authors: " + numberOfAuthors(listOfBooks));
			toPrint = toPrint.concat("\n" + authorsMultipleBooks(listOfBooks));
			toPrint = toPrint.concat("\n" + avgMultipleBooks(listOfBooks));
			toPrint = toPrint.concat("\n" + authorMostBooks(listOfBooks));
			toPrint = toPrint.concat("\n" + shortestBook(listOfBooks));
			toPrint = toPrint.concat("\n" + longestBook(listOfBooks));
			toPrint = toPrint.concat("\n" + avgPageNum(listOfBooks));
			toPrint = toPrint.concat("\n" + worstGenRating(listOfBooks));
			toPrint = toPrint.concat("\n" + bestGenRating(listOfBooks));
			toPrint = toPrint.concat("\n");
			return toPrint;
		}
	}
	
}
