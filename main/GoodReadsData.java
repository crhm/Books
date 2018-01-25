package main;
import java.util.HashMap;

/** Main class - establishes the data structures of the library, and holds the lists 
 * of shelves, authors, books and genres overall.
 * <br>The constructor initialises attributes as empty.
 * @author crhm
 */
public class GoodReadsData { 

	private HashMap<String, Shelf> listShelves;
	private HashMap<String, Author> listAuthors;
	private HashMap<String, Book> listBooks;
	private HashMap<String, Genre> listGenres;


	/** Constructor of the GoodReadsData object - Initialises its two attributes as empty
	 * @param listShelves Initialises listShelves as an empty HashMap<String, Shelf>
	 * @param listAuthors Initialises listAuthors as an empty HashMap<String, Author> 
	 * @param listBooks Initialises listBooks as an empty HashMap<String, Book>
	 * @param listGenres Initialises listGenres as an empty HashMap<String, Genre>
	 */
	public GoodReadsData() {
		this.listShelves = new HashMap<>();
		this.listAuthors = new HashMap<>();
		this.listBooks = new HashMap<>();
		this.listGenres = new HashMap<>();
	}

	/** Returns the HashMap<String, Shelf> called listShelves that contains all the shelves in the library export
	 * and their key is their names, which goodreads seems to format "currently-reading" for "Currently Reading".
	 * @return the listShelves
	 */
	public HashMap<String, Shelf> getListShelves() {
		return this.listShelves;
	}


	/**
	 * @param listShelves the listShelves to set
	 */
	public void setListShelves(HashMap<String, Shelf> listShelves) {
		this.listShelves = listShelves;
	}

	/** Returns the HashMap<String, Author> called listAuthors that contains all authors in the library export
	 * and their key is their last Names, hence why authors who go by a single name like Colette are assigned 
	 * an empty first name rather than an empty last name.
	 * @return the listAuthors
	 */
	public HashMap<String, Author> getListAuthors() {
		return this.listAuthors;
	}

	/**
	 * @param listAuthors the listAuthors to set
	 */
	public void setListAuthors(HashMap<String, Author> listAuthors) {
		this.listAuthors = listAuthors;
	}

	/** Returns the HashMap<String, Book> called listBooks that contains all of the books of the library export
	 * and their key is their ISBN, to be consistent with the listOfBooks in Shelf and Author which also work
	 * via ISBN as keys rather than book titles. 
	 * @return the listBooks
	 */
	public HashMap<String, Book> getListBooks() {
		return this.listBooks;
	}

	/**
	 * @param listBooks the listBooks to set
	 */
	public void setListBooks(HashMap<String, Book> listBooks) {
		this.listBooks = listBooks;
	}

	/** Returns the list of genres in the whole library, in the form of a HashMap<String, Genre>
	 * @return HashMap of genres
	 */
	public HashMap<String, Genre> getListGenres() {
		return this.listGenres;
	}

	/**
	 * @param listGenres HashMap of genres of the library
	 */
	public void setListGenres(HashMap<String, Genre> listGenres) {
		this.listGenres = listGenres;
	}

	/** Adds an author to the listAuthors parameter of this class
	 * @param author Author to be added to the listAuthors
	 */
	public void listAuthorsAdd(Author author){
		this.listAuthors.put(author.getLastName(), author);
	}

	/** Adds a shelf to the listShelves parameter of this class
	 * @param shelf Shelf to be added to the listShelves
	 */
	public void listShelvesAdd(Shelf shelf){
		this.listShelves.put(shelf.getName(), shelf);
	}
	
	/** Adds a book to the listBooks parameter of this class
	 * @param book Book to be added to the listBooks
	 */
	public void listBooksAdd(Book book) {
		this.listBooks.put(book.getIsbn(), book);
	}
	
	/** Adds a genre to the listGenres parameter of this class
	 * @param genre Genre to be added to the listGenres
	 */
	public void listGenresAdd(Genre genre) {
		this.listGenres.put(genre.getName(), genre);
	}	

}
