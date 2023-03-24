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
    
    public void displayChartData() throws IOException {
        // create a number axis for the y-axis
        DetailedWeather d1 = new DetailedWeather();
        XYChart.Series<String, Number> weather = new XYChart.Series<>();

        // add some data points to the series
        weather.getData().add(new XYChart.Data<>(d1.getDay(1), d1.getTemperature(1)));
        weather.getData().add(new XYChart.Data<>(d1.getDay(3), d1.getTemperature(3)));
        weather.getData().add(new XYChart.Data<>(d1.getDay(5), d1.getTemperature(5)));
        weather.getData().add(new XYChart.Data<>(d1.getDay(7), d1.getTemperature(7)));
        weather.getData().add(new XYChart.Data<>(d1.getDay(9), d1.getTemperature(9)));
        weather.getData().add(new XYChart.Data<>(d1.getDay(11), d1.getTemperature(11)));
        weather.getData().add(new XYChart.Data<>(d1.getDay(13), d1.getTemperature(13)));

        // add the data series to the chart
        homeForecastLineChart.getData().add(weather);
    }
}