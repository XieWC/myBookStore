package com.eix.bookstore.domain;

public class TradeItem {
 private Integer itemId;
 private int bookId;
 private int quantity;
 private int tradeId;
 private Book book;
public Book getBook() {
	return book;
}
public void setBook(Book book) {
	this.book = book;
}
public Integer getItemId() {
	return itemId;
}
public void setItemId(Integer itemId) {
	this.itemId = itemId;
}
public int getBookId() {
	return bookId;
}
public void setBookId(int bookId) {
	this.bookId = bookId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getTradeId() {
	return tradeId;
}
public void setTradeId(int tradeId) {
	this.tradeId = tradeId;
}
 
}
