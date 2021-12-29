<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Book</title>
</head>
<body>
	<form:form action="/newBook" method="post" modelAttribute="book">
		<form:input type="hidden" path="user" value="${user.id}" />
		<p>
			<form:label path="title">title</form:label>
			<form:errors path="title" />
			<form:input path="title" />
		</p>
		<p>
			<form:label path="authorName">author</form:label>
			<form:errors path="authorName" />
			<form:textarea path="authorName" />
		</p>
		<p>
			<form:label path="thoughts">thoughts</form:label>
			<form:errors path="thoughts" />
			<form:input path="thoughts" />
		</p>

		<input type="submit" value="Create" />
	</form:form>
</body>
</html>