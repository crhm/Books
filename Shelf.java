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
	public HashMap<String, Book> getListOfBooks() {
		return listOfBooks;
	}
	public void setListOfBooks(HashMap<String, Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}
	
	public void addBook(Book book){
		this.listOfBooks.put(book.getIsbn(), book);
	}
	
	public int numberOfBooks(){
		return getListOfBooks().values().size();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String list = "";
		for (Book b : listOfBooks.values()){
			list = list.concat(b.toStringShort() + "\n");
		}
		return "Shelf " + name + ":\n" + list ;
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
