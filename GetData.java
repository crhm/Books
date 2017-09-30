import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class GetData {

	/** Returns the number of books in the passed HashMap listOfBooks, as an int
	 * @param listOfBooks HashMap<String, Book> containing the books to be counted
	 * @return int number of books
	 */
	public static int numberOfBooks(HashMap<String, Book> listOfBooks) {
		return listOfBooks.values().size();
	}
	
	
	/** Returns the number of unique authors in the passed HashMap listOfBooks, as an int
	 * @param listOfBooks HashMap<String, Book> containing the books whose authors are to be counted
	 * @return int. The number of unique authors
	 */
	public static int numberOfAuthors(HashMap<String, Book> listOfBooks) {
		HashMap<String, Author> listAuthors = new HashMap<String, Author>();
		if (listOfBooks.isEmpty()) {
			return 0;
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
	 * Rounded to the 3rd decimal (the nearest one, or the one up from the number if it is 5).
	 * @param listOfBooks HashMap<String, Book> That contains the books whose general ratings are to be averaged
	 * @return double - the average general rating of books in this HashMap
	 */
	public static double averageGenRating(HashMap<String, Book> listOfBooks) {
		int totalNumber = numberOfBooks(listOfBooks);
		double sum = 0;
		for (Book b : listOfBooks.values()) {
			sum = sum + b.getGeneralRating();
		}
		// For the rounding:
		BigDecimal bd = new BigDecimal(sum / totalNumber);
		bd = bd.setScale(3, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	/** This method returns a double that is the average rating the user has given to books the user has 
	 * rated in the HashMap listOfBooks, rounded to the 3rd decimal (the nearest one, or up if it's 5).
	 * @param listOfBooks HashMap<String, Book> That contains the books whose user ratings are to be averaged
	 * @return double - the average user rating of books rater by the user in this HashMap
	 */
	public static double averageMyRating(HashMap<String, Book> listOfBooks) {
		ArrayList<Book> ratedBooks = new ArrayList<Book>();
		for (Book b : listOfBooks.values()) {
			if (b.getMyRating() > 0) {
				ratedBooks.add(b);
			}
		}
		int totalNumber = ratedBooks.size();
		double sum = 0;
		for (Book b : ratedBooks) {
			sum = sum + b.getMyRating();
		}
		// For the rounding:
		BigDecimal bd = new BigDecimal(sum / totalNumber);
		bd = bd.setScale(3, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	/** This method returns the number of Authors with more than one book in listOfBooks as an int
	 * @param listOfBooks HashMap<String, Book> That contains the books whose authors with more than one books
	 * are to be counted
	 * @return int - the number of authors with more than one book in the listOfBooks
	 */
	public static int authorsMultipleBooks(HashMap<String, Book> listOfBooks) {
		HashMap<String, Author> listAuthors = new HashMap<String, Author>();
		for (Book b : listOfBooks.values()) {
			if (b.getAuthor().getListOfBooks().values().size() > 1) {
				listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
			}
		}
		return listAuthors.values().size();
	}
	
	
	/** Returns the average number of books amongst authors who have more than one book in listOfBooks
	 * as a double, rounded to nearest 3rd decimal
	 * @param listOfBooks HashMap<String, Book> That contains the books whose authors with more than one books's 
	 * number of books is to be averaged
	 * @return a double of the rounded average of number of books by Authors with more than 1 book
	 */
	public static double averageMultipleBooks(HashMap<String, Book> listOfBooks) {
		HashMap<String, Author> listAuthors = new HashMap<String, Author>();
		for (Book b : listOfBooks.values()) {
			if (b.getAuthor().getListOfBooks().values().size() > 1) {
				listAuthors.put(b.getAuthor().getLastName(), b.getAuthor());
			}
		}
		int totalNumber = listAuthors.size();
		int sum = 0;
		for (Author a : listAuthors.values()) {
			sum = sum + numberOfBooks(a.getListOfBooks());
		}

		// For the rounding:
		// Had to convert sum and total number to BigDecimal so that I could divide them as BigDecimals,
		// Otherwise it was always giving me a result of two because of the innacuracies of double and int
		BigDecimal sum1 = new BigDecimal(sum);
		BigDecimal totalNumber1 = new BigDecimal(totalNumber);
		BigDecimal bd = sum1.divide(totalNumber1, 3, RoundingMode.HALF_UP);
		return bd.doubleValue(); 
	}

	/** This method returns a string containing the name of the author with the largest number of books in the
	 * HashMap and in parenthesis, the number of books he or she has written that are in the HashMap.
	 * If the HashMap is empty, then a mere error message is displayed
	 * @param listOfBooks HashMap<String, Book> containing the books whose authors will be checked
	 * @return a string of the format "Author Name (X books)"
	 */
	public static String authorMostBooks(HashMap<String, Book> listOfBooks) {
		if (listOfBooks.isEmpty()) { // Initial check to see that the argument passed is not empty
			return "No books found here, sorry! Check argument.";
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
	 *  It excludes from its count books with a page number equal to or smaller than 0.
	 *  	If the HashMap is empty, then a mere error message is displayed
	 * @param listOfBooks HashMap<String, Book> containing the books whose length will be checked
	 * @return String following the format "The shortest book in that list is Title, by Author (x pages)"
	 */
	public static String shortestBook(HashMap<String, Book> listOfBooks) {
		if (listOfBooks.isEmpty()) { // Initial check to see that the argument passed is not empty
			return "No books found here, sorry! Check argument.";
		} else {		
			// Converting the HashMap to an array to be able to use indexes in order to check
			// number of pages of current book against that of the next one.
			Book[] bookArray = new Book[(listOfBooks.values().size())];
			int counter1 = 0;
			for (Book b : listOfBooks.values()) {
				bookArray[counter1] = b;
				counter1++;
			}
			
			// Comparing all books in the array in succession by their number of pages, 
			// Starting outside the while with the first author in the array
			int counter2 = 0;
			Book shortestBook = bookArray[counter2];
			while (counter2 < (listOfBooks.values().size() - 1)) {
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
	 *  	If the HashMap is empty, then a mere error message is displayed
	 * @param listOfBooks HashMap<String, Book> containing the books whose length will be checked
	 * @return String following the format "The longest book in that list is Title, by Author (x pages)"
	 */
	public static String longestBook(HashMap<String, Book> listOfBooks) {
		if (listOfBooks.isEmpty()) { // Initial check to see that the argument passed is not empty
			return "No books found here, sorry! Check argument.";
		} else {		
			// Converting the HashMap to an array to be able to use indexes in order to check
			// number of pages of current book against that of the next one.
			Book[] bookArray = new Book[(listOfBooks.values().size())];
			int counter1 = 0;
			for (Book b : listOfBooks.values()) {
				bookArray[counter1] = b;
				counter1++;
			}
			
			// Comparing all books in the array in succession by their number of pages, 
			// Starting outside the while with the first author in the array
			int counter2 = 0;
			Book longestBook = bookArray[counter2];
			while (counter2 < (listOfBooks.values().size() - 1)) {
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
	
}
