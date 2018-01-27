package main;

import export.IExportStrategy;

/** Main class - establishes the data structures of the library, and holds the lists 
 * of shelves, authors, books and genres overall.
 * <br>IExportStrategy is initialized as null.
 * @author crhm
 */
public class GoodReadsData {

	private ListShelves libraryShelves;
	private ListAuthors libraryAuthors;
	private ListBooks libraryBooks;
	private ListGenres libraryGenres;
	private IExportStrategy exportStrategy = null;

	/**GoodReadsData constructor.
	 * <br>Initialises library variables as new instances of respective classes.
	 */
	public GoodReadsData() {
		this.libraryShelves = new ListShelves();
		this.libraryAuthors = new ListAuthors();
		this.libraryBooks = new ListBooks();
		this.libraryGenres = new ListGenres();
	}

	public ListShelves getLibraryShelves() {
		return this.libraryShelves;
	}

	public void setListShelves(ListShelves libraryShelves) {
		this.libraryShelves = libraryShelves;
	}

	public ListAuthors getLibraryAuthors() {
		return this.libraryAuthors;
	}

	public void setLibraryAuthors(ListAuthors libraryAuthors) {
		this.libraryAuthors = libraryAuthors;
	}

	public ListBooks getLibraryBooks() {
		return this.libraryBooks;
	}

	public void setLibraryBooks(ListBooks libraryBooks) {
		this.libraryBooks = libraryBooks;
	}

	public ListGenres getLibraryGenres() {
		return libraryGenres;
	}

	public void setLibraryGenres(ListGenres libraryGenres) {
		this.libraryGenres = libraryGenres;
	}

	/**Sets the export strategy to be subsequently used for export(Object o)
	 * @param strategy IExportStrategy to set for later export
	 */
	public void setExportStrategy(IExportStrategy strategy) {
		this.exportStrategy = strategy;
	}
	
	/**Delegates exporting to currently set exportStrategy.
	 * @param o The object to be exported
	 */
	public void export(Object o) {
		this.exportStrategy.export(o);
	}
	
	/**Finds an author in the list of all authors of the library.
	 * @param s The name of the Author to find
	 * @return the author of that name if one exists in the library, 
	 * and returns a null object if not.
	 */
	public Author getAuthor(String s) {
		Author author = null;
		if (libraryAuthors.getList().containsKey(s)) {
			author = libraryAuthors.getList().get(s);
		} else {
			//throw new IllegalArgumentException("No author of that name was found in the library.");
			// TODO The above broke the app so figure out something else / implications of having nothing here?
		}
		return author;
	}
	
	/**Finds a book in the list of all books of the library.
	 * @param s The ISBN of the book to find
	 * @return the book of that ISBN if one exists in the library, 
	 * and returns a null object if not.
	 */
	public Book getBook(String s) {
		Book book = null;
		if (libraryBooks.getList().containsKey(s)) {
			book = libraryBooks.getList().get(s);
		} else {
			//throw new IllegalArgumentException("No book with that ISBN was found in the library.");
			// TODO The above broke the app so figure out something else / implications of having nothing here?
		}
		return book;
	}
	
	/**Finds a shelf in the list of all shelves of the library.
	 * @param s The name of the shelf to find
	 * @return the shelf of that name if one exists in the library, 
	 * and returns a null object if not.
	 */
	public Shelf getShelf(String s) {
		Shelf shelf = null;
		if (libraryShelves.getList().containsKey(s)) {
			shelf = libraryShelves.getList().get(s);
		} else {
			//throw new IllegalArgumentException("No shelf with that name was found in the library.");
			//TODO The above broke the app so figure out something else / implications of having nothing here?
		}
		return shelf;
	}
	

	/**Finds a genre in the list of all genres of the library.
	 * @param s The name of the genre to find
	 * @return the genre of that name if one exists in the library, 
	 * and returns a null object if not.
	 */
	public Genre getGenre(String s) {
		Genre genre = null;
		if(libraryGenres.getList().containsKey(s)) {
			genre = libraryGenres.getList().get(s);
		} else {
			//throw new IllegalArgumentException("No genre with that name was found in the library.");
			//TODO The above broke the app so figure out something else / implications of having nothing here?
		}
		return genre;
  }
  
}
