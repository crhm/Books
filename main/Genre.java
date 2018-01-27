package main;

/** This class is for book genres.
 * <br>It keeps track of their parentGenres and their subGenres. 
 * <br>It also keeps track of how many users assigned each book to the genre.
 * <br>It assumes that there is never more than a direct parent-child relationship, and that there
 * is never grandparent-genres for example. (This seems to be the case in the expansion provided as
 * far as I can tell).
 * @author crhm
 */
public class Genre {
	
	private ListBooksWithGenreAssignment listOfBooks; // This is not the usual ListBooks format for a list of books because
	// there is additional information to hold about each genre's assignment to a each book, namely,
	// the number of goodreads users who had, at the time, assigned that genre to that book. That is
	// represented by the class variable 'listWithGenreAssignment'.
	private String name;
	private ListGenres parentGenres; // String should be name of parentGenre
	private ListGenres subGenres; // String should be name of subGenre
	
	/** Constructs a genre with an new listOfBooksWithGenreAssignment, a name as given in argument, 
	 * and new ListGenres for parentGenres and subGenres.
	 * @param name String name of the genre to be created
	 */
	public Genre(String name) {
		super();
		this.listOfBooks = new ListBooksWithGenreAssignment();
		this.name = name;
		this.parentGenres = new ListGenres();
		this.subGenres = new ListGenres();
	}
	
	/**Note: This returns a ListBooksWithGenreAssignment, which has two HashMaps, one compatible
	 * with regular operations, and one not, as it is a HashMap<Book, Integer> rather than a
	 * HashMap<String, Book>. Use getListOfBooks().getList() for a standard HashMap<String, Book>, and 
	 * getListOfBooks().getListWithGenreAssignment() for the one where books are paired with number of users who assigned
	 * them to the genre.
	 * @return a ListBooksWithGenreAssignment 
	 */
	public ListBooksWithGenreAssignment getListOfBooks() {
		return listOfBooks;
	}

	public String getName() {
		return name;
	}
	
	public ListGenres getParentGenres() {
		return parentGenres;
	}
	
	public ListGenres getSubGenres() {
		return subGenres;
	}

	/** Adds a book to this genre's ListBooksWithGenreAssignment, by adding to both of its 
	 * class variable HashMaps. 
	 * @param b The book to add to the list
	 * @param i The number of users who assigned this book to the genre.
	 */
	public void addBook(Book b, Integer i) {
		this.listOfBooks.addWithGenreAssignment(b, i);
		this.listOfBooks.add(b);
	}
	
	/** Returns a string of the format "Genre: genreName, number of Books: x, parent-Genres: [setOfParentGenres]"
	 * <br>When there are no parentGenres, the brackets are empty.
	 * @return String details of genre
	 */
	public String toStringLong() {

		return "Genre: "  + name + ", number of Books: " + listOfBooks.getList().size() 
				+ ", parent-genres: " + parentGenres.getList().keySet() + ", sub-genres: " + subGenres.getList().keySet();
	}
	
	/** Only returns the name of the genre
	 * 
	 */
	@Override
	public String toString() {
		return name;
	}

}