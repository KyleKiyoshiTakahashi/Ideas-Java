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
<title>Welcome</title>
</head>
<body>

<div>
		<h1>Welcome</h1>
		<div>
			
			<h1>Register</h1>
			<form:form method="post" action="/register" modelAttribute="userObj">
				<h4>
					<form:label path="name">Name:</form:label>
					<form:input  type="text" path="name"/>
				</h4>
			
				<h4>
					<form:label path="email">Email:</form:label>
					<form:input type="email" path="email"/>
				</h4>
				<h4 >
					<form:label path="password">Password:</form:label>
					<form:password path="password"/>
				</h4>
				<h4>
					<form:label path="confirmPassword">Confirm Password:</form:label>
					<form:password path="confirmPassword"/>
				</h4>
				<input type="submit" value="Register"/>
			</form:form>			
			<form:errors path="userObj.*"/>
			
		</div>
		<hr>
		<div>
			<h1>Login</h1>
				<p><c:out value="${error}" /></p>
				<form action="/login" method="post">
					<h4>Email:<input type="email" name="email"></h4>
					<h4>Password:<input type="password" name="password"></h4>
					<input type="submit" value="Login">
				</form> 
		</div>
	</div>
</body>
</html>