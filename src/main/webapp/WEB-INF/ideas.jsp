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
<title>Ideas</title>
</head>
<body>
<a href="/logout">Logout</a>
<h1>Welcome, <c:out value="${user.name}"/></h1>
<table>
				<thead>
	  				<tr>
	    				<th>Idea</th>
					    <th>Created By</th>
					    <th>Likes</th>
					    <th>Action</th>
	  				</tr>
				</thead>
				<tbody>
					<c:forEach items="${ideas}" var="i">
	  				<tr>
	  					<td><a href="/ideas/${i.id}"><c:out value="${i.name}"/></a></td>
					    <td><c:out value="${i.user.name}"/></td>
                   <%--      <c:choose> --%>
                        <%-- <c:when test="${i.user == user}">
                            <td>*Like* | <a href="/likes/${i.id}/edit">Edit</a> | <a href="ideas/${i.id}/delete">Delete</a></td>
                        </c:when>
                        <c:otherwise>
                            <c:set var="Like" value="${false}"/>
                            <c:forEach items="${i.getLikeUsers()}" var="like">
                            	<!-- info on "if and test" https://www.tutorialspoint.com/jsp/jstl_core_if_tag.htm  -->
                                <c:if test="${like == user}">
                                    <c:set var="liked" value="${true}"/>
                                </c:if>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${like == false}">
                                    <td><a href="/ideas/${i.id}/like">Like</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td>*Like* | <a href="ideas/${i.id}/cancel">Cancel</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                        </c:choose>   --%>
	  				</tr>
	  				</c:forEach>
				</tbody>
			</table>
			
			<h2><a href="/ideas/new"> Create an Idea</a></h2>
			
</body>
</html>