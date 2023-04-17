package csc325;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.NumberAxis;
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
 *
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

    @FXML
    private NumberAxis homeNumberAxis;

   

    public void initialize() throws IOException{
        Location location = new Location("Chicago");
        DailyForecast dailyForecast = new DailyForecast();
        dailyForecast.generateDailyForecast(location, 1);
        WeeklyForecast weeklyForecast = new WeeklyForecast(location);
        weeklyForecast.getWeeklyForecast();
        stateLabel.setText(location.toString());
        
        displayChartData(weeklyForecast);
        todayImage(weeklyForecast);
    }

    /**
     * Creates and displays a scatter chart and populates it with weather
     * information. The upper and lower bounds of the scatter chart are
     * dynamically set according to the highest and lowest temperature values.
     *
     * @author Jonathan Vasquez
     * @throws IOException
     */
    public void displayChartData(WeeklyForecast wf) throws IOException {
        // create a number axis for the y-axis
        XYChart.Series<String, Number> weatherSeries = new XYChart.Series<>();
        tempLabel.setText(Integer.toString(wf.getTemperatures(0)) + "°F");

        homeForecastScatterChart.getXAxis().setTickLabelRotation(360);
        homeForecastScatterChart.getXAxis().setTickLabelFill(Color.BLACK);
        homeForecastScatterChart.getYAxis().setTickLabelFill(Color.BLACK);
        
        int highestTemperature = wf.getTemperatures(0);
        int lowestTemperature = wf.getTemperatures(0);

        /* Add some data points to the series
        (For loop loops through the 7 days of the array, 
        NOT the data points inside the API (which required i + 2, i <= 13), 
        was causing out of bounds error.)*/
        for (int i = 0; i < 7; i++) { 
            String day = wf.getDays(i);
            int temperature = wf.getTemperatures(i);
            String iconLink = wf.getIcons(i);

            XYChart.Data<String, Number> data = new XYChart.Data<>(day, temperature);
            weatherSeries.getData().add(data);

            // create an image view for the icon and add it to the data point
            Image icon = new Image(iconLink);
            ImageView imageView = new ImageView(icon);

            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            imageView.setClip(new Circle(15, 15, 15));
            Circle circle = new Circle(15, 15, 16);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(2);

            Group group = new Group(circle, imageView);
            data.setNode(group);

            if (temperature > highestTemperature) {
                highestTemperature = temperature;
            }
            if (temperature < lowestTemperature) {
                lowestTemperature = temperature;
            }
        }

        homeNumberAxis.setUpperBound(Math.round((highestTemperature + 10) / 10) * 10);
        homeNumberAxis.setLowerBound(Math.round((lowestTemperature - 10) / 10) * 10);

        // add the data series to the chart
        homeForecastScatterChart.getData().add(weatherSeries);
    }

    /**
     * @author Jonathan Vasquez
     */
    public void todayImage(WeeklyForecast wf) {
        
        String iconLink = wf.getIcons(0);

        Image icon = new Image(iconLink);
        todayImage.setImage(icon);

        todayImage.setClip(new Circle(85, 85, 85));

    }

}
