package com.eix.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.eix.bookstore.dao.AccountDao;
import com.eix.bookstore.daoimpl.AccountDaoImpl;

public class AccountDaoTest {
    AccountDao accountDao = new AccountDaoImpl();
	@Test
	public void testGetAccount() {
		System.out.println(accountDao.getAccount(1));
	}

	@Test
	public void testUpdateBalance() {
		accountDao.updateBalance(1, 100);
	}

}
