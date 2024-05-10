<%@ page import="com.example.moviesintellij.GlobalVars" %>
<%@ page import="com.example.moviesintellij.Review" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Reviews</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="reviews.jsp">Reviews</a>
        <a href="addReview.jsp">Add Review</a>
        <a href="search.jsp">Search</a>
    </nav>
    <h1>Reviews</h1>
    <div class="manual">
    <%
        for (Review review : GlobalVars.getReviews()) {
    %>
        <h2><%= review.getMovie() %></h2>
        <p>Rating: <%= review.getScore() %></p>
        <p>Review: <%= review.getBody() %></p>
    <%}%>
    </div>



</body>
</html>