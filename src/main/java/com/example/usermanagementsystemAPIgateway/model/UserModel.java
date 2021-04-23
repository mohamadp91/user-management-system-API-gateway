package com.example.usermanagementsystemAPIgateway.model;

public class UserModel {

    private long id;
    private String firstName;
    private String lastName;
    private String creationTime;
    private String emailAddress;

    public UserModel() {
    }

    public UserModel(String firstName, String lastName, String creationTime, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationTime = creationTime;
        this.emailAddress = emailAddress;
    }

    public UserModel(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}


