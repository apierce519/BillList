<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View all Bill Lists</title>
</head>
<body>
	<form method = "post" action = "billListNavigationServlet">
	
		<table>
		
			<c:forEach items = "${requestScope.allLists}" var = "currentlist">
				<tr>
					<td><input type = "radio" name = "id" value = "${currentlist.id}"></td>
					<td><h2>${currentlist.listName}</h2></td>
				</tr>
				<c:forEach var = "listVal" items="${currentlist.listOfBills}">
					<tr>
						<td></td>
						<td colspan = "3">${listVal.name} | ${listVal.cost}</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
		
		<input type = "submit" value = "edit" name = "doThisCommand">
		<input type = "submit" value = "delete" name = "doThisCommand">
		<input type = "submit" value = "add" name = "doThisCommand">
	</form>
	
	<ul>
		<li><a href="generateBillItemListServlet">Create a new List</a></li>
		<li><a href="start-page.html">Return to Start Page</a></li>
	</ul>
	
</body>
</html>