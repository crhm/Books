package main;

import java.util.HashMap;

public class ListGenres {
	
	private HashMap<String, Genre> list;
	
	public ListGenres() {
		this.list = new HashMap<String, Genre>();
	}
	
	/** Returns the list of genres, in the form of a HashMap<String, Genre>
	 * @return HashMap of genres
	 */
	public HashMap<String, Genre> getList() {
		return this.list;
	}

	/**
	 * @param listGenres HashMap of genres to set
	 */
	public void setList(HashMap<String, Genre> listGenres) {
		this.list = listGenres;
	}
	
	/** Adds a genre to the listGenres parameter of this class
	 * @param genre Genre to be added to the listGenres
	 */
	public void add(Genre genre) {
		this.list.put(genre.getName(), genre);
	}

	@Override
	public String toString() {
		String printableList = "";
		for (Genre g : list.values()) {
			printableList = printableList.concat(g.toStringLong() + "\n");
		}
		return printableList;
	}

}
