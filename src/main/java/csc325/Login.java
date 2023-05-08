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
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.exceptions.NitriteIOException;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import static org.dizitart.no2.objects.filters.ObjectFilters.and;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

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
        appStage.setWidth(1200);
        appStage.setHeight(815);
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
        Nitrite db = null;
        try {
            db = Nitrite.builder()
                    .filePath("UserAccounts.db")
                    .openOrCreate("username", "password");

            // check if the user already exists
            String username = userNameText.getText();
            ObjectRepository<User> userRepo = db.getRepository(User.class);
            User existingUser = userRepo.find(eq("username", username)).firstOrDefault();

            if (existingUser != null) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.getDialogPane().getStylesheets().add("csc325/WeatherLens.css");
                alert.show();
                return;
            }

            // create the new user
            String firstName = firstNameText.getText();
            String password = passwordText.getText();
            String homeZipCode = homeZipCodeText.getText();
            if (firstName.isEmpty() || username.isEmpty() || password.isEmpty() || homeZipCode.isEmpty()) {
                System.out.println("Blank fields");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("All fields must be filled.");
                alert.getDialogPane().getStylesheets().add("csc325/WeatherLens.css");
                alert.show();
                return;
            }
            User newUser = new User(username, firstName, password, homeZipCode);
            userRepo.insert(newUser);
            db.close();
            System.out.println("User inserted");
            System.out.println(newUser.toString());
        } catch (NitriteIOException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
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
    /*public void checkLogin() throws IOException {
        Nitrite db = Nitrite.builder()
                    .filePath("UserAccounts.db")
                    .openOrCreate();
        try {

            // check if the user exists and the password is correct
            String username = userLoginText.getText();
            String password = passwordLoginText.getText();
            ObjectRepository<User> userRepo = db.getRepository(User.class);
        User existingUser = userRepo.find(and(eq("username", username), eq("userPassword", password))).firstOrDefault();
            
           
        if (existingUser != null) {
                System.out.println("Login successful. Opening the page...");
                handleLoginButton();
            } else {
                System.out.println("User not found or wrong password");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Credentials are wrong");
                alert.getDialogPane().getStylesheets().add("csc325/WeatherLens.css");
                alert.show();
            }
        } catch (NitriteIOException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }*/
    public void checkLogin() throws IOException {
        String username = userLoginText.getText();
        String password = passwordLoginText.getText();

        Nitrite db = null;
        try {
            db = Nitrite.builder()
                    .filePath("UserAccounts.db")
                    .openOrCreate("username", "password");

            ObjectRepository<User> userRepo = db.getRepository(User.class);
            User user = userRepo.find(eq("username", username)).firstOrDefault();

            if (user != null && user.getUserPassword().equals(password)) {
                handleLoginButton();
                db.close();
            }
            else{
                db.close();
            }
        } catch (NitriteIOException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

}
