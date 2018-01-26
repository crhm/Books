package main;

import java.util.HashMap;

public class ListBooks {
	
	private HashMap<String, Book> list;
	
	public ListBooks() {
		this.list = new HashMap<String, Book>();
	}

	/** Returns the HashMap<String, Book> called listBooks that contains all books in the list
	 * and their key is their ISBN, which is a string. 
	 * @return the listBooks
	 */
	public HashMap<String, Book> getList() {
		return list;
	}

	public void setList(HashMap<String, Book> listBooks) {
		this.list = listBooks;
	}

	/** Adds an book to the listBooks parameter of this class
	 * @param book Book to be added to the listBooks
	 */
	public void add(Book book){
		this.list.put(book.getIsbn(), book);
	}
	
	/** This returns a print-friendly String of all the books 
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
