package main;
import java.util.HashMap;

public class Author {
	
	private String firstName;
	private String lastName;
	private double goodreadsID;
	private HashMap<String, Book> listOfBooks; //String is the isbn
	
	/** Constructor of Author - object author to be associated with books
	 * Take data from Goodreads?
	 * @param firstName First Name of Author (May be empty)
	 * @param lastName Last Name(s) of Author (Required)
	 * @param goodreadsID Double corresponding to a goodreads ID number (required)
	 */
	public Author(String firstName, String lastName, double goodreadsID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.goodreadsID = goodreadsID;
		this.listOfBooks = new HashMap<>();
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getGoodreadsID() {
		return goodreadsID;
	}
	public void setGoodreadsID(double goodreadsID) {
		this.goodreadsID = goodreadsID;
	}
	
	public void addBook(Book book){
		this.listOfBooks.put(book.getIsbn(), book);
	}
	
	public HashMap<String, Book> getListOfBooks() {
		return listOfBooks;
	}
	
	/** Returns detailed info about one of its books if it finds it, otherwise it displays
	 * an error message
	 * @param title String of book title
	 * @return All info available on that book
	 */
	public String getBook(String title) {
		String errorMessage = "Sorry, " + title + " was not found for this author";
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
	
	/** Function that returns a detailed string containing all the information about the author
	 * @return a single line containing all info of author (first Name, Last Name, goodreadsID)
	 */
	public String toStringLong(){
		return "Author\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nGoodreads ID: " 
				+ goodreadsID + "\n";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (firstName.length() > 0) {
			return firstName + " " + lastName;
		} else {
			return lastName;
		}	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(goodreadsID);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	// On the assumption that goodreads IDs are unique in Goodreads
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (Double.doubleToLongBits(goodreadsID) != Double.doubleToLongBits(other.goodreadsID))
			return false;
		return true;
	}

}
