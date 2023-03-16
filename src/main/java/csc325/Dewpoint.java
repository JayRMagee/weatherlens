package csc325;

import com.google.gson.annotations.SerializedName;

/**
 * Holds multivalued (unit + value) data from dewpoint field of ForecastPeriod.
 * @author Jonathan Crain
 */
public class Dewpoint {
    //Default: degrees C.
    @SerializedName("unitCode")
    private String unitCode;
    
    @SerializedName("value")
    private double dewpoint;

    /**
     * No arg constructor.
     */
    public Dewpoint() {
    }

    /**
     * Parameterized constructor.
     * @param unitCode
     * @param dewpoint 
     */
    public Dewpoint(String unitCode, double dewpoint) {
        this.unitCode = unitCode;
        this.dewpoint = dewpoint;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public double getDewpoint() {
        return dewpoint;
    }
    
}
