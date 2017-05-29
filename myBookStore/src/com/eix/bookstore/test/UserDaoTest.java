package com.eix.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.eix.bookstore.dao.UserDao;
import com.eix.bookstore.daoimpl.UserDaoImpl;

public class UserDaoTest {
   
	@Test
	public void test() {
		UserDao userDao = new UserDaoImpl();
		System.out.println(userDao.getUser("xie"));
	}

}
