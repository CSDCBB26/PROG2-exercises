package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static at.ac.fhcampuswien.fhmdb.utils.MovieAPI.API_URL;

public class MovieUtils {


    public static List<Movie> filter(Genre selectedGenre, String searchQuery, int selectedReleaseYear, double selectedRatingFrom) {
        String json = MovieAPI.getMoviesByQueries(API_URL, searchQuery, selectedGenre, selectedReleaseYear, selectedRatingFrom);
        return MovieUtils.parseMovies(json);
    }


    /**
     * ToDo: Refactor for API by Jakob
     * unnecessary -> sorting works the same
     */
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
        if (movies == null || movies.isEmpty()) {
            return "";
        }

        List<Movie> allMoviesHavingCast = movies.stream()
                .filter(movie -> movie.getMainCast() != null && !movie.getMainCast().isEmpty()).toList();

        if (allMoviesHavingCast.isEmpty()) {
            return "";
        }

        Map<String, Long> actorNameToOccurencesMap = allMoviesHavingCast.stream()
                // use flatMap as each movie contains a list of actors
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return actorNameToOccurencesMap
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                // return name of the actor
                .map(Map.Entry::getKey)
                // or an empty string if not found
                .orElse("");
    }

    public static int getLongestMovieTitle(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return 0;
        }

        List<Movie> allMoviesHavingTitle = movies.stream()
                .filter(movie -> movie.getTitle() != null && !movie.getTitle().isBlank()).toList();

        if (allMoviesHavingTitle.isEmpty()) {
            return 0;
        }

        Map<String, Integer> movieNameToTitleLengthMap = allMoviesHavingTitle.stream()
                .map(Movie::getTitle)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), String::length));

        return movieNameToTitleLengthMap
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                // return the length of title
                .map(Map.Entry::getValue)
                // or 0 if not found
                .orElse(0);
    }

    //ToDo @Sergiu
    public static long countMoviesFrom(List<Movie> movies, String director) {
        //TODO implement
        return 0;
    }

    //ToDo @Sergiu
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int
            endYear) {
        //TODO implement
        return null;
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