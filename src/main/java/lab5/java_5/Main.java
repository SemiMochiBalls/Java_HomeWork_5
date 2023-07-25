package lab5.java_5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox; // Change VBox to HBox
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Option Screen");

        // Create the option screen using the OptionScreen class
        OptionScreen optionScreen = new OptionScreen(primaryStage);
        primaryStage.setScene(optionScreen);
        primaryStage.show();
    }


    private void handleInsertButton(Stage primaryStage) {
        // Switch to the InsertScreen when the "Insert" button is clicked
        Scene insertScene = new InsertScreen(primaryStage);
        primaryStage.setScene(insertScene);
    }

    /*private void handleUpdateButton(Stage primaryStage) {
        // Switch to the UpdateScreen when the "Update" button is clicked
        Scene updateScene = new UpdateScreen();
        primaryStage.setScene(updateScene);
    }

    private void handleDisplayButton(Stage primaryStage) {
        // Switch to the DisplayScreen when the "Display" button is clicked
        Scene displayScene = new DisplayScreen();
        primaryStage.setScene(displayScene);
    }
*/
    public static void main(String[] args) {
        launch(args);
    }
}
