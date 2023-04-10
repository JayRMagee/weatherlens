/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csc325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

/**
 *
 * @author jayso
 */
public class DailyForecast {

    int number;
    String name;
    int temp;
    String tempUnit;
    String trend;
    String windSpeed;
    String windDirection;
    String iconURL;
    String shortForecast;
    String detailedForecast;

    //Location searchLocation;

//    String id = searchLocation.gridID;
//    int x = searchLocation.gridX;
//    int y = searchLocation.gridY;

//    public DailyForecast() {
//        getWeeklyWeatherURL(Location l);
//    }

    public void getWeeklyWeatherURL(Location l, int i) {
        String id = l.gridID;
        int x = l.gridX;
        int y = l.gridY;
        
        URLConnection connection;
        String finalUrl = "https://api.weather.gov/gridpoints/" + id + "/" + x + "," + y + "/forecast";
        //System.out.println("hbhde");
        URL url;
        try {
            // Construct the API URL using the latitude and longitude
            url = new URL(finalUrl);

            // Make a request to the NWS API
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Check if the request was successful
            if (con.getResponseCode() == 200) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                //System.out.println("bjdb");
                String[] names = response.toString().split("\"name\":");
                name = (names[i].split(",")[0].trim());
                System.out.println(name);

                
            } else {
                System.out.println("Error: " + con.getResponseCode());
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        

    }
    
    

    
}