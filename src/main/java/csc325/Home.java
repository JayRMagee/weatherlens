package csc325;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The Home class holds all the pertinent objects which present weather data to
 * the user.
 * @author Jonathan Vasquez
 */
public class Home {
    @FXML
    private ImageView todayImage;
    
    @FXML
    private ScatterChart<String, Number> homeForecastScatterChart;
    
    @FXML
    private Label tempLabel;
    
    @FXML
    private Label stateLabel;
    
    @FXML
    private JFXTextField search;
    
    DetailedWeather d1 = new DetailedWeather();

    public void initialize() throws IOException {
        displayChartData();
        todayImage();
    }

    /**
     * 
     * @author Jonathan Vasquez
     * @throws IOException 
     */
    public void displayChartData() throws IOException {
        // create a number axis for the y-axis
        XYChart.Series<String, Number> weatherSeries = new XYChart.Series<>();
        tempLabel.setText(Integer.toString(d1.getTemperature(1)) + "°F");
        
        homeForecastScatterChart.getXAxis().setTickLabelRotation(360);
        Color color = Color.BLACK;
        homeForecastScatterChart.getXAxis().setTickLabelFill(color);
        // add some data points to the series
        for (int i = 1; i <= 13; i = i + 2) {
            String day = d1.getDay(i);
            int temperature = d1.getTemperature(i);
            String iconLink = d1.getIcon(i);
            
            
            XYChart.Data<String, Number> data = new XYChart.Data<>(day, temperature);
            weatherSeries.getData().add(data);
            
            // create an image view for the icon and add it to the data point
            Image icon = new Image(iconLink);
            ImageView imageView = new ImageView(icon);
           
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
        homeForecastScatterChart.getData().add(weatherSeries);
    }

    /**
     * @author Jonathan Vasquez
     */
    public void todayImage() {
        String iconLink = d1.getIcon(1);
        
        Image icon = new Image(iconLink);
        todayImage.setImage(icon);

        todayImage.setClip(new Circle(85,85,85));
        
    }
  
}
