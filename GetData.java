import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

/** This class holds methods that return interesting data, indicated by their name. So far they are 
 * intended to be printer-friendly and hence most often return strings formatted for printing in console.
 * <br>Ideally in the future this would be used to display lists in a window graphically.
 * <br>Methods created so far:
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
 * @author Laurene
 */
public class GetData {

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
	
	/** Returns a double that is the average general Goodreads rating of books in the HashMap listOfBooks
	 * rounded to the 3rd decimal (the nearest one, or the one up from the number if it is 5).
	 * @param listOfBooks HashMap<String, Book> That contains the books whose general ratings are to be averaged
	 * @return double - the average general rating of books in this HashMap
	 */
	public static double avgGenRating(HashMap<String, Book> listOfBooks) {
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
			return bd.doubleValue();	
		}
	}
	
	/** This method returns a double that is the average rating the user has given to books the user has 
	 * rated in the HashMap listOfBooks, rounded to the 3rd decimal (the nearest one, or up if it's 5).
	 * <br>If no user rating has been found, then it returns zero.
	 * @param listOfBooks HashMap<String, Book> That contains the books whose user ratings are to be averaged
	 * @return double - the average user rating of books rater by the user in this HashMap
	 */
	public static double avgUserRating(HashMap<String, Book> listOfBooks) {
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
				return bd.doubleValue();	
			} else {
				return 0;
			}
			
		}
	}
	
	/** This method returns the number of Authors with more than one book in listOfBooks as an int
	 * @param listOfBooks HashMap<String, Book> That contains the books whose authors with more than one books
	 * are to be counted
	 * @return int - the number of authors with more than one book in the listOfBooks
	 */
	public static int authorsMultipleBooks(HashMap<String, Book> listOfBooks) {
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			HashMap<String, Author> listAuthors = new HashMap<String, Author>();
			for (Book b : listOfBooks.values()) {
				if (b.getAuthor().getListOfBooks().values().size() > 1) {
					listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
				}
			}
			return listAuthors.values().size();	
		}
	}
	
	
	/** Returns the average number of books amongst authors who have more than one book in listOfBooks
	 * as a double, rounded to nearest 3rd decimal.
	 * <br>If no authors have more than one book, it returns 0.
	 * @param listOfBooks HashMap<String, Book> That contains the books whose authors with more than one books's 
	 * number of books is to be averaged
	 * @return a double of the rounded average of number of books by Authors with more than 1 book
	 */
	public static double avgMultipleBooks(HashMap<String, Book> listOfBooks) {
		HashMap<String, Author> listAuthors = new HashMap<String, Author>();
		if (listOfBooks == null) {
			throw new NullPointerException("The HashMap passed as method argument cannot be null.");
		} else if (listOfBooks.isEmpty()) {
			throw new IllegalArgumentException("The HashMap passed as method argument cannot be empty.");
		} else {
			for (Book b : listOfBooks.values()) {
				if (b.getAuthor().getListOfBooks().values().size() > 1) {
					listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
				}
			}
			if (!listAuthors.isEmpty()) {
				int sum = 0;
				for (Author a : listAuthors.values()) {
					sum = sum + numberOfBooks(a.getListOfBooks());
				}

				// For the rounding and the calculation of the average:
				// Had to convert sum and total number to BigDecimal so that I could divide them as BigDecimals,
				// Otherwise it was always giving me a result of two because of the innacuracies of double and int
				BigDecimal sum1 = new BigDecimal(sum);
				BigDecimal totalNumber = new BigDecimal(listAuthors.size());
				BigDecimal bd = sum1.divide(totalNumber, 3, RoundingMode.HALF_UP);
				return bd.doubleValue(); 	
			} else {
				return 0;
			}
		}
	}

	/** This method returns a string containing the name of the author with the largest number of books in the
	 * HashMap and in parenthesis, the number of books he or she has written that are in the HashMap.
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
			
			// Comparing all authors in the array in succession by their number of books, 
			// Starting outside the while with the first author in the array
			int counter2 = 0;
			int bookNumber = numberOfBooks(authorArray[counter2].getListOfBooks());
			Author largestAuthor = authorArray[counter2];
			while (counter2 < (listAuthors.values().size() - 1)) {
				if (bookNumber < numberOfBooks(authorArray[counter2 + 1].getListOfBooks())) {
					bookNumber = numberOfBooks(authorArray[counter2 + 1].getListOfBooks());
					largestAuthor = authorArray[counter2 + 1];
					counter2 = counter2 + 1;
				} else {
					counter2 = counter2 + 1;
				}
			}
			return largestAuthor + " (" + bookNumber + " books)\n";
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
			return "The shortest book in that list is " + shortestBook.getTitle() + ", by " 
				+ shortestBook.getAuthor() + " (" + (int) shortestBook.getNumPages() + " pages)";
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
			return "The longest book in that list is " + longestBook.getTitle() + ", by " 
				+ longestBook.getAuthor() + " (" + (int) longestBook.getNumPages() + " pages)";
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
			return "The average length of books in this list is " + average.doubleValue() + " pages.";
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
					+ "rating between " + lowerBoundary + " and " + upperBoundary + " stars:\n";
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
					throw new IllegalArgumentException("Sorry, there was a mistake with your rating-boundaries passed"
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
							+ "rating between " + ratings[0] + " and " + ratings[1] + " stars:\n";
					for (Book b : ratingSpecific.values()) {
						toPrint = toPrint.concat(b + " (" + (int) b.getUserRating() + ")\n");
					}
					return toPrint;
				}
				
			} else { // If there is only one int passed as argument, then it is the exact number of stars required:
				
				// Checking parameter is valid and excluding zero because goodreads puts minimum user rating at 1
				if (ratings[0] > 5 || ratings[0] < 1) {  
					return "Sorry, the argument passed was not valid.";
				
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
							+ "rating of " + ratings[0] + " stars:\n";
					for (Book b : ratingSpecific.values()) {
						toPrint = toPrint.concat(b + " (" + (int) b.getUserRating() + ")\n");
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
			// Starting outside the while with the first author in the array
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
			return "The worst-rated book in that list is " + worstBook.getTitle() + ", by " 
				+ worstBook.getAuthor() + " (" + worstBook.getGenRating() + ")";
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
			
			// Comparing all books in the array in succession by their rating of pages, 
			// Starting outside the while with the first author in the array
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
			return "The best-rated book in that list is " + bestBook.getTitle() + ", by " 
				+ bestBook.getAuthor() + " (" + bestBook.getGenRating() + ")";
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
			
			return "The average User Rating is " + userAvgBD.doubleValue() + ", whereas the average General Goodreads"
					+ " Rating (for those same books) is " + genAvgBD.doubleValue() + ", representing a difference of "
					+ diffBD.doubleValue() + ".";	
		}
	}
	
}