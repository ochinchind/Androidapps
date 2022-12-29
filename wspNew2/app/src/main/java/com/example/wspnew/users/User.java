package com.example.wspnew.users;

import com.example.wspnew.activities.MainActivity;
import com.example.wspnew.enums.Gender;

public abstract class User {
    private String usertype;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Gender gender;

    public User(String usertype, String firstName, String lastName, String login, String password, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.usertype = usertype;
    }
    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", login=" + login + ", password=" + password
                + ", gender=" + gender + "]";
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Gender getGender() {
        return gender;
    }
    public String getUsertype() {
        return usertype;
    }
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
