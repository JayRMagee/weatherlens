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
        weather.getData().add(new XYChart.Data<>("Monday", 23));
        weather.getData().add(new XYChart.Data<>("Tuesday", 22));
        weather.getData().add(new XYChart.Data<>("Wednesday", 24));
        weather.getData().add(new XYChart.Data<>("Thursday", 23));
        weather.getData().add(new XYChart.Data<>("Friday", 21));
        weather.getData().add(new XYChart.Data<>("Saturday", 19));
        weather.getData().add(new XYChart.Data<>("Sunday", 18));

        // add the data series to the chart
        homeForecastLineChart.getData().add(weather);
    }
}