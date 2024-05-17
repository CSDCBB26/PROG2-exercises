package at.ac.fhcampuswien.fhmdb;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene {
    public static void switchScene(ActionEvent actionEvent, String fxmlPath) {
        try {
            // Get the current stage
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Load the FXML file for the view
            Parent root = FXMLLoader.load(Scene.class.getResource(fxmlPath));

            // Create a new scene with the loaded root
            javafx.scene.Scene scene = new javafx.scene.Scene(root);

            // Set the new scene on the current stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void home(ActionEvent actionEvent) {
        Scene.switchScene(actionEvent, "home-view.fxml");
    }

    public void watchlist(ActionEvent actionEvent) {
        Scene.switchScene(actionEvent, "watchlist-view.fxml");
    }

}