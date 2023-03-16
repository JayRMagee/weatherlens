package csc325;

import com.google.gson.annotations.SerializedName;

/**
 * Class to hold data corresponding to a single period of a forecast.
 * For example, daily forecasts contain 14 half-day periods.
 * @author Jonathan Crain
 */
public class ForecastPeriod {
    
    /*
    Note that there are three values which contain nested values.
    probabilityOfPrecipitation, dewpoint, and relativeHumidity all have a nested
    structure. For example:
    "dewpoint": {
                    "unitCode": "wmoUnit:degC",
                    "value": -7.7777777777777777
                }
    I am not sure how best to handle this...
    For the time being, based on some internet skimming, I've made separate 
    objects for each of these. 
    But maybe this is too complicated? Maybe easier to just read these as 
    strings, and then parse the string for the value as necessary, e.g., by 
    using GSON or just basic String methods?
    */
    
    @SerializedName("number")
    private int number;
    
    @SerializedName("name")
    private String name;
    
    @SerializedName("startTime")
    private String startTime;
    
    @SerializedName("endTime")
    private String endTime;
    
    @SerializedName("isDaytime")
    private Boolean isDaytime;
    
    @SerializedName("temperature")
    private int temperature;
    
    @SerializedName("temperatureUnit")
    private String temperatureUnit;
    
    @SerializedName("temperatureTrend")
    private String temperatureTrend;
    
    //Contains nested values (unitCode, value). See note above.
    @SerializedName("probabilityOfPrecipitation")
    private ProbabilityOfPrecipitation probabilityOfPrecipitation;
    
    //Contains nested values (unitCode, value). See note above.
    @SerializedName("dewpoint")
    private Dewpoint dewpoint;
    
    //Contains nested values (unitCode, value). See note above.
    @SerializedName("relativeHumidity")
    private RelativeHumidity relativeHumidity;
    
    @SerializedName("windSpeed")
    private String windSpeed;
    
    @SerializedName("windDirection")
    private String windDirection;
    
    @SerializedName("icon")
    private String icon;
    
    @SerializedName("shortForecast")
    private String shortForecast;
    
    @SerializedName("detailedForecast")
    private String detailedForecast;

    /**
     * Parameterized constructor.
     * @param number
     * @param name
     * @param startTime
     * @param endTime
     * @param isDaytime
     * @param temperature
     * @param temperatureUnit
     * @param temperatureTrend
     * @param probabilityOfPrecipitation
     * @param dewpoint
     * @param relativeHumidity
     * @param windSpeed
     * @param windDirection
     * @param icon
     * @param shortForecast
     * @param detailedForecast 
     */
    public ForecastPeriod(int number, String name, String startTime, String endTime, Boolean isDaytime, int temperature, String temperatureUnit, String temperatureTrend, ProbabilityOfPrecipitation probabilityOfPrecipitation, Dewpoint dewpoint, RelativeHumidity relativeHumidity, String windSpeed, String windDirection, String icon, String shortForecast, String detailedForecast) {
        this.number = number;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDaytime = isDaytime;
        this.temperature = temperature;
        this.temperatureUnit = temperatureUnit;
        this.temperatureTrend = temperatureTrend;
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
        this.dewpoint = dewpoint;
        this.relativeHumidity = relativeHumidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.icon = icon;
        this.shortForecast = shortForecast;
        this.detailedForecast = detailedForecast;
    }

    /**
     * No arg constructor.
     */
    public ForecastPeriod() {
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Boolean getIsDaytime() {
        return isDaytime;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public String getTemperatureTrend() {
        return temperatureTrend;
    }

    public ProbabilityOfPrecipitation getProbabilityOfPrecipitation() {
        return probabilityOfPrecipitation;
    }

    public Dewpoint getDewpoint() {
        return dewpoint;
    }

    public RelativeHumidity getRelativeHumidity() {
        return relativeHumidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getIcon() {
        return icon;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public String getDetailedForecast() {
        return detailedForecast;
    }

}
