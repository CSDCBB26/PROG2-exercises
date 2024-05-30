package at.ac.fhcampuswien.fhmdb;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Scene {
    public static void switchScene(ActionEvent actionEvent, String fxmlPath) {
        try {
            // Get the current stage
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Load the FXML file for the view
            Parent root = FXMLLoader.load(Objects.requireNonNull(Scene.class.getResource(fxmlPath)));

            // Create a new scene with the loaded root
            javafx.scene.Scene scene = new javafx.scene.Scene(root);

            // Set the new scene on the current stage
            stage.setScene(scene);
        } catch (IOException | NullPointerException e) {
            // Create an alert window
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("An Error Occurred");
            alert.setContentText("Could not switch to the movie watch list: " + e.getMessage());
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    public void home(ActionEvent actionEvent) {
        Scene.switchScene(actionEvent, "home-view.fxml");
    }

    public void watchlist(ActionEvent actionEvent) {
        Scene.switchScene(actionEvent, "watchlist-view.fxml");
    }

}