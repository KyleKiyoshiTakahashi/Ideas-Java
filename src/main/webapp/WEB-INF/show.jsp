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
<title>Details</title>
</head>
<body>
<h1><c:out value="${idea.name}"/></h1>
<a href="/logout">Logout</a>
<p>Created by: <c:out value="${idea.user.name}"/>  </p>
<a href="/ideas/${idea.id}/delete">Delete Idea</a>
<%-- <h3>Users who liked your idea:</h3>
<table>
				<thead>
	  				<tr>
	    				<th>Name</th>
					   
	  				</tr>
				</thead>
				<tbody>
  					<c:forEach items="${likes}" var="like">
		  				<tr>
					    	<td><c:out value="${like.name}"/> </td>
						<tr>
					</c:forEach>
				</tbody>
			</table> --%>

<a href="/ideas/${idea.id}/edit">Edit</a>
</body>
</html>