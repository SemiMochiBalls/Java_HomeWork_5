package lab5.java_5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayScreen extends Scene {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/playerdatabasehomework";
    private static final String DB_USER = "Sachi";
    private static final String DB_PASSWORD = "12345";

    public DisplayScreen(Stage primaryStage) {
        super(new VBox(), 600, 400);
        VBox vbox = (VBox) getRoot();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        ObservableList<String> playerIds = getPlayerIdsFromDatabase();
        ComboBox<String> playerIdComboBox = new ComboBox<>();
        playerIdComboBox.setPromptText("Select Player ID");
        playerIdComboBox.setItems(playerIds);

        TableView<PlayedGame> tableView = new TableView<>();
        TableColumn<PlayedGame, String> gameNameColumn = new TableColumn<>("Game Name");
        TableColumn<PlayedGame, Integer> highScoreColumn = new TableColumn<>("High Score");
        TableColumn<PlayedGame, String> playingDateColumn = new TableColumn<>("Playing Date");

        gameNameColumn.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        highScoreColumn.setCellValueFactory(new PropertyValueFactory<>("highScore"));
        playingDateColumn.setCellValueFactory(new PropertyValueFactory<>("playingDate"));

        tableView.getColumns().addAll(gameNameColumn, highScoreColumn, playingDateColumn);

        Button showReportButton = new Button("Show Report");
        showReportButton.setOnAction(e -> handleShowReport(primaryStage, tableView, playerIdComboBox.getValue()));

        vbox.getChildren().addAll(playerIdComboBox, tableView, showReportButton);
    }

    private ObservableList<String> getPlayerIdsFromDatabase() {
        ObservableList<String> playerIds = FXCollections.observableArrayList();
        String query = "SELECT player_id FROM players";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String playerId = resultSet.getString("player_id");
                playerIds.add(playerId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerIds;
    }

    private void handleShowReport(Stage primaryStage, TableView<PlayedGame> tableView, String selectedPlayerId) {
        ObservableList<PlayedGame> playedGames = FXCollections.observableArrayList();
        String query = "SELECT game_name, high_score, playing_date FROM played_games WHERE player_id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, selectedPlayerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String gameName = resultSet.getString("game_name");
                int highScore = resultSet.getInt("high_score");
                String playingDate = resultSet.getString("playing_date");
                playedGames.add(new PlayedGame(gameName, highScore, playingDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableView.setItems(playedGames);
    }
}
