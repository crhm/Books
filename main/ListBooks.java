package main;

import java.util.HashMap;

/** Class that manipulates lists of books.
 * <br>CLass variable 'list' is a HashMap that contains the books, 
 * where their key is their ISBN (as a String).
 * @author crhm
 *
 */
public class ListBooks {
	
	private HashMap<String, Book> list;
	
	/**ListBooks constructor.
	 * <br>Initialises class variable 'list' as an empty HashMap.
	 */
	public ListBooks() {
		this.list = new HashMap<String, Book>();
	}

	public HashMap<String, Book> getList() {
		return list;
	}

	public void setList(HashMap<String, Book> listBooks) {
		this.list = listBooks;
	}

	/** Adds an book to class variable 'list'
	 * @param book Book to be added to the list
	 */
	public void add(Book book){
		this.list.put(book.getIsbn(), book);
	}
	
	/** This returns a print-friendly String of all the books,
	 * each toString() and on its own line. 
	 */
	@Override
	public String toString() {
		String printableList = "";
		for (Book bo : list.values()) {
			printableList = printableList.concat(bo + "\n");
		}
		return printableList;
	}	

}
