package com.studentloanservices.sls.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.studentloanservices.sls.dao.LoanApplicationDAO;
import com.studentloanservices.sls.dao.UserDAO;
import com.studentloanservices.sls.model.LoanApplication;

@Service
public class LoanApplicationService {
	
	private final LoanApplicationDAO loanDAO;
	
	@Autowired
    public LoanApplicationService(UserDAO repDAO, LoanApplicationDAO loanDAO) {
        this.loanDAO = loanDAO;
    }
	
//	public List<LoanApplication> getDraftApplications() {
////        return loanDAO.findDraftApplications();
//		
//    }
	public List<LoanApplication> getDraftApplications() {
        try {
            List<LoanApplication> drafts = loanDAO.findDraftApplications();
            return drafts;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to Retrieve Draft Applications");
        }
    }

    public void assignApplication(Long applicationId, int assigneeId) {
    	try {
            int rowsAffected = loanDAO.assignToRepresentative(applicationId, assigneeId);
            if (rowsAffected != 1) {
                throw new RuntimeException("Failed to assign application to representative");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to assign application to representative");
        }
    }

//    public List<LoanApplication> getAssignedApplications() {
//        return loanDAO.findByAssigneeId();
//    }
    public List<LoanApplication> getAssignedApplications() {
        try {
            List<LoanApplication> applications = loanDAO.findByAssigneeId();
            return applications;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to Retrieve Applications Under Review");
        }
    }
    
    

//    public void approveApplication(Long applicationId, int assigneeId) {
//        loanDAO.updateStatus(applicationId, "APPROVED", assigneeId);
//    }
//
//    public void rejectApplication(Long applicationId, int assigneeId) {
//        loanDAO.updateStatus(applicationId, "REJECTED", assigneeId);
//    }
    public void updateStatus(Long applicationId, String status, int assigneeId) {
        try {
            int rowsAffected = loanDAO.updateStatus(applicationId, status, assigneeId);
            if (rowsAffected != 1) {
                throw new RuntimeException("Failed to update application status");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update application status:");
        }
    }
}
