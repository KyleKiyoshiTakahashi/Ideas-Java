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
<title>New Idea</title>
</head>
<body>
<h1>Create a New Idea</h1>
			<form:form method="post" action="/addIdea" modelAttribute="ideaObj">
				<h4>
					<form:label path="name">Name:</form:label>
					<form:input type="text" path="name"/>
				</h4>
				<form:hidden path="user" value="${user.id}"/>
				<input type="submit" value="Create"/>
			</form:form>
			<form:errors  path="ideaObj.*"/>
</body>
</html>