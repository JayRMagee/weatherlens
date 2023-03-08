package csc325;

import java.io.IOException;
import javafx.fxml.FXML;

public class Home {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}