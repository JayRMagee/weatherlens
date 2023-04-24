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
    public String getDays(int i) {
            return weeklyForecast[i].getName();
    }
     public int getPeriods(int i) {
            return weeklyForecast[i].getNumber();
    }
     public int getTemperatures(int i) {
            return weeklyForecast[i].getTemperature();
    }
     public String getWindSpeeds(int i) {
            return weeklyForecast[i].getWindSpeed();
    }
      public String getWindDirections(int i) {
            return weeklyForecast[i].getWindDirection();
    }
       public String getIcons(int i) {
            return weeklyForecast[i].getIconUrl();
    }
        public String getShortForecasts(int i) {
            return weeklyForecast[i].getShortForecast();
    }
     public String getDetailedForecasts(int i) {
           return weeklyForecast[i].getDetailedForecast();
    }

}
