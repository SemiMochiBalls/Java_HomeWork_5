package lab5.java_5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
        super(createUpdateGrid(primaryStage), 400, 450);
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
        // Labels for game information
        Label gameTitleLabel = new Label("Game Title:");
        Label playingDateLabel = new Label("Playing Date:");
        Label scoreLabel = new Label("Score:");

        // TextFields for game information
        TextField gameTitleTextField = new TextField();
        TextField playingDateTextField = new TextField();
        TextField scoreTextField = new TextField();

        // Add labels and text fields to the grid
        gridPane.add(gameTitleLabel, 0, 7);
        gridPane.add(gameTitleTextField, 1, 7);
        gridPane.add(playingDateLabel, 0, 8);
        gridPane.add(playingDateTextField, 1, 8);
        gridPane.add(scoreLabel, 0, 9);
        gridPane.add(scoreTextField, 1, 9);

        // ComboBox for Player ID selection
        ComboBox<String> playerIdComboBox = new ComboBox<>();
        playerIdComboBox.setPromptText("Select Player ID");
        playerIdComboBox.setItems(getPlayerIdsFromDatabase());

        // TextFields for user input
        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField postalCodeTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField provinceTextField = new TextField();
        TextField phoneTextField = new TextField();

        // Add labels and text fields to the grid
        gridPane.add(playerIdLabel, 0, 0);
        gridPane.add(playerIdComboBox, 1, 0);
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
        gridPane.add(fetchPlayerButton, 0, 10, 2, 1);

        // Create a button to submit the updated information
        Button submitUpdateButton = new Button("Submit Update");
        gridPane.add(submitUpdateButton, 0, 11, 2, 1);

        // Handle the button click events
        fetchPlayerButton.setOnAction(e -> handleFetchPlayer(primaryStage, playerIdComboBox.getValue(),
                firstNameTextField, lastNameTextField, postalCodeTextField, addressTextField,
                provinceTextField, phoneTextField,
                gameTitleTextField, playingDateTextField, scoreTextField));

        submitUpdateButton.setOnAction(e -> handleUpdateSubmission(primaryStage,
                playerIdComboBox.getValue(), firstNameTextField.getText(), lastNameTextField.getText(),
                postalCodeTextField.getText(), addressTextField.getText(),
                provinceTextField.getText(), phoneTextField.getText(),
                gameTitleTextField.getText(), playingDateTextField.getText(), scoreTextField.getText()));


        return gridPane;
    }

    private static void handleFetchPlayer(Stage primaryStage, String playerId,
                                          TextField firstNameTextField, TextField lastNameTextField,
                                          TextField postalCodeTextField, TextField addressTextField,
                                          TextField provinceTextField, TextField phoneTextField,
                                          TextField gameTitleTextField, TextField playingDateTextField,
                                          TextField scoreTextField) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT p.*, g.game_title, pg.playing_date, pg.score " +
                    "FROM player p " +
                    "LEFT JOIN playerandgame pg ON p.player_id = pg.player_player_id " +
                    "LEFT JOIN game g ON pg.game_game_id = g.game_id " +
                    "WHERE p.player_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Populate the text fields with fetched player and game information from the database
                firstNameTextField.setText(resultSet.getString("first_name"));
                lastNameTextField.setText(resultSet.getString("last_name"));
                postalCodeTextField.setText(resultSet.getString("postal_code"));
                addressTextField.setText(resultSet.getString("address"));
                provinceTextField.setText(resultSet.getString("province"));
                phoneTextField.setText(resultSet.getString("phone_number"));

                gameTitleTextField.setText(resultSet.getString("game_title"));
                playingDateTextField.setText(resultSet.getString("playing_date"));
                scoreTextField.setText(resultSet.getString("score"));
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
                                               String province, String phoneNumber,
                                               String gameTitle, String playingDate, String score) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            connection.setAutoCommit(false); // Start a transaction

            // Update player information
            String updatePlayerQuery = "UPDATE player SET first_name = ?, last_name = ?, postal_code = ?, " +
                    "address = ?, province = ?, phone_number = ? WHERE player_id = ?";
            PreparedStatement playerStatement = connection.prepareStatement(updatePlayerQuery);
            playerStatement.setString(1, firstName);
            playerStatement.setString(2, lastName);
            playerStatement.setString(3, postalCode);
            playerStatement.setString(4, address);
            playerStatement.setString(5, province);
            playerStatement.setString(6, phoneNumber);
            playerStatement.setString(7, playerId);

            int playerRowsAffected = playerStatement.executeUpdate();

            // Update game information
            String updateGameQuery = "UPDATE game g " +
                    "JOIN playerandgame pg ON g.game_id = pg.game_game_id " +
                    "SET g.game_title = ?, pg.playing_date = ?, pg.score = ? " +
                    "WHERE pg.player_player_id = ?";
            PreparedStatement gameStatement = connection.prepareStatement(updateGameQuery);
            gameStatement.setString(1, gameTitle);
            gameStatement.setString(2, playingDate);
            gameStatement.setString(3, score);
            gameStatement.setString(4, playerId);

            int gameRowsAffected = gameStatement.executeUpdate();

            connection.commit(); // Commit the transaction

            if (playerRowsAffected > 0 && gameRowsAffected > 0) {
                // Both player and game information updated successfully
                showSuccessMessage();

                // Switch back to the main screen (option screen)
                Scene optionScene = new OptionScreen(primaryStage);
                primaryStage.setScene(optionScene);
            } else {
                // Update failed
                showErrorMessage("Failed to update player and game information");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorMessage("Error updating player and game information");
        }
    }

    private static ObservableList<String> getPlayerIdsFromDatabase() {
        ObservableList<String> playerIds = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT player_id FROM player");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String playerId = resultSet.getString("player_id");
                playerIds.add(playerId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorMessage("Error fetching player IDs");
        }
        return playerIds;
    }

    private static void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Player information updated successfully!");
        alert.showAndWait();
    }


    private static void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
