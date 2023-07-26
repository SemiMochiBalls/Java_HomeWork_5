package lab5.java_5;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class OptionScreen extends Scene {

    public OptionScreen(Stage primaryStage) {
        super(createOptionGrid(primaryStage), 400, 100);
    }

    private static HBox createOptionGrid(Stage primaryStage) {
        // Create buttons for different options
        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");
        Button displayButton = new Button("Display");

        // Set event handlers for the buttons
        insertButton.setOnAction(e -> handleInsertButton(primaryStage));
        updateButton.setOnAction(e -> handleUpdateButton(primaryStage));
        displayButton.setOnAction(e -> handleDisplayButton(primaryStage));

        // Create an HBox to hold the buttons horizontally
        HBox hbox = new HBox(10); // 10 is the spacing between buttons
        hbox.setPadding(new Insets(20));
        hbox.getChildren().addAll(insertButton, updateButton, displayButton);

        return hbox;
    }

    private static void handleInsertButton(Stage primaryStage) {
        // Switch to the InsertScreen when the "Insert" button is clicked
        Scene insertScene = new InsertScreen(primaryStage);
        primaryStage.setScene(insertScene);
    }

    private static void handleUpdateButton(Stage primaryStage) {
        // Switch to the UpdateScreen when the "Update" button is clicked
        Scene updateScene = new UpdateScreen(primaryStage);
        primaryStage.setScene(updateScene);
    }

    private static void handleDisplayButton(Stage primaryStage) {
        // Switch to the DisplayScreen when the "Display" button is clicked
        Scene displayScene = new DisplayScreen(primaryStage);
        primaryStage.setScene(displayScene);
    }

}