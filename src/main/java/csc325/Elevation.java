package csc325;

import com.google.gson.annotations.SerializedName;

/**
 * Class to hold elevation field from Forecast properties.
 * @author Jonathan Crain
 */
public class Elevation {
    //Default is meters.
    @SerializedName("unitCode")
    private String unitCode;
    
    @SerializedName("value")
    private double altitude;

    /**
     * No arg constructor.
     */
    public Elevation() {
    }

    /**
     * Parameterized constructor.
     * @param unitCode
     * @param altitude 
     */
    public Elevation(String unitCode, double altitude) {
        this.unitCode = unitCode;
        this.altitude = altitude;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public double getAltitude() {
        return altitude;
    }

    
    
    
}
