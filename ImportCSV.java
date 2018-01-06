import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ImportCSV {

	public Boolean checkIfNotEmptyOrNull(String infoToCheck) {
		if (infoToCheck!=null && infoToCheck.length() >0) {
			return true;
		} else {
			return false;
		}
	}
	
	// TODO this function is massive and unwieldy - see if you can break it down into functions  a little more. 
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
	public ImportCSV(){
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		String line = "";

		try {
			br1 = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please input name of csv file to be imported (without path) (Please ensure that "
					+ "the file is in the \"src\" folder):");
			try {
				String fileName = br1.readLine();
				
				// Ensures this works whether the user inputs the extension or not
				if (fileName.endsWith(".csv")) {
					br2 = new BufferedReader(new FileReader("src/" + fileName));
				} else {
					br2 = new BufferedReader(new FileReader("src/" + fileName + ".csv")); 
				}
				
				// Builds a string from the data in the csv file being read, where each csv line
				// are on a separate line
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
					if (counter!=0){ // to avoid the first line of the header of the csv, aka labels
						
						// Splits the line into its elements and places them in order in an array of Strings
						// and makes sure that the comas stay within the ""
						String[] temp = el.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); 
						
						// Creates an array that goes to the last required column of csv
						String[] book = new String[33];

						// Takes each element in the temp and places it in the according position in book
						// Stops after the 33 first elements, discarding the rest since it is useless info
						int count = 0;
						for (String e : temp){
							if(count<33){
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
						
						// Takes the 4th csv column entry and splits it around the commma to obtain first
						// and last name of the author
						String[] namesAuthor = book[3].split(", ");
						String lastName = namesAuthor[0].replace("\"", "");
						String firstName = namesAuthor[1].replace("\"", "");
						
						// To remove the doubling of names when authors go by a single name, like Colette
						if (lastName.equals(firstName)) {
							firstName = "";
						}
						
						if (!GoodReadsData.getListAuthors().containsKey(lastName)){
							GoodReadsData.listAuthorsAdd(new Author(firstName, lastName, 0 ));
						}
						
						String isbn = "";
						if (book[5]!= null && book[5].length()==17){
							//isbn = book[5].substring(4, 14); // What is this? What was it for?
							isbn = book[5].replaceAll("\"", "").replaceAll("=", "");
						}
						
						double userRating = -1;
						if (checkIfNotEmptyOrNull(book[7])){
							userRating = Double.parseDouble(book[7]);
						}
						
						double avRating = -1;
						if (checkIfNotEmptyOrNull(book[8])){
							avRating = Double.parseDouble(book[8]);
						}
						
						double numPages = -1;
						if (checkIfNotEmptyOrNull(book[11])){
							numPages = Double.parseDouble(book[11]); 
						}
						
						String year = "";
						if (checkIfNotEmptyOrNull(book[13])){
							year = book[13];
						}
						
						String dateRead = "";
						if (checkIfNotEmptyOrNull(book[14])){
							dateRead = book[14];
						}
						
						String dateAdded = book[15];

						if (!GoodReadsData.getListShelves().containsKey(book[18])){
							GoodReadsData.listShelvesAdd(new Shelf(book[18]));
						}

						// Genres
						
						HashMap<Genre, Integer> listGenresOfBook = new HashMap<Genre, Integer>();
						
						String extractCSVGenres = book[32];
						if (checkIfNotEmptyOrNull(extractCSVGenres)) {
							extractCSVGenres = extractCSVGenres.replace("\"", "");
							String[] genresAndSubgenres = extractCSVGenres.split(";");		
							for (String g : genresAndSubgenres) { 
								g = g.replace("|", ";"); // because for some reason splitting around | did not work
								String[] genreAndNumberSeparated = g.split(";");
								String number = genreAndNumberSeparated[1];
								String genreAndSubgenreSeparated = genreAndNumberSeparated[0].replace(",", ">");
								if (!GoodReadsData.getListGenres().containsKey(genreAndSubgenreSeparated)) {
									GoodReadsData.listGenresAdd(new Genre(genreAndSubgenreSeparated));
								}
								listGenresOfBook.put(GoodReadsData.getListGenres().get(genreAndSubgenreSeparated), Integer.parseInt(number));
								if (genreAndSubgenreSeparated.contains(">")) {
									String parentGenreName = genreAndSubgenreSeparated.split(">")[0];
									if (!GoodReadsData.getListGenres().containsKey(parentGenreName)) {
										GoodReadsData.listGenresAdd(new Genre(parentGenreName));
									}							
									GoodReadsData.getListGenres().get(genreAndSubgenreSeparated).addParentGenre(GoodReadsData.getListGenres().get(parentGenreName));
									GoodReadsData.getListGenres().get(parentGenreName).addSubGenre(GoodReadsData.getListGenres().get(genreAndSubgenreSeparated));
									listGenresOfBook.put(GoodReadsData.getListGenres().get(parentGenreName), Integer.parseInt(number));
								}

							}
						} 
						
						// Creates the book with its parameters
						Book livre = new Book(title, GoodReadsData.getListAuthors().get(lastName), isbn, goodreadsID, 
								numPages, year, avRating, userRating, dateRead, dateAdded, 
								GoodReadsData.getListShelves().get(book[18]), listGenresOfBook);
						
						GoodReadsData.listBooksAdd(livre);
						
						// Adds book to its shelf
						livre.getShelf().addBook(livre);
						// Adds book to its author
						livre.getAuthor().addBook(livre);
						// Adds book to relevant genres
						for (Genre g : listGenresOfBook.keySet()) {
							g.addBook(livre, listGenresOfBook.get(g));
							// Including parent
							for (Genre parentGenre : g.getParentGenres().values()) {
								parentGenre.addBook(livre, listGenresOfBook.get(parentGenre));
							}
						}
						
					}
					counter++;
				}
				if (GoodReadsData.getListGenres().isEmpty()) {
						System.out.println("Please note, this csv does not seem to have the genres expansion.\n");
				}
			} catch (FileNotFoundException e) {
				System.out.println("Sorry, no such file was found in folder \"src\".\nPlease make sure that "
						+ "the name was typed correctly (no path, no typo),\n"
						+ "and that the file is in the \"src\" folder.\n");
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
	
}
