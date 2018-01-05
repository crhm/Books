// BIG
// TODO pull csv and data not in csv and stuff from goodreads API ?
// TODO implement try/ catch blocks and error messages
// TODO order authors/genres (See github issue) (separate OrderBy classes)
// TODO check that method comments are up to date

// SMALL

public class Testing {

	public static void main(String[] args) {
		new GoodReadsData();
		new ImportCSV();

////		TESTING GoodReadsData, ImportCSV and ExportToTxt
//		
//		
////		Test printing all shelves
//		System.out.println(GoodReadsData.printableShelves());
//		
////		Other test to print shelves
//		System.out.println(GoodReadsData.getListShelves());
//
////		To test whether getting a single shelf works
//		System.out.println(GoodReadsData.getListShelves().get("read"));
//
////		To test whether getting a single author works		
//		System.out.println(GoodReadsData.getListAuthors().get("Murakami"));
//
//// 	To test whether getting something like listofBooks from an author works
//		System.out.println(GoodReadsData.getListAuthors().get("Murakami").getListOfBooks());
//
////		To test book.toStringLong, I needed to create a getBook function that used it in Shelf and/or Author.
//		System.out.println(GoodReadsData.getListShelves().get("read").getBook("Medea"));
//		System.out.println(GoodReadsData.getListAuthors().get("Murakami").getBook("Norwegian Wood"));
//		
////		Testing the new ExportToTxt():
//		new ExportToTxt(OrderBooksBy.title(GoodReadsData.getListBooks(), true));		
//		
//		
////		TESTING OrderBooksBy		
//		
//		
////		Testing the order by publication year
//		System.out.println(OrderBooksBy.publicationDate(GoodReadsData.getListBooks(), true));
//		
////		Testing the order by book title
//		System.out.println(OrderBooksBy.title(GoodReadsData.getListBooks(), true));
//		
////		Testing the order by authors' last names:
//		System.out.println(OrderBooksBy.lastName(GoodReadsData.getListBooks(), true));
//
////		Testing the order by number of pages:
//		System.out.println(OrderBooksBy.numberOfPages(GoodReadsData.getListBooks(), true));
//		
////		Testing the order by Date added:
//		System.out.println(OrderBooksBy.dateAdded(GoodReadsData.getListBooks(), true));
//		
////		Testing the order by Date read:
//		System.out.println(OrderBooksBy.dateRead(GoodReadsData.getListBooks(), true));
//		
////		Testing the order by general Rating:
//		System.out.println(OrderBooksBy.genRating(GoodReadsData.getListBooks(), true));
//		
////		Testing the order by user Rating:
//		System.out.println(OrderBooksBy.userRating(GoodReadsData.getListBooks(), true));
//		
//		
////		TESTING GetData		
//		
//		
////		Testing whether the new GetData.numberOfBooks works:
//		System.out.println(GetData.numberOfBooks(GoodReadsData.getListBooks()));
//		
////		Testing whether the new GetData.numberOfAuthors works:
//		System.out.println(GetData.numberOfAuthors(GoodReadsData.getListBooks()));
//		
////		Testing whether the new GetData.avgGenRating works:
//		System.out.println(GetData.avgGenRating(GoodReadsData.getListBooks()));
//		
////		Testing whether the new GetData.avgUserRating works:
//		System.out.println(GetData.avgUserRating(GoodReadsData.getListBooks()));
//		
////		Testing whether the new GetData.authorsMultipleBooks works:
//		System.out.println(GetData.authorsMultipleBooks(GoodReadsData.getListBooks()));
//
////		Testing whether the new GetData.avgMultipleBooks works:
//		System.out.println(GetData.avgMultipleBooks(GoodReadsData.getListBooks()));
//		
////		Testing whether the new GetData.authorMostBooks works:
//		System.out.println(GetData.authorMostBooks(GoodReadsData.getListBooks()));
//		
////		Testing whether the new GetData.shortestBook works:
//		System.out.println(GetData.shortestBook(GoodReadsData.getListBooks()));
//		
////		Testing whether the new GetData.longestBook works:
//		System.out.println(GetData.longestBook(GoodReadsData.getListBooks()));
//		
////		Testing whether the new GetData.avgPageNum works:
//		System.out.println(GetData.avgPageNum(GoodReadsData.getListBooks()));
//		
////		Testing whether the new GetData.genRatingSpecificList works:
//		System.out.println(GetData.genRatingSpecificList(GoodReadsData.getListBooks(), 0, 3));
//		
////		Testing whether the new GetData.userRatingSpecificList works:
//		System.out.println(GetData.userRatingSpecificList(GoodReadsData.getListBooks(), 5));
//		
////		Testing whether the new GetData.worstGenRating works:
//		System.out.println(GetData.worstGenRating(GoodReadsData.getListBooks()));
//
////		Testing whether the new GetData.bestGenRating works:
//		System.out.println(GetData.bestGenRating(GoodReadsData.getListBooks()));
//		
////		Testing whether the new GetData.avgRatingDiff works:
//		System.out.println(GetData.avgRatingDiff(GoodReadsData.getListBooks()));
//		
////		Testing the new GetData.allData:
//		System.out.println(GetData.allData(GoodReadsData.getListBooks()));
//		
////		TESTING GENRES
//
////		Testing overall library for genres
//		System.out.println(GoodReadsData.printableGenres());
//		
////		Testing number of genres
//		System.out.println(GoodReadsData.getListGenres().size());
//
////		Testing getting a list of genres of a book (the melancholy of resistance)
//		System.out.println(GoodReadsData.getListBooks().get("0811215040").getListGenres()); 
//		
////		Testing getting a list of books of a genre (Cultural)
//		System.out.println(GoodReadsData.getListGenres().get("Cultural>Greece").getListOfBooks());
//
////		Testing getting parentGenres of a genre (Hungary)
//		System.out.println(GoodReadsData.getListGenres().get("Cultural>Hungary").getParentGenres().keySet());
//		
////		Testing genre toString
//		System.out.println(GoodReadsData.getListGenres().get("Cultural"));
//		
////		Testing genre toStringLong
//		System.out.println(GoodReadsData.getListGenres().get("Cultural>Hungary").toStringLong());
//		
//		Testing genre's subgenre list
		System.out.println(GoodReadsData.getListGenres().get("European Literature").getSubGenres().keySet());
	}

}
