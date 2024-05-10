package com.example.moviesintellij;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchSubmit", value = "/SearchSubmit")
public class SearchSubmit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            String searchParam = request.getParameter("searchBar");

            ArrayList<Review> reviews = GlobalVars.getReviews();
            ArrayList<Review> searchResults = new ArrayList<Review>();
            for (Review review : reviews) {
                if (review.getMovie().toLowerCase().contains(searchParam.toLowerCase())) {
                    searchResults.add(review);
                }

            }

            request.setAttribute("searchResults", searchResults);
            RequestDispatcher disp = request.getRequestDispatcher("results.jsp");
            disp.forward(request, response);


        } catch (NullPointerException | NumberFormatException ex) {
            response.setStatus(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
