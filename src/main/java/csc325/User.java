/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csc325;

/**
 *
 * @author nicholasshah
 */
public class User {

    private String firstName;
    private String userName;
    private String userPassword;
    private String homeZipCode;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public User(String userName, String firstName, String userPassword, String homeZipCode) {
        this.firstName = firstName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.homeZipCode = homeZipCode;
    }

    public User() {
        firstName = "";
        userName = "";
        userPassword = "";
        homeZipCode = "";
    }

}
