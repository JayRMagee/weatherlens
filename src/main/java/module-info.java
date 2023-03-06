module csc325 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens csc325 to javafx.fxml;
    exports csc325;
}
