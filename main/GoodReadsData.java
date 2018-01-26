package main;
import java.util.HashMap;

import export.IExportStrategy;

/** Main class - establishes the data structures of the library, and holds the lists 
 * of shelves, authors, books and genres overall.
 * <br>The constructor initialises attributes as empty.
 * @author crhm
 */
public class GoodReadsData { 

	private HashMap<String, Shelf> listShelves;
	//private HashMap<String, Author> listAuthors;
	private LibraryAuthors libraryAuthors;
	private HashMap<String, Book> listBooks;
	private HashMap<String, Genre> listGenres;
	private IExportStrategy exportStrategy = null;


	/** Constructor of the GoodReadsData object - Initialises its two attributes as empty
	 * @param listShelves Initialises listShelves as an empty HashMap<String, Shelf>
	 * @param listAuthors Initialises listAuthors as an empty HashMap<String, Author> 
	 * @param listBooks Initialises listBooks as an empty HashMap<String, Book>
	 * @param listGenres Initialises listGenres as an empty HashMap<String, Genre>
	 */
	public GoodReadsData() {
		this.listShelves = new HashMap<>();
		//this.listAuthors = new HashMap<>();
		this.libraryAuthors = new LibraryAuthors();
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

	
	public LibraryAuthors getLibraryAuthors() {
		return this.libraryAuthors;
	}

	
	public void setLibraryAuthors(LibraryAuthors libraryAuthors) {
		this.libraryAuthors = libraryAuthors;
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
	
//	public String getAuthor(String s) {
//		String author = "";
//		//TODO
//		return author;
//	}
}
