package com.eix.bookstore.dao;

import com.eix.bookstore.domain.Account;

public interface AccountDao {
    Account getAccount(int accountId);
    void updateBalance(int accountId, float amount);
}
