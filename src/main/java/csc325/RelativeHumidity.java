package csc325;

import com.google.gson.annotations.SerializedName;

/**
 * Holds multivalued (unit + value) data from relativeHumidity field of ForecastPeriod.
 * @author Jonathan Crain
 */
public class RelativeHumidity {
    //Default is percent.
    @SerializedName("unitCode")
    private String unitCode;
    
    @SerializedName("value")
    private int relHumidity;

    /**
     * No arg constructor.
     */
    public RelativeHumidity() {
    }

    /**
     * Parameterized constructor.
     * @param unitCode
     * @param relHumidity 
     */
    public RelativeHumidity(String unitCode, int relHumidity) {
        this.unitCode = unitCode;
        this.relHumidity = relHumidity;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public int getRelHumidity() {
        return relHumidity;
    }
    
}
