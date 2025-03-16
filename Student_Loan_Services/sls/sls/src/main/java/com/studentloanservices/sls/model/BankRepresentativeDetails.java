// src/main/java/com/studentloanservices/sls/model/BankRepresentativeDetails.java
package com.studentloanservices.sls.model;

public class BankRepresentativeDetails {
    private int employeeId;
    private int userId;
    private String userName;
    private String email;
    private long phoneNumber;

    // Constructors
    public BankRepresentativeDetails() {}

    public BankRepresentativeDetails(int employeeId, int userId, String userName, String email, long phoneNumber) {
        this.employeeId = employeeId;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "BankRepresentativeDetails{" +
                "employeeId=" + employeeId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}