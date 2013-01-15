package jp.bcat;

import org.junit.Test;
import junit.framework.TestCase;

public class BookCatalogTest extends TestCase {
	private BookCatalog testCatalog;
	private String bid;
	
 	private Book createBook() {
		Book testData = new Book();
		testData.setTitle("Java�{");
		testData.setAuthor("Foo J. Bar");
		testData.setTranslator("���� ���Y, ���� ���Y");
		testData.setPublisher("���ꑾ�Y�o��");
		testData.setPublicationDate("2003-12-25");
		testData.setCode("ISBN4-123-4567-X");
		testData.setMemo("����");
		testData.setKeyword("�v���O���~���O����");
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
		// Test�̒��O�ɂ��̃��\�b�h���Ă΂��
		testCatalog = new BookCatalog();
		Book testBook = createBook();
		testCatalog.addBook(testBook);
		bid = testBook.getBookId();
	}
	
	@Override
	protected void tearDown() {
		// Test�̏I����ɂ��̃��\�b�h���Ă΂��
		testCatalog.deleteBook(bid);
	}
	
	@Test
	public void testAddGetAndDelete() {
		// �e�X�g�f�[�^�̍쐬
		Book testData1 = createBook();
		// addBook�̃e�X�g
		Book created1 = testCatalog.addBook(testData1);
		assertNotNull(created1);
		String bookId1 = created1.getBookId();
		assertNotNull(bookId1);
		// getBook�̃e�X�g
		Book book = testCatalog.getBook(bookId1);
		assertNotNull(book);
		assertBookEquals(book, created1);
		// getBooks�̃e�X�g
		Book [] books = testCatalog.getBooks();
		assertNotNull(books);
		assertTrue(books.length >= 2);
		// deleteBook�̃e�X�g
		testCatalog.deleteBook(bookId1);
		assertNull(testCatalog.getBook(bookId1));
	}

	@Test
	public void testSaveAndLoad() {
		BookCatalog catalog = new BookCatalog();
		// �e�X�g�f�[�^�̏���
		Book testData = createBook();
		// save�̃e�X�g
		Book created = catalog.addBook(testData);
		assertNotNull(created);
		String bookId = created.getBookId();
		System.out.println(bookId);
		assertNotNull(bookId);
		// load�̃e�X�g
		catalog = new BookCatalog();
		Book reloaded = catalog.getBook(bookId);
		assertNotNull(reloaded);
		assertBookEquals(created, reloaded);
		// �e�X�g�f�[�^�̏���
		catalog.deleteBook(bookId);
	}
	
	@Test
	public void testSearchBook() {
		Book[] searchResult = testCatalog.searchBooks("���ꑾ�Y");
		assertEquals(1, searchResult.length);
		Book bookToCompare = testCatalog.getBook(bid);
		assertBookEquals(bookToCompare, searchResult[0]);
	}
}
