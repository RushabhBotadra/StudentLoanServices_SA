package com.studentloanservices.sls.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentloanservices.sls.model.LoanApplication;
import com.studentloanservices.sls.service.LoanApplicationService;

@RestController
@RequestMapping("/api/application")
public class LoanApplicationController {
	
	private final LoanApplicationService loanService;

    public LoanApplicationController(LoanApplicationService loanService) {
        this.loanService = loanService;
    }
	    
	@GetMapping("/getDraftApplications")
    public ResponseEntity<Map<String, Object>> getDraftApplications() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<LoanApplication> drafts = loanService.getDraftApplications();
            response.put("success", true);
            response.put("message", "Draft applications retrieved successfully");
            response.put("data", drafts);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PostMapping("/assign/{applicationId}")
    public ResponseEntity<Map<String, Object>> assignApplication(
            @PathVariable Long applicationId,
            @RequestParam int assigneeId) {
//        loanService.assignApplication(applicationId, assigneeId);
//        return ResponseEntity.ok("Application assigned successfully");
            Map<String, Object> response = new HashMap<>();
            try {
                loanService.assignApplication(applicationId, assigneeId);
                response.put("success", true);
                response.put("message", "Application assigned to representative successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                response.put("success", false);
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } catch (RuntimeException e) {
                response.put("success", false);
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    
    @GetMapping("/getAssignedApplications")
    public ResponseEntity<Map<String, Object>> getAssignedApplications() {
//        return ResponseEntity.ok(loanService.getAssignedApplications());
            Map<String, Object> response = new HashMap<>();
            try {
                List<LoanApplication> applications = loanService.getAssignedApplications();
                response.put("success", true);
                response.put("message", "Applications under review retrieved successfully");
                response.put("data", applications);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (RuntimeException e) {
                response.put("success", false);
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @PostMapping("/approve/{applicationId}")
//    public ResponseEntity<String> approveApplication(@PathVariable Long applicationId,
//    												@RequestParam int assigneeId) {
//        loanService.approveApplication(applicationId, assigneeId);
//        return ResponseEntity.ok("Application approved successfully");
//    }
    public ResponseEntity<Map<String, Object>> approveApplication(
            @PathVariable Long applicationId,
            @RequestParam int assigneeId) {
        Map<String, Object> response = new HashMap<>();
        try {
            loanService.updateStatus(applicationId, "APPROVED", assigneeId);
            response.put("success", true);
            response.put("message", "Application approved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/reject/{applicationId}")
//    public ResponseEntity<String> rejectApplication(@PathVariable Long applicationId) {
//        loanService.rejectApplication(applicationId, 0);
//        return ResponseEntity.ok("Application rejected successfully");
//    }
    @PostMapping("/reject/{applicationId}")
    public ResponseEntity<Map<String, Object>> rejectApplication(
            @PathVariable Long applicationId) {
        Map<String, Object> response = new HashMap<>();
        try {
            loanService.updateStatus(applicationId, "REJECTED", 0);
            response.put("success", true);
            response.put("message", "Application rejected successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
