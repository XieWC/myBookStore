package com.eix.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eix.bookstore.domain.Account;
import com.eix.bookstore.domain.Book;
import com.eix.bookstore.domain.ShoppingCart;
import com.eix.bookstore.domain.ShoppingCartItem;
import com.eix.bookstore.domain.User;
import com.eix.bookstore.service.BookService;
import com.eix.bookstore.web.CriteriaBook;
import com.eix.bookstore.web.Page;
import com.eix.bookstore.web.ShoppingCartUtils;
import com.google.gson.Gson;

/**
 * Servlet implementation class BookServlet
 */
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookService bookService = new BookService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void cash(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userNameStr = request.getParameter("userName");
		String accountIdStr = request.getParameter("accountId");
	    StringBuffer errors = validateFormField(userNameStr, accountIdStr);
		if(errors.toString().equals("")){
			errors = validateUser(userNameStr, accountIdStr, errors);
			if(errors.toString().equals("")){
				errors = validateBookStoreNumber(request, errors);
				if(errors.toString().equals("")){
					errors = validateBalance(request, accountIdStr, errors);
				}
			}
		}
		if(!errors.toString().equals("")){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request, response);
			return;
		}
		
		//逻辑操作
		bookService.cash(ShoppingCartUtils.getShoppingCart(request),userNameStr,accountIdStr);
		response.sendRedirect(request.getContextPath() +"/success.jsp");
	}

	private StringBuffer validateBalance(HttpServletRequest request,
			String accountIdStr, StringBuffer errors) {
		ShoppingCart sc = ShoppingCartUtils.getShoppingCart(request);
		Account account = bookService.getAccount(Integer.parseInt(accountIdStr));
		if(account.getBalance() < sc.getTotalMoney()){
			errors.append("余额不足！");
		}
		return errors;
	}

	private StringBuffer validateBookStoreNumber(HttpServletRequest request,
			StringBuffer errors) {
		ShoppingCart sc = ShoppingCartUtils.getShoppingCart(request);
		for(ShoppingCartItem sci : sc.getItems()){
			int quantity = sci.getQuantity();
			int storeNumber = bookService.getBook(sci.getBook().getId()).getStoreNumber();
			if(quantity > storeNumber){
				errors.append(sci.getBook().getTitle() + "库存不足");
				
			}
		}
		return errors;
	}

	private StringBuffer validateUser(String userNameStr, String accountIdStr,
			StringBuffer errors) {
		User user = bookService.getUser(userNameStr);
		if(user != null){
			int accountId2 = user.getAccountId();
			if(accountIdStr.equals("" + accountId2)){
				return errors;
			}
		}
		return errors.append("用户名和密码不正确");
	}

	private StringBuffer validateFormField(String userStr, String passwordStr) {
		StringBuffer errors1 = new StringBuffer("");
		if(userStr == null || userStr.trim().equals("")){
			errors1.append("用户名不能为空");
		}
		if(passwordStr == null || passwordStr.trim().equals("")){
			errors1.append("密码不能为空");
		}
		return errors1;
	}
	protected void updateItemQuantity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String quantityStr = request.getParameter("quantity");
		ShoppingCart sc = ShoppingCartUtils.getShoppingCart(request);
		int id = -1;
		int quantity = -1;
		try {
			id = Integer.parseInt(idStr);
			quantity = Integer.parseInt(quantityStr);
		} catch (NumberFormatException e) {}
		if(id > 0 && quantity > 0){
			bookService.updateItemQuantity(id,quantity,sc);
			
		}
		
		//Json
		Map<String,Object> result = new HashMap<>();
		result.put("bookNumber", sc.getBookNumber());
		result.put("totalMoney", sc.getTotalMoney());
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(result);
		response.setContentType("text/javascript");
		response.getWriter().print(jsonStr);
	}
	
	protected void remove(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		ShoppingCart sc = ShoppingCartUtils.getShoppingCart(request);
		bookService.remove(sc,id);
		request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
	}
	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart sc = ShoppingCartUtils.getShoppingCart(request);
		bookService.clear(sc);
	    request.getRequestDispatcher("/WEB-INF/pages/emptyCart.jsp").forward(request, response);
	}
	
	protected void forward(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("page");
		request.getRequestDispatcher("/WEB-INF/pages/"+name+".jsp").forward(request, response);
	}
	protected void addToCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = 0;
		
		try {
			id=Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		if(id > 0){
			ShoppingCart sc = ShoppingCartUtils.getShoppingCart(request);
			bookService.addToCart(id,sc);
		}
		getBooks(request,response);
		return;
	}
	
	protected void getBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
	
		int pageNo = 0;
		int id = 0;
		if(idStr != null){
			try {
				id = Integer.parseInt(idStr);
			} catch (NumberFormatException e) {}
		
		}
		
		
		Book book = bookService.getBook(id);
		request.setAttribute("book", book);
	
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
	}
	
	protected void getBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");
		
		int pageNo = 1;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {
		}
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {
			
		}
		CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice, pageNo);
		System.out.println(criteriaBook.getMinPrice());
		Page<Book> page = bookService.getPage(criteriaBook);
		
		request.setAttribute("bookpage", page);
		request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request, response);
	}

}
