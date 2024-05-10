package edu.neumont;

import alex.lib.Console;
import edu.neumont.csc150.openmedia.MovieRating;
import edu.neumont.csc150.openmedia.MovieSearchResultList;
import edu.neumont.csc150.openmedia.OpenMediaClient;
import jdk.swing.interop.SwingInterOpUtils;

public class Main {

    public static void main(String[] args) {
        OpenMediaClient client = new OpenMediaClient("b3b1546a");
        String query = args[0] + " " + args[1];
        //display list of movies
        for (int page = 1; page <= 3; page++) {
            System.out.println("PAGE " + page);
            var movies = client.searchMoviesByTitle(query, page);
            for (var  movie : movies.getMovies()) {
                System.out.println(movie.getTitle() + " " + movie.getYear() + " " + movie.getPoster());
            }
        }

        // display imdb information
        var movies = client.searchMoviesByTitle(query, 1);
        var details = client.getMovieByImdbId(movies.getMovies().get(0).getImdbId());
        Console.println("Title: " + details.getTitle(), Console.WHITE);
        Console.println("DvD Release Date: " + details.getDvdReleaseDate(), Console.CYAN);
        Console.println("Director: " + details.getDirector(), Console.BLUE);
        Console.println("Production Company: " + details.getProduction(), Console.YELLOW);
        Console.println("Runtime: " + details.getRuntime(), Console.GREEN);
        Console.println("Imdb Rating: " + details.getImdbRating(), Console.RED);

        // display reviews
        var ratings = details.getRatings();
        for (var rating : ratings) {
            System.out.println(rating.getValue() + " - " + rating.getSource());
        }

        //String string = Console.getString("enter a string: ");
        //Console.println(string, Console.BLUE);

        boolean bool = Console.getBoolean("enter boolean (yes/no): ", "yes", "no");
        int selection = Console.getMenuSelection(new String[]{ "edit", "run" }, false);

        System.out.println("Results: ");
        System.out.println(bool);
        System.out.println(selection);
    }
}
