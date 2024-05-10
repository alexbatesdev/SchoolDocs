<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.moviesintellij.Review" %><%--
  Created by IntelliJ IDEA.
  User: mbates
  Date: 10/14/2022
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Results</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <nav>
    <a href="index.jsp">Home</a>
    <a href="reviews.jsp">Reviews</a>
    <a href="addReview.jsp">Add Review</a>
    <a href="search.jsp">Search</a>
  </nav>
  <h1>Search Results</h1>
  <% ArrayList<Review> searchResults = (ArrayList<Review>) request.getAttribute("searchResults");%>
  <div class="manual">
    <% for (int i = 0; i < searchResults.size(); i++) {%>
      <h2><%= searchResults.get(i).getMovie() %></h2>
      <p><%= searchResults.get(i).getScore() %></p>
      <p><%= searchResults.get(i).getBody() %></p>
    <%}%>
  </div>
</body>
</html>
