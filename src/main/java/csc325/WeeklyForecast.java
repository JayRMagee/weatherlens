package csc325;

import java.util.Map;
import java.util.TreeMap;

/**
 * A WeeklyForecast is made up of up to seven DailyForecast objects.
 * @author Steven Miladinovic
 * @author Nicolas Shah
 */
public class WeeklyForecast {
    private Map<Integer, DailyForecast> weeklyForecastMap;
    int weeklyHighTemperature, weeklyLowTemperature;
    
    public void generateWeeklyForecast() {
        weeklyForecastMap = new TreeMap();
        
        for (int i = 0; i < 10; i++) {
            
        }
    }
}
