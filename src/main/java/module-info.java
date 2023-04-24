module csc325 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires com.jfoenix;
    requires unirest.java;
    opens csc325 to javafx.fxml;
    exports csc325;
}
