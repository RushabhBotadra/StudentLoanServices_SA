package com.studentloanservices.sls.service;

import com.studentloanservices.sls.dao.BankRepresentativeDAO;
import com.studentloanservices.sls.model.BankRepresentativeDetails;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankRepresentativeService {
	
	private final BankRepresentativeDAO bankRepresentativeDAO;
	
	@Autowired
    public BankRepresentativeService(BankRepresentativeDAO bankRepresentativeDAO) {
        this.bankRepresentativeDAO = bankRepresentativeDAO;
    }
	
	public List<BankRepresentativeDetails> getAllRepresentatives() {
        return bankRepresentativeDAO.findAllRepresentatives();
    }
}
