package csc325;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {
    
    @FXML
    private void handleLoginButton(ActionEvent event) throws IOException {
    // code to verify login credentials and switch to main page

    Stage mainStage = new Stage();
    Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("Primary.fxml")));
    
    mainStage.setScene(mainScene);
    mainStage.setWidth(1280); // set the initial width of the main page's window
    mainStage.setHeight(720); // set the initial height of the main page's window
    mainStage.show();
}
}