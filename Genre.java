import java.util.HashMap;

/** This class is for book genres and their sub-genres. 
 * This assumes that there is never more than a direct parent-child relationship, and that there
 * is never grandparent-genres for example.
 * @author crhm
 */
public class Genre {
	
	private HashMap<Book, Integer> listOfBooks;
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
	
	/** Only returns the name of the genre
	 * 
	 */
	@Override
	public String toString() {
		return name;
	}

}