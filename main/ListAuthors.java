package main;

import java.util.HashMap;

/**Class that manipulates lists of Authors, holding them in class variable 'list', 
 * a HashMap where their key is their last Names, hence why authors who go by a single name like Colette are assigned 
	 * an empty first name rather than an empty last name.
 * @author crhm
 *
 */
public class ListAuthors {
	
	final private HashMap<String, Author> list;
	
	/**ListAuthors constructor.
	 * Initialises class variable 'list' as an empty HashMap.
	 */
	public ListAuthors() {
		this.list = new HashMap<String, Author>();
	}

	public HashMap<String, Author> getList() {
		return list;
	}

	/**Adds an author to class variable 'list'.
	 * @param author Author to add to the list.
	 */
	public void add(Author author){
		this.list.put(author.getLastName(), author);
	}
	
	/** This returns a print-friendly String of all the authors in 'list', 
	 * each toString() and on a separate line.
	 */
	@Override
	public String toString() {
		String printableList = "";
		for (Author au : this.list.values()) {
			printableList = printableList.concat(au + "\n");
		}
		return printableList;
	}	

}
