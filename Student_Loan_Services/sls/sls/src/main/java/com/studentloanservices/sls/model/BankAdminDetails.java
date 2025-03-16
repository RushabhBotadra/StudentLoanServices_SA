// src/main/java/com/studentloanservices/sls/model/BankAdminDetails.java
package com.studentloanservices.sls.model;

public class BankAdminDetails {
    private int adminId;
    private int userId;
    private String userName;
    private String email;
    private long phoneNumber;
    private String authorityLevel;

    // Constructors
    public BankAdminDetails() {}

    public BankAdminDetails(int adminId, int userId, String userName, String email, long phoneNumber, String authorityLevel) {
        this.adminId = adminId;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authorityLevel = authorityLevel;
    }

    // Getters and Setters
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public String getAuthorityLevel() {
        return authorityLevel;
    }

    public void setAuthorityLevel(String authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    @Override
    public String toString() {
        return "BankAdminDetails{" +
                "adminId=" + adminId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", authorityLevel='" + authorityLevel + '\'' +
                '}';
    }
}