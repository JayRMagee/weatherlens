package csc325;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrimaryController {

    @FXML
    private TextField firstNameText;
    @FXML
    private TextField userNameText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField homeZipCodeText;
    @FXML
    private Button enterButton;

    @FXML
    private void handleLoginButton() throws IOException {
        // code to verify login credentials and switch to main page

        Stage stage = (Stage) enterButton.getScene().getWindow();
        stage.close();

        Stage appStage = new Stage();
        Scene appScene = new Scene(FXMLLoader.load(getClass().getResource("primary.fxml")));

        appStage.setScene(appScene);
        appStage.setWidth(1280); // set the initial width of the main page's window
        appStage.setHeight(720); // set the initial height of the main page's window
        appStage.show();

    }

    @FXML
    private void handleCreateAccountPageOpenLabel() throws IOException {
        Stage createStage = new Stage();
        Scene createScene = new Scene(FXMLLoader.load(getClass().getResource("createAccount.fxml")));

        createStage.setScene(createScene);
        createStage.setWidth(350); // set the initial width of the main page's window
        createStage.setHeight(450); // set the initial height of the main page's window
        createStage.show();
    }

    public void handleCreateAccount() {
    }
}
