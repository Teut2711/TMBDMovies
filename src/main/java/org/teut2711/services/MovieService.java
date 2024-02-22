package org.teut2711.services;

import java.util.List;

import org.teut2711.exceptions.MoviesException;
import org.teut2711.exceptions.NoSuchMovieException;
import org.teut2711.exceptions.NoSuchPageException;
import org.teut2711.models.Movie;
import org.teut2711.models.MovieDetails;


public interface MovieService {
    MovieDetails getLatestMovie() throws MoviesException, NoSuchMovieException;
    List<Movie> getPopularMovies(int page) throws MoviesException, NoSuchPageException;
    MovieDetails getMovieDetails(int movieId) throws MoviesException, NoSuchMovieException;
}
