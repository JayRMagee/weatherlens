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
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrimaryController {
    
    @FXML
    private Button enterButton;
    
    @FXML
    private void handleLoginButton(ActionEvent event) throws IOException {
    // code to verify login credentials and switch to main page
    
   KeyValue disableValue1 = new KeyValue(enterButton.disableProperty(), false);
   KeyFrame disableFrame1 = new KeyFrame(Duration.INDEFINITE, disableValue1);
   KeyValue disableValue2 = new KeyValue(enterButton.disableProperty(), true);
   KeyFrame disableFrame2 = new KeyFrame(Duration.ZERO, disableValue2);
   Timeline disableTimeLine = new Timeline(disableFrame2, disableFrame1);
   disableTimeLine.play();
    
    Stage appStage = new Stage();
    Scene appScene = new Scene(FXMLLoader.load(getClass().getResource("primary.fxml")));
    
    appStage.setScene(appScene);
    appStage.setWidth(1280); // set the initial width of the main page's window
    appStage.setHeight(720); // set the initial height of the main page's window
    appStage.show();
}
}