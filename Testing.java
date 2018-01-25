// NEW
// TODO pull csv and data not in csv and stuff from goodreads API ?
// TODO order genres (See github issue)

// MAINTENANCE
// TODO implement try/ catch blocks and error messages
// TODO check that method comments are up to date

public class Testing {

	public static void main(String[] args) {
		GoodReadsData library = new GoodReadsData();
		new ImportCSV(library);

////		TESTING GoodReadsData, ImportCSV and ExportToTxt
//		
//		
////		Test printing all shelves
//		Print.libraryShelves(library);
//		
////		Other test to print shelves
//		System.out.println(library.getListShelves());
//
////		To test whether getting a single shelf works
//		System.out.println(library.getListShelves().get("read"));
//
////		To test whether getting a single author works		
//		System.out.println(library.getListAuthors().get("Murakami"));
//
//// 	To test whether getting something like listofBooks from an author works
//		System.out.println(library.getListAuthors().get("Murakami").getListOfBooks());
//
////		To test book.toStringLong, I needed to create a getBook function that used it in Shelf and/or Author.
//		System.out.println(library.getListShelves().get("read").getBook("Medea"));
//		System.out.println(library.getListAuthors().get("Murakami").getBook("Norwegian Wood"));
//		
////		Testing the new ExportToTxt():
//		new ExportToTxt(OrderBooksBy.title(library.getListBooks(), true));		
//		
//		
////		TESTING OrderBooksBy		
//		
//		
////		Testing the order by publication year
//		System.out.println(OrderBooksBy.publicationDate(library.getListBooks(), true));
//		
////		Testing the order by book title
//		System.out.println(OrderBooksBy.title(library.getListBooks(), true));
//		
////		Testing the order by authors' last names:
//		System.out.println(OrderBooksBy.lastName(library.getListBooks(), true));
//
////		Testing the order by authors' first names:
//		System.out.println(OrderBooksBy.firstName(library.getListBooks(), false));
//
////		Testing the order by number of pages:
//		System.out.println(OrderBooksBy.numberOfPages(library.getListBooks(), true));
//		
////		Testing the order by Date added:
//		System.out.println(OrderBooksBy.dateAdded(library.getListBooks(), true));
//		
////		Testing the order by Date read:
//		System.out.println(OrderBooksBy.dateRead(library.getListBooks(), true));
//		
////		Testing the order by general Rating:
//		System.out.println(OrderBooksBy.genRating(library.getListBooks(), true));
//		
////		Testing the order by user Rating:
//		System.out.println(OrderBooksBy.userRating(library.getListBooks(), true));
//		
//		
////		TESTING OrderAuthorsBy
//
//		
////		Testing the new lastName
//		System.out.println(OrderAuthorsBy.lastName(library.getListShelves().get("read").getListOfBooks(), false));
//	
////		Testing the new firstName
//		System.out.println(OrderAuthorsBy.firstName(library.getListShelves().get("read").getListOfBooks(), true));
//		
////		Testing the new number of Books
//		System.out.println(OrderAuthorsBy.numberOfBooks(library.getListBooks(), false));
//
////		Testing the new average Publication year
//		System.out.println(OrderAuthorsBy.averagePublicationYear(library.getListGenres().get("Thriller").getCompatibleListOfBooks(), true));
//		
////		Testing the new ReadRatio
//		System.out.println(OrderAuthorsBy.readRatio(library.getListBooks(), true));
//		
////		TESTING GetData		
//		
//		
////		Testing whether the new GetData.numberOfBooks works:
//		System.out.println(GetData.numberOfBooks(library.getListBooks()));
//		
////		Testing whether the new GetData.numberOfAuthors works:
//		System.out.println(GetData.numberOfAuthors(library.getListBooks()));
//		
////		Testing whether the new GetData.avgGenRating works:
//		System.out.println(GetData.avgGenRating(library.getListBooks()));
//		
////		Testing whether the new GetData.avgUserRating works:
//		System.out.println(GetData.avgUserRating(library.getListBooks()));
//		
////		Testing whether the new GetData.authorsMultipleBooks works:
//		System.out.println(GetData.authorsMultipleBooks(library.getListBooks()));
//
////		Testing whether the new GetData.avgMultipleBooks works:
//		System.out.println(GetData.avgMultipleBooks(library.getListBooks()));
//		
////		Testing whether the new GetData.authorMostBooks works:
//		System.out.println(GetData.authorMostBooks(library.getListBooks()));
//		
////		Testing whether the new GetData.shortestBook works:
//		System.out.println(GetData.shortestBook(library.getListBooks()));
//		
////		Testing whether the new GetData.longestBook works:
//		System.out.println(GetData.longestBook(library.getListBooks()));
//		
////		Testing whether the new GetData.avgPageNum works:
//		System.out.println(GetData.avgPageNum(library.getListBooks()));
//		
////		Testing whether the new GetData.genRatingSpecificList works:
//		System.out.println(GetData.genRatingSpecificList(library.getListBooks(), 0, 3));
//		
////		Testing whether the new GetData.userRatingSpecificList works:
//		System.out.println(GetData.userRatingSpecificList(library.getListBooks(), 5));
//		
////		Testing whether the new GetData.worstGenRating works:
//		System.out.println(GetData.worstGenRating(library.getListBooks()));
//
////		Testing whether the new GetData.bestGenRating works:
//		System.out.println(GetData.bestGenRating(library.getListBooks()));
//		
////		Testing whether the new GetData.avgRatingDiff works:
//		System.out.println(GetData.avgRatingDiff(library.getListBooks()));
//		
////		Testing the new GetData.allData:
//		System.out.println(GetData.allData(library.getListBooks()));
//		
////		TESTING GENRES
//
////		Testing overall library for genres
//		Print.libraryGenres(library);
//		
////		Testing number of genres
//		System.out.println(library.getListGenres().size());
//
////		Testing getting a list of genres of a book (the melancholy of resistance)
//		System.out.println(library.getListBooks().get("0811215040").getListGenres()); 
//		
////		Testing getting a list of books of a genre (Greece)
//		System.out.println(library.getListGenres().get("Greece").getListOfBooks());
//
////		Testing getting parentGenres of a genre (Hungary)
//		System.out.println(library.getListGenres().get("Hungary").getParentGenres().keySet());
//		
////		Testing genre toString
//		System.out.println(library.getListGenres().get("Cultural"));
//		
////		Testing genre toStringLong
//		System.out.println(library.getListGenres().get("Hungary").toStringLong());
//		
////		Testing genre's subgenre list
//		System.out.println(library.getListGenres().get("European Literature").getSubGenres().keySet());
//		
//		
////		TESTING OrderGenresBy
//		
//		
////		Testing new number of Books
		System.out.println(OrderGenresBy.numberOfBooks(library.getListShelves().get("read").getListOfBooks(), true));
	}

}
