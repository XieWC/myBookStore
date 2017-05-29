package com.eix.bookstore.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.eix.bookstore.dao.TradeItemDao;
import com.eix.bookstore.domain.TradeItem;

public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao {

	@Override
	public void batchSave(Collection<TradeItem> items) {
		// TODO Auto-generated method stub
         String sql = "INSERT INTO tradeitem (bookId,quantity,tradeId) VALUES (?,?,?)";
         Object [][] params = new Object[items.size()][3];
         List<TradeItem> tradeItems = new ArrayList<>(items);
         for(int i = 0;i < tradeItems.size();i++){
        	 params[i][0] = tradeItems.get(i).getBookId();
        	 params[i][1] = tradeItems.get(i).getQuantity();
        	 params[i][2] = tradeItems.get(i).getTradeId();
        	 
         }
         batch(sql, params);
	}

	@Override
	public Set<TradeItem> getTradeItemByTradeId(Integer tradeId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tradeitem WHERE tradeId = ?";
		
		return new LinkedHashSet<>(queryforList(sql, tradeId));
	}

}
