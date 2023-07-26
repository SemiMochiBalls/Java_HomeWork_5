package lab5.java_5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game Records");
        Scene optionScene = new OptionScreen(primaryStage);
        primaryStage.setScene(optionScene);
        primaryStage.show();
    }
}
