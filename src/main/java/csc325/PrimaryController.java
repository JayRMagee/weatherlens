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
import java.sql.*;
import javafx.scene.control.Alert;

public class PrimaryController {

    @FXML
    private TextField passwordLoginText;
    @FXML
    private TextField userLoginText;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField userNameText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField homeZipCodeText;
    @FXML
    private Button loginButton;
    @FXML
    private Button createAccountButton;

    @FXML
    private void handleLoginButton() throws IOException {
        // code to verify login credentials and switch to main page

        Stage stage = (Stage) loginButton.getScene().getWindow();
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

    @FXML
    private void handleCreateAccountButton() {
        Stage stage = (Stage) createAccountButton.getScene().getWindow();
        stage.close();

    }

    public void sendAccountDB() {
        String databaseURL = "";
        Connection conn = null;
        PreparedStatement userExist = null;
        ResultSet result = null;
        try {
            databaseURL = "jdbc:ucanaccess://.//UserAccounts.accdb";
            conn = DriverManager.getConnection(databaseURL);
            userExist = conn.prepareStatement("SELECT * FROM UserInfo WHERE Username = ?");
            userExist.setString(1, userLoginText.getText());
            result = userExist.executeQuery();
            if (result.isBeforeFirst()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else {
                try {
                    User u1 = new User();
                    u1.setFirstName(firstNameText.getText());
                    u1.setUserName(userNameText.getText());
                    u1.setUserPassword(passwordText.getText());
                    u1.setHomeZipCode(homeZipCodeText.getText());
                    String sql = "INSERT INTO UserInfo ([First name],[Username],[Password],[ Zipcode]) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, u1.getFirstName());
                    preparedStatement.setString(2, u1.getUserName());
                    preparedStatement.setString(3, u1.getUserPassword());
                    preparedStatement.setString(4, u1.getHomeZipCode());
                    int row = preparedStatement.executeUpdate();
                    if (row > 0) {
                        System.out.println("Row inserted");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (userExist != null) {
                try {
                    userExist.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }
}
