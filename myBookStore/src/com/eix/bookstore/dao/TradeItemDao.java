package com.eix.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import com.eix.bookstore.domain.TradeItem;

public interface TradeItemDao {
   void batchSave(Collection<TradeItem> items);
   Set<TradeItem> getTradeItemByTradeId(Integer tradeId);
}
