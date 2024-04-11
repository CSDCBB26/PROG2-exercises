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

    /**
     * ToDo: Refactor for API by Jakob
     * unnecessary -> only used in old filter method
     */
    public static List<Movie> search(String input, List<Movie> movieList) {
        return movieList.stream()
                .filter(movie -> isMovieMatchesSearchQuery(movie, input))
                .collect(Collectors.toList());
    }

    /**
     * ToDo: Refactor for API by Jakob
     */
    public static List<Movie> filter_old(Genre selectedGenre, List<Movie> movieList, String searchQuery) {
        if (movieList == null || movieList.isEmpty()) {
            return new ArrayList<>();
        }

        List<Movie> filteredList = selectedGenre == null || selectedGenre == Genre.ALL
                ? movieList
                : movieList.stream().filter(movie -> movie.getGenres().contains(selectedGenre)).toList();

        if (!searchQuery.isEmpty()) {
            filteredList = search(searchQuery, filteredList);
        }

        return filteredList;
    }

    public static List<Movie> filter(Genre selectedGenre, String searchQuery, int selectedReleaseYear, int selectedRatingFrom) {
        String json = MovieAPI.getMoviesByQueries(API_URL, searchQuery, selectedGenre, selectedReleaseYear, selectedRatingFrom);
        List<Movie> temp = MovieUtils.parseMovies(json);


        return temp;
    }

    /**
     * ToDo: Refactor for API by Jakob
     * only used in search which is only used in old tests
     */
    protected static boolean isMovieMatchesSearchQuery(Movie movie, String searchQuery) {
        return movie.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                movie.getDescription().toLowerCase().contains(searchQuery.toLowerCase());
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
        if (!validateMoviesList(movies)) {
            return "";
        }

        List<Movie> allMoviesHavingCast = movies.stream()
                .filter(movie -> movie.getMainCast() != null && !movie.getMainCast().isEmpty())
                .toList();

        if (!validateMoviesList(allMoviesHavingCast)) {
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
        if (!validateMoviesList(movies)) {
            return 0;
        }

        List<Movie> allMoviesHavingTitle = movies.stream()
                .filter(movie -> movie.getTitle() != null && !movie.getTitle().isBlank())
                .toList();

        if (!validateMoviesList(allMoviesHavingTitle)) {
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

    public static long countMoviesFrom(List<Movie> movies, String director) {
        if (!validateMoviesList(movies) || director == null || director.isBlank()) {
            return 0;
        }

        List<Movie> allMoviesHavingDirectors = movies.stream()
                .filter(movie -> movie.getDirectors() != null && !movie.getDirectors().isEmpty())
                .toList();

        if (!validateMoviesList(allMoviesHavingDirectors)) {
            return 0;
        }

        return allMoviesHavingDirectors.stream()
                // Stream the list of directors for each movie and do case-sensitive comparison
                .filter(movie -> movie.getDirectors().stream()
                        .anyMatch(d -> d.equalsIgnoreCase(director)))
                .count();
    }

    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        if (!validateMoviesList(movies)) {
            return new ArrayList<>();
        }

        List<Movie> allMoviesHavingReleaseYear = movies.stream()
                .filter(movie -> movie.getReleaseYear() > 0)
                .toList();

        if (!validateMoviesList(allMoviesHavingReleaseYear)) {
            return new ArrayList<>();
        }

        return allMoviesHavingReleaseYear.stream()
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