// src/main/java/com/studentloanservices/sls/model/BankAdmin.java
package com.studentloanservices.sls.model;

public class BankAdmin {
    private int adminId;
    private int userId;
    private String authorityLevel;

    // Constructors
    public BankAdmin() {}

    public BankAdmin(int adminId, int userId, String authorityLevel) {
        this.adminId = adminId;
        this.userId = userId;
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

    public String getAuthorityLevel() {
        return authorityLevel;
    }

    public void setAuthorityLevel(String authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    @Override
    public String toString() {
        return "BankAdmin{" +
                "adminId=" + adminId +
                ", userId=" + userId +
                ", authorityLevel='" + authorityLevel + '\'' +
                '}';
    }
}