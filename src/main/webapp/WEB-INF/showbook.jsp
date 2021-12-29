<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show one book</title>
</head>
<body>
	<h3>
		<a href="/home">Home</a>
	</h3>
	<h1>
		<c:out value="${book.title}" />
	</h1>
	<h3>
		<c:out value="${user.userName}" />
		read
		<c:out value="${book.title}" />
		by
		<c:out value="${book.authorName}" />
	</h3>
	<h3>
		Here are
		<c:out value="${user.userName}" />
		thoughts
	</h3>
	<hr />
	<h3>
		<c:out value="${book.thoughts}" />
	</h3>
	<h3>
		<c:if test="${user.id == book.user.id}">
			<a href="/${book.id}/editBook">Edit</a>
		</c:if>
	</h3>
	<form action="/deleteBook/${book.id}" method="post">
		<input type="hidden" name="_method" value="delete"></input> <input
			type="submit" value="Delete"></input>
	</form>
</body>
</html>