<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="/logout">Logout</a>
	<a href="/books/new">New Book</a>
	<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author Name</th>
            <th>Posted By</th>
        </tr>
    </thead>
    <tbody>
    	<c:forEach var="book" items="${books}">
		<tr>
            <td><c:out value="${book.id }"/></td>
            <td><a href="/${book.id }/showBook"><c:out value="${book.title }"/></a></td>
            <td><c:out value="${book.authorName }"/></td>
            <td><c:out value="${book.user.userName }"/></td>
        </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>