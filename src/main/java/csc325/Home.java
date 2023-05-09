package csc325;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
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
    private Label forecastLabel;
    @FXML
    private Label windDirectionLabel;
    @FXML
    private Label windSpeedLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label tempertureLabel;
    @FXML
    private Label tempLabel;

    @FXML
    private Label stateLabel;

    @FXML
    private JFXTextField locationSearch;

    @FXML
    private NumberAxis homeNumberAxis;

    Location location = new Location("Farmingdale, New York");
    WeeklyForecast weeklyForecast = new WeeklyForecast(location);
    DailyForecast dailyForecast = new DailyForecast();

    /**
     * Ensures all necessary functions are called when the main pane is opened.
     * @throws IOException 
     */
    public void initialize() throws IOException {
        Thread t = new Thread(() -> {
            dailyForecast.generateDailyForecast(location, 1);
        });
        t.start();
        weeklyForecast.getWeeklyForecast();
        stateLabel.setText(location.toString());

        displayChartData(weeklyForecast);
        todayImage(weeklyForecast);
        displayForecastDetails(weeklyForecast);
    }

    /**
     * Sets the text values of each GUI Label to the appropriate value from the forecast.
     * @param wf 
     */
    public void displayForecastDetails(WeeklyForecast wf) {
        dateLabel.setText("Date: " + wf.getDays(0));
        tempertureLabel.setText("Temperature: \"" + String.valueOf(wf.getTemperatures(0)) + "°\"");
        windSpeedLabel.setText("Wind Speeds: " + wf.getWindSpeeds(0));
        windDirectionLabel.setText("Wind Directions: " + wf.getWindDirections(0));
        forecastLabel.setText("Forecast: \"" + wf.getDetailedForecasts(0) + "\"");
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

        homeForecastScatterChart.getData().clear();
        weatherSeries.getData().clear();
        homeNumberAxis.setUpperBound((Math.ceil((wf.getWeeklyHighTemperature() + 10) / 10.0) * 10.0));
        homeNumberAxis.setLowerBound(Math.floor((wf.getWeeklyLowTemperature() - 10) / 10) * 10);
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
        }

        // add the data series to the chart
        homeForecastScatterChart.getData().add(weatherSeries);
    }

    /**
     * Handles icon generation.
     * @author Jonathan Vasquez
     */
    public void todayImage(WeeklyForecast wf) {

        String iconLink = wf.getIcons(0);

        Image icon = new Image(iconLink);
        todayImage.setImage(icon);

        todayImage.setClip(new Circle(85, 85, 85));

    }

    /**
     * Updates all values on the main pane.
     * @throws IOException 
     */
    @FXML
    public void update() throws IOException {

        String newLocation = locationSearch.getText();
        if (!newLocation.isBlank()) {
            location = new Location(newLocation);

            WeeklyForecast wF = new WeeklyForecast(location);
            if (location.toString().matches("null, null")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The Location You Searched Is Unavailable. Please Try A New Location.");
                alert.getDialogPane().getStylesheets().add("csc325/WeatherLens.css");
                alert.show();
                locationSearch.clear();
            } else {
                wF.getWeeklyForecast();
                stateLabel.setText(location.toString());
                locationSearch.clear();

                displayForecastDetails(wF);
                displayChartData(wF);
                todayImage(wF);
            }
        }
    }

}
