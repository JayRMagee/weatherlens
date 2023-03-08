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

}
