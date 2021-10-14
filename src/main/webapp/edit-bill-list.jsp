<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Bill List</title>
</head>
<body>
<h1>Edit Bill List</h1>
<form action = "editBillListServlet	" method = "post">
	List Name: <input type = "text" name = "${billListToEdit.listName}"><br>
		<br>
		Available Bills:<br>
		
			<select name = "allBills" multiple size = "6">
			
				<c:forEach items = "${requestScope.allBills}" var = "currentitem">
				
					<option value = "${currentitem.id}">${currentitem.name} | ${currentitem.cost}</option>
					
				</c:forEach>
			
			</select>
		<br>
		<input type = "submit" value = "Create list and Add Bills">
		
	</form>
</body>
</html>