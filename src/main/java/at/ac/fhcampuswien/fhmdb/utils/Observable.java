package at.ac.fhcampuswien.fhmdb.utils;

public interface Observable<T extends Observer> {
        void addObserver(T observer);
        void removeObserver(T observer);
        void notifyObservers(String message);
    }