package com.eix.bookstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.eix.bookstore.dao.Dao;
import com.eix.bookstore.db.JDBCUtils;
import com.eix.bookstore.reflection.ReflectionUtils;
import com.eix.bookstore.web.ConnectionContext;



public class BaseDao<T> implements Dao<T> {
    private QueryRunner queryRunner = new QueryRunner();
    
    private Class<T> clazz;
    public BaseDao(){
    	clazz = ReflectionUtils.getSuperGenericType(getClass());
    }
    
	@Override
	public long insert(String sql, Object... args) {
		// TODO Auto-generated method stub
		long id = 0;
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet resultSet = null;
		
		
		try {
			connection = ConnectionContext.getInstance().get();
			preparedstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			if(args != null){
				for(int i = 0;i < args.length;i++){
					preparedstatement.setObject(i+1, args[i]);
				}
			}
			
			preparedstatement.executeUpdate();
			
			resultSet = preparedstatement.getGeneratedKeys();
			if(resultSet.next()){
				id = resultSet.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.release(resultSet, preparedstatement);
		}
		return id;
	}

	@Override
	public void update(String sql, Object... args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = ConnectionContext.getInstance().get();
			queryRunner.update(connection, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public T query(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = ConnectionContext.getInstance().get();
			return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> queryforList(String sql, Object... args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = ConnectionContext.getInstance().get();
			return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <V> V getSingleVal(String sql, Object... objects) {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = ConnectionContext.getInstance().get();
			return (V)queryRunner.query(connection, sql, new ScalarHandler(), objects);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void batch(String sql, Object[]... objects) {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = ConnectionContext.getInstance().get();
			queryRunner.batch(connection, sql, objects);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
