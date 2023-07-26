package lab5.java_5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class Player {

    private String playerId;
    private String firstName;
    private String lastName;
    private String postalCode;
    private String address;
    private String province;
    private String phoneNumber;
    private SortedList<Object> playedGames; // List of played games for the player

    public Player(String playerId, String firstName, String lastName, String postalCode,
                  String address, String province, String phoneNumber) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
        this.address = address;
        this.province = province;
        this.phoneNumber = phoneNumber;
        this.playedGames = FXCollections.observableArrayList().sorted(); // Initialize the list of played games
    }

    // Getters and setters for player properties
    // ...

    public ObservableList<Object> getPlayedGames() {
        return playedGames;
    }

    public String getPlayerId() {
        return playerId;
    }
}
