package main;

import java.util.HashMap;

public class LibraryAuthors {
	
	private HashMap<String, Author> list;
	
	public LibraryAuthors() {
		this.list = new HashMap<String, Author>();
	}

	/** Returns the HashMap<String, Author> called listAuthors that contains all authors in the library export
	 * and their key is their last Names, hence why authors who go by a single name like Colette are assigned 
	 * an empty first name rather than an empty last name.
	 * @return the listAuthors
	 */
	public HashMap<String, Author> getList() {
		return list;
	}

	public void setList(HashMap<String, Author> listAuthors) {
		this.list = listAuthors;
	}

	/** Adds an author to the listAuthors parameter of this class
	 * @param author Author to be added to the listAuthors
	 */
	public void listAdd(Author author){
		this.list.put(author.getLastName(), author);
	}
	
	/** This returns a print-friendly String of all the authors, 
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
