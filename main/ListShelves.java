package main;

import java.util.HashMap;

/** Class that manipulates lists of Shelves, holding them in class variable 'list', a HashMap, 
 * and their key is their names, formatted by goodreads as follows:<br>
 * "currently-reading" for "Currently Reading".
 * @author crhm
 *
 */
public class ListShelves {
	
	private HashMap<String, Shelf> list;
	
	/**ListShelves constructor.
	 * Initialises class variable 'list' as an empty HashMap.
	 */
	public ListShelves() {
		this.list = new HashMap<String, Shelf>();
	}

	public HashMap<String, Shelf> getList() {
		return list;
	}
	
	/**Adds a Shelf to class variable 'list'.
	 * @param shelf Shelf to add to the list.
	 */
	public void add(Shelf shelf) {
		list.put(shelf.getName(), shelf);
	}
	
	/**Returns a formatted String of each shelf in list, 
	 * toString() and on a separate line.
	 */
	@Override
	public String toString() {
		String printableList = "";
		for (Shelf sh : list.values()) {
			printableList = printableList.concat(sh + "\n");
		}
		return printableList;
	}

}
