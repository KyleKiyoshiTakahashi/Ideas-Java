<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
</head>
<body>
<a href="/logout">Logout</a>
<h3>Edit <c:out value="${idea.name}"/></h3>
<form:form method="post" action="/ideas/${id}/edit" modelAttribute="idea">
		
				<input type="hidden" name="_method" value="put">
				<h4>
					<form:label path="name">Name:</form:label>
					<form:input  type="text" path="name"/>
					<form:errors path="name"/>
				</h4>
				<form:hidden path="user" value="${user.id}"/>
				<input type="submit" value="Edit"/>
		
</form:form>
<form:errors  path="idea.*"/>
		<!-- <p><c:out value="${error}" /></p> -->
			<a href="ideas/${idea.id}/delete">Delete Idea</a>
</body>
</html>