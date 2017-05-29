<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
<%@include file="/commons/queryPrice.jsp" %>
<script type="text/javascript">
  $(function(){
	  $(".delete").click(function(){
		  var $tr=$(this).parent().parent();
		  var title=$.trim($tr.find("td:first").text());
		  var flag=confirm("你确定要删除" + title + "的信息吗？");
		  if(flag){
			  return true;
		  }
		  return false;
	  });
	  //ajax
	  $(":text").change(function(){
	     var quantityStr = $.trim(this.value);
	     var flag = false;
	     var quantity = -1;
	     var reg = /^\d+$/g;
	     
	     if(reg.test(quantityStr)){
	    	 quantity = quantityStr;
	    	 if(quantity >= 0){
	    		 flag = true;
	    	 }
	     }
	     if(!flag){
	    	 alert("输入的数量不合法");
	    	 $(this).val($(this).attr("class"));
	    	 return;
	     }
	     
	     var $tr = $(this).parent().parent();
	     var title = $.trim($tr.find("td:first").text());
	     
	     if(quantity ==  0){
	    	 var flag2 = confirm("确定要删除" + title + "吗？");
	    	 if(flag2){
	    		
	    		 var $a = $tr.find("td:last").find("a");
	    	
	    		 $a[0].onclick();
	    	
	    		 return;
	    	 }
	    	 
	    	 
	     }
	     var flag3 = confirm("确定要修改" + title + "数量吗？");
	     if(!flag3){
	    	 $(this).val($(this).attr("class"));
	    	 return;
	     }
	    
	     var url = "bookServlet";
	     var idVal = $.trim(this.name);
	     alert(idVal);
	     var args = {"method":"updateItemQuantity","id":idVal,"quantity":quantityStr,"time":new Date()};
	   
	     $.post(url,args,function(data){
	    	 var bookNumber = data.bookNumber;
	    	 var totalMoney = data.totalMoney;
	    	 
	    	 $("#bookNumber").text("您的购物车中共有**"+ bookNumber +"本书");
	    	 $("#totalMoney").text("总金额："+ totalMoney);
	     },"JSON");
	  });
  });
</script>
</head>
<body>
	<center>
		<br> <br> 
	<div id="bookNumber">您的购物车中共${sessionScope.ShoppingCart.bookNumber }有本书</div>	
		<table cellpadding="10">
			<tr>
				<td>Title</td>
				<td>Quantity</td>
				<td>Price</td>
				<td>&nbsp;</td>
			</tr>
			<c:forEach items="${sessionScope.ShoppingCart.items }" var="item">
				<tr>
					<td>${item.book.title }</td>
					<td><input type="text" size="1" value="${item.quantity }" name="${item.book.id }" class="${item.quantity }"/></td>
					<td>${item.book.price }</td>
					<td><a href="bookServlet?method=remove&pageNo=${param.pageNo }&id=${item.book.id }" class="delete">删除</a></td>
				</tr>
			</c:forEach>
            <tr>
            <td id="totalMoney" colspan="4">总金额：${sessionScope.ShoppingCart.totalMoney }</td>
            </tr>
            <tr>
            <td colspan="4">
            <a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>&nbsp;&nbsp;
            <a href="bookServlet?method=clear">清空购物车</a>&nbsp;&nbsp;
            <a href="bookServlet?method=forward&page=cash">结账</a>&nbsp;&nbsp;
            
            </td>
            </tr>
		</table>
	</center>
</body>
</html>