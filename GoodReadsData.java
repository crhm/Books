// TODO pull author ID form goodreads API
// TODO get rid of the occasional quotes in book titles to make format uniform
// TODO improve shelf to string or get listShelves so that it is clearer when a new shelf starts
// in the print out
// TODO make get listAuthors to print more readably
// TODO comment code

/* The code if made to work with a raw, unmodified csv as it is given directly by Goodreads,
 * from the name to everything else, so no changes are needed on that end if the file is to be
 * gotten from an api request in the future.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GoodReadsData { 

	private HashMap<String, Shelf> listShelves;
	private HashMap<String, Author> listAuthors;



	/** Constructor of the GoodReadsData object - Initialises its two attributes as empty
	 * @param listShelves Initialises listShelves as an empty HashMap<String, Shelf>
	 * @param listAuthors Initialises listAuthors as an empty HashMap<String, Author> 
	 */
	public GoodReadsData() {
		this.listShelves = new HashMap<>();
		this.listAuthors = new HashMap<>();
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

	public void listAuthorsAdd(Author autor){
		this.listAuthors.put(autor.getLastName(), autor);
	}

	public void listShelvesAdd(Shelf shelf){
		this.listShelves.put(shelf.getName(), shelf);
	}

	public void importFromGDCSV(){
		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader("src/goodreads_library_export.csv"));
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null){
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			String todo1 = sb.toString();
			String[] todo2 = todo1.split("\n");

			int counter = 0;

			for (String el : todo2 ){
				if (counter!=0){ // to avoid the first line of the header of the csv
					String[] temp = el.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); // so that the comas stay within the ""
					String[] book = new String[19];

					int count = 0;
					for (String e : temp){
						if(count<19){
							book[count] = e.trim();
							count++;
						}
					}


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

					Book livre = new Book(title, getListAuthors().get(lastName), isbn, goodreadsID, numPages, 
							year, avRating, myRating, dateRead, dateAdded, getListShelves().get(book[18]));
					livre.getShelf().addBook(livre);
					livre.getAuthor().addBook(livre);
				}
				counter++;
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (br != null){
				try{
					br.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		GoodReadsData obj = new GoodReadsData();
		obj.importFromGDCSV();

////		To test 	whether it worked in general by printing all shelves
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
//
////		To test whether function getListAuthors works
//		System.out.println(obj.getListAuthors());
//
////		To test whether getting a single shelf works
//		System.out.println(obj.getListShelves().get("read"));
//
////		To test whether getting a single author works		
//		System.out.println(obj.getListAuthors().get("Murakami"));
//
//// 		To test whether getting something like listofBooks from an author works
//		System.out.println(obj.getListAuthors().get("Murakami").getListOfBooks());


	}

}
