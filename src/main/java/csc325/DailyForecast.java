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

    Location searchLocation;

    String id = searchLocation.gridID;
    int x = searchLocation.gridX;
    int y = searchLocation.gridY;

    public DailyForecast() {
        getWeeklyWeatherURL();
        fetchDailyForecastData();
    }

    private void getWeeklyWeatherURL() {
        URLConnection connection;
        String finalUrl = "https://api.weather.gov/gridpoints/" + id + "/" + x + "," + y + "/forecast";
        try {
            connection = new URL(finalUrl).openConnection();
            InputStream response = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray results = jsonObject.getJSONArray("results");
            //Could loop through but right now we don't know what result to pick - so just take the first one (index 0).
            JSONObject result = results.getJSONObject(0);
            JSONObject geometry = result.getJSONObject("geometry");
            number = geometry.getInt("number");
            System.out.println(number);
            name = geometry.getString("name");
            System.out.println(name);
        } catch (IOException | JSONException ex) {
            System.out.println("error");
        }

    }

    private void fetchDailyForecastData() {

    }
}
