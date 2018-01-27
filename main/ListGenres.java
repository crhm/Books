package main;

import java.util.HashMap;

/** Class that manipulates lists of genres,
 * holding them in class variable 'list', a HashMap where their key is their name (String).
 * @author crhm
 *
 */
public class ListGenres {
	
	final private HashMap<String, Genre> list;
	
	/**ListGenres constructor.
	 * <br>Initialises the class variable 'list', as an empty HashMap.
	 */
	public ListGenres() {
		this.list = new HashMap<String, Genre>();
	}
	
	public HashMap<String, Genre> getList() {
		return this.list;
	}
	
	/** Adds a genre to the class variable 'list'
	 * @param genre Genre to be added to the list
	 */
	public void add(Genre genre) {
		this.list.put(genre.getName(), genre);
	}

	/** Returns a formatted String with each genre in 'list', 
	 * each toStringLong(), and on a separate line.
	 */
	@Override
	public String toString() {
		String printableList = "";
		for (Genre g : list.values()) {
			printableList = printableList.concat(g.toStringLong() + "\n");
		}
		return printableList;
	}

}
