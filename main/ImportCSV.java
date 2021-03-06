package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ImportCSV {
	
	private GoodReadsData library;

	private Boolean checkIfNotEmptyOrNull(String infoToCheck) {
		if (infoToCheck!=null && infoToCheck.length() >0) {
			return true;
		} else {
			return false;
		}
	}
	
	/** Creates the list of genres (and associated number of votes) of a book from its "genre" value in the csv,
	 *  and returns it so that it can be used to construct the book.
	 * <br>If it encounters a new genre, it adds it to GoodReadsData's list of Genres.
	 * <br>If a sub-genre is in the list, its parent-genre is added to its parentGenres list, 
	 * and it is added to the list of subGenres of its parent-genre.
	 * @param extractCSVGenres The csv value for 'Genres' for a given book
	 * @return The HashMap list of Genres of the book, so that it can be used in book constructor
	 */
	private HashMap<Genre, Integer> genresImport(String extractCSVGenres) {
		HashMap<Genre, Integer> listGenresOfBook = new HashMap<Genre, Integer>();
		
		if (checkIfNotEmptyOrNull(extractCSVGenres)) {
			extractCSVGenres = extractCSVGenres.replace("\"", "");
			String[] genresAndSubgenres = extractCSVGenres.split(";");		
			
			for (String g : genresAndSubgenres) { 
				g = g.replace("|", ";"); // because for some reason splitting around "|" did not work
				String[] genreAndNumberSeparated = g.split(";");
				String number = genreAndNumberSeparated[1];
				String genreAndSubgenre = genreAndNumberSeparated[0];
				
				if (genreAndSubgenre.contains(",")) { // if it is a subgenre
					String genre = genreAndSubgenre.split(",")[1];
					String parentGenre = genreAndSubgenre.split(",")[0];
					if (library.getGenre(genre) == null) { //TODO Check
						library.getLibraryGenres().add(new Genre(genre));
					}
					listGenresOfBook.put(library.getGenre(genre), Integer.parseInt(number));
					if (library.getGenre(parentGenre) == null) {
						library.getLibraryGenres().add(new Genre(parentGenre));
					}		
					// Check if parentGenre is not already in listGenresOfBook
					// If so, increment its value rather than replace it.					
					if (listGenresOfBook.containsKey(library.getGenre(parentGenre))) { //TODO Check
						int oldValue = listGenresOfBook.get(library.getGenre(parentGenre));
						listGenresOfBook.put(library.getGenre(parentGenre), oldValue + Integer.parseInt(number));
					} else {
						listGenresOfBook.put(library.getGenre(parentGenre), Integer.parseInt(number));
					}
					
					// Making sure the parent-genre to child-genre relationship is stored in the respective
					// HashMaps of the genres
					library.getGenre(genre).getParentGenres().add(library.getGenre(parentGenre));
					library.getGenre(parentGenre).getSubGenres().add(library.getGenre(genre));
				
				} else { // if it is not
					String genre = genreAndSubgenre;
					if (library.getGenre(genre) == null) { //TODO Check
						library.getLibraryGenres().add(new Genre(genre));
					}
					listGenresOfBook.put(library.getGenre(genre), Integer.parseInt(number));
				}
			}
		} 
		return listGenresOfBook;
	}
	
	/** Does the heavy lifting of parsing the value of a csv line, extracting the relevant info and 
	 * creating the corresponding book, as well as adding it to the library.
	 * <br>It also adds the book to its corresponding shelf, genre and author.
	 * @param line csv value of a line, aka a book's info to be parsed
	 */
	private void importLine(String line) {
		// Splits the line into its elements and places them in order in an array of Strings
		// and makes sure that the comas stay within the ""
		String[] temp = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); 
		
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
		
		if (!library.getLibraryAuthors().getList().containsKey(lastName)){
			library.getLibraryAuthors().add(new Author(firstName, lastName, 0 ));
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
		
		String dateAdded = "";
		if (checkIfNotEmptyOrNull(book[15])) {
			dateAdded = book[15];
		}

		if (!library.getLibraryShelves().getList().containsKey(book[18])){
			library.getLibraryShelves().add(new Shelf(book[18]));
		}

		// Genres
		String extractCSVGenres = book[32];
		HashMap<Genre, Integer> listGenresOfBook = genresImport(extractCSVGenres);

		
		// Creates the book with its parameters
		Book livre = new Book(title, library.getLibraryAuthors().getList().get(lastName), isbn, goodreadsID, 
				numPages, year, avRating, userRating, dateRead, dateAdded, 
				library.getLibraryShelves().getList().get(book[18]), listGenresOfBook);
		
		library.getLibraryBooks().add(livre);
		
		// Adds book to its shelf
		livre.getShelf().addBook(livre);
		// Adds book to its author
		livre.getAuthor().addBook(livre);
		// Adds book to relevant genres
		for (Genre g : listGenresOfBook.keySet()) {
			g.addBook(livre, listGenresOfBook.get(g));
			// Including parent
			for (Genre parentGenre : g.getParentGenres().getList().values()) {
				parentGenre.addBook(livre, listGenresOfBook.get(parentGenre));
			}
		}
	}
	
	/**Parses csv and loads the information into a corresponding library.
	 * <br>Asks the name of the csv file to be inputted by the
	 * user in console, but expects to find the file in the src folder in which the code is. 
	 * Then it divides the input into one line per csv entry (and holds them in array todo2), 
	 * then calls importLine on each.
	 * <br>The csv should be the standard Goodreads 'export library' csv, or the expanded 
	 * version (see enhance_goodreads_export.py and https://github.com/PaulKlinger/Enhance-GoodReads-Export)
	 *  for genres information. This function will notify the user if the csv being loaded is not expanded.
	 * @param library The GoodReadsData object that the data will be loaded into.
	 */
	public ImportCSV(GoodReadsData library){
		this.library = library;
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
						importLine(el);
					}
					counter++;
				}
				if (library.getLibraryGenres().getList().isEmpty()) { //TODO Check
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
