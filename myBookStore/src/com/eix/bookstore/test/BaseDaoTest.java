package com.eix.bookstore.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.eix.bookstore.daoimpl.BaseDao;
import com.eix.bookstore.daoimpl.BookDaoImpl;
import com.eix.bookstore.domain.Book;

public class BaseDaoTest {

   private BaseDao baseDao = new BaseDao<>();
   private BookDaoImpl bookDaoImpl = new BookDaoImpl();
	@Test
	public void testInsert() {
		String sql = "INSERT INTO userinfo (USerName, accountid) VALUES(?,?)";
		long id = baseDao.insert(sql, "xie", 1);
		System.out.println("*"+ id);
		
	}

	@Test
	public void testUpdate() {
		String sql = "UPDATE mybooks SET author = ? WHERE id = ?";
		baseDao.update(sql, "XIE", 1);
	}

	@Test
	public void testQuery() {
		String sql = "SELECT id,author,title,price,publishingDate,salesAmount,storeNumber,remark FROM mybooks WHERE id = ?";
		Book book =  bookDaoImpl.query(sql, 4);
		System.out.println(book);
	}

	@Test
	public void testQueryforList() {
		String sql = "SELECT * FROM mybooks WHERE id < ?";
		List<Book> books = bookDaoImpl.queryforList(sql, 4);
		System.out.println(books);
	}

	@Test
	public void testGetSingleVal() {
		String sql = "SELECT title FROM mybooks WHERE author = ?";
		System.out.println(bookDaoImpl.getSingleVal(sql, "XIE"));
	}

	@Test
	public void testBatch() {
		String sql = "INSERT userinfo (username,accountid) VALUES(?,?)";
		bookDaoImpl.batch(sql, new Object[]{"a",1},new Object[]{"b",1});
	}

}
