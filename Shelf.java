import java.util.HashMap;

public class Shelf {
	
	private String name;
	private HashMap<String, Book> listOfBooks; //String is the isbn
	
	/** Constructor of Shelf
	 * @param name Name of Shelf
	 * @param listOfBooks Collection of books in the Shelf
	 */
	public Shelf(String name) {
		this.name = name;
		this.listOfBooks = new HashMap<String, Book>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/** Returns the HashMap<String, Book> called listOfBooks which contains all the books in the shelf, 
	 * and their key is their ISBN.
	 * @return
	 */
	public HashMap<String, Book> getListOfBooks() {
		return listOfBooks;
	}
	
	/** Sets the list of books that the shelf contains, 
	 * @param listOfBooks A HashMap with book ISBNs as keys and Book instances as values that will be the list
	 * of books that the shelf contains
	 */
	public void setListOfBooks(HashMap<String, Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}
	
	/** Adds a book to the Shelf by putting it in its listOfBooks
	 * @param book Book to add to shelf
	 */
	public void addBook(Book book){
		this.listOfBooks.put(book.getIsbn(), book);
	}
	
	/** Returns detailed info about one of its books if it finds it, otherwise it displays
	 * an error message
	 * @param title String of book title
	 * @return All info available on that book
	 */
	public String getBook(String title) {
		String errorMessage = "Sorry, " + title + " was not found in this shelf";
		double flag = 0;
		String bookInfo = "";
		for (Book b : getListOfBooks().values()){
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
		String list = "";
		for (Book b : getListOfBooks().values()){
			list = list.concat(b.toString() + "\n");
		}
		return "\nShelf " + name + ":\n" + list ;
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
