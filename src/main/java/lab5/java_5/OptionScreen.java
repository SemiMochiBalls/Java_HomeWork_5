package lab5.java_5;

import javafx.geometry.Pos;
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
        Button updateButton = new Button("Update/display");

        // Set event handlers for the buttons
        insertButton.setOnAction(e -> handleInsertButton(primaryStage));
        updateButton.setOnAction(e -> handleUpdateButton(primaryStage));

        // Create an HBox to hold the buttons horizontally
        HBox hbox = new HBox(10); // 10 is the spacings between buttons
        hbox.setPadding(new Insets(20));
        hbox.setAlignment(Pos.CENTER); // Set the alignment to center
        hbox.getChildren().addAll(insertButton, updateButton);

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

}