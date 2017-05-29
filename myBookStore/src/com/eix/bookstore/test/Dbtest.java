package com.eix.bookstore.test;

import org.junit.Test;

import com.eix.bookstore.db.JDBCUtils;

public class Dbtest {

	@Test
	public void test() {
		System.out.println(JDBCUtils.getConnection());
	}

}
