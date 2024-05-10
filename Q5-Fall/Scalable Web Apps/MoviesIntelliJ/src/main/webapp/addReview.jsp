<%@ page import="com.example.moviesintellij.GlobalVars" %>
<%@ page import="com.example.moviesintellij.Review" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add Review</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="reviews.jsp">Reviews</a>
        <a href="addReview.jsp">Add Review</a>
        <a href="search.jsp">Search</a>
    </nav>
    <h1>Add Review</h1>
    <%-- The action specifies what happens when we click submit--%>
    <form action="${pageContext.request.contextPath}/AddReview" method="post">
        <table>
            <tr>
                <td><label for="movie">Movie</label></td>
                <td><input type="text" id="movie" name="movie" /></td>
            </tr>
            <tr>
                <td><label for="score">Score</label></td>
                <td><input type="number" id="score" name="score" /></td>
            </tr>
            <tr>
                <td><label for="body">Review</label></td>
                <td><textarea name="body" id="body" cols="20" rows="10"></textarea></td>
            </tr>
            <tr>
                <td><input type="submit" value="Add Review" /></td>
            </tr>
        </table>
    </form>
        <% //This is for testing purposes so I don't have to manually create reviews
            for (int i = 0; i <5; i++) {
                GlobalVars.addReview(new Review("Morbius " + i, (Math.random()*(10-0+1)+0), "Morbius is the best movie of all time"));
                System.out.println("Added review");
        }%>



</body>
</html>