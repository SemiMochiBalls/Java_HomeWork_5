package lab5.java_5;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InsertScreen extends Scene {

    public InsertScreen(Stage primaryStage) {
        super(createInsertGrid(primaryStage), 400, 300);
    }

    private static GridPane createInsertGrid(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Labels
        Label playerIdLabel = new Label("Player ID:");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label postalCodeLabel = new Label("Postal Code:");
        Label addressLabel = new Label("Address:");
        Label provinceLabel = new Label("Province:");
        Label phoneLabel = new Label("Phone Number:");

        // TextFields
        TextField playerIdTextField = new TextField();
        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField postalCodeTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField provinceTextField = new TextField();
        TextField phoneTextField = new TextField();

        // Add labels and text fields to the grid
        gridPane.add(playerIdLabel, 0, 0);
        gridPane.add(playerIdTextField, 1, 0);
        gridPane.add(firstNameLabel, 0, 1);
        gridPane.add(firstNameTextField, 1, 1);
        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(lastNameTextField, 1, 2);
        gridPane.add(postalCodeLabel, 0, 3);
        gridPane.add(postalCodeTextField, 1, 3);
        gridPane.add(addressLabel, 0, 4);
        gridPane.add(addressTextField, 1, 4);
        gridPane.add(provinceLabel, 0, 5);
        gridPane.add(provinceTextField, 1, 5);
        gridPane.add(phoneLabel, 0, 6);
        gridPane.add(phoneTextField, 1, 6);

        // Create a button to submit the information
        Button submitButton = new Button("Submit");
        gridPane.add(submitButton, 0, 7, 2, 1);

        // Handle the button click event
        submitButton.setOnAction(e -> handleSubmission(primaryStage, playerIdTextField.getText(),
                firstNameTextField.getText(), lastNameTextField.getText(), postalCodeTextField.getText(),
                addressTextField.getText(), provinceTextField.getText(), phoneTextField.getText()));

        return gridPane;
    }

    private static void handleSubmission(Stage primaryStage, String playerId, String firstName, String lastName,
                                         String postalCode, String address, String province, String phone) {
        // You can process the basic information here (e.g., save it to a database)

        // Switch to the GameInfoScreen to ask for game information
        Scene gameInfoScene = new GameInfoScreen(primaryStage);
        primaryStage.setScene(gameInfoScene);
    }
}