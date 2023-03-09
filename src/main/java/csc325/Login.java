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
import java.net.*;
import java.io.*;
import javafx.scene.image.ImageView;

public class Login {
    
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
                    if (!firstNameText.getText().isEmpty() ||
                            !userNameText.getText().isEmpty() ||
                            !passwordText.getText().isEmpty() ||
                            !homeZipCodeText.getText().isEmpty()) {
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
                    } else {
                        System.out.println("Blank Fields");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("All fields must be filled.");
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

    }

    public void getWeather() throws IOException {
        // Construct the API URL using the latitude and longitude
        URL url = new URL("https://api.weather.gov/gridpoints/OKX/33,37/forecast");

        // Make a request to the NWS API
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Check if the request was successful
        if (con.getResponseCode() == 200) {
            StringBuilder response = new StringBuilder();
            try ( BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            String[] temperatures = response.toString().split("\"temperature\":");
            for (int i = 1; i < temperatures.length; i++) {
                int temperature = Integer.parseInt(temperatures[i].split(",")[0].trim());
                System.out.println("Temperature " + i + ": " + temperature);
            }
        } else {
            System.out.println("Error: " + con.getResponseCode());
        }
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
