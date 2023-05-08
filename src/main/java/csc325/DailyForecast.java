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
 * @author Jayson Magee
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
        this.number = getNumber();
        this.name = getName();
        this.temperature = getTemperature();
        this.temperatureUnit = getTempUnit();
        this.trend = getTrend();
        this.windSpeed = getWindSpeed();
        this.windDirection = getWindDirection();
        this.iconUrl = getIconUrl();
        this.shortForecast = getShortForecast();
        this.detailedForecast = getDetailedForecast();
    }

    public void generateDailyForecast(Location l, int i) {
        DailyForecast[] dailyForecast = new DailyForecast[14];
        String id = l.gridID;
        int x = l.gridX;
        int y = l.gridY;

        URLConnection connection;
        String finalUrl = "https://api.weather.gov/gridpoints/" + id + "/" + x + "," + y + "/forecast";
        URL url;

        try {
            url = new URL(finalUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            if (con.getResponseCode() == 200) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    String[] names = response.toString().split("\"name\":");
                    name = (names[i].split(",")[0].trim());
                    this.setName(name);

                    String[] temperatures = response.toString().split("\"temperature\":");
                    temperature = Integer.parseInt(temperatures[i].split(",")[0].trim());
                    this.setTemperature(temperature);

                    String[] temperatureUnits = response.toString().split("\"temperatureUnit\":");
                    temperatureUnit = (temperatureUnits[i].split(",")[0].trim());
                    this.setTemperatureUnit(temperatureUnit);

                    String[] trends = response.toString().split("\"temperatureTrend\":");
                    trend = (trends[i].split(",")[0].trim());
                    this.setTrend(trend);

                    String[] numbers = response.toString().split("\"number\":");
                    number = Integer.parseInt(numbers[i].split(",")[0].trim());
                    this.setNumber(number);

                    String[] windSpeeds = response.toString().split("\"windSpeed\":");
                    windSpeed = (windSpeeds[i].split(",")[0].trim());
                    this.setWindSpeed(windSpeed);

                    String[] windDirections = response.toString().split("\"windDirection\":");
                    windDirection = (windDirections[i].split(",")[0].trim());
                    this.setWindDirection(windDirection);

                    String[] iconUrls = response.toString().split("\"icon\":");
                    iconUrl = iconUrls[i].split(",")[0].replaceAll("\"", "").trim();
                    this.setIconUrl(iconUrl);

                    String[] shortForecasts = response.toString().split("\"shortForecast\":");
                    shortForecast = (shortForecasts[i].split(",")[0].trim());
                    this.setShortForecast(shortForecast);

                    String[] detailedForecasts = response.toString().split("\"detailedForecast\":");
                    detailedForecast = (detailedForecasts[i].split(",")[0].replaceAll("\"", "").trim());
                    this.setDetailedForecast(detailedForecast);
                }
            } else {
                System.out.println("Error: " + con.getResponseCode());
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public void setDetailedForecast(String detailedForecast) {
        this.detailedForecast = detailedForecast;
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
