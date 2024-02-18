package org.teut2711.network;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MockNetworkClient implements NetworkClient {
    private final String bearerToken;

    public MockNetworkClient(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    @Override
    public Response get(@NotNull String url, @NotNull Map<String, String> queryParams) throws IOException {
        Headers headers = new Headers.Builder()
                .add("Authorization", String.format("Bearer %s", bearerToken))
                .build();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        Request.Builder requestBuilder = new Request.Builder()
                .url(urlBuilder.build())
                .headers(headers);

        Request request = requestBuilder.build();
        Response response;

        if (url.equals("https://api.themoviedb.org/3/movie/latest")) {
            response = getLatestMovieResponse(request);
        } else {
            Path moviesPath = Paths.get("./src/test/resources/popularMovies.json");
            if (url.equals("https://api.themoviedb.org/3/movie/popular")) {
                String jsonString = new String(Files.readAllBytes(moviesPath));

                response = new Response.Builder()
                        .request(request)
                        .code(200)
                        .body(ResponseBody.create(
                                jsonString,
                                MediaType.get("application/json; charset=utf-8")
                        ))
                        .build();

             } else if (url.matches("https://api.themoviedb.org/3/movie/\\d+")) {
                Pattern pattern = Pattern.compile("https://api.themoviedb.org/3/movie/(\\d+)");
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                String movieId = matcher.group(1);
                String jsonString = new String(Files.readAllBytes(moviesPath));

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

                // Get the "results" array from JSON
                JsonArray resultsArray = jsonObject.getAsJsonArray("results");

                // Find the movie with the specified ID from the array
                JsonObject movieObject = null;
                for (int i = 0; i < resultsArray.size(); i++) {
                    JsonObject obj = resultsArray.get(i).getAsJsonObject();
                    if (obj.get("id").getAsString().equals(movieId)) {
                        movieObject = obj;
                        break;
                    }
                }

                if (movieObject != null) {
                    // Return the found movie as the response
                    response = new Response.Builder()
                            .request(request)
                            .code(200)
                            .body(ResponseBody.create(
                                    gson.toJson(movieObject),
                                    MediaType.get("application/json; charset=utf-8")
                            ))
                            .build();
                } else {
                    // If movie with the specified ID is not found, return 404 response
                    response = new Response.Builder()
                            .request(request)
                            .code(404)
                            .body(ResponseBody.create(
                                    "{}",
                                    MediaType.get("application/json; charset=utf-8")
                            ))
                            .build();
                }
            } else {
                // If movie ID extraction fails
                response = new Response.Builder()
                        .request(request)
                        .code(404)
                        .body(ResponseBody.create(
                                "{}",
                                MediaType.get("application/json; charset=utf-8")
                        ))
                        .build();
            }
            } else {
                throw new Error("Not implemented request");
            }
        }
        return response;
}

    @NotNull
    private static Response getLatestMovieResponse(Request request) throws IOException {
        Response response;
        String jsonString = new String(Files.readAllBytes(Paths.get("./src/test/resources/latestMovie.json")));

        response = new Response.Builder()
                .request(request)
                .code(200)
                .body(ResponseBody.create(
                        jsonString,
                        MediaType.get("application/json; charset=utf-8")
                ))
                .build();
        return response;
    }
}