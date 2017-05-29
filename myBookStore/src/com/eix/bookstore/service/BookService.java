package com.eix.bookstore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.eix.bookstore.dao.AccountDao;
import com.eix.bookstore.dao.BookDao;
import com.eix.bookstore.dao.TradeDao;
import com.eix.bookstore.dao.TradeItemDao;
import com.eix.bookstore.dao.UserDao;
import com.eix.bookstore.daoimpl.AccountDaoImpl;
import com.eix.bookstore.daoimpl.BookDaoImpl;
import com.eix.bookstore.daoimpl.TradeDaoImpl;
import com.eix.bookstore.daoimpl.TradeItemDaoImpl;
import com.eix.bookstore.daoimpl.UserDaoImpl;
import com.eix.bookstore.domain.Account;
import com.eix.bookstore.domain.Book;
import com.eix.bookstore.domain.ShoppingCart;
import com.eix.bookstore.domain.ShoppingCartItem;
import com.eix.bookstore.domain.Trade;
import com.eix.bookstore.domain.TradeItem;
import com.eix.bookstore.domain.User;
import com.eix.bookstore.web.CriteriaBook;
import com.eix.bookstore.web.Page;

public class BookService {
    private BookDao bookDao = new BookDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private AccountDao accountDao = new AccountDaoImpl();
    private TradeDao tradeDao = new TradeDaoImpl();
    private TradeItemDao tradeItemDao = new TradeItemDaoImpl();
	public Page<Book> getPage(CriteriaBook criteriaBook) {
		// TODO Auto-generated method stub
		return bookDao.getPage(criteriaBook);
	}
	public Book getBook(int id) {
		// TODO Auto-generated method stub
		
		return bookDao.getBook(id);
	}
	public void addToCart(int id, ShoppingCart sc) {
		// TODO Auto-generated method stub
		Book book = bookDao.getBook(id);
		if(book != null){
		sc.add(book);
		}
	}
	public void clear(ShoppingCart sc) {
		// TODO Auto-generated method stub
		sc.clear();
	}
	public void remove(ShoppingCart sc, int id) {
		// TODO Auto-generated method stub
		sc.removeItem(id);
	}
	public void updateItemQuantity(int id, int quantity, ShoppingCart sc) {
		// TODO Auto-generated method stub
		sc.updateItemQuantity(id, quantity);
	}
	public User getUser(String userNameStr) {
		// TODO Auto-generated method stub
		return userDao.getUser(userNameStr);
	}
	public Account getAccount(int accountId) {
		// TODO Auto-generated method stub
		return accountDao.getAccount(accountId);
	}
	public void cash(ShoppingCart sc, String userNameStr,
			String accountIdStr) {
		// TODO Auto-generated method stub
		bookDao.batchUpdateStoreNumberAndSalesAmount(sc.getItems());
		accountDao.updateBalance(Integer.parseInt(accountIdStr), sc.getTotalMoney());
		Trade trade = new Trade();
		trade.setUserId(userDao.getUser(userNameStr).getUserId());
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		tradeDao.insertTrade(trade);
		
		Collection<TradeItem> items = new ArrayList<>();
		for(ShoppingCartItem sci : sc.getItems()){
			TradeItem tradeItem = new TradeItem();
			tradeItem.setTradeId(trade.getTradeId());
			tradeItem.setBookId(sci.getBook().getId());
			tradeItem.setQuantity(sci.getQuantity());
			
			items.add(tradeItem);
		}
		tradeItemDao.batchSave(items);
		sc.clear();
	}
	

}
