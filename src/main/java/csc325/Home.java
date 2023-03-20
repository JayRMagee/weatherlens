package csc325;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;

public class Home {

    @FXML
    private void displayChartData() throws IOException {
        XYChart.Series<String, Number> weatherDisplay = new XYChart.Series<>();
        DetailedWeather d1 = new DetailedWeather();
        d1.getDetailedWeather();
        
        //weatherDisplay.getData().add(new XYChart.Data<>(d1.getDetailedWeather().))
    }
}