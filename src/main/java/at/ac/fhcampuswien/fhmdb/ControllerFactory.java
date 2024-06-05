package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.utils.Controller;
import javafx.util.Callback;

import java.lang.reflect.InvocationTargetException;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private static HomeController homeControllerInstance;
    private static WatchlistController watchlistControllerInstance;

    @Override
    public Object call(Class<?> aClass) {
        try {
            if (aClass == HomeController.class) {
                if (homeControllerInstance == null) {
                    homeControllerInstance = (HomeController) aClass.getDeclaredConstructor().newInstance();
                }
                return homeControllerInstance;
            } else if (aClass == WatchlistController.class) {
                if (watchlistControllerInstance == null) {
                    watchlistControllerInstance = (WatchlistController) aClass.getDeclaredConstructor().newInstance();
                }
                return watchlistControllerInstance;
            }
        } catch (InstantiationException e) {
            System.err.println("Failed to instantiate the controller: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Illegal access while trying to instantiate the controller: " + e.getMessage());
        } catch (InvocationTargetException e) {
            System.err.println("An error occurred while trying to instantiate the controller: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.err.println("The controller class does not have a no-arg constructor: " + e.getMessage());
        }
        return null;
    }
}