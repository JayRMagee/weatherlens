/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csc325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;

/**
 *
 * @author jayso
 */
public class DetailedWeather {

    @FXML
    private void home() throws IOException {
        App.setRoot("home");
    }

    public String getDay(int i) {
        try {
            //public void getWeather() throws IOException {
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

                String[] names = response.toString().split("\"name\":");
                String[] temperatures = response.toString().split("\"temperature\":");
                String[] shortForecasts = response.toString().split("\"shortForecast\":");

                
                    int temperature = Integer.parseInt(temperatures[i].split(",")[0].trim());
                    String name = (names[i].split(",")[0].trim());
                    String shortForecast = (shortForecasts[i].split(",")[0].trim());

                    System.out.println(name + ":" + temperature + "°F" +  " - " + shortForecast);
                return name;
            } else {
                System.out.println("Error: " + con.getResponseCode());
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(DetailedWeather.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetailedWeather.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public Integer getTemperature(int i) {
        try {
            //public void getWeather() throws IOException {
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

                String[] names = response.toString().split("\"name\":");
                String[] temperatures = response.toString().split("\"temperature\":");
                String[] shortForecasts = response.toString().split("\"shortForecast\":");

                
                    int temperature = Integer.parseInt(temperatures[i].split(",")[0].trim());
                    String name = (names[i].split(",")[0].trim());
                    String shortForecast = (shortForecasts[i].split(",")[0].trim());

                    System.out.println(name + ":" + temperature + "°F" +  " - " + shortForecast);
                return temperature;
            } else {
                System.out.println("Error: " + con.getResponseCode());
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(DetailedWeather.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetailedWeather.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
