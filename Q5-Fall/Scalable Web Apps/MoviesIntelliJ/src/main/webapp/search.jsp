<%@ page import="com.example.moviesintellij.GlobalVars" %>
<%@ page import="com.example.moviesintellij.Review" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Search</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="reviews.jsp">Reviews</a>
        <a href="addReview.jsp">Add Review</a>
        <a href="search.jsp">Search</a>
    </nav>
    <h1>Search</h1>
    <form method="GET" action="${pageContext.request.contextPath}/SearchSubmit" > <%--${pageContext.request.contextPath}/--%>
        <input type="text" name="searchBar" />
        <input type="submit"/>
    </form>



</body>
</html>