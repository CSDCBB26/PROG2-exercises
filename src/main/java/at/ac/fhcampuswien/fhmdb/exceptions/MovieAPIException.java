package at.ac.fhcampuswien.fhmdb.exceptions;

public class MovieAPIException  extends Exception {
    public MovieAPIException(String message) {
        super(message);
    }

    public MovieAPIException() {}

    public MovieAPIException(String urlIsNotValid, Exception e) {
    }
}