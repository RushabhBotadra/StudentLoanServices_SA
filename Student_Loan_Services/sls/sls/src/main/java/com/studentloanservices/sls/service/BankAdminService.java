// src/main/java/com/studentloanservices/sls/service/BankAdminService.java
package com.studentloanservices.sls.service;

import com.studentloanservices.sls.dao.BankAdminDAO;
import com.studentloanservices.sls.model.BankAdminDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAdminService {

    private final BankAdminDAO bankAdminDAO;

    @Autowired
    public BankAdminService(BankAdminDAO bankAdminDAO) {
        this.bankAdminDAO = bankAdminDAO;
    }


    public List<BankAdminDetails> getAllAdmins() {
        return bankAdminDAO.findAllAdmins();
    }
}