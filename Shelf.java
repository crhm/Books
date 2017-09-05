import java.util.HashMap;

public class Shelf { // todo: method return number of books
	
	private String name;
	private HashMap<String, Book> listOfBooks; //String es el isbn
	
	/** Constructor de estante
	 * @param name Nombre del estante
	 * @param listOfBooks Collection de libros en el estante
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
	
	public void addBook(Book libro){
		this.listOfBooks.put(libro.getIsbn(), libro);
	}
	
	public int numberOfBooks(){
		return getListOfBooks().values().size();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String lista = "";
		for (Book b : listOfBooks.values()){
			lista = lista.concat(b.toStringCorto() + "\n");
		}
		return "Shelf " + name + ":\n" + lista ;
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
