/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csc325;

/**
 * The User class represents a user of the WeatherLens application. It stores
 * information such as the user's first name, username, password, and home
 * zip code. This class provides getter and setter methods for accessing and
 * updating this information.
 * @author nicholasshah
 */
public class User {

    private String firstName;
    private String username;
    private String userPassword;
    private String homeZipCode;
    /*
    Suggestion: User should have a list or map of Location, representing their 
    previously accessed locations. e.g.,:
    ArrayList<Location> locations;
    or
    HashMap<String,Location> locations
    If we use a map, we can look up a location by its name. We can add String 
    name to Location, and then use the same as the key for the map. This would 
    let us look up specific locations, while if we wanted to iterate over all a 
    user's locations, we can use Map interface's .values().toArray() function
    */
    
    /**
     * Returns the home zip code of the User.
     * @return 
     */
    public String getHomeZipCode() {
        return homeZipCode;
    }

    public void setHomeZipCode(String homeZipCode) {
        this.homeZipCode = homeZipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public User(String userName, String firstName, String userPassword, String homeZipCode) {
        this.firstName = firstName;
        this.username = userName;
        this.userPassword = userPassword;
        this.homeZipCode = homeZipCode;
    }

    public User() {
        firstName = "";
        username = "";
        userPassword = "";
        homeZipCode = "";
    }

    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", username=" + username + ", userPassword=" + userPassword + ", homeZipCode=" + homeZipCode + '}';
    }
    

}
