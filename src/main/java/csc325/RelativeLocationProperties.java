package csc325;

import com.google.gson.annotations.SerializedName;

/**
 * Corresponds to the "properties" of the "relativeLocation" within the 
 * properties returned by a request to the NWS API for metadata based upon 
 * lat/long coordinates. 
 * @author Jonathan Crain
 */
public class RelativeLocationProperties {
    @SerializedName("city")
    private String city;
    
    @SerializedName("state")
    private String state;
    
    /*
    Actually contains a distinct JSON object carrying 2 key-value pairs.
    However, I don't anticipate using this data, so for simplicity we can just
    read this in as a String instead of creating a new class.
    */
    @SerializedName("distance")
    private String distance;
    
    /*
    Likewise, also contains two nested key-value pairs, and likewise, I don't
    expect to use this data.
    */
    @SerializedName("bearing")
    private String value;

    /**
     * No arg constructor.
     */
    public RelativeLocationProperties() {
    }

    /**
     * Parameterized constructor.
     * @param city
     * @param state
     * @param distance
     * @param value 
     */
    public RelativeLocationProperties(String city, String state, String distance, String value) {
        this.city = city;
        this.state = state;
        this.distance = distance;
        this.value = value;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getDistance() {
        return distance;
    }

    public String getValue() {
        return value;
    }
    
}
