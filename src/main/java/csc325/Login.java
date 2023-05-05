package csc325;

//import com.google.firebase.auth.UserRecord;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * This class is designed to hold the methods needed to login in to the weather
 * app as well as controls the page for creating an account for first time
 * users.
 *
 * @author Nicholas Shah
 */
public class Login {

    @FXML
    private Pane accountPane;
    @FXML
    private Pane contentPane;
    @FXML
    private JFXTextField passwordLoginText;
    @FXML
    private JFXTextField userLoginText;
    @FXML
    private JFXTextField firstNameText;
    @FXML
    private JFXTextField userNameText;
    @FXML
    private JFXTextField passwordText;
    @FXML
    private JFXTextField homeZipCodeText;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton createAccountButton;

    @FXML
    private void showLoginPane() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        App.stage.getScene().setRoot(root);
    }

    /**
     * code to verify login credentials and switch to main page
     *
     * @throws IOException
     */
    public void handleLoginButton() throws IOException {

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();

        Stage appStage = new Stage();
        Scene appScene = new Scene(FXMLLoader.load(getClass().getResource("home.fxml")));

        appStage.setScene(appScene);
        appStage.setWidth(1220);
        appStage.setHeight(825);
        appStage.setResizable(false);
        appStage.show();

    }

    /**
     * Method to replace login pane with create account pane.
     *
     * @throws IOException
     */
    public void handleCreateAccountPageOpenLabel() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("createAccount.fxml"));
        contentPane.getChildren().removeAll();
        contentPane.getChildren().setAll(fxml);
    }

    /**
     * Method to send data to DB and return to login page.
     *
     * @throws IOException
     */
    public void handleCreateAccountButton() throws IOException {
        Stage stage = (Stage) createAccountButton.getScene().getWindow();
        sendAccountDB();
    }

    /**
     * Method to insert user information into database, thus crating an account.
     *
     * @throws IOException
     */
    public void sendAccountDB() throws IOException {
        String databaseURL = "";
        Connection conn = null;
        PreparedStatement userExist = null;
        ResultSet result = null;
        try {
            databaseURL = "jdbc:ucanaccess://.//UserAccounts.accdb";
            conn = DriverManager.getConnection(databaseURL);
            userExist = conn.prepareStatement("SELECT * FROM UserInfo WHERE Username = ?");
            userExist.setString(1, userNameText.getText());
            result = userExist.executeQuery();
            if (result.isBeforeFirst()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.getDialogPane().getStylesheets().add("csc325/WeatherLens.css");
                alert.show();
            } else {
                try {
                    if (!firstNameText.getText().isEmpty()
                            || !userNameText.getText().isEmpty()
                            || !passwordText.getText().isEmpty()
                            || !homeZipCodeText.getText().isEmpty()) {
                        User u1 = new User();
                        u1.setFirstName(firstNameText.getText());
                        u1.setUsername(userNameText.getText());
                        u1.setUserPassword(passwordText.getText());
                        u1.setHomeZipCode(homeZipCodeText.getText());
                        String sql = "INSERT INTO UserInfo ([First Name],[Username],[Password],[Zipcode]) VALUES (?, ?, ?, ?)";
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, u1.getFirstName());
                        preparedStatement.setString(2, u1.getUsername());
                        preparedStatement.setString(3, u1.getUserPassword());
                        preparedStatement.setString(4, u1.getHomeZipCode());
                        int row = preparedStatement.executeUpdate();
                        if (row > 0) {
                            System.out.println("Row inserted");
                        }
                        /*
                        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                                .setEmail(userNameText.getText())
                                .setEmailVerified(false)
                                .setPassword(passwordText.getText())
                                .setDisabled(false);
                        */
                    } else {
                        System.out.println("Blank Fields");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("All fields must be filled.");
                        alert.getDialogPane().getStylesheets().add("csc325/WeatherLens.css");
                        alert.show();
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
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        App.stage.getScene().setRoot(root);

    }

    /**
     * method that confirms login credentials
     *
     * @throws IOException
     */
    public void checkLogin() throws IOException {
        String databaseURL = "";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            databaseURL = "jdbc:ucanaccess://.//UserAccounts.accdb";
            conn = DriverManager.getConnection(databaseURL);
            ps = conn.prepareStatement("SELECT Password FROM UserInfo WHERE Username = ?");
            ps.setString(1, userLoginText.getText());
            result = ps.executeQuery();

            if (!result.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Credentials are wrong");
                alert.getDialogPane().getStylesheets().add("csc325/WeatherLens.css");
                alert.show();
            } else {
                while (result.next()) {
                    String retreivedPass = result.getString("Password");

                    if (retreivedPass.equals(passwordLoginText.getText())) {
                        handleLoginButton();
                    } else {
                        System.out.println("Password wrong");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Wrong");
                        alert.getDialogPane().getStylesheets().add("csc325/WeatherLens.css");
                        alert.show();

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
