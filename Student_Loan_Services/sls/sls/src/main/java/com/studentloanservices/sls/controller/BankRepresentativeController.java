package com.studentloanservices.sls.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentloanservices.sls.model.BankRepresentativeDetails;
import com.studentloanservices.sls.service.BankRepresentativeService;

@RestController
@RequestMapping("/api/bankRepresentative")
public class BankRepresentativeController {
	
	private final BankRepresentativeService service;

    @Autowired
    public BankRepresentativeController(BankRepresentativeService service) {
        this.service = service;
    }
    
	@GetMapping("/all")
    public ResponseEntity<List<BankRepresentativeDetails>> getAllRepresentatives() {
        try {
            List<BankRepresentativeDetails> representatives = service.getAllRepresentatives();
            return ResponseEntity.ok(representatives);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
