package csc325;

import com.google.gson.annotations.SerializedName;

/**
 * Corresponds to the "properties" section of the JSON returned by a request
 * to the NWS API for metadata based upon lat/long coordinates.
 * @author Jonathan Crain
 */
public class LocationProperties {
    @SerializedName("@id")
    private String id;
    
    @SerializedName("@type")
    private String type;
    
    @SerializedName("cwa")
    private String cwa;
    
    @SerializedName("forecastOffice")
    private String forecastOfficeURL;
    
    @SerializedName("gridId")
    private String gridID;
    
    @SerializedName("gridX")
    private int gridX;
    
    @SerializedName("gridY")
    private int gridY;
    
    @SerializedName("forecast")
    private String forecastDailyURL;
    
    @SerializedName("forecastHourly")
    private String forecastHourlyURL;
    
    @SerializedName("forecastGridData")
    private String forecastGridDataURL;
    
    @SerializedName("observationStations")
    private String observationStationsURL;
    
    @SerializedName("relativeLocation")
    private RelativeLocation relativeLocation;
    
    @SerializedName("forecastZone")
    private String forecastZoneURL;
    
    @SerializedName("county")
    private String countyURL;
    
    @SerializedName("fireWeatherZone")
    private String fireWeatherZoneURL;
    
    @SerializedName("timeZone")
    private String timeZone;
    
    @SerializedName("radarStation")
    private String radarStation;

    /**
     * No arg constructor.
     */
    public LocationProperties() {
    }

    /**
     * Parameterized constructor.
     * @param id
     * @param type
     * @param cwa
     * @param forecastOfficeURL
     * @param gridID
     * @param gridX
     * @param gridY
     * @param forecastURL
     * @param forecastHourlyURL
     * @param forecastGridDataURL
     * @param observationStationsURL
     * @param relativeLocation
     * @param forecastZoneURL
     * @param countyURL
     * @param fireWeatherZoneURL
     * @param timeZone
     * @param radarStation 
     */
    public LocationProperties(String id, String type, String cwa, String forecastOfficeURL, String gridID, int gridX, int gridY, String forecastDailyURL, String forecastHourlyURL, String forecastGridDataURL, String observationStationsURL, RelativeLocation relativeLocation, String forecastZoneURL, String countyURL, String fireWeatherZoneURL, String timeZone, String radarStation) {
        this.id = id;
        this.type = type;
        this.cwa = cwa;
        this.forecastOfficeURL = forecastOfficeURL;
        this.gridID = gridID;
        this.gridX = gridX;
        this.gridY = gridY;
        this.forecastDailyURL = forecastDailyURL;
        this.forecastHourlyURL = forecastHourlyURL;
        this.forecastGridDataURL = forecastGridDataURL;
        this.observationStationsURL = observationStationsURL;
        this.relativeLocation = relativeLocation;
        this.forecastZoneURL = forecastZoneURL;
        this.countyURL = countyURL;
        this.fireWeatherZoneURL = fireWeatherZoneURL;
        this.timeZone = timeZone;
        this.radarStation = radarStation;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCwa() {
        return cwa;
    }

    public String getForecastOfficeURL() {
        return forecastOfficeURL;
    }

    public String getGridID() {
        return gridID;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public String getDailyForecastURL() {
        return forecastDailyURL;
    }

    public String getForecastHourlyURL() {
        return forecastHourlyURL;
    }

    public String getForecastGridDataURL() {
        return forecastGridDataURL;
    }

    public String getObservationStationsURL() {
        return observationStationsURL;
    }

    public RelativeLocation getRelativeLocation() {
        return relativeLocation;
    }

    public String getForecastZoneURL() {
        return forecastZoneURL;
    }

    public String getCountyURL() {
        return countyURL;
    }

    public String getFireWeatherZoneURL() {
        return fireWeatherZoneURL;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getRadarStation() {
        return radarStation;
    }
            
}
