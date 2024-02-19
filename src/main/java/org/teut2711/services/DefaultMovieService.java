package org.teut2711.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.Response;
import org.teut2711.exceptions.MoviesException;
import org.teut2711.exceptions.NoSuchMovieException;
import org.teut2711.exceptions.NoSuchPageException;
import org.teut2711.models.Movie;
import org.teut2711.network.NetworkClient;

import java.io.IOException;
import java.util.*;

public class DefaultMovieService implements MovieService {
    private final NetworkClient networkClient;

    public DefaultMovieService(NetworkClient networkClient) {
        this.networkClient = networkClient;
     }

    @Override
    public Movie getLatestMovie() throws MoviesException, NoSuchMovieException {
        try{
            Map<String, String> queryParams = new HashMap<>();

            Response response = networkClient.get("https://api.themoviedb.org/3/movie/latest", queryParams);
            if (response.isSuccessful()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

                return gson.fromJson(jsonObject, Movie.class);
            } else {
                throw new NoSuchMovieException("Page does not exists");
            }

        }catch (IOException e){
            throw new MoviesException("Failed to get your movies: " + e.getMessage());
        }

    }
    @Override
    public List<Movie> getPopularMovies(int page) throws MoviesException, NoSuchPageException {
      try{

          Map<String, String> queryParams = new HashMap<>();
          queryParams.put("page", String.valueOf(page));
          Response response = networkClient.get("https://api.themoviedb.org/3/movie/popular", queryParams);

            if (response.isSuccessful()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

                // Extract the "results" array from the JSON object
                JsonArray resultsArray = jsonObject.getAsJsonArray("results");
                Movie[] movies= gson.fromJson(resultsArray, Movie[].class);

                return Arrays.stream(movies).toList();
            } else {
                throw new NoSuchPageException("Page does not exists for popular movies");
              }

      }catch (IOException e){
          throw new MoviesException("Failed to get your popular movies: " + e.getMessage());
      }

    }


    @Override
    public Movie getMovieDetails(int movieId) throws MoviesException, NoSuchMovieException {
        try{
            Map<String, String> queryParams = new HashMap<>();

            Response response = networkClient.get("https://api.themoviedb.org/3/movie/"+movieId, queryParams);

            if (response.isSuccessful()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

                // Extract the "results" array from the JSON object
                JsonArray resultsArray = jsonObject.getAsJsonArray("results");

                return gson.fromJson(resultsArray, Movie.class);
            } else {
                throw new NoSuchMovieException("Page does not exists");
            }

        }catch (IOException e){
            throw new MoviesException("Failed to get your movies: " + e.getMessage());
        }

    }
}
