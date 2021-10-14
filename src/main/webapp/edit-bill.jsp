<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit bills</title>
</head>
<body>
	<form action = "editBillServlet" method = "post">
		Bill name: <input type = "text" name = "billName" value = "${billToEdit.name}">
		Bill cost: <input type = "text" name = "billCost" value = "${billToEdit.cost}">
		<input type = "hidden" name = "id" value = "${billToEdit.id}">
		<input type = "submit" value = "Save Edited Bill">
	</form>

</body>
</html>