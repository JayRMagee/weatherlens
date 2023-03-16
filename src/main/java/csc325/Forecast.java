package csc325;

import com.google.gson.annotations.SerializedName;

/**
 * Abstract Forecast class.
 * @author niarc
 */
public abstract class Forecast {
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
    
}
