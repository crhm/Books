package main;
import java.util.HashMap;

import export.IExportStrategy;

/** Main class - establishes the data structures of the library, and holds the lists 
 * of shelves, authors, books and genres overall.
 * @author crhm
 */
public class GoodReadsData {

	private HashMap<String, Shelf> listShelves;
	private ListAuthors libraryAuthors;
	private ListBooks libraryBooks;
	private HashMap<String, Genre> listGenres;
	private IExportStrategy exportStrategy = null;


	public GoodReadsData() {
		this.listShelves = new HashMap<>();
		this.libraryAuthors = new ListAuthors();
		this.libraryBooks = new ListBooks();
		this.listGenres = new HashMap<>();
	}

	/** Returns the ListShelves called libraryShelves that contains all the shelves in the library export
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

	
	public ListAuthors getLibraryAuthors() {
		return this.libraryAuthors;
	}

	
	public void setLibraryAuthors(ListAuthors libraryAuthors) {
		this.libraryAuthors = libraryAuthors;
	}

	/** Returns the ListBooks called libraryBooks that contains all of the books of the library export
	 * and their key is their ISBN, to be consistent with the listOfBooks in Shelf and Author which also work
	 * via ISBN as keys rather than book titles. 
	 * @return the listBooks
	 */
	public ListBooks getLibraryBooks() {
		return this.libraryBooks;
	}

	/**
	 * @param library the listBooks to set
	 */
	public void setLibraryBooks(ListBooks libraryBooks) {
		this.libraryBooks = libraryBooks;
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



	/** Adds a shelf to the listShelves parameter of this class
	 * @param shelf Shelf to be added to the listShelves
	 */
	public void listShelvesAdd(Shelf shelf){
		this.listShelves.put(shelf.getName(), shelf);
	}
	
	
	/** Adds a genre to the listGenres parameter of this class
	 * @param genre Genre to be added to the listGenres
	 */
	public void listGenresAdd(Genre genre) {
		this.listGenres.put(genre.getName(), genre);
	}	

	/**Sets the export strategy to be subsequently used for export(String s)
	 * @param strategy IExportStrategy to set for later export
	 */
	public void setExportStrategy(IExportStrategy strategy) {
		this.exportStrategy = strategy;
	}
	
	/**Delegates exporting to currently set exportStrategy.
	 * @param s The string to be exported
	 */
	public void export(String s) {
		this.exportStrategy.export(s);
	}
	
	public Author getAuthor(String s) {
		Author author = null;
		if (libraryAuthors.getList().containsKey(s)) {
			author = libraryAuthors.getList().get(s);
		} else {
			throw new IllegalArgumentException("No author of that name was found in the library.");
		}
		return author;
	}
	
	public Book getBook(String s) {
		Book book = null;
		if (libraryBooks.getList().containsKey(s)) {
			book = libraryBooks.getList().get(s);
		} else {
			throw new IllegalArgumentException("No book with that ISBN was found in the library.");
		}
		return book;
	}
}
