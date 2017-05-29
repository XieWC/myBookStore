package com.eix.bookstore.dao;

import java.util.Set;

import com.eix.bookstore.domain.Trade;

public interface TradeDao {
   Set<Trade> getTradeWithUserId(int userId);
   
   void insertTrade(Trade trade);
}
