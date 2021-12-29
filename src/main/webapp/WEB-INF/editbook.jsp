<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Book</title>
</head>
<body>
	<form:form action="/${book.id}/book" method="post" modelAttribute="book">
		 <input type="hidden" name="_method" value="put">
    	<p>
        	<form:label path="title">Title</form:label>
        	<form:errors path="title"/>
        	<form:input path="title"/>
    	</p>
    	<p>
        	<form:label path="authorName">Author</form:label>
        	<form:errors path="authorName"/>
        	<form:textarea path="authorName"/>
    	</p>
    	<p>
        	<form:label path="thoughts">Thoughts</form:label>
        	<form:errors path="thoughts"/>
        	<form:input path="thoughts"/>
    	</p>  
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>