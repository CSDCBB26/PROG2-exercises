package at.ac.fhcampuswien.fhmdb.utils;

public interface Observable {
        void addObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers(String message);
    }