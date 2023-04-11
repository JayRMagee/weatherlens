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

    Location location;
    DailyForecast[] weeklyForecast;
    int weeklyHighTemperature, weeklyLowTemperature;

    public WeeklyForecast(Location location) {
        this.location = location;
    }

    private void generateWeeklyForecast() {
        weeklyForecast = new DailyForecast[7];
        int j = 0;
        for (int i = 1; i <= 13; i = i + 2) {
            weeklyForecast[j] = new DailyForecast();
            weeklyForecast[j].generateDailyForecast(location, i);
            j++;
        }
    }

    public DailyForecast[] getWeeklyForecast() {
        generateWeeklyForecast();
        return weeklyForecast;
    }

    public void readArray() {
        for (int i = 0; i < weeklyForecast.length; i++) {
            System.out.println(weeklyForecast[i].toString());
        }
    }
    
    

}
