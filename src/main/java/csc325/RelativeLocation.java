package csc325;

import com.google.gson.annotations.SerializedName;

/**
 * Corresponds to the "relativeLocation" within the properties returned by a request
 * to the NWS API for metadata based upon lat/long coordinates. 
 * @author Jonathan Crain
 */
public class RelativeLocation {
    @SerializedName("type")
    private String type;
    
    /*
    Contains nested JSON, i.e., "type" and "coordinates". Nominally should be 
    an object unto itself, but I don't think we'll actually use this data.
    */
    @SerializedName("geometry")
    private String geometry;
    
    @SerializedName("properties")
    private RelativeLocationProperties properties;

    /**
     * No arg constructor.
     */
    public RelativeLocation() {
    }

    /**
     * Parameterized constructor.
     * @param type
     * @param geometry
     * @param properties 
     */
    public RelativeLocation(String type, String geometry, RelativeLocationProperties properties) {
        this.type = type;
        this.geometry = geometry;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public String getGeometry() {
        return geometry;
    }

    public RelativeLocationProperties getProperties() {
        return properties;
    }
    
    
}
