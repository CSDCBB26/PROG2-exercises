package at.ac.fhcampuswien.fhmdb.utils;

public interface Observable<T> {
        void addObserver(T observer);
        void removeObserver(T observer);
        void notifyObservers(T message);
    }