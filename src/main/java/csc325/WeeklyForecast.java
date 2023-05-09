package csc325;

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

    /**
     * Creates a new Array of DailyForecasts and populates it with seven upcoming forecasts. 
     */
    private void generateWeeklyForecast() {
        weeklyForecast = new DailyForecast[7];
        int j = 0;
        for (int i = 1; i <= 13; i = i + 2) {
            weeklyForecast[j] = new DailyForecast();
            weeklyForecast[j].generateDailyForecast(location, i);
            j++;
        }
    }

    /**
     * Returns the values of a weekly forecast.
     * @return 
     */
    public DailyForecast[] getWeeklyForecast() {
        generateWeeklyForecast();
        return weeklyForecast;
    }

    /**
     * Iterates through a weekly forecast and returns the To String value of each index.
     */
    public void readArray() {
        for (int i = 0; i < weeklyForecast.length; i++) {
            System.out.println(weeklyForecast[i].toString());
        }
    }

    /**
     * Returns the days of the week of a weekly forecast.
     * @param i
     * @return 
     */
    public String getDays(int i) {
        return weeklyForecast[i].getName();
    }

    /**
     * Returns the specific weather forecast periods of a weekly forecast.
     * @param i
     * @return 
     */
    public int getPeriods(int i) {
        return weeklyForecast[i].getNumber();
    }

    /**
     * Returns the temperatures of a weekly forecast.
     * @param i
     * @return 
     */
    public int getTemperatures(int i) {
        return weeklyForecast[i].getTemperature();
    }

    /**
     * Returns the wind speeds of a weekly forecast.
     * @param i
     * @return 
     */
    public String getWindSpeeds(int i) {
        return weeklyForecast[i].getWindSpeed();
    }

    /**
     * Returns the wind directions of a weekly forecast.
     * @param i
     * @return 
     */
    public String getWindDirections(int i) {
        return weeklyForecast[i].getWindDirection();
    }

    /**
     * Returns the icons representing the conditions of a weekly forecast.
     * @param i
     * @return 
     */
    public String getIcons(int i) {
        return weeklyForecast[i].getIconUrl();
    }

    /**
     * Returns a brief forecasts for a weekly forecast.
     * @param i
     * @return 
     */
    public String getShortForecasts(int i) {
        return weeklyForecast[i].getShortForecast();
    }

    /**
     * Returns more verbose forecasts for a weekly forecast.
     * @param i
     * @return 
     */
    public String getDetailedForecasts(int i) {
        return weeklyForecast[i].getDetailedForecast();
    }

    /**
     * Iterates through a weekly forecast to determine the highest temperature for the coming week.
     * @return 
     */
    public int getWeeklyHighTemperature() {
        if (weeklyHighTemperature == 0) {
            for (DailyForecast dailyForecast : weeklyForecast) {
                int dailyHighTemperature = dailyForecast.getTemperature();
                if (dailyHighTemperature > weeklyHighTemperature) {
                    weeklyHighTemperature = dailyHighTemperature;
                }
            }
        }
        return weeklyHighTemperature;
    }

    /**
     * Iterates through a weekly forecast to determine the lowest temperature for the coming week.
     * @return 
     */
    public int getWeeklyLowTemperature() {
        if (weeklyLowTemperature == 0) {
            for (DailyForecast dailyForecast : weeklyForecast) {
                int dailyLowTemperature = dailyForecast.getTemperature();
                if (weeklyLowTemperature == 0 || dailyLowTemperature < weeklyLowTemperature) {
                    weeklyLowTemperature = dailyLowTemperature;
                }
            }
        }
        return weeklyLowTemperature;
    }
}
