// TODO pull author ID form goodreads API
// TODO write up what the format of csv I'm using is
// TODO write a function to go from basic csv export to usable one
// TODO get rid of the occasional quotes in book titles to make format uniform
// TODO improve shelf to string or get listashelves so that it is clearer when a new shelf starts
// in the print out
// TODO fix units of my rating and page number to be integers
// TODO make get listaAutores to print more readably

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GoodReadsData { 

	private HashMap<String, Shelf> listaShelves;
	private HashMap<String, Author> listaAutores;



	/** Constructor del objeto Goodreads data - Inicialisa a vacio sus dos atributos
	 * @param listaShelves Inicialisa listaShelves a un HashMap<String, Shelf> vacio
	 * @param listaAutores Inicialisa listaAutores a un HashMap<String, Author> vacio
	 */
	public GoodReadsData() {
		this.listaShelves = new HashMap<>();
		this.listaAutores = new HashMap<>();
	}

	/**
	 * @return the listaShelves
	 */
	public HashMap<String, Shelf> getListaShelves() {
		return listaShelves;
	}


	/**
	 * @param listaShelves the listaShelves to set
	 */
	public void setListaShelves(HashMap<String, Shelf> listaShelves) {
		this.listaShelves = listaShelves;
	}

	/**
	 * @return the listaAutores
	 */
	public HashMap<String, Author> getListaAutores() {
		return listaAutores;
	}

	/**
	 * @param listaAutores the listaAutores to set
	 */
	public void setListaAutores(HashMap<String, Author> listaAutores) {
		this.listaAutores = listaAutores;
	}

	public void listaAutoresAdd(Author autor){
		this.listaAutores.put(autor.getLastName(), autor);
	}

	public void listaShelvesAdd(Shelf shelf){
		this.listaShelves.put(shelf.getName(), shelf);
	}

	public void importFromGDCSV(){
		BufferedReader br = null;
		String linea = "";

		try {
			br = new BufferedReader(new FileReader("src/goodreads_library_export.csv"));
			StringBuilder sb = new StringBuilder();
			while ((linea = br.readLine()) != null){
				sb.append(linea);
				sb.append(System.lineSeparator());
			}
			String todo1 = sb.toString();
			String[] todo2 = todo1.split("\n");

			int contador = 0;

			for (String el : todo2 ){
				if (contador!=0){ // para evitar la primera linea de cabeceras del csv
					String[] temp = el.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); // para que se quedan las comas de dentro de ""

					String[] libro = new String[19];

					int cont = 0;
					for (String e : temp){
						if(cont<19){
							libro[cont] = e.trim();
							cont++;
						}
					}


					long goodreadsID = Long.parseLong(libro[0]);
					String title = libro[1];
					String[] nombresAutor = libro[3].split(", ");
					String appellido = nombresAutor[0].replace("\"", "");
					String nombre = nombresAutor[1].replace("\"", "");
					if (!getListaAutores().containsKey(appellido)){
						listaAutoresAdd(new Author(nombre, appellido, 0 ));
					}
					String isbn = "";
					if (libro[5]!= null && libro[5].length()==17){
						//isbn = libro[5].substring(4, 14);
						isbn = libro[5].replaceAll("\"", "").replaceAll("=", "");

					}
					double myRating = -1;
					if (libro[7]!= null && libro[7].length() >0){
						myRating = Double.parseDouble(libro[7]);
					}
					double avRating = -1;
					if (libro[8]!= null && libro[8].length() >0){
						avRating = Double.parseDouble(libro[8]);
					}
					double numPages = -1;
					if (libro[11]!= null && libro[11].length() >0){
						numPages = Double.parseDouble(libro[11]); 
					}
					String year = "";
					if (libro[13]!= null && libro[13].length() >0){
						year = libro[13];
					}
					String dateRead = "";
					if (libro[14]!= null && libro[14].length() >0){
						dateRead = libro[14];
					}
					String dateAdded = libro[15];

					if (!getListaShelves().containsKey(libro[18])){
						listaShelvesAdd(new Shelf(libro[18]));
					}

					Book livre = new Book(title, getListaAutores().get(appellido), isbn, goodreadsID, numPages, 
							year, avRating, myRating, dateRead, dateAdded, getListaShelves().get(libro[18]));
					livre.getShelf().addBook(livre);
					livre.getAuthor().addBook(livre);
				}
				contador++;
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

//		To test 	whether it worked in general by printing all shelves
//		System.out.println(obj.getListaShelves());
		
//		To test whether the function numberOfBooks works for each shelf		
//		System.out.println("\nNumber of books in Read Shelf:");
//		System.out.println(obj.getListaShelves().get("read").numberOfBooks());
//		System.out.println("\nNumber of books in To Read Shelf:");
//		System.out.println(obj.getListaShelves().get("to-read").numberOfBooks());
//		System.out.println("\nNumber of books in Currently Reading Shelf:");
//		System.out.println(obj.getListaShelves().get("currently-reading").numberOfBooks());


//		To test whether function getListaAutores works
//		System.out.println(obj.getListaAutores());

//		To test whether getting a single shelf works
//		System.out.println(obj.getListaShelves().get("read"));

//		To test whether getting a single author works		
//		System.out.println(obj.getListaAutores().get("Murakami"));

// To test whether getting something like listofBooks from an author works
//		System.out.println(obj.getListaAutores().get("Murakami").getListOfBooks());


	}

}
