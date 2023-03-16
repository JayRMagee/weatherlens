package csc325;

import com.google.gson.annotations.SerializedName;

/**
 * Holds multivalued (unit + value) data from probabilityOfPrecipitation field of ForecastPeriod.
 * @author Jonathan Crain
 */
public class ProbabilityOfPrecipitation {
    //Default is percent.
    @SerializedName("unitCode")
    private String unitCode;
    
    @SerializedName("value")
    private int precipitationProbability;

    /**
     * No arg constructor.
     */
    public ProbabilityOfPrecipitation() {
    }

    /**
     * Parameterized constructor.
     * @param unitCode
     * @param precipitationProbability 
     */
    public ProbabilityOfPrecipitation(String unitCode, int precipitationProbability) {
        this.unitCode = unitCode;
        this.precipitationProbability = precipitationProbability;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public int getPrecipitationProbability() {
        return precipitationProbability;
    }
    
}
