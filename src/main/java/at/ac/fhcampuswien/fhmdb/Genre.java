package at.ac.fhcampuswien.fhmdb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Genre {
    ALL,
    ACTION,
    ADVENTURE,
    ANIMATION,
    BIOGRAPHY,
    COMEDY,
    CRIME,
    DRAMA,
    DOCUMENTARY,
    FAMILY,
    FANTASY,
    HISTORY,
    HORROR,
    MUSICAL,
    MYSTERY,
    ROMANCE,
    SCIENCE_FICTION,
    SPORT,
    THRILLER,
    WAR,
    WESTERN;

    public static List<Genre> stringToGenres(String genres) {
        try {
            return Arrays.stream(genres.split(","))
                    .map(String::trim)
                    .map(Genre::valueOf)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}