// src/main/java/com/studentloanservices/sls/controller/BankAdminController.java
package com.studentloanservices.sls.controller;

import com.studentloanservices.sls.model.BankAdminDetails;
import com.studentloanservices.sls.service.BankAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bankAdmin")
public class BankAdminController {

    private final BankAdminService bankAdminService;

    @Autowired
    public BankAdminController(BankAdminService bankAdminService) {
        this.bankAdminService = bankAdminService;
    }

    @GetMapping("/getBankAdmins")
    public ResponseEntity<List<BankAdminDetails>> getAllBankAdmins() {
        try {
            List<BankAdminDetails> admins = bankAdminService.getAllAdmins();
            return ResponseEntity.ok(admins);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}