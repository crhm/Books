import java.util.HashMap;

/** This class is for book genres and their sub-genres. 
 * This assumes that there is never more than a direct parent-child relationship, and that there
 * is never grandparent-genres for example. (This seems to be the case in the expansion provided as
 * far as I can tell).
 * @author crhm
 */
public class Genre {
	
	private HashMap<Book, Integer> listOfBooks; // This is not the usual HashMap format for a list of books because
	// there is additional information to hold about each genre's assignment to a each book, namely,
	// the number of goodreads users who had, at the time, assigned that genre to that book. That is
	// represented by the integer.
	private String name;
	private HashMap<String, Genre> parentGenres; // String should be name of parentGenre
	private HashMap<String, Genre> subGenres; // String should be name of subGenre
	
	/** Constructs a genre with an empty listOfBooks, a name as given in argument, and if that 
	 * name is of the format "parentGenre>subGenre>subsubGenre..." it will create a genre 
	 * for each genre above the last subGenre and add it to the genre's HashMap of parentGenres
	 * @param name String name of the genre to be created
	 */
	public Genre(String name) {
		super();
		this.listOfBooks = new HashMap<Book, Integer>();
		this.name = name;
		this.parentGenres = new HashMap<String, Genre>();
		this.subGenres = new HashMap<String, Genre>();
		if (name.contains(">")) {
			String temp[] = name.split(">");
			int counter = 0;
			while (counter < temp.length - 1) {
				parentGenres.put(temp[counter], new Genre(temp[counter]));
				counter++;
			}
		}
	}
	
	/**Note: This returns the actual Genre listOfBooks, which is not compatible
	 * with regular operations as it is a HashMap<Book, Integer> rather than a
	 * HashMap<String, Book>. Use getCompatibleListOfBooks() for a usable
	 * HashMap<String, Book>.
	 * @return a HashMap associating each book of the genre with the number of users
	 *  who assigned said book to said genre.
	 */
	public HashMap<Book, Integer> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(HashMap<Book, Integer> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public HashMap<String, Genre> getParentGenres() {
		return parentGenres;
	}
	
	public void setParentGenres(HashMap<String, Genre> parentGenres) {
		this.parentGenres = parentGenres;
	}

	public void addParentGenre(Genre newParentGenre) {
		this.parentGenres.put(newParentGenre.getName(), newParentGenre);
	}
	
	public HashMap<String, Genre> getSubGenres() {
		return subGenres;
	}

	public void setSubGenres(HashMap<String, Genre> subGenres) {
		this.subGenres = subGenres;
	}
	
	public void addSubGenre(Genre newSubGenre) {
		this.subGenres.put(newSubGenre.getName(), newSubGenre);
	}

	public void addBook(Book b, Integer i) {
		this.listOfBooks.put(b, i);
	}
	
	/** Returns a string of the format "Genre: genreName, number of Books: x, parent-Genres: [setOfParentGenres]"
	 * <br>When there are no parentGenres, the brackets are empty.
	 * @return String details of genre
	 */
	public String toStringLong() {

		return "Genre: "  + name + ", number of Books: " + listOfBooks.size() 
				+ ", parent-genres: " + parentGenres.keySet() + ", sub-genres: " + subGenres.keySet();
	}
	
	/** Method meant to return a listOfBooks for the genre that is compatible with further operations
	 * as designed for a HashMap<String, Book>, and thus creates one by discarding the number
	 * associated with each book in the main listOfBooks of Genre.
	 * @return a compatible HashMap of the books of this genre.
	 */
	public HashMap<String, Book> getCompatibleListOfBooks(){
		HashMap<String, Book> compatibleList = new HashMap<String, Book>();
		for (Book b : listOfBooks.keySet()) {
			compatibleList.put(b.getIsbn(), b);
		}
		return compatibleList;
	}
	
	/** Only returns the name of the genre
	 * 
	 */
	@Override
	public String toString() {
		return name;
	}

}