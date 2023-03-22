package csc325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Home {

    @FXML
    private LineChart<String, Number> homeForecastLineChart;
    
    @FXML
    private NumberAxis homeNumberAxis;
    
    @FXML
    private CategoryAxis homeCategoryAxis;
    
    public void initialize() throws IOException{
        displayChartData();
    }
    
    private void displayChartData() throws IOException {
        // create a number axis for the y-axis
        XYChart.Series<String, Number> weather = new XYChart.Series<>();

        // add some data points to the series
        weather.getData().add(new XYChart.Data<>(getDay(), getTemperature(getDay())));
        weather.getData().add(new XYChart.Data<>("Tuesday", 22));
        weather.getData().add(new XYChart.Data<>("Wednesday", 24));
        weather.getData().add(new XYChart.Data<>("Thursday", 23));
        weather.getData().add(new XYChart.Data<>("Friday", 21));
        weather.getData().add(new XYChart.Data<>("Saturday", 19));
        weather.getData().add(new XYChart.Data<>("Sunday", 18));

        // add the data series to the chart
        homeForecastLineChart.getData().add(weather);
    }
    
    public int getTemperature(String dayOfWeek) {
    try {
        // Construct the API URL using the latitude and longitude
        URL url = new URL("https://api.weather.gov/gridpoints/OKX/33,37/forecast");

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

            // Parse the JSON response to find the temperature for the specified day of the week
            int temperature = 0;
            String[] names = response.toString().split("\"name\":");
            String[] temperatures = response.toString().split("\"temperature\":");
            String[] shortForecasts = response.toString().split("\"shortForecast\":");

            for (int i = 0; i < names.length; i++) {
                // Find the index of the day of the week in the "name" array
                int dayIndex = names[i].indexOf(dayOfWeek);
                if (dayIndex != -1) {
                    // Use the index to find the corresponding temperature value in the "temperature" array
                    temperature = Integer.parseInt(temperatures[i * 2 + 1].split(",")[0].trim());
                    break;
                }
            }
            
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
    
    public String getDay() {
    try {
        // Construct the API URL using the latitude and longitude
        URL url = new URL("https://api.weather.gov/gridpoints/OKX/33,37/forecast");

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

            String[] names = response.toString().split("\"name\":");
            String[] temperatures = response.toString().split("\"temperature\":");
            String[] shortForecasts = response.toString().split("\"shortForecast\":");

            for (int i = 1; i < temperatures.length; i = i + 2) {
                String name = (names[i].split(",")[0].trim());
                String forecast = (shortForecasts[i].split(",")[0].trim());

                if (forecast.toLowerCase(Locale.ROOT).contains("today")) {
                    // Extract the date from the day's name and format it as "yyyy-MM-dd"
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(name.split(" ")[0], formatter);

                    // Format the date as the day of the week
                    return date.getDayOfWeek().name();
                }
            }
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
}