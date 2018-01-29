package main;

import java.util.HashMap;

/** Class that manipulates lists of books.
 * <br>CLass variable 'list' is a HashMap that contains the books, 
 * where their key is their ISBN (as a String).
 * @author crhm
 *
 */
public class ListBooks {
	
	final private HashMap<String, Book> list;
	
	/**ListBooks constructor.
	 * <br>Initialises class variable 'list' as an empty HashMap.
	 */
	public ListBooks() {
		this.list = new HashMap<String, Book>();
	}

	public HashMap<String, Book> getList() {
		return list;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	/**Returns true if object is a ListBooks with a HashMap 'list' equal to this one's.
	 * <br>If not, returns false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListBooks other = (ListBooks) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}


	
}
