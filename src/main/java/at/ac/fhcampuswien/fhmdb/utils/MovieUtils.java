package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static at.ac.fhcampuswien.fhmdb.api.MovieAPI.API_URL;

public class MovieUtils {


    public static List<Movie> filter(Genre selectedGenre, String searchQuery, int selectedReleaseYear, double selectedRatingFrom)  {
        List<Movie> movieList = new ArrayList<>();

        try {
            String json = MovieAPI.getMoviesByQueries(API_URL, searchQuery, selectedGenre, selectedReleaseYear, selectedRatingFrom);
            movieList = MovieUtils.parseMovies(json);
        } catch (MovieAPIException e) {
            System.out.println("Error while filtering the movies " + e.getMessage());
        }

        return movieList;
    }


    public static List<Movie> sort(String mode, List<Movie> movieList) {
        if (movieList.isEmpty()) {
            return movieList;
        }

        if ("descending".equals(mode)) {
            movieList.sort(Comparator.comparing(Movie::getTitle).reversed());
        } else {
            movieList.sort(Comparator.comparing(Movie::getTitle));
        }

        return movieList;
    }

    public static String getMostPopularActor(List<Movie> movies) {
        if (!validateMoviesList(movies)) {
            return "";
        }

        Map<String, Long> actorNameToOccurrencesMap = movies.stream()
                .filter(movie -> movie.getMainCast() != null && !movie.getMainCast().isEmpty())
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return actorNameToOccurrencesMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public static int getLongestMovieTitle(List<Movie> movies) {
        if (!validateMoviesList(movies)) {
            return 0;
        }

        return movies.stream()
                .filter(movie -> movie.getTitle() != null && !movie.getTitle().isBlank())
                .mapToInt(title -> title.getTitle().length())
                .max()
                .orElse(0);
    }

    public static long countMoviesFrom(List<Movie> movies, String director) {
        if (!validateMoviesList(movies) || director == null || director.isBlank()) {
            return 0;
        }

        return movies.stream()
                .filter(movie -> movie.getDirectors() != null && movie.getDirectors().stream()
                        .anyMatch(d -> d.equalsIgnoreCase(director)))
                .count();
    }

    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        if (!validateMoviesList(movies)) {
            return new ArrayList<>();
        }

        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .toList();
    }

    protected static boolean validateMoviesList(List<Movie> movies) {
        return movies != null && !movies.isEmpty();
    }

    /**
     * Converts a JSON Response (String) to a list of movie objects
     *
     * @param jsonString
     * @return
     */
    public static List<Movie> parseMovies(String jsonString) {
        Gson gson = new Gson();
        Type movieListType = new TypeToken<List<Movie>>() {
        }.getType();
        return gson.fromJson(jsonString, movieListType);
    }
}