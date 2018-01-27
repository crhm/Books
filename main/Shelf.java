package main;

public class Shelf {
	
	final private String name;
	final private ListBooks listOfBooks; //String is the isbn
	
	/** Constructor of Shelf
	 * @param name Name of Shelf (required)
	 * @param listOfBooks Collection of books in the Shelf (may be empty?)
	 */
	public Shelf(String name) {
		this.name = name;
		this.listOfBooks = new ListBooks();
	}
	
	public String getName() {
		return name;
	}
	
	/** Returns the HashMap<String, Book> called listOfBooks which contains all the books in the shelf, 
	 * and their key is their ISBN.
	 * @return
	 */
	public ListBooks getListOfBooks() {
		return listOfBooks;
	}
	
	/** Adds a book to the Shelf by putting it in its listOfBooks
	 * @param book Book to add to shelf
	 */
	public void addBook(Book book){
		this.listOfBooks.add(book);
	}
	
	/** Returns detailed info about one of its books if it finds it, otherwise it displays
	 * an error message
	 * @param title String of book title
	 * @return All info available on that book
	 */
	public String getBook(String title) { //TODO figure out if this is needed and if it should be simplified cf. GoodReadsData()
		String errorMessage = "Sorry, " + title + " was not found in this shelf";
		double flag = 0;
		String bookInfo = "";
		for (Book b : getListOfBooks().getList().values()){
			if (b.getTitle().equals(title)) {
				flag++;
				bookInfo = b.toStringLong();
			}
		}
		if (flag == 0) {
			return errorMessage;
		} else return bookInfo;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "\nShelf " + name + ":\n" + listOfBooks ;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shelf other = (Shelf) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
