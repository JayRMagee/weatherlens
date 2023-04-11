package csc325;

import java.util.Map;
import java.util.TreeMap;

/**
 * A WeeklyForecast is made up of up to seven DailyForecast objects.
 *
 * @author Steven Miladinovic
 * @author Nicolas Shah
 */
public class WeeklyForecast extends DailyForecast {

    //private Map<Integer, DailyForecast> weeklyForecastMap;
    Location location;
    int weeklyHighTemperature, weeklyLowTemperature;

    public WeeklyForecast(Location location) {
        this.location = location;
    }

    public void generateWeeklyForecast() {
        DailyForecast[] weeklyForecast = new DailyForecast[7];
        int j = 0;
        for (int i = 1; i <= 13; i = i + 2) {
            weeklyForecast[j] = new DailyForecast();
            weeklyForecast[j].generateDailyForecast(location, i);
            j++;
        }
    }
}
