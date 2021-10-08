<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create a new Bill List</title>
</head>
<body>

	<form action = "createNewBillListServlet" method = post>
	
		List Name: <input type = "text" name = "billListName"><br>
		
		Available Bills: <br>
		
		<select name = "allBillsToAdd" multiple size = "6">
		
			<c:forEach items = "${requestScope.allBills}" var = "currentbill">
					
					<option value = "${currentbill.id}">${currentbill.billName} | ${currentbill.billCost}</option>
					
			</c:forEach>
		
		</select>
		<br>
		<input type = "submit" value = "Create List and Add Bills">
	</form>
	<a href = "StartPage.html">Return to Start Page</a>
</body>
</html>