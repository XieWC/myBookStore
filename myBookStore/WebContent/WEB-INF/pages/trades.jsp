<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<br> <br> User:${user.userName } <br> <br>
		<table>
			<c:forEach items="${user.trades }" var="trade">
				<tr>
					<td>
						<table border="1" cellpadding="10">
							<tr>
								<td colspan="3">TradeTime:${trade.tradeTime }</td>
							</tr>
							<c:forEach items="${trade.tradeItems }" var="tradeItem">
								<tr>
									<td>${tradeItem.book.title }</td>
									<td>${tradeItem.book.price }</td>
									<td>${tradeItem.quantity }</td>
								</tr>
							</c:forEach>
						</table>

					</td>

				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>