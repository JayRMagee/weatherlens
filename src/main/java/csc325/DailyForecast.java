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

    /**
     * Non-parameterized constructor for the DailyForecast class.
     */
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

    /**
     * Populates all the values of a DailyForecast using an API call.
     * @param l
     * @param i 
     */
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

                    String[] temperatures = response.toString().split("\"temperature\":");
                    temperature = Integer.parseInt(temperatures[i].split(",")[0].trim());
                    this.setTemperature(temperature);
                    
                    String[] names = response.toString().split("\"name\":");
                    name = (names[i].split(",")[0].trim());
                    this.setName(name);

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

    /**
     * Sets the number period of the DailyForecast object.
     * @param number 
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Sets the day of the week of the DailyForecast object.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the temperature of the DailyForecast object.
     * @param temperature 
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * Sets the temperature unit of measure of the DailyForecast object.
     * @param temperatureUnit 
     */
    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    /**
     * Sets the temperature trend of the DailyForecast object.
     * @param trend 
     */
    public void setTrend(String trend) {
        this.trend = trend;
    }

    /**
     * Sets the wind speed of the DailyForecast object.
     * @param windSpeed 
     */
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * Sets the wind direction of the DailyForecast object.
     * @param windDirection 
     */
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * Sets the icon URL of the DailyForecast object.
     * @param iconUrl 
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * Sets the short forecast of the DailyForecast object.
     * @param shortForecast 
     */
    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    /**
     * Sets the detailed forecast of the DailyForecast object.
     * @param detailedForecast 
     */
    public void setDetailedForecast(String detailedForecast) {
        this.detailedForecast = detailedForecast;
    }

    /**
     * Returns the period number of the DailyForecast object.
     * @return 
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the day of the week of the DailyForecast object.
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the temperature of the DailyForecast object.
     * @return 
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Returns the temperature unit of measure of the DailyForecast object.
     * @return 
     */
    public String getTempUnit() {
        return temperatureUnit;
    }

    /**
     * Returns the temperature trend of the DailyForecast object.
     * @return 
     */
    public String getTrend() {
        return trend;
    }

    /**
     * Returns the wind speed of the DailyForecast object.
     * @return 
     */
    public String getWindSpeed() {
        return windSpeed;
    }

    /**
     * Returns the wind direction of the DailyForecast object.
     * @return 
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * Returns the icon URL of the DailyForecast object.
     * @return 
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * Returns the short forecast of the DailyForecast object.
     * @return 
     */
    public String getShortForecast() {
        return shortForecast;
    }

    /**
     * Returns the detailed forecast of the DailyForecast object.
     * @return 
     */
    public String getDetailedForecast() {
        return detailedForecast;
    }

    /**
     * Returns the DailyForecast object as a String of comma-separated values.
     * @return 
     */
    @Override
    public String toString() {
        return "DailyForecast{" + "number=" + number + ", name=" + name + ", temperature=" + temperature + ", tempUnit=" + temperatureUnit + ", trend=" + trend + ", windSpeed=" + windSpeed + ", windDirection=" + windDirection + ", iconURL=" + iconUrl + ", shortForecast=" + shortForecast + ", detailedForecast=" + detailedForecast + '}';
    }

}
