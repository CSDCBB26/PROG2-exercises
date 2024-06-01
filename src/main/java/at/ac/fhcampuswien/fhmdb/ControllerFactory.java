package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.utils.Controller;
import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private static HomeController homeControllerInstance;
    private static WatchlistController watchlistControllerInstance;
    private static Controller controllerInstance;

    @Override
    public Object call(Class<?> aClass) {
        if (aClass == HomeController.class) {
            if (homeControllerInstance == null) {
                try {
                    homeControllerInstance = (HomeController) aClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return homeControllerInstance;
        } else if (aClass == WatchlistController.class) {
            if (watchlistControllerInstance == null) {
                try {
                    watchlistControllerInstance = (WatchlistController) aClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return watchlistControllerInstance;
        } else if (aClass == Controller.class) {
            if (controllerInstance == null) {
                try {
                    controllerInstance = (Controller) aClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return controllerInstance;
        }
        return null;
    }
}