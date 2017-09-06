// TODO pull author ID form goodreads API
// TODO get rid of the occasional quotes in book titles to make format uniform
// These are not originally in the csv so they get added on for some reason...
// TODO create an output to a text file that has data; 
// TODO create the statistical functions, see Github. 
// TODO implement try/ catch blocks and error messages
// TODO fix doubling of names when authors don't have a first and last name but just a last name
// like Euripides for example
// TODO fix get book function in shelf and author
// TODO make sure user input for file name is user-error proof

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
		this.setListBooks(new HashMap<>());
	}

	/**
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

	/**
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

	/**
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

	public void listAuthorsAdd(Author autor){
		this.listAuthors.put(autor.getLastName(), autor);
	}

	public void listShelvesAdd(Shelf shelf){
		this.listShelves.put(shelf.getName(), shelf);
	}
	
	public void listBooksAdd(Book book) {
		this.listBooks.put(book.getIsbn(), book);
	}
	
	/** This is supposed to return a print-friendly String of all the authors, 
	 * But it doesn't work because it doesn't enter the for and I don't know why.
	 * @return each author, to string, one after the other
	 */
	public String printableAuthors() {
		String printableList = "";
		for (Author au : getListAuthors().values()) {
			printableList.concat(au + "\n");
		}
		return printableList;
	}
	
	/** This is supposed to return a print-friendly String of all the contents of each shelf, 
	 * But it doesn't work because it doesn't enter the for and I don't know why.
	 * @return each shelf, to string, one after the other
	 */
	public String printableShelves() {
		String printableList = "";
		for (Shelf sh : getListShelves().values()) {
			printableList.concat(sh + "\n");
		}
		return printableList;
	}
	
	/** This is supposed to return a print-friendly String of all the contents of each shelf, 
	 * But it doesn't work because it doesn't enter the for and I don't know why.
	 * @return each book, to string, one after the other
	 */
	public String printableBooks() {
		String printableList = "";
		for (Book bo : getListBooks().values()) {
			printableList.concat(bo + "\n");
		}
		return printableList;
	}
	
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
					String title = book[1];
					String[] namesAuthor = book[3].split(", ");
					String lastName = namesAuthor[0].replace("\"", "");
					String firstName = namesAuthor[1].replace("\"", "");
					
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

//		Test printing all shelves
		System.out.println(obj.printableShelves());
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
//		System.out.println(obj.getListAuthors().get("Murakami").getBook("1Q84"));

	}

}
