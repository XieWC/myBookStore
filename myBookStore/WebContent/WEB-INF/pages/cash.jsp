<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
<script type="text/javascript"></script>
<%@ include file="/commons/queryPrice.jsp"%>
</head>
<body>
	<center>
		您的购物车里有${sessionScope.ShoppingCart.bookNumber }本书 <br>
		共金额为${sessionScope.ShoppingCart.totalMoney }元 <br> <br>
		
		<c:if test="${!empty errors }">
		<font color="red">${errors }</font>
		</c:if>
		<form action="bookServlet?method=cash" method="post">
			<table cellpadding="10">
				<tr>
					<td>User:</td>
					<td><input type="text" name="userName" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="text" name="accountId" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>