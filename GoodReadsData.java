import java.util.HashMap;

/** The code if made to work with a raw, unmodified csv as it is given directly by Goodreads,
 * from the name to everything else, so no changes are needed on that end if the file is to be
 * gotten from an api request in the future.
 * <br>The constructor initialises attributes as empty.
 * @author crhm
 */
public class GoodReadsData { 

	private static HashMap<String, Shelf> listShelves;
	private static HashMap<String, Author> listAuthors;
	private static HashMap<String, Book> listBooks;
	private static HashMap<String, Genre> listGenres;


	/** Constructor of the GoodReadsData object - Initialises its two attributes as empty
	 * @param listShelves Initialises listShelves as an empty HashMap<String, Shelf>
	 * @param listAuthors Initialises listAuthors as an empty HashMap<String, Author> 
	 * @param listBooks Initialises listBooks as an empty HashMap<String, Book>
	 * @param listGenres Initialises listGenres as an empty HashMap<String, Genre>
	 */
	public GoodReadsData() {
		GoodReadsData.listShelves = new HashMap<>();
		GoodReadsData.listAuthors = new HashMap<>();
		GoodReadsData.listBooks = new HashMap<>();
		GoodReadsData.listGenres = new HashMap<>();
	}

	/** Returns the HashMap<String, Shelf> called listShelves that contains all the shelves in the library export
	 * and their key is their names, which goodreads seems to format "currently-reading" for "Currently Reading".
	 * @return the listShelves
	 */
	public static HashMap<String, Shelf> getListShelves() {
		return listShelves;
	}


	/**
	 * @param listShelves the listShelves to set
	 */
	public static void setListShelves(HashMap<String, Shelf> listShelves) {
		GoodReadsData.listShelves = listShelves;
	}

	/** Returns the HashMap<String, Author> called listAuthors that contains all authors in the library export
	 * and their key is their last Names, hence why authors who go by a single name like Colette are assigned 
	 * an empty first name rather than an empty last name.
	 * @return the listAuthors
	 */
	public static HashMap<String, Author> getListAuthors() {
		return listAuthors;
	}

	/**
	 * @param listAuthors the listAuthors to set
	 */
	public static void setListAuthors(HashMap<String, Author> listAuthors) {
		GoodReadsData.listAuthors = listAuthors;
	}

	/** Returns the HashMap<String, Book> called listBooks that contains all of the books of the library export
	 * and their key is their ISBN, to be consistent with the listOfBooks in Shelf and Author which also work
	 * via ISBN as keys rather than book titles. 
	 * @return the listBooks
	 */
	public static HashMap<String, Book> getListBooks() {
		return listBooks;
	}

	/**
	 * @param listBooks the listBooks to set
	 */
	public static void setListBooks(HashMap<String, Book> listBooks) {
		GoodReadsData.listBooks = listBooks;
	}

	/** Returns the list of genres in the whole library, in the form of a HashMap<String, Genre>
	 * @return HashMap of genres
	 */
	public static HashMap<String, Genre> getListGenres() {
		return listGenres;
	}

	/**
	 * @param listGenres HashMap of genres of the library
	 */
	public static void setListGenres(HashMap<String, Genre> listGenres) {
		GoodReadsData.listGenres = listGenres;
	}

	/** Adds an author to the listAuthors parameter of this class
	 * @param author Author to be added to the listAuthors
	 */
	public static void listAuthorsAdd(Author author){
		GoodReadsData.listAuthors.put(author.getLastName(), author);
	}

	/** Adds a shelf to the listShelves parameter of this class
	 * @param shelf Shelf to be added to the listShelves
	 */
	public static void listShelvesAdd(Shelf shelf){
		GoodReadsData.listShelves.put(shelf.getName(), shelf);
	}
	
	/** Adds a book to the listBooks parameter of this class
	 * @param book Book to be added to the listBooks
	 */
	public static void listBooksAdd(Book book) {
		GoodReadsData.listBooks.put(book.getIsbn(), book);
	}
	
	/** Adds a genre to the listGenres parameter of this class
	 * @param genre Genre to be added to the listGenres
	 */
	public static void listGenresAdd(Genre genre) {
		GoodReadsData.listGenres.put(genre.getName(), genre);
	}
	
	/** This returns a print-friendly String of all the authors, 
	 * @return each author, to string, on a separate line
	 */
	public static String printableAuthors() {
		String printableList = "";
		for (Author au : getListAuthors().values()) {
			printableList = printableList.concat(au + "\n");
		}
		return printableList;
	}
	
	/** This returns a print-friendly String of all the contents of each shelf, 
	 * @return each shelf, to string, ending with a new line
	 */
	public static String printableShelves() {
		String printableList = "";
		for (Shelf sh : getListShelves().values()) {
			printableList = printableList.concat(sh + "\n");
		}
		return printableList;
	}
	
	/** This returns a print-friendly String of all the contents of each shelf, 
	 * @return each book, to string, on a separate line
	 */
	public static String printableBooks() {
		String printableList = "";
		for (Book bo : getListBooks().values()) {
			printableList = printableList.concat(bo + "\n");
		}
		return printableList;
	}
	
	/** This returns a print-friendly String of all the genres in the library
	 * @return Each genre's detailed information, on a separate line
	 */
	public static String printableGenres() {
		String printableList = "";
		for (Genre g : getListGenres().values()) {
			printableList = printableList.concat(g.toStringLong() + "\n");
		}
		return printableList;
	}		

}
