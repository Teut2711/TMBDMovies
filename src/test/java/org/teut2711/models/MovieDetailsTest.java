package org.teut2711.models;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieDetailsTest {

    @Test
    public void testMovieCreation() throws IOException {
        // Read the contents of the JSON file
        String jsonString = Files.readString(Paths.get("./src/test/resources/movieDetails.json"));
        // Given
        Gson gson = new Gson();

        // When
        MovieDetails movieDetails = gson.fromJson(jsonString, MovieDetails.class);
                assertFalse(movieDetails.isAdult());
                assertEquals("/pWsD91G2R1Da3AKM3ymr3UoIfRb.jpg", movieDetails.getBackdropPath());
                assertNotNull(movieDetails.getCollectionDetails());
                assertEquals(0, movieDetails.getBudget());
                assertEquals(933131, movieDetails.getId());
                assertEquals("tt29722855", movieDetails.getImdbId());
                assertEquals("ko", movieDetails.getOriginalLanguage());
                assertEquals("황야", movieDetails.getOriginalTitle());
                assertEquals("After a deadly earthquake turns Seoul into a lawless badland, a fearless huntsman springs into action to rescue a teenager abducted by a mad doctor.", movieDetails.getOverview());
                assertEquals(1382.144, movieDetails.getPopularity());
                assertEquals("/zVMyvNowgbsBAL6O6esWfRpAcOb.jpg", movieDetails.getPosterPath());
                assertNotNull(movieDetails.getProductionCompanies());
                assertEquals(107, movieDetails.getRuntime());
                assertEquals("Released", movieDetails.getStatus());
                assertEquals("One last hunt to save us all.", movieDetails.getTagline());
                assertEquals("Badland Hunters", movieDetails.getTitle());
                assertFalse(movieDetails.isVideo());
                assertEquals(6.742, movieDetails.getVoteAverage());
                assertEquals(443, movieDetails.getVoteCount());

                // Additional assertions for nested objects
                MovieDetails.Collection collection = movieDetails.getCollectionDetails();
                assertEquals(1196130, collection.getId());
                assertEquals("Concrete Utopia Collection", collection.getName());
                assertEquals("/l4emA6jN9YQxhdpoZ4IThpMofc6.jpg", collection.getPosterPath());
                assertEquals("/9iJi448p9cvnpnLN7C0jBFjSseX.jpg", collection.getBackdropPath());





    }
}


