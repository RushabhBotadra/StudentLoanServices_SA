package com.studentloanservices.sls.model;

import java.time.LocalDate;

public class LoanApplication {
    private Long applicationId; // Changed from id to applicationId
    private LocalDate submittedDate;
    private String studentName;
    private String university;
    private String program;
    private double amountRequested;
    private String purpose;
    private String guarantorName;
    private String relationship;
    private String occupation;
    private double annualIncome;
    private String status;
    private int assigneeId; // Changed from bankRepId to assigneeId

    public LoanApplication() {
    }

    public LoanApplication(Long applicationId, LocalDate submittedDate, String studentName, String university, String program,
                           double amountRequested, String purpose, String guarantorName, String relationship,
                           String occupation, double annualIncome, String status, int assigneeId) {
        this.applicationId = applicationId;
        this.submittedDate = submittedDate;
        this.studentName = studentName;
        this.university = university;
        this.program = program;
        this.amountRequested = amountRequested;
        this.purpose = purpose;
        this.guarantorName = guarantorName;
        this.relationship = relationship;
        this.occupation = occupation;
        this.annualIncome = annualIncome;
        this.status = status;
        this.assigneeId = assigneeId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public double getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(double amountRequested) {
        this.amountRequested = amountRequested;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getGuarantorName() {
        return guarantorName;
    }

    public void setGuarantorName(String guarantorName) {
        this.guarantorName = guarantorName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    @Override
    public String toString() {
        return "LoanApplication [applicationId=" + applicationId + ", submittedDate=" + submittedDate + ", studentName=" + studentName +
               ", university=" + university + ", program=" + program + ", amountRequested=" + amountRequested +
               ", purpose=" + purpose + ", guarantorName=" + guarantorName + ", relationship=" + relationship +
               ", occupation=" + occupation + ", annualIncome=" + annualIncome + ", status=" + status +
               ", assigneeId=" + assigneeId + "]";
    }
}