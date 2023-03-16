package csc325;

/**
 * 
 * @author Jonathan Crain
 */
public class Location {
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private double lat;
    private double lon;
    private LocationProperties properties;
    private Forecast dailyForecast;
    private Forecast hourlyForecast;

    
    public Location() {
    }

    public Location(String street, String city, String state, String zip, double lat, double lon) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.lat = lat;
        this.lon = lon;
        //Get coordinates from geocoder.
        //Get gridpoints from https://api.weather.gov/points/{lat},{lon} --> get properites --> read into LocationProperties object
            //name = relativeLocation city and state
        //Get forecasts
            //dailyForecast = fetch LocationProperties.forecastDailyURL
    }
    
    
    
    
    
}
