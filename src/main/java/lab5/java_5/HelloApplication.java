package lab5.java_5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the GridPane to hold the form elements
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Create ten labels and text fields
        Label label1 = new Label("Label 1:");
        Label label2 = new Label("Label 2:");
        Label label3 = new Label("Label 3:");
        Label label4 = new Label("Label 4:");
        Label label5 = new Label("Label 5:");
        Label label6 = new Label("Label 6:");
        Label label7 = new Label("Label 7:");
        Label label8 = new Label("Label 8:");
        Label label9 = new Label("Label 9:");
        Label label10 = new Label("Label 10:");

        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();
        TextField textField6 = new TextField();
        TextField textField7 = new TextField();
        TextField textField8 = new TextField();
        TextField textField9 = new TextField();
        TextField textField10 = new TextField();

        // Add the label and text field to the GridPane
        gridPane.add(label1, 0, 0);
        gridPane.add(textField1, 1, 0);

        gridPane.add(label2, 0, 1);
        gridPane.add(textField2, 1, 1);

        gridPane.add(label3, 0, 2);
        gridPane.add(textField3, 1, 2);

        gridPane.add(label4, 0, 3);
        gridPane.add(textField4, 1, 3);

        gridPane.add(label5, 0, 4);
        gridPane.add(textField5, 1, 4);

        gridPane.add(label6, 0, 5);
        gridPane.add(textField6, 1, 5);

        gridPane.add(label7, 0, 6);
        gridPane.add(textField7, 1, 6);

        gridPane.add(label8, 0, 7);
        gridPane.add(textField8, 1, 7);

        gridPane.add(label9, 0, 8);
        gridPane.add(textField9, 1, 8);

        gridPane.add(label10, 0, 9);
        gridPane.add(textField10, 1, 9);

        // Create the Submit button
        Button submitButton = new Button("Submit");

        // Create a non-editable TextArea
        TextArea dataTextArea = new TextArea();
        dataTextArea.setEditable(false);
        // Simulate data retrieval from the database and set it in the TextArea
        String dataFromDatabase = fetchDataFromDatabase(); // Replace this with actual database retrieval logic
        dataTextArea.setText(dataFromDatabase);

        // Create an HBox to hold the Submit button and center it
        HBox buttonBox = new HBox(submitButton);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Create a VBox to hold the GridPane, TextArea, and the button
        VBox mainBox = new VBox(gridPane, dataTextArea, buttonBox);
        mainBox.setSpacing(10);
        mainBox.setPadding(new Insets(10));

        // Create the scene and set it on the stage
        Scene scene = new Scene(mainBox, 800, 800);
        primaryStage.setTitle("JavaFX Framework Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Simulate data retrieval from the database
    private String fetchDataFromDatabase() {
        // Replace this with your actual database retrieval logic
        return "Data from the database will be displayed here.";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
