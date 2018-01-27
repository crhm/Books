package main;
import java.util.HashMap;

public class Book {
	
	final private String title;
	final private Author author;
	final private String isbn;
	final private long goodreadsID;
	final private double numPages;
	final private String yearPublished;
	final private double genRating;
	final private double userRating;
	final private String dateRead;
	final private String dateAdded;
	final private Shelf shelf;
	final private HashMap<Genre, Integer> genres;

	/** Constructor of Book
	 * Ideally this data is extracted from a Goodreads export csv file
	 * and modified as necessary (i.e. dates, casts ?)
	 * @param title Title of book as a String (required)
	 * @param author Instance of Author class for author (required)
	 * @param isbn ISBN of the book as a String (required)
	 * @param goodreadsID ID number given by GoodReads as a long (required)
	 * @param numPages Page number as a double (may be empty)
	 * @param yearPublished Year of (first) publication as a String (may be empty)
	 * @param genRating General rating in Goodreads as a double (may be empty)
	 * @param userRating Rating user gave the book as a double (may be empty)
	 * @param dateRead Date the book was read as a String (may be empty)
	 * @param dateAdded Date the book was added to my shelves as a String (required)
	 * @param shelf Instance of class Shelf for the shelf to which it belongs (required)
	 * @param genres HashMap associating genres to the number (integer) of users who assigned it to the book.
	 */
	public Book(String title, Author author, String isbn, long goodreadsID, double numPages, String yearPublished,
			double genRating, double userRating, String dateRead, String dateAdded, Shelf shelf, 
			HashMap<Genre, Integer> genres) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.goodreadsID = goodreadsID;
		this.numPages = numPages;
		this.yearPublished = yearPublished;
		this.genRating = genRating;
		this.userRating = userRating;
		this.dateRead = dateRead;
		this.dateAdded = dateAdded;
		this.shelf = shelf;
		this.genres = genres;
	}


	public String getTitle() {
		return title;
	}

	public Author getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public long getGoodreadsID() {
		return goodreadsID;
	}

	public double getNumPages() {
		return numPages;
	}

	public String getYearPublished() {
		return yearPublished;
	}

	public double getGenRating() {
		return genRating;
	}

	public double getUserRating() {
		return userRating;
	}

	public String getDateRead() {
		return dateRead;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public Shelf getShelf() {
		return shelf;
	}

	/** Returns a List of bookGenres, which associate a Genre and the number of users 
	 * who assigned this genre to the book.
	 * @return the list of book genres
	 */
	public HashMap<Genre, Integer> getListGenres() {
		return genres;
	}

	/** 
	 * @return All parameters of the book, in order, one per line: title, author, isbn, goodreadsID, 
	 * number of pages, year Published, general Rating, my Rating, date read, date added, Shelf, 
	 * and list of Genres associated with the number of users who assigned this genre to this book.
	 */
	public String toStringLong(){
		int pages = (int) numPages;
		int rating = (int) userRating;
		String genresPrint = "";
		for (Genre g : genres.keySet()) {
			genresPrint = genresPrint.concat(g + " (" + genres.get(g) + "), ");
		}
		return "Book\nTitle: " + title + "\nAuthor: " + author + "\nISBN: " + isbn + "\nGoodreads ID: "
				+ goodreadsID + "\nNumber of Pages: " + pages + "\nYear Published: " + yearPublished 
				+ "\nGeneral Rating: " + genRating + "\nMy Rating: " + rating + "\nDate Read: " 
				+ dateRead + "\nDate Added: " + dateAdded + "\nShelf: " + shelf.getName() + "\n"
				+ "Genres: " + genresPrint;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return title + ", " + author; 

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
