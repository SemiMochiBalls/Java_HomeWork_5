package lab5.java_5;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class InsertScreen extends Scene {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/playerdatabasehomework";
    private static final String DB_USER = "Sachi";
    private static final String DB_PASSWORD = "12345";

    public InsertScreen(Stage primaryStage) {
        super(createInsertGrid(primaryStage), 400, 370);
    }

    private static GridPane createInsertGrid(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Labels
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label postalCodeLabel = new Label("Postal Code:");
        Label addressLabel = new Label("Address:");
        Label provinceLabel = new Label("Province:");
        Label phoneNumberLabel = new Label("Phone Number:");
        // Labels for game information
        Label gameTitleLabel = new Label("Game Title:");
        Label playingDateLabel = new Label("Playing Date:");
        Label scoreLabel = new Label("Score:");

        // TextFields for game information
        TextField gameTitleTextField = new TextField();
        TextField playingDateTextField = new TextField();
        TextField scoreTextField = new TextField();
        // TextFields
        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField postalCodeTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField provinceTextField = new TextField();
        TextField phoneNumberTextField = new TextField();

        // Add labels and text fields to the grid
        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(firstNameTextField, 1, 0);
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(lastNameTextField, 1, 1);
        gridPane.add(postalCodeLabel, 0, 2);
        gridPane.add(postalCodeTextField, 1, 2);
        gridPane.add(addressLabel, 0, 3);
        gridPane.add(addressTextField, 1, 3);
        gridPane.add(provinceLabel, 0, 4);
        gridPane.add(provinceTextField, 1, 4);
        gridPane.add(phoneNumberLabel, 0, 5);
        gridPane.add(phoneNumberTextField, 1, 5);
        gridPane.add(gameTitleLabel, 0, 7);
        gridPane.add(gameTitleTextField, 1, 7);
        gridPane.add(playingDateLabel, 0, 8);
        gridPane.add(playingDateTextField, 1, 8);
        gridPane.add(scoreLabel, 0, 9);
        gridPane.add(scoreTextField, 1, 9);


        // Create a button to submit the information
        Button submitButton = new Button("Submit");
        gridPane.add(submitButton, 0, 10, 2, 1);

        // Handle the button click event
        submitButton.setOnAction(e -> handleSubmission(primaryStage, firstNameTextField.getText(),
                lastNameTextField.getText(), postalCodeTextField.getText(),
                addressTextField.getText(), provinceTextField.getText(), phoneNumberTextField.getText(),
                gameTitleTextField.getText(), playingDateTextField.getText(), scoreTextField.getText()));


        return gridPane;
    }

    private static void handleSubmission(Stage primaryStage, String firstName, String lastName,
                                         String postalCode, String address, String province, String phoneNumber,
                                         String gameTitle, String playingDate, String score) {
        // Save the player and game information to the database
        savePlayerAndGameToDatabase(firstName, lastName, postalCode, address, province, phoneNumber,
                gameTitle, playingDate, score);

        // Switch to the OptionScreen
        Scene optionScene = new OptionScreen(primaryStage);
        primaryStage.setScene(optionScene);
    }

    private static void savePlayerAndGameToDatabase(String firstName, String lastName,
                                                    String postalCode, String address, String province, String phoneNumber,
                                                    String gameTitle, String playingDate, String score) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Insert player information into the player table
            String insertPlayerQuery = "INSERT INTO player (first_name, last_name, postal_code, address, province, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement playerStatement = connection.prepareStatement(insertPlayerQuery, Statement.RETURN_GENERATED_KEYS);

            playerStatement.setString(1, firstName);
            playerStatement.setString(2, lastName);
            playerStatement.setString(3, postalCode);
            playerStatement.setString(4, address);
            playerStatement.setString(5, province);
            playerStatement.setString(6, phoneNumber);

            playerStatement.executeUpdate();

            // Retrieve the auto-generated player ID
            int playerId;
            try (ResultSet generatedKeys = playerStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    playerId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to get player ID.");
                }
            }

            // Insert game information into the game table
            String insertGameQuery = "INSERT INTO game (game_title) VALUES (?)";
            PreparedStatement gameStatement = connection.prepareStatement(insertGameQuery, Statement.RETURN_GENERATED_KEYS);

            gameStatement.setString(1, gameTitle);
            gameStatement.executeUpdate();

            // Retrieve the auto-generated game ID
            int gameId;
            try (ResultSet generatedKeys = gameStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    gameId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to get game ID.");
                }
            }

            // Insert player and game information into the playerandgame table
            String insertPlayerAndGameQuery = "INSERT INTO playerandgame (player_player_id, game_game_id, playing_date, score) VALUES (?, ?, ?, ?)";
            PreparedStatement playerAndGameStatement = connection.prepareStatement(insertPlayerAndGameQuery);

            playerAndGameStatement.setInt(1, playerId);
            playerAndGameStatement.setInt(2, gameId);
            playerAndGameStatement.setString(3, playingDate);
            playerAndGameStatement.setString(4, score);

            playerAndGameStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
