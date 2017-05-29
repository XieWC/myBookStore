package com.eix.bookstore.service;

import java.util.Iterator;
import java.util.Set;

import com.eix.bookstore.dao.BookDao;
import com.eix.bookstore.dao.TradeDao;
import com.eix.bookstore.dao.TradeItemDao;
import com.eix.bookstore.dao.UserDao;
import com.eix.bookstore.daoimpl.BookDaoImpl;
import com.eix.bookstore.daoimpl.TradeDaoImpl;
import com.eix.bookstore.daoimpl.TradeItemDaoImpl;
import com.eix.bookstore.daoimpl.UserDaoImpl;
import com.eix.bookstore.domain.Trade;
import com.eix.bookstore.domain.TradeItem;
import com.eix.bookstore.domain.User;

public class UserService {
	private UserDao userDao = new UserDaoImpl();
	private TradeDao tradeDao = new TradeDaoImpl();
	private TradeItemDao tradeItemDao = new TradeItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();

	public User getUserWithTrade(String userNameStr) {
		// TODO Auto-generated method stub
		User user = userDao.getUser(userNameStr);
		if (user == null) {
			return null;
		}
		Set<Trade> trades = tradeDao.getTradeWithUserId(user.getUserId());
		if (trades != null) {
			Iterator<Trade> tradeIt = trades.iterator();
			while (tradeIt.hasNext()) {
				Trade trade = tradeIt.next();

				Set<TradeItem> items = tradeItemDao.getTradeItemByTradeId(trade
						.getTradeId());

				if (items != null) {
					for (TradeItem item : items) {
						item.setBook(bookDao.getBook(item.getBookId()));
					}
					if (items.size() != 0) {
						trade.setTradeItems(items);
					}
				}
				if (items == null || items.size() == 0) {
					tradeIt.remove();
				}

			}
		}
		if (trades != null & trades.size() != 0) {
			user.setTrades(trades);
		}
		return user;
	}

}
