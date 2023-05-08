module csc325 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires com.jfoenix;
    requires unirest.java;
    requires com.
    requires com.google.auth.oauth2;
    requires com.google.cloud.firestore;
    requires com.google.firebase;
    requires com.google.firebase.auth;
    requires com.google.firebase.cloud;

    opens csc325 to javafx.fxml;
    exports csc325;
}

