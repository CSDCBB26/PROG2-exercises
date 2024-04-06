package at.ac.fhcampuswien.fhmdb.utils;

import at.ac.fhcampuswien.fhmdb.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieUtils {


    /**
     * refactor to movie or new class movieUtils
     */
    public static List<Movie> search(String input, List<Movie> movieList) {
        return movieList.stream()
                .filter(movie -> isMovieMatchesSearchQuery(movie, input))
                .collect(Collectors.toList());
    }

    /**
     * refactor to movie or new class movieUtils
     */
    public static List<Movie> filter(Genre selectedGenre, List<Movie> movieList, String searchQuery) {
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
    /**
     * refactor to movie or new class movieUtils
     */
    protected static boolean isMovieMatchesSearchQuery(Movie movie, String searchQuery) {
        return movie.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) ||
                movie.getDescription().toLowerCase().contains(searchQuery.toLowerCase());
    }

    /**
     * refactor to movie or new class movieUtils
     */
    public static List<Movie> sort(String mode, List<Movie> movieList){
        if(movieList.isEmpty()){
            return movieList;
        }

        if ("descending".equals(mode)) {
            movieList.sort(Comparator.comparing(Movie::getTitle).reversed());
        } else {
            movieList.sort(Comparator.comparing(Movie::getTitle));
        }

        return movieList;
    }

    //ToDo @
    public static String getMostPopularActor(List<Movie> movies){
        //TODO implement
        return "";
    }

    //ToDo @
    public static int getLongestMovieTitle(List<Movie> movies){
        //TODO implement
        return 0;
    }

    //ToDo @
    public static long countMoviesFrom(List<Movie> movies, String director)
    {
        //TODO implement
        return 0;
    }

    //ToDo @
    public static List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int
            endYear){
        //TODO implement

        return null;
    }

}