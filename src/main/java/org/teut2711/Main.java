package org.teut2711;

import org.teut2711.exceptions.MoviesException;
import org.teut2711.exceptions.NoSuchMovieException;
import org.teut2711.exceptions.NoSuchPageException;
import org.teut2711.models.Movie;
import org.teut2711.models.MovieDetails;
import org.teut2711.network.DefaultNetworkClient;
import org.teut2711.network.NetworkClient;
import org.teut2711.services.DefaultMovieService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        NetworkClient networkClient = new DefaultNetworkClient("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1ZjkzNGRiNDM5MGM2NThiOTI5YzBiYWU3ZGY2ODlmYiIsInN1YiI6IjY1Y2U3Mjc2ZGIxNTRmMDE0OTlkODFjYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XR2B_l8H4IXNQGbugX6xkvNTdxN2-wbb56C52KsxSfQ");

        DefaultMovieService movieService = new DefaultMovieService(networkClient);

        try{
            List<Movie> movies = movieService.getPopularMovies(1);
            for (Movie movie : movies) {

                printMovie(movie);
            }
        } catch (MoviesException | NoSuchPageException e) {
            System.out.println(e.getMessage());
        }
        try {
            MovieDetails movieDetails = movieService.getLatestMovie();
//            printMovie(movieDetails);
        } catch (MoviesException | NoSuchMovieException e) {
            System.out.println(e.getMessage());
        }
        try {
            MovieDetails movieDetails = movieService.getMovieDetails(933131);
            printMovieDetails(movieDetails);
        } catch (MoviesException | NoSuchMovieException e) {
            System.out.println(e.getMessage());
        }
    }
    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormat.format(date);
    }
    private  static  void  printMovie(Movie movieDetails){
        System.out.println("Id: " + movieDetails.getId());

        System.out.println("Title: " + movieDetails.getTitle());
        System.out.println("Overview: " + movieDetails.getOverview());
        System.out.println("Release Date: " + formatDate(movieDetails.getReleaseDate()));
        System.out.println("Vote Average: " + movieDetails.getVoteAverage());
        System.out.println("Is Adult: " + movieDetails.isAdult());
        System.out.println("Backdrop Path: " + movieDetails.getBackdropPath());
        System.out.println("Original Language: " + movieDetails.getOriginalLanguage());
        System.out.println("Original Title: " + movieDetails.getOriginalTitle());
        System.out.println("Popularity: " + movieDetails.getPopularity());
        System.out.println("Poster Path: " + movieDetails.getPosterPath());
        System.out.println("Is Video: " + movieDetails.isVideo());
        System.out.println("Vote Count: " + movieDetails.getVoteCount());
        System.out.println();
    }

    private  static  void  printMovieDetails(MovieDetails movie){
        System.out.println("Id: " + movie.getId());

        System.out.println("Title: " + movie.getTitle());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("Release Date: " + formatDate(movie.getReleaseDate()));
        System.out.println("Vote Average: " + movie.getVoteAverage());
        System.out.println("Is Adult: " + movie.isAdult());
        System.out.println("Backdrop Path: " + movie.getBackdropPath());
        System.out.println("Original Language: " + movie.getOriginalLanguage());
        System.out.println("Original Title: " + movie.getOriginalTitle());
        System.out.println("Popularity: " + movie.getPopularity());
        System.out.println("Poster Path: " + movie.getPosterPath());
        System.out.println("Is Video: " + movie.isVideo());
        System.out.println("Vote Count: " + movie.getVoteCount());
        System.out.println();
    }
}