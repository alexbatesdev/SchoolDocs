package com.example.moviesintellij;

import java.util.ArrayList;

public class GlobalVars {
    private static ArrayList<Review> reviews = new ArrayList<Review>();


    public static void addReview(Review review) {
        reviews.add(review);
    }


    public static ArrayList<Review> getReviews() {
        return reviews;
    }


    public static void removeReview(Review review) {
        reviews.remove(review);
    }

}
