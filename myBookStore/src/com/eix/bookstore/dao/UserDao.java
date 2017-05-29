package com.eix.bookstore.dao;

import com.eix.bookstore.domain.User;

public interface UserDao {
    User getUser(String userName);
   
}
