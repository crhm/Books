public class Book {
	
	private String title;
	private Author author;
	private String isbn;
	private long goodreadsID;
	private double numPages;
	private String yearPublished;
	private double generalRating;
	private double myRating;
	private String dateRead;
	private String dateAdded;
	private Shelf shelf;
	

	/** Constructor of Book
	 * Ideally this data is extracted from a Goodreads export csv file
	 * and modified as necessary (i.e. dates, casts ?)
	 * @param title Title of book as a String
	 * @param author Instance of Author class for author
	 * @param isbn ISBN of the book as a String
	 * @param goodreadsID Id number given by GoodReads as a long
	 * @param numPages Page number as a double
	 * @param yearPublished Year of (first) publication as a String
	 * @param generalRating General rating in Goodreads as a double
	 * @param myRating Rating I gave the book as a double
	 * @param dateRead Date the book was read as a String
	 * @param dateAdded Date the book was added to my shelves as a String
	 * @param shelf Instance of class Shelf for the shelf to which it belongs
	 */
	public Book(String title, Author author, String isbn, long goodreadsID, double numPages, String yearPublished,
			double generalRating, double myRating, String dateRead, String dateAdded, Shelf shelf) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.goodreadsID = goodreadsID;
		this.numPages = numPages;
		this.yearPublished = yearPublished;
		this.generalRating = generalRating;
		this.myRating = myRating;
		this.dateRead = dateRead;
		this.dateAdded = dateAdded;
		this.shelf = shelf;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public long getGoodreadsID() {
		return goodreadsID;
	}


	public void setGoodreadsID(long goodreadsID) {
		this.goodreadsID = goodreadsID;
	}


	public double getNumPages() {
		return numPages;
	}


	public void setNumPages(double numPages) {
		this.numPages = numPages;
	}


	public String getYearPublished() {
		return yearPublished;
	}


	public void setYearPublished(String yearPublished) {
		this.yearPublished = yearPublished;
	}


	public double getGeneralRating() {
		return generalRating;
	}


	public void setGeneralRating(double generalRating) {
		this.generalRating = generalRating;
	}


	public double getMyRating() {
		return myRating;
	}


	public void setMyRating(double myRating) {
		this.myRating = myRating;
	}


	public String getDateRead() {
		return dateRead;
	}


	public void setDateRead(String dateRead) {
		this.dateRead = dateRead;
	}


	public String getDateAdded() {
		return dateAdded;
	}


	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}


	public Shelf getShelf() {
		return shelf;
	}


	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}


	public String toStringShort(){
		return title + ", " + author; 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", isbn=" + isbn + ", goodreadsID=" + goodreadsID
				+ ", numPages=" + numPages + ", yearPublished=" + yearPublished + ", generalRating=" + generalRating
				+ ", myRating=" + myRating + ", dateRead=" + dateRead + ", dateAdded=" + dateAdded + ", shelf=" + shelf.getName()
				+ "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
