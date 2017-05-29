package com.eix.bookstore.daoimpl;

import com.eix.bookstore.dao.UserDao;
import com.eix.bookstore.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		String sql = "SELECT * from userinfo WHERE userName = ?";
		return query(sql, userName);
	}

}
