package csc325;

import java.io.File;
import kong.unirest.Unirest;

/**
 *
 * @author Jonathan Crain
 */
public class TestLocation {

    String name;
    String street;
    String city;
    String state;
    String zip;
    String countryCode;
    //Latitude and longitude (decimal degrees)
    double lat;
    double lon;
    //NWS grid points
    double gridX;
    double gridY;
    //OpenWeather API key
    String apiKey = "2659eca765c2a2a2a34ebdbaf6c16a29";

    /**
     * Default (no arg) constructor.
     */
    public TestLocation() {
    }

    /*User enters location in GUI. Upon pressing button, triggers method to parse string and pass information to this class to construct the location.
    Should create several overloaded methods to handle different combinations of location information (since user may e.g., only enter city and state).*/
    public TestLocation(String name, String street, String city, String state, String zip, String countryCode) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.countryCode = countryCode;
        //TODO - complete this constructor
        //fetchCoordinates();
        //createForecasts(); //fetches forecasts and maps JSON to objects
    }

    /*Should throw error if coords or gridpoints could not be fetched*/
    public void geocodeCity() {
        /*
        String geocodeRouteURL = "http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}";
        File result = Unirest.get(geocodeRouteURL)
                            .routeParam("city name", city)
                            .routeParam("state code", state)
                            .routeParam("country code", countryCode)
                            .routeParam("limit", "1")
                            .routeParam("API key", apiKey)
                            .asFile("./result.txt")
                            .getBody();
        */
        
        String baseURL = "http://api.openweathermap.org/geo/1.0/direct?";
        File result = Unirest.get(baseURL)
                             .queryString("q", city + "," + state + "," + countryCode)
                             .queryString("appid", apiKey)
                             .asFile("./result.txt")
                             .getBody();
    }
    
    public void geocodeZIP(){
        
        String baseZipURL = "http://api.openweathermap.org/geo/1.0/zip?";
        File result = Unirest.get(baseZipURL)
                             .queryString("zip", zip + "," + countryCode)
                             .queryString("appid", apiKey)
                             .asFile("./result.txt")
                             .getBody();
        
        /*
        String manualURL = "http://api.openweathermap.org/geo/1.0/zip?zip="
                            + zip + "," + countryCode + "&appid=" + apiKey;
        File result = Unirest.get(manualURL)
                            .asFile("./result.txt")
                            .getBody();
        */
    }
    
    public void censusGeocodeZIP(){
        String baseURL = "https://geocoding.geo.census.gov/geocoder/locations/address";
        String benchmark = "Public_AR_Current";
        String format = "json";
        File result = Unirest.get(baseURL)
                              .queryString("street", street)
                              .queryString("zip", zip)
                              .queryString("benchmark", benchmark)
                              .queryString("format", format)
                              .asFile("./result.txt")
                              .getBody();
    }
    
    public void censusGeocodeCityState(){
        String baseURL = "https://geocoding.geo.census.gov/geocoder/locations/address";
        String benchmark = "Public_AR_Current";
        String format = "json";
        File result = Unirest.get(baseURL)
                              .queryString("street", street)
                              .queryString("city", city)
                              .queryString("state", state)
                              .queryString("benchmark", benchmark)
                              .queryString("format", format)
                              .asFile("./result.txt")
                              .getBody();
    }
    
    public void censusGeoCodeFullAddress(){
        String baseURL = "https://geocoding.geo.census.gov/geocoder/locations/address";
        String benchmark = "Public_AR_Current";
        String format = "json";
        File result = Unirest.get(baseURL)
                              .queryString("street", street)
                              .queryString("city", city)
                              .queryString("state", state)
                              .queryString("zip", zip)
                              .queryString("benchmark", benchmark)
                              .queryString("format", format)
                              .asFile("./result.txt")
                              .getBody();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Location{" + "name=" + name + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + ", countryCode=" + countryCode + ", lat=" + lat + ", lon=" + lon + ", gridX=" + gridX + ", gridY=" + gridY + '}';
    }

}
