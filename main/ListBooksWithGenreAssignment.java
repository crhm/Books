package main;

import java.util.HashMap;

/** Class that holds both the list of books of a genre, and the number of users who 
 * assigned each book to the genre, in two separate class variables that are distinct HashMaps.
 * <br>The traditional list of books is the same as for a standard ListBooks.
 * <br>The extra list is 'listWithGenreAssignment' (method names follow this format), 
 * which is a HashMap of Integers where their key is a Book; the integer represents 
 * the number of users who assigned the book to the genre.
 * <br>!!!Please note!!!: There is NO synchronisation of the two HashMaps; adding a book to one does not
 * add it to the other. This must be done separately by calling the other add method.
 * @author crhm
 *
 */
public class ListBooksWithGenreAssignment extends ListBooks {
	
	private HashMap<Book, Integer> listWithGenreAssignment;
	
	/**ListBooksWithGenreAssignment constructor.
	 * <br>Calls ListBooks constructor then 
	 * initialises class variable 'listWithGenreAssignment' as an empty HashMap.
	 */
	public ListBooksWithGenreAssignment() {
		super();
		this.listWithGenreAssignment = new HashMap<Book, Integer>();
	}

	public HashMap<Book, Integer> getListWithGenreAssignment() {
		return listWithGenreAssignment;
	}

	/** Adds an book to class variable 'listWithGenreAssignment'
	 * @param book Book to be added to the list
	 * @param i number of users who assigned this book to this genre
	 */
	public void addWithGenreAssignment(Book book, Integer i){
		this.listWithGenreAssignment.put(book, i);
	}
	
	/** This returns a print-friendly String of all the books associated to this genre,
	 * each toString() and on its own line, with the number of users who assigned it to the genre in parenthesis. 
	 */
	@Override
	public String toString() {
		String printableList = "";
		for (Book bo : listWithGenreAssignment.keySet()) {
			printableList = printableList.concat(bo + " (" + listWithGenreAssignment.get(bo) + ")" + "\n");
		}
		return printableList;
	}	

}
