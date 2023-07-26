package lab5.java_5;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateScreen extends Scene {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/playerdatabasehomework";
    private static final String DB_USER = "Sachi";
    private static final String DB_PASSWORD = "12345";

    public UpdateScreen(Stage primaryStage) {
        super(createUpdateGrid(primaryStage), 400, 200);
    }

    private static GridPane createUpdateGrid(Stage primaryStage) {
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

        // TextFields for user input
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

        // Create a button to fetch and display the player information
        Button fetchPlayerButton = new Button("Fetch Player Info");
        gridPane.add(fetchPlayerButton, 0, 7, 2, 1);

        // Create a button to submit the updated information
        Button submitUpdateButton = new Button("Submit Update");
        gridPane.add(submitUpdateButton, 0, 8, 2, 1);

        // Handle the button click events
        fetchPlayerButton.setOnAction(e -> handleFetchPlayer(primaryStage, playerIdTextField.getText(),
                firstNameTextField, lastNameTextField, postalCodeTextField, addressTextField,
                provinceTextField, phoneTextField));
        submitUpdateButton.setOnAction(e -> handleUpdateSubmission(primaryStage,
                playerIdTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(),
                postalCodeTextField.getText(), addressTextField.getText(),
                provinceTextField.getText(), phoneTextField.getText()));

        return gridPane;
    }

    private static void handleFetchPlayer(Stage primaryStage, String playerId,
                                          TextField firstNameTextField, TextField lastNameTextField,
                                          TextField postalCodeTextField, TextField addressTextField,
                                          TextField provinceTextField, TextField phoneTextField) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM players WHERE player_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Populate the text fields with fetched player information from the database
                firstNameTextField.setText(resultSet.getString("first_name"));
                lastNameTextField.setText(resultSet.getString("last_name"));
                postalCodeTextField.setText(resultSet.getString("postal_code"));
                addressTextField.setText(resultSet.getString("address"));
                provinceTextField.setText(resultSet.getString("province"));
                phoneTextField.setText(resultSet.getString("phone"));
            } else {
                // Player with the given player ID not found
                showErrorMessage("Player not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorMessage("Error fetching player information");
        }
    }


    private static void handleUpdateSubmission(Stage primaryStage, String playerId, String firstName,
                                               String lastName, String postalCode, String address,
                                               String province, String phoneNumber) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String updateQuery = "UPDATE players SET first_name = ?, last_name = ?, postal_code = ?, " +
                    "address = ?, province = ?, phone = ? WHERE player_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, postalCode);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, province);
            preparedStatement.setString(6, phoneNumber);
            preparedStatement.setString(7, playerId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Player information updated successfully
                showSuccessMessage();

                // Switch back to the main screen (option screen)
                Scene optionScene = new OptionScreen(primaryStage);
                primaryStage.setScene(optionScene);
            } else {
                // No rows were affected, update failed
                showErrorMessage("Failed to update player information");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorMessage("Error updating player information");
        }
    }

    private static void showSuccessMessage() {
        // ... (same as the original code)
    }

    private static void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
