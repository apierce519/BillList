<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new Bill List</title>
</head>
<body>
	<form action = "createNewBillListServlet" method = "post">
	
		List Name: <input type = "text" name = "billListName"><br>
		<br>
		Available Bills:<br>
		
			<select name = "allBills" multiple size = "6">
			
				<c:forEach items = "${requestScope.allBills}" var = "currentitem">
				
					<option value = "${currentitem.id}">${currentitem.billName} | ${currentitem.billCost}</option>
					
				</c:forEach>
			
			</select>
		<br>
		<input type = "submit" value = "Create list and Add Bills">
		
	</form>
<a href = "StartPage.html">Back to Main Menu</a>
</body>
</html>