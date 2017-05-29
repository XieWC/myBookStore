package com.eix.bookstore.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.eix.bookstore.dao.BookDao;
import com.eix.bookstore.domain.Book;
import com.eix.bookstore.domain.ShoppingCartItem;
import com.eix.bookstore.web.CriteriaBook;
import com.eix.bookstore.web.Page;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public Book getBook(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM mybooks WHERE id = ?";
		return query(sql, id);
	}

	@Override
	public int getStoreNumber(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT storeNumber FROM mybooks WHERE id = ?";
		return getSingleVal(sql, id);
	}
	


	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		// TODO Auto-generated method stub
		Page<Book> page = new Page<>(cb.getPageNo());
		page.setTotalItemNumber(getTotalBookNumber(cb));
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 3));
		
		return page;
	}

	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM mybooks WHERE price >= ? AND price <= ? LIMIT ?,?";
		return queryforList(sql, cb.getMinPrice(), cb.getMaxPrice(), (cb.getPageNo() - 1)*pageSize,pageSize);
		
		
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		// TODO Auto-generated method stub
        String sql = "SELECT count(id) FROM mybooks WHERE price >= ? AND price <=?";
		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice());
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
		// TODO Auto-generated method stub
		String sql = "UPDATE mybooks SET salesAmount = salesAmount + ?, storeNumber = storeNumber - ? WHERE id = ?";
		Object [][] params = new Object[items.size()][3];
		List<ShoppingCartItem> scis = new ArrayList<ShoppingCartItem>(items);
		for(int i = 0;i < scis.size();i++){
			params[i][0] = scis.get(i).getQuantity();
			params[i][1] = scis.get(i).getQuantity();
			params[i][2] = scis.get(i).getBook().getId();
			
		}
		batch(sql, params);
	}

}
