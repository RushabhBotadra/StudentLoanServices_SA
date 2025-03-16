// src/main/java/com/studentloanservices/sls/model/BankRepresentative.java
package com.studentloanservices.sls.model;

public class BankRepresentative {
    private int employeeId;
    private int userId;

    // Constructors
    public BankRepresentative() {}

    public BankRepresentative(int employeeId, int userId) {
        this.employeeId = employeeId;
        this.userId = userId;
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

    @Override
    public String toString() {
        return "BankRepresentative{" +
                "employeeId=" + employeeId +
                ", userId=" + userId +
                '}';
    }
}