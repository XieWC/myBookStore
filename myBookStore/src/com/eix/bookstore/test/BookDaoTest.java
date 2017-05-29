package com.eix.bookstore.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.eix.bookstore.dao.BookDao;
import com.eix.bookstore.daoimpl.BookDaoImpl;
import com.eix.bookstore.domain.Book;
import com.eix.bookstore.web.CriteriaBook;
import com.eix.bookstore.web.Page;

public class BookDaoTest {
    private BookDao bookDaoImpl = new BookDaoImpl();
	@Test
	public void testGetBook() {
		System.out.println(bookDaoImpl.getBook(3));
		
	}

	@Test
	public void testGetStoreNumber() {
		System.out.println(bookDaoImpl.getStoreNumber(5));
	}

	@Test
	public void testGetPage() {
		CriteriaBook cb = new CriteriaBook(55,57,1);
		
		Page<Book> page = bookDaoImpl.getPage(cb);
		System.out.println(page);
	}

	@Test
	public void testGetPageList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalBookNumber() {
		CriteriaBook cb = new CriteriaBook();
		System.out.println(bookDaoImpl.getTotalBookNumber(cb));
	}

}
