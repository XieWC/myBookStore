package com.eix.bookstore.dao;

import java.util.List;

public interface Dao<T> {
  long insert(String sql, Object ... args);
  
  void update(String sql, Object ... args);
  
  T query(String sql, Object ... args);
  
  List<T> queryforList(String sql, Object ... args);
  
  <V> V getSingleVal(String sql, Object ...objects);
  
  void batch(String sql, Object[] ...objects );
  
}
