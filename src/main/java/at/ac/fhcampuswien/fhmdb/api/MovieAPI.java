package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;
import okhttp3.*;

public class MovieAPI {
    public static final String API_URL = "https://prog2.fh-campuswien.ac.at";
    private static final OkHttpClient client = new OkHttpClient();


    public static String getAllMovies(String api_url) throws MovieAPIException {
        Request request;

        try {
            request = new MovieAPIRequestBuilder(api_url + "/movies").buildRequest();
        } catch (IllegalArgumentException e) {
            throw new MovieAPIException("URL is not valid");
        }

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new MovieAPIException("Failed to fetch movies: " + response.code());
            }
        } catch (Exception e) {
            throw new MovieAPIException(e.getMessage());
        }
    }

    /**
     * retrofit library - https://square.github.io/retrofit/ and https://swagger.io/tools/swagger-codegen/
     */
    public static String getMoviesByQueries(String api_url, String query, Genre genre, int releaseYear, double ratingFrom) throws MovieAPIException {

        Request request = new MovieAPIRequestBuilder(api_url + "/movies")
                .query(query)
                .genre(genre != null ? genre.name() : null)
                .releaseYear(releaseYear > 0 ? String.valueOf(releaseYear) : null)
                .ratingFrom(ratingFrom > 0 ? String.valueOf(ratingFrom) : null)
                .buildRequest();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new MovieAPIException("Failed to fetch movies: " + response.code());
            }
        } catch (Exception e) {
            throw new MovieAPIException("Error: " + e.getMessage(), e);
        }
    }
}