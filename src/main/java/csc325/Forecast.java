package csc325;

import com.google.gson.annotations.SerializedName;

/**
 * Forecast class.
 * @author niarc
 */
public class Forecast {
    @SerializedName("updated")
    private String updated;
    
    @SerializedName("units")
    private String units;
    
    @SerializedName("forecastGenerator")
    private String forecastGenerator;
    
    @SerializedName("generatedAt")
    private String generatedAt;
    
    @SerializedName("updateTime")
    private String updateTime;
    
    @SerializedName("validTimes")
    private String validTimes;
    
    @SerializedName("elevation")
    private Elevation elevation;
    
    /*
    Will GSON be smart enough to create an array of the correct size based on 
    the number of periods in a forecast? Daily forecasts contain 14 periods, 
    hourly forecasts I'm not sure about (but the sample contains 156, which is 
    6.5 days).
    
    Hourly forcasts contain some number of periods, corresponding to one hour 
    intervales which run from startTime to endTime.
    
    Daily forecasts contain 14x 12-hour periods, running from 06:00:00 to 18:00:00
    (isDaytime == true) and from 18:00:00 to 06:00:00 (isDaytime == false).
    */
    @SerializedName("periods")
    private ForecastPeriod[] periods;

    /**
     * Parameterized constructor.
     * @param updated
     * @param units
     * @param forecastGenerator
     * @param generatedAt
     * @param updateTime
     * @param validTimes
     * @param elevation 
     */
    public Forecast(String updated, String units, String forecastGenerator, String generatedAt, String updateTime, String validTimes, Elevation elevation) {
        this.updated = updated;
        this.units = units;
        this.forecastGenerator = forecastGenerator;
        this.generatedAt = generatedAt;
        this.updateTime = updateTime;
        this.validTimes = validTimes;
        this.elevation = elevation;
    }

    /**
     * No arg constructor.
     */
    public Forecast() {
    }

    public String getUpdated() {
        return updated;
    }

    public String getUnits() {
        return units;
    }

    public String getForecastGenerator() {
        return forecastGenerator;
    }

    public String getGeneratedAt() {
        return generatedAt;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getValidTimes() {
        return validTimes;
    }

    public Elevation getElevation() {
        return elevation;
    }

    /*
    Possibly a little dangerous - array size will vary depending on forecast
    type. Caller will have to know better than to try to access something which 
    is out of bounds. Might be safer to make this class abstract and generate 
    hourly/daily forecasts after all, even if they differ only in the size of 
    the array.
    */
    public ForecastPeriod[] getPeriods() {
        return periods;
    }
    
}
