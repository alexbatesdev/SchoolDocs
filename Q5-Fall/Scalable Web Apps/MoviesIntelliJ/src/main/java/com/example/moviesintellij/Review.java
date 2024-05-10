package com.example.moviesintellij;

public class Review {
    public Review(String movie, double score, String body) {
        this.movie = movie;
        this.score = Math.round(score * 10.0) / 10.0;
        this.body = body;
    }

    private String movie;
    private double score;
    private String body;

    public String getMovie() {
        return movie;
    }

    public double getScore() {
        return score;
    }

    public String getBody() {
        return body;
    }
}
