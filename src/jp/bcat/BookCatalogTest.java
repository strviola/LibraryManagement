package jp.bcat;

import org.junit.Test;
import junit.framework.TestCase;

public class BookCatalogTest extends TestCase {
	private BookCatalog testCatalog;
	private String bid;
	
 	private Book createBook() {
		Book testData = new Book();
		testData.setTitle("Java本");
		testData.setAuthor("Foo J. Bar");
		testData.setTranslator("言語 太郎, 言語 次郎");
		testData.setPublisher("言語太郎出版");
		testData.setPublicationDate("2003-12-25");
		testData.setCode("ISBN4-123-4567-X");
		testData.setMemo("メモ");
		testData.setKeyword("プログラミング言語");
		testData.setDataCreator("fujiwara");
		testData.setDataCreatedDate("2004-01-15");
		return testData;
	}

	void assertBookEquals(Book book1, Book book2) {
		assertEquals(book1.getBookId(), book2.getBookId());
		assertEquals(book1.getTitle(), book2.getTitle());
		assertEquals(book1.getAuthor(), book2.getAuthor());
		assertEquals(book1.getTranslator(), book2.getTranslator());
		assertEquals(book1.getPublisher(), book2.getPublisher());
		assertEquals(book1.getPublicationDate(), book2.getPublicationDate());
		assertEquals(book1.getCode(), book2.getCode());
		assertEquals(book1.getMemo(), book2.getMemo());
		assertEquals(book1.getKeyword(), book2.getKeyword());
		assertEquals(book1.getDataCreator(), book2.getDataCreator());
		assertEquals(book1.getDataCreatedDate(), book2.getDataCreatedDate());
	}

	@Override
	protected void setUp() {
		// Testの直前にこのメソッドが呼ばれる
		testCatalog = new BookCatalog();
		Book testBook = createBook();
		testCatalog.addBook(testBook);
		bid = testBook.getBookId();
	}
	
	@Override
	protected void tearDown() {
		// Testの終了後にこのメソッドが呼ばれる
		testCatalog.deleteBook(bid);
	}
	
	@Test
	public void testAddGetAndDelete() {
		// テストデータの作成
		Book testData1 = createBook();
		// addBookのテスト
		Book created1 = testCatalog.addBook(testData1);
		assertNotNull(created1);
		String bookId1 = created1.getBookId();
		assertNotNull(bookId1);
		// getBookのテスト
		Book book = testCatalog.getBook(bookId1);
		assertNotNull(book);
		assertBookEquals(book, created1);
		// getBooksのテスト
		Book [] books = testCatalog.getBooks();
		assertNotNull(books);
		assertTrue(books.length >= 2);
		// deleteBookのテスト
		testCatalog.deleteBook(bookId1);
		assertNull(testCatalog.getBook(bookId1));
	}

	@Test
	public void testSaveAndLoad() {
		BookCatalog catalog = new BookCatalog();
		// テストデータの準備
		Book testData = createBook();
		// saveのテスト
		Book created = catalog.addBook(testData);
		assertNotNull(created);
		String bookId = created.getBookId();
		System.out.println(bookId);
		assertNotNull(bookId);
		// loadのテスト
		catalog = new BookCatalog();
		Book reloaded = catalog.getBook(bookId);
		assertNotNull(reloaded);
		assertBookEquals(created, reloaded);
		// テストデータの消去
		catalog.deleteBook(bookId);
	}
	
	@Test
	public void testSearchBook() {
		Book[] searchResult = testCatalog.searchBooks("言語太郎");
		assertEquals(1, searchResult.length);
		Book bookToCompare = testCatalog.getBook(bid);
		assertBookEquals(bookToCompare, searchResult[0]);
	}
}
