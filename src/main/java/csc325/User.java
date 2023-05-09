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
 * @author Nicholas Shah
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

    /**
     * Sets the home zip code of the User.
     * @param homeZipCode 
     */
    public void setHomeZipCode(String homeZipCode) {
        this.homeZipCode = homeZipCode;
    }

    /**
     * Returns the first name of the User.
     * @return 
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the User.
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the username of the User.
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the User.
     * @param userName 
     */
    public void setUsername(String userName) {
        this.username = userName;
    }

    /**
     * Returns the password of the User.
     * @return 
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the password of the user.
     * @param userPassword 
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Parameterized constructor method for the User class.
     * @param userName
     * @param firstName
     * @param userPassword
     * @param homeZipCode 
     */
    public User(String userName, String firstName, String userPassword, String homeZipCode) {
        this.firstName = firstName;
        this.username = userName;
        this.userPassword = userPassword;
        this.homeZipCode = homeZipCode;
    }

    /**
     * Non-parameterized constructor method for the User class.
     */
    public User() {
        firstName = "";
        username = "";
        userPassword = "";
        homeZipCode = "";
    }

    /**
     * To string method which returns all values as comma-separated values.
     * @return 
     */
    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", username=" + username + ", userPassword=" + userPassword + ", homeZipCode=" + homeZipCode + '}';
    }
    

}
