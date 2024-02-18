package org.teut2711.models;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    @Test
    public void testMovieCreation() throws IOException {
        // Read the contents of the JSON file
        String jsonString = new String(Files.readAllBytes(Paths.get("./src/test/resources/latestMovie.json")));

        // Given
        Gson gson = new Gson();

        // When
        Movie movie = gson.fromJson(jsonString, Movie.class);

        // Then
        assertEquals(933131, movie.getId());
        assertEquals("Badland Hunters", movie.getTitle());
        assertEquals("After a deadly earthquake turns Seoul into a lawless badland, a fearless huntsman springs into action to rescue a teenager abducted by a mad doctor.", movie.getOverview());
        assertEquals(2214.237, movie.getPopularity());
        assertEquals("2024-01-26", new SimpleDateFormat("yyyy-MM-dd").format(movie.getReleaseDate()));
        assertEquals(6.729, movie.getVoteAverage());
        assertFalse(movie.isAdult());
        assertEquals("/pWsD91G2R1Da3AKM3ymr3UoIfRb.jpg", movie.getBackdropPath());
        assertEquals("ko", movie.getOriginalLanguage());
        assertEquals("황야", movie.getOriginalTitle());
        assertEquals("/zVMyvNowgbsBAL6O6esWfRpAcOb.jpg", movie.getPosterPath());
        assertFalse(movie.isVideo());
        assertEquals(420, movie.getVoteCount());
        assertNotNull(movie.getGenreIds());
        assertEquals(3, movie.getGenreIds().length);
        assertEquals(878, movie.getGenreIds()[0]);
        assertEquals(28, movie.getGenreIds()[1]);
        assertEquals(18, movie.getGenreIds()[2]);
    }
}
