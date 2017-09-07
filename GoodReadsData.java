// BIG
// TODO pull author ID form goodreads API
// TODO create an output to a text file that has data analysis; 
// TODO create the statistical functions, see Github. 
// TODO implement try/ catch blocks and error messages

// SMALL
// TODO make sure user input for file name is user-error proof
// TODO print list of books in shelf / author alphabetically ordered

/* The code if made to work with a raw, unmodified csv as it is given directly by Goodreads,
 * from the name to everything else, so no changes are needed on that end if the file is to be
 * gotten from an api request in the future.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class GoodReadsData { 

	private HashMap<String, Shelf> listShelves;
	private HashMap<String, Author> listAuthors;
	private HashMap<String, Book> listBooks;


	/** Constructor of the GoodReadsData object - Initialises its two attributes as empty
	 * @param listShelves Initialises listShelves as an empty HashMap<String, Shelf>
	 * @param listAuthors Initialises listAuthors as an empty HashMap<String, Author> 
	 */
	public GoodReadsData() {
		this.listShelves = new HashMap<>();
		this.listAuthors = new HashMap<>();
		this.listBooks = new HashMap<>();
	}

	/** Returns the HashMap<String, Shelf> called listShelves that contains all the shelves in the library export
	 * and their key is their names, which goodreads seems to format "currently-reading" for "Currently Reading".
	 * @return the listShelves
	 */
	public HashMap<String, Shelf> getListShelves() {
		return listShelves;
	}


	/**
	 * @param listShelves the listShelves to set
	 */
	public void setListShelves(HashMap<String, Shelf> listShelves) {
		this.listShelves = listShelves;
	}

	/** Returns the HashMap<String, Author> called listAuthors that contains all authors in the library export
	 * and their key is their last Names, hence why authors who go by a single name like Colette are assigned 
	 * an empty first name rather than an empty last name.
	 * @return the listAuthors
	 */
	public HashMap<String, Author> getListAuthors() {
		return listAuthors;
	}

	/**
	 * @param listAuthors the listAuthors to set
	 */
	public void setListAuthors(HashMap<String, Author> listAuthors) {
		this.listAuthors = listAuthors;
	}

	/** Returns the HashMap<String, Book> called listBooks that contains all of the books of the library export
	 * and their key is their ISBN, to be consistent with the listOfBooks in Shelf and Author which also work
	 * via ISBN as keys rather than book titles. 
	 * @return the listBooks
	 */
	public HashMap<String, Book> getListBooks() {
		return listBooks;
	}

	/**
	 * @param listBooks the listBooks to set
	 */
	public void setListBooks(HashMap<String, Book> listBooks) {
		this.listBooks = listBooks;
	}

	/** Adds an author to the listAuthors parameter of this class
	 * @param author Author to be added to the listAuthors
	 */
	public void listAuthorsAdd(Author author){
		this.listAuthors.put(author.getLastName(), author);
	}

	/** Adds a shelf to the listShelves parameter of this class
	 * @param shelf Shelf to be added to the listShelves
	 */
	public void listShelvesAdd(Shelf shelf){
		this.listShelves.put(shelf.getName(), shelf);
	}
	
	/** Adds a book to the listBooks parameter of this class
	 * @param book Book to be added to the listBooks
	 */
	public void listBooksAdd(Book book) {
		this.listBooks.put(book.getIsbn(), book);
	}
	
	/** This is supposed to return a print-friendly String of all the authors, 
	 * But it doesn't work because it doesn't enter the for and I don't know why.
	 * @return each author, to string, on a separate line
	 */
	public String printableAuthors() {
		String printableList = "";
		for (Author au : getListAuthors().values()) {
			printableList = printableList.concat(au + "\n");
		}
		return printableList;
	}
	
	/** This is supposed to return a print-friendly String of all the contents of each shelf, 
	 * But it doesn't work because it doesn't enter the for and I don't know why.
	 * @return each shelf, to string, ending with a new line
	 */
	public String printableShelves() {
		String printableList = "";
		for (Shelf sh : getListShelves().values()) {
			printableList = printableList.concat(sh + "\n");
		}
		return printableList;
	}
	
	/** This is supposed to return a print-friendly String of all the contents of each shelf, 
	 * But it doesn't work because it doesn't enter the for and I don't know why.
	 * @return each book, to string, on a separate line
	 */
	public String printableBooks() {
		String printableList = "";
		for (Book bo : getListBooks().values()) {
			printableList = printableList.concat(bo + "\n");
		}
		return printableList;
	}
	
	/** This is the crucial function of this class. It asks the name of the csv file to be inputted by the
	 * user in console, but expects to find the file in the src folder in which the code is. 
	 * Then it divides the input into one line per csv entry (and holds them in array todo2), 
	 * then the first 19 components of each line (corresponding to the first 19 columns of the csv) 
	 * are placed into an array, and the useful elements amongst those 19 are used to create an instance of Book, 
	 * placed into an existing shelf or creating a new shelf if need be, and assigned to an existing author 
	 * or creating a new author if need be.
	 * The operation is repeated for each line in array todo2, effectively doing so for each line in the csv,
	 * and loading the goodreads library into Books, Authors and Shelves.
	 */
	public void importFromGDCSV(){
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		String line = "";

		try {
			br1 = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please input name of csv file to be imported (without extension or path):");
			String fileName = br1.readLine();
			br2 = new BufferedReader(new FileReader("src/" + fileName + ".csv"));
			StringBuilder sb = new StringBuilder();
			while ((line = br2.readLine()) != null){
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			String todo1 = sb.toString();
			// Places each line as an element in an array of Strings
			String[] todo2 = todo1.split("\n");

			int counter = 0;

			for (String el : todo2 ){
				if (counter!=0){ // to avoid the first line of the header of the csv
					
					// Splits the line into its elements and places them in order in an array of Strings
					// and makes sure that the comas stay within the ""
					String[] temp = el.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); 
					
					// Creates an empty 19-place string array to hold the 19 different informations needed
					String[] book = new String[19];

					// Takes each element in the temp and places it in the according position in book
					// Stops after the 19 first elements, discarding the rest since it is useless info
					// Like notes or number of copies owned
					int count = 0;
					for (String e : temp){
						if(count<19){
							book[count] = e.trim();
							count++;
						}
					}
					
					// Uses values in book as the parameters of the relevant class instances,
					// initialising doubles at -1 and strings as empty, 
					// and then checking that the values exist and that they are not empty,
					// checking the isbn is the right length,
					// and creating the relevant class instance if they don't already exist
					
					long goodreadsID = Long.parseLong(book[0]);
					String title = book[1].replace("\"", "");
					String[] namesAuthor = book[3].split(", ");
					String lastName = namesAuthor[0].replace("\"", "");
					String firstName = namesAuthor[1].replace("\"", "");
					
					// To remove the doubling of names when authors go by a single name, like Colette
					if (lastName.equals(firstName)) {
						firstName = "";
					}
					
					if (!getListAuthors().containsKey(lastName)){
						listAuthorsAdd(new Author(firstName, lastName, 0 ));
					}
					
					String isbn = "";
					if (book[5]!= null && book[5].length()==17){
						//isbn = book[5].substring(4, 14);
						isbn = book[5].replaceAll("\"", "").replaceAll("=", "");

					}
					
					double myRating = -1;
					if (book[7]!= null && book[7].length() >0){
						myRating = Double.parseDouble(book[7]);
					}
					
					double avRating = -1;
					if (book[8]!= null && book[8].length() >0){
						avRating = Double.parseDouble(book[8]);
					}
					
					double numPages = -1;
					if (book[11]!= null && book[11].length() >0){
						numPages = Double.parseDouble(book[11]); 
					}
					
					String year = "";
					if (book[13]!= null && book[13].length() >0){
						year = book[13];
					}
					
					String dateRead = "";
					if (book[14]!= null && book[14].length() >0){
						dateRead = book[14];
					}
					
					String dateAdded = book[15];

					if (!getListShelves().containsKey(book[18])){
						listShelvesAdd(new Shelf(book[18]));
					}

					// Creates the book with its parameters
					Book livre = new Book(title, getListAuthors().get(lastName), isbn, goodreadsID, 
							numPages, year, avRating, myRating, dateRead, dateAdded, 
							getListShelves().get(book[18]));
					
					listBooksAdd(livre);
					
					// Adds book to its shelf
					livre.getShelf().addBook(livre);
					// Adds book to its author
					livre.getAuthor().addBook(livre);
				}
				counter++;
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (br2 != null){
				try{
					br2.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		GoodReadsData obj = new GoodReadsData();
		obj.importFromGDCSV();

////		Test printing all shelves
//		System.out.println(obj.printableShelves());
//		
////		Test meanwhile printable methods don't work
//		System.out.println(obj.getListShelves());
//		
////		To test whether the function numberOfBooks works for each shelf		
//		System.out.println("\nNumber of books in Read Shelf:");
//		System.out.println(obj.getListShelves().get("read").numberOfBooks());
//		System.out.println("\nNumber of books in To Read Shelf:");
//		System.out.println(obj.getListShelves().get("to-read").numberOfBooks());
//		System.out.println("\nNumber of books in Currently Reading Shelf:");
//		System.out.println(obj.getListShelves().get("currently-reading").numberOfBooks());
//
////		To test whether getting a single shelf works
//		System.out.println(obj.getListShelves().get("read"));
//
////		To test whether getting a single author works		
//		System.out.println(obj.getListAuthors().get("Murakami"));
//
//// 	To test whether getting something like listofBooks from an author works
//		System.out.println(obj.getListAuthors().get("Murakami").getListOfBooks());
//
////		To test book.toStringLong, I needed to create a getBook function that used it in Shelf and/or Author.
//		System.out.println(obj.getListShelves().get("read").getBook("Medea"));
//		System.out.println(obj.getListAuthors().get("Murakami").getBook("Norwegian Wood"));

	}

}
