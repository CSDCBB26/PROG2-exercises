package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.Genre;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
//ToDo implement the methods by Andreas Drozd
public class MovieAPI {
    public static final String API_URL = "https://prog2.fh-campuswien.ac.at";

    public static String getAllMovies(String api_url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(api_url + "/movies")
                .header("User-Agent", "http.agent")
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        return null;
    }

    public static String getMoviesByQueries(String api_url, String query, Genre genre, int releaseYear, double ratingFrom) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(api_url + "/movies").newBuilder();
        if (query != null) {
            urlBuilder.addQueryParameter("query", query);
        }
        if (genre != null) {
            urlBuilder.addQueryParameter("genre", genre.name());
        }
        if (releaseYear != 0) {
            urlBuilder.addQueryParameter("releaseYear", String.valueOf(releaseYear));
        }
        if (ratingFrom != 0) {
            urlBuilder.addQueryParameter("ratingFrom", String.valueOf(ratingFrom));
        }

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "http.agent")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
        return null;
    }
}