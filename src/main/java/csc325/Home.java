package csc325;

import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.Group;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Home {
    @FXML
    private ImageView todayImage;
    @FXML
    private ScatterChart<String, Number> homeForecastLineChart;

    @FXML
    private NumberAxis homeNumberAxis;

    @FXML
    private CategoryAxis homeCategoryAxis;
    
    @FXML
    private Label tempLabel;
    
    @FXML
    private JFXTextField Search;
    
    
    

    public void initialize() throws IOException {
        displayChartData();
        
    }

    public void displayChartData() throws IOException {
        // create a number axis for the y-axis
        DetailedWeather d1 = new DetailedWeather();
        XYChart.Series<String, Number> weather = new XYChart.Series<>();
        tempLabel.setText(Integer.toString(d1.getTemperature(1)) + "°F");
        

        homeForecastLineChart.setAnimated(false);
        homeForecastLineChart.getXAxis().setTickLabelRotation(90);
        // add some data points to the series
        for (int i = 1; i <= 13; i += 2) {
            String day = d1.getDay(i);
            int temperature = d1.getTemperature(i);
            String iconLink = d1.getIcon(i);
            
            XYChart.Data<String, Number> data = new XYChart.Data<>(day, temperature);
            weather.getData().add(data);
            
            // create an image view for the icon and add it to the data point
            Image icon = new Image(iconLink);
            ImageView imageView = new ImageView(icon);
            todayImage.setImage(icon);
           
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            imageView.setClip(new Circle(15,15,15));
            Circle circle = new Circle(15, 15, 16);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(2);
        
            Group group = new Group(circle, imageView);
            data.setNode(group);
        }

        // add the data series to the chart
        homeForecastLineChart.getData().add(weather);
    }
  
}
