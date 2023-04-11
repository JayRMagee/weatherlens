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
import java.net.URLConnection;

/**
 *
 * @author jayso
 */
public class DailyForecast {

    DailyForecast[] dailyForecasts;

    int number;
    String name;
    int temperature;
    String temperatureUnit;
    String trend;
    String windSpeed;
    String windDirection;
    String iconUrl;
    String shortForecast;
    String detailedForecast;

    public DailyForecast() {
        this.number = 1;
        this.name = "";
        this.temperature = 99;
        this.temperatureUnit = "";
        this.trend = "";
        this.windSpeed = "";
        this.windDirection = "";
        this.iconUrl = "";
        this.shortForecast = "";
        this.detailedForecast = "";
    }

    //Location searchLocation;
//    String id = searchLocation.gridID;
//    int x = searchLocation.gridX;
//    int y = searchLocation.gridY;
//    public DailyForecast() {
//        getWeeklyWeatherURL(Location l);
//    }
    public void generateDailyForecast(Location l, int i) {
        DailyForecast[] dailyForecast = new DailyForecast[14];
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
                

                String[] names = response.toString().split("\"name\":");
                name = (names[i].split(",")[0].trim());

                String[] temperatures = response.toString().split("\"temperature\":");
                temperature = Integer.parseInt(temperatures[i].split(",")[0].trim());

                String[] temperatureUnits = response.toString().split("\"temperatureUnit\":");
                temperatureUnit = (temperatureUnits[i].split(",")[0].trim());

                String[] trends = response.toString().split("\"temperatureTrend\":");
                trend = (trends[i].split(",")[0].trim());

                String[] numbers = response.toString().split("\"number\":");
                number = Integer.parseInt(numbers[i].split(",")[0].trim());

                String[] windSpeeds = response.toString().split("\"windSpeed\":");
                windSpeed = (windSpeeds[i].split(",")[0].trim());

                String[] windDirections = response.toString().split("\"windDirection\":");
                windDirection = (windDirections[i].split(",")[0].trim());

                String[] iconUrls = response.toString().split("\"icon\":");
                iconUrl = (iconUrls[i].split(",")[0].trim());

                String[] shortForecasts = response.toString().split("\"shortForecast\":");
                shortForecast = (shortForecasts[i].split(",")[0].trim());

                String[] detailedForecasts = response.toString().split("\"detailedForecast\":");
                detailedForecast = (detailedForecasts[i].split(",")[0].trim());
            }
        }else {
                System.out.println("Error: " + con.getResponseCode());
            }
    }
    catch (MalformedURLException ex

    
        ) {
            ex.printStackTrace();
    }
    catch (IOException ex

    
        ) {
            ex.printStackTrace();
    }

}

public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getTempUnit() {
        return temperatureUnit;
    }

    public String getTrend() {
        return trend;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public String getDetailedForecast() {
        return detailedForecast;
    }

    @Override
public String toString() {
        return "DailyForecast{" + "number=" + number + ", name=" + name + ", temperature=" + temperature + ", tempUnit=" + temperatureUnit + ", trend=" + trend + ", windSpeed=" + windSpeed + ", windDirection=" + windDirection + ", iconURL=" + iconUrl + ", shortForecast=" + shortForecast + ", detailedForecast=" + detailedForecast + '}';
    }

}
