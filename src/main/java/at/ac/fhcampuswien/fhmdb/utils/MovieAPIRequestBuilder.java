package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class MovieAPIRequestBuilder {
    private String base;
    private String query;
    private String genre;
    private String releaseYear;
    private String ratingFrom;

    public MovieAPIRequestBuilder(String base) {
        this.base = base;
    }

    public MovieAPIRequestBuilder query(String query) {
        this.query = query;
        return this;
    }

    public MovieAPIRequestBuilder genre(String genre) {
        this.genre = genre;
        return this;
    }

    public MovieAPIRequestBuilder releaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public MovieAPIRequestBuilder ratingFrom(String ratingFrom) {
        this.ratingFrom = ratingFrom;
        return this;
    }

    public String build() throws MovieAPIException {
        HttpUrl parsedUrl = HttpUrl.parse(base);
        if (parsedUrl == null) {
            throw new MovieAPIException("Base URL is not valid");
        }
        HttpUrl.Builder urlBuilder = parsedUrl.newBuilder();
        if (query != null) urlBuilder.addQueryParameter("query", query);
        if (genre != null) urlBuilder.addQueryParameter("genre", genre);
        if (releaseYear != null) urlBuilder.addQueryParameter("releaseYear", releaseYear);
        if (ratingFrom != null) urlBuilder.addQueryParameter("ratingFrom", ratingFrom);
        return urlBuilder.build().toString();
    }

    public Request buildRequest() throws MovieAPIException {
        String url = build();
        if (url == null || url.isEmpty()) {
            throw new MovieAPIException("Movie URL is not valid");
        }
        return new Request.Builder().url(url).header("User-Agent", "http.agent").build();
    }
}