// BIG
// TODO pull csv and data not in csv and stuff from goodreads API
// TODO create an output to a text file that has data analysis; 
// TODO implement try/ catch blocks and error messages
// TODO order authors (See github issue) (separate file from order books?)

// SMALL
// TODO figure out if methods other than authorMostBooks are affected by the single shelf vs. library
// problem too and fix it if so.

public class Testing {

	public static void main(String[] args) {
		GoodReadsData obj = new GoodReadsData();
		obj.importFromGDCSV();

////		Test printing all shelves
//		System.out.println(obj.printableShelves());
//		
////		Other test to print shelves
//		System.out.println(obj.getListShelves());
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
//		
//		
////		TESTING OrderBy		
//		
//		
////		Testing the order by publication year
//		System.out.println(OrderBy.publicationDate(obj.getListBooks(), true));
//		
////		Testing the order by book title
//		System.out.println(OrderBy.title(obj.getListBooks(), true));
//		
////		Testing the order by authors' last names:
//		System.out.println(OrderBy.lastName(obj.getListBooks(), true));
//
////		Testing the order by number of pages:
//		System.out.println(OrderBy.numberOfPages(obj.getListBooks(), true));
//		
////		Testing the order by Date added:
//		System.out.println(OrderBy.dateAdded(obj.getListBooks(), true));
//		
////		Testing the order by Date read:
//		System.out.println(OrderBy.dateRead(obj.getListBooks(), true));
//		
////		Testing the order by general Rating:
//		System.out.println(OrderBy.genRating(obj.getListBooks(), true));
//		
////		Testing the order by user Rating:
//		System.out.println(OrderBy.userRating(obj.getListBooks(), true));
//		
//		
////		TESTING GetData		
//		
//		
////		Testing whether the new GetData.numberOfBooks works:
//		System.out.println(GetData.numberOfBooks(obj.getListBooks()));
//		System.out.println(GetData.numberOfBooks(obj.getListShelves().get("read").getListOfBooks()));
//		
////		Testing whether the new GetData.numberOfAuthors works:
//		System.out.println(GetData.numberOfAuthors(obj.getListBooks()));
//		
////		Testing whether the new GetData.avgGenRating works:
//		System.out.println(GetData.avgGenRating(obj.getListBooks()));
//		
////		Testing whether the new GetData.avgUserRating works:
//		System.out.println(GetData.avgUserRating(obj.getListBooks()));
//		
//		Testing whether the new GetData.authorsMultipleBooks works:
		System.out.println(GetData.authorsMultipleBooks(obj.getListBooks()));
//
////		Testing whether the new GetData.avgMultipleBooks works:
//		System.out.println(GetData.avgMultipleBooks(obj.getListBooks()));
//		
////		Testing whether the new GetData.authorMostBooks works:
//		System.out.println(GetData.authorMostBooks(obj.getListBooks()));
//		
////		Testing whether the new GetData.shortestBook works:
//		System.out.println(GetData.shortestBook(obj.getListBooks()));
//		
////		Testing whether the new GetData.longestBook works:
//		System.out.println(GetData.longestBook(obj.getListBooks()));
//		
////		Testing whether the new GetData.avgPageNum works:
//		System.out.println(GetData.avgPageNum(obj.getListBooks()));
//		
////		Testing whether the new GetData.genRatingSpecificList works:
//		System.out.println(GetData.genRatingSpecificList(obj.getListBooks(), 0, 3));
//		
////		Testing whether the new GetData.userRatingSpecificList works:
//		System.out.println(GetData.userRatingSpecificList(obj.getListBooks(), 5));
//		
////		Testing whether the new GetData.worstGenRating works:
//		System.out.println(GetData.worstGenRating(obj.getListBooks()));
//
////		Testing whether the new GetData.bestGenRating works:
//		System.out.println(GetData.bestGenRating(obj.getListBooks()));
//		
////		Testing whether the new GetData.avgRatingDiff works:
//		System.out.println(GetData.avgRatingDiff(obj.getListBooks()));
	}

}
