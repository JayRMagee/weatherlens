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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Home {

    @FXML
    private LineChart<String, Number> homeForecastLineChart;

    @FXML
    private NumberAxis homeNumberAxis;

    @FXML
    private CategoryAxis homeCategoryAxis;

    public void initialize() throws IOException {
        displayChartData();
    }

    public void displayChartData() throws IOException {
        // create a number axis for the y-axis
        DetailedWeather d1 = new DetailedWeather();
        XYChart.Series<String, Number> weather = new XYChart.Series<>();

        // add some data points to the series
        for (int i = 1; i <= 13; i += 2) {
            String day = d1.getDay(i);
            int temperature = d1.getTemperature(i);
            
            XYChart.Data<String, Number> data = new XYChart.Data<>(day, temperature);
            weather.getData().add(new XYChart.Data<>(day, temperature));
            
            weather.getData().add(data);
        }

        // add the data series to the chart
        homeForecastLineChart.getData().add(weather);
    }
}
