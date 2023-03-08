package csc325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
        sendAccountDB();
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
            userExist.setString(1, userNameText.getText());
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
    
    public void getWeather() throws IOException {
        
        // Construct the API endpoint URL with latitude and longitude
        String url = "https://api.weather.gov/";

        // Create a URL object and HttpURLConnection
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Set the necessary headers for the request
        con.setRequestProperty("Accept", "application/geo+json");

        // Send the request to the API endpoint
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parse the JSON response using Gson
        Gson gson = new Gson();
        JsonObject forecastJson = gson.fromJson(response.toString(), JsonObject.class);

        // Print the forecast for the next hour
        JsonObject nextHourForecast = forecastJson.getAsJsonObject("period");
        System.out.println("Next hour forecast: " + nextHourForecast.get("shortForecast"));
    }
    

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
                        alert.show();

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
