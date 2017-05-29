package com.eix.bookstore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eix.bookstore.domain.ShoppingCart;

public class ShoppingCartUtils {
  public static ShoppingCart getShoppingCart(HttpServletRequest request){
	  HttpSession session = request.getSession();
	  ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
	  if(sc == null){
		  sc = new ShoppingCart();
		  session.setAttribute("ShoppingCart", sc);
	  }
	  return sc;
  }
}
