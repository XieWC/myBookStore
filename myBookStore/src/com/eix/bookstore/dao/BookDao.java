package com.eix.bookstore.dao;

import java.util.Collection;
import java.util.List;

import com.eix.bookstore.domain.Book;
import com.eix.bookstore.domain.ShoppingCartItem;
import com.eix.bookstore.web.CriteriaBook;
import com.eix.bookstore.web.Page;

public interface BookDao {


   Book getBook(int id);
   int getStoreNumber(int id);
   
   Page<Book> getPage(CriteriaBook cb);
   List<Book> getPageList(CriteriaBook cb,int pageSize);
   
   long getTotalBookNumber(CriteriaBook cb);
    void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items);
}
