package org.teut2711.services;

import java.util.List;

import org.teut2711.exceptions.MoviesException;
import org.teut2711.exceptions.NoSuchMovieException;
import org.teut2711.exceptions.NoSuchPageException;
import org.teut2711.models.Movie;


public interface MovieService {
    Movie getLatestMovie() throws MoviesException, NoSuchMovieException;
    List<Movie> getPopularMovies(int page) throws MoviesException, NoSuchPageException;
    Movie getMovieDetails(int movieId) throws MoviesException, NoSuchMovieException;
}
