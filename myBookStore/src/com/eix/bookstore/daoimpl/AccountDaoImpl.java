package com.eix.bookstore.daoimpl;

import com.eix.bookstore.dao.AccountDao;
import com.eix.bookstore.domain.Account;

public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

	@Override
	public Account getAccount(int accountId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM account WHERE accountId = ?";
		return query(sql,accountId);
	}

	@Override
	public void updateBalance(int accountId, float amount) {
		// TODO Auto-generated method stub
		String sql = "UPDATE account SET balance = balance - ? WHERE accountId = ?";
		update(sql,amount,accountId);
	}

}
