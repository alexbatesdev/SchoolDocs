package com.example.moviesintellij;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddReview", value = "/AddReview")
public class AddReview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GlobalVars.addReview(
            new Review(
                request.getParameter("movie"),
                Double.parseDouble(request.getParameter("score")),
                request.getParameter("body")
            )
        );
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
