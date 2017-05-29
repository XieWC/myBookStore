package com.eix.bookstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
   private Map<Integer,ShoppingCartItem> books = new HashMap<>();
   public void add(Book book){
	   if(hasBook(book.getId())){
		   ShoppingCartItem sci = books.get(book.getId());
		   sci.increment();
	   }else{
		   ShoppingCartItem sci = new ShoppingCartItem(book);
		   books.put(book.getId(), sci);
	   }
	   
	   
   }
   public boolean hasBook(Integer id){
	   return books.containsKey(id);
   }
   public Map<Integer,ShoppingCartItem> getBooks(){
	   return books;
   }
   public Collection<ShoppingCartItem> getItems(){
	  return books.values();
   }
   public int getBookNumber(){
	 int total = 0;
	 for(ShoppingCartItem sci:getItems()){
		 total = total + sci.getQuantity();
	 }
	 return total;
   }
   public float getTotalMoney(){
	   float total = 0;
	   for(ShoppingCartItem sci:getItems()){
		   total = total + sci.getItemMoney(); 
	   }
	   return total;
   }
   public boolean isEmpty(){
	   return books.isEmpty();
   }
   public void clear(){
	    books.clear();
   }
   public void removeItem(int id){
	   books.remove(id);
   }
   public void updateItemQuantity(int id ,int quantity){
	   ShoppingCartItem sci = books.get(id);
	   if(sci != null){
		   sci.setQuantity(quantity);
	   }
	   
   }
}
