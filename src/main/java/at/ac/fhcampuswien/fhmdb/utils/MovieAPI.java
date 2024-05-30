package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;
import okhttp3.*;

public class MovieAPI {
    public static final String API_URL = "https://prog2.fh-campuswien.ac.at";


    public static String getAllMovies(String api_url) throws MovieAPIException {
        OkHttpClient client = new OkHttpClient();
        Request request;

        try {
            request = new Request.Builder().url(api_url + "/movies").header("User-Agent", "http.agent").build();
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
     *  retrofit library - https://square.github.io/retrofit/ and https://swagger.io/tools/swagger-codegen/
     */
    public static String getMoviesByQueries(String api_url, String query, Genre genre, int releaseYear, double ratingFrom) throws MovieAPIException {

        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = HttpUrl.parse(api_url + "/movies");

        if (httpUrl == null) {
            throw new MovieAPIException("URL is not valid");
        }

        HttpUrl.Builder urlBuilder = httpUrl.newBuilder();
        if (query != null) {
            urlBuilder.addQueryParameter("query", query);
        }
        if (genre != null) {
            urlBuilder.addQueryParameter("genre", genre.name());
        }
        if (releaseYear > 0) {
            urlBuilder.addQueryParameter("releaseYear", String.valueOf(releaseYear));
        }
        if (ratingFrom > 0) {
            urlBuilder.addQueryParameter("ratingFrom", String.valueOf(ratingFrom));
        }

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).header("User-Agent", "http.agent").build();

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