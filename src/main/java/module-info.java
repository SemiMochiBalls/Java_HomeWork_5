module lab5.java_5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens lab5.java_5 to javafx.fxml;
    exports lab5.java_5;
}