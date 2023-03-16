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
    I am not sure how best to handle this...maybe just read this in as a single
    string, then make use of GSON to parse a string by key when we need to get
    the data?
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
    private String probabilityOfPrecipitation;
    
    //Contains nested values (unitCode, value). See note above.
    @SerializedName("dewpoint")
    private String dewpoint;
    
    //Contains nested values (unitCode, value). See note above.
    @SerializedName("relativeHumidity")
    private String relativeHumidity;
    
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
    
}
