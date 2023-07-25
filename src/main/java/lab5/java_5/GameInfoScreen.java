package lab5.java_5;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameInfoScreen extends Scene {

    public GameInfoScreen(Stage primaryStage) {
        super(createGameInfoGrid(primaryStage), 400, 300);
    }

    private static GridPane createGameInfoGrid(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Labels
        Label gameNameLabel = new Label("Game Name:");
        Label highScoreLabel = new Label("High Score:");
        Label playingDateLabel = new Label("Playing Date:");

        // TextFields
        TextField gameNameTextField = new TextField();
        TextField highScoreTextField = new TextField();
        TextField playingDateTextField = new TextField();

        // Add labels and text fields to the grid
        gridPane.add(gameNameLabel, 0, 0);
        gridPane.add(gameNameTextField, 1, 0);
        gridPane.add(highScoreLabel, 0, 1);
        gridPane.add(highScoreTextField, 1, 1);
        gridPane.add(playingDateLabel, 0, 2);
        gridPane.add(playingDateTextField, 1, 2);

        // Create a button to submit the game information (you can handle this button's functionality)
        Button submitGameButton = new Button("Submit Game Info");
        gridPane.add(submitGameButton, 0, 3, 2, 1);



        // Handle the button click event
        submitGameButton.setOnAction(e -> handleGameInfoSubmission(primaryStage));

        return gridPane;
    }
    private static void handleGameInfoSubmission(Stage primaryStage) {
        // You can add functionality here to save the game information to a database or perform other actions.

        // Show a message to the user indicating successful submission
        showSuccessMessage();

        // Switch back to the main screen (option screen)
        Scene optionScene = new OptionScreen(primaryStage);
        primaryStage.setScene(optionScene);
    }

    private static void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Game information submitted successfully!");
        alert.showAndWait();
    }
}

