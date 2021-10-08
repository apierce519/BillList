<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>List of bills</title>
	<p>By A. Pierce</p>
</head>
<body>
	<form method = "post" action = "navigationServlet">
		<table>
			<c:forEach items="${requestScope.allBills}" var = "currentBill">
				<tr>
					<td><input type = "radio" name = "id" value = "${currentBill.id}"></td>
					<td>${currentBill.billName}</td>
					<td>${currentBill.billCost}</td>
				</tr>
			</c:forEach>
		</table>
		<input type = "submit" value = "edit" name = "doThisCommand">
		<input type = "submit" value = "delete" name = "doThisCommand">
		<input type = "submit" value = "add" name = "doThisCommand">
	</form>
<br>
<a href = "StartPage.html">Return to Start Page</a>
</body>
</html>