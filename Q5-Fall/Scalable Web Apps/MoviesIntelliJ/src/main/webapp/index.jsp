<%@ page import="com.example.moviesintellij.GlobalVars" %>
<%@ page import="com.example.moviesintellij.Review" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <link rel="stylesheet" href="style.css">
</head>
    <body>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="reviews.jsp">Reviews</a>
            <a href="addReview.jsp">Add Review</a>
            <a href="search.jsp">Search</a>
        </nav>
        <h1>Movie Review site</h1>
        <img src="https://pa1.narvii.com/7892/c84f7e55c4c6e71c3de015e60fa8fcd4c3e227f7r1-320-180_hq.gif" alt="RobTheWoz" />
<%--     This gets the url that intellij is using   "${pageContext.request.contextPath}/url"--%>
    </body>
</html>