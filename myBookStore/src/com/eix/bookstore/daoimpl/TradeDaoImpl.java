package com.eix.bookstore.daoimpl;

import java.util.LinkedHashSet;
import java.util.Set;

import com.eix.bookstore.dao.TradeDao;
import com.eix.bookstore.domain.Trade;

public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao {
   
	@Override
	public Set<Trade> getTradeWithUserId(int userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM trade WHERE userId = ? ORDER BY tradeTime DESC";
		return new LinkedHashSet(queryforList(sql, userId));
	}

	@Override
	public void insertTrade(Trade trade) {
		// TODO Auto-generated method stub
        String sql = "INSERT INTO trade (userId, tradeTime) VALUES(?,?)";
        long tradeId = insert(sql, trade.getUserId(),trade.getTradeTime());
        trade.setTradeId((int)tradeId);
	}

}
