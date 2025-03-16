package com.studentloanservices.sls.dao;

import com.studentloanservices.sls.model.LoanApplication;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class LoanApplicationDAO {
    
    private final JdbcTemplate jdbcTemplate;

    public LoanApplicationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<LoanApplication> rowMapper = (rs, rowNum) -> {
        LoanApplication loan = new LoanApplication();
        loan.setApplicationId(rs.getLong("APPLICATION_ID")); // Changed from ID
        Date sqlDate = rs.getDate("SUBMITTED_DATE");
        if (sqlDate != null) {
            loan.setSubmittedDate(sqlDate.toLocalDate());
        }
        loan.setStudentName(rs.getString("STUDENT_NAME"));
        loan.setUniversity(rs.getString("UNIVERSITY"));
        loan.setProgram(rs.getString("PROGRAM"));
        loan.setAmountRequested(rs.getDouble("AMOUNT_REQUESTED"));
        loan.setPurpose(rs.getString("PURPOSE"));
        loan.setGuarantorName(rs.getString("GUARANTOR_NAME"));
        loan.setRelationship(rs.getString("RELATIONSHIP"));
        loan.setOccupation(rs.getString("OCCUPATION"));
        loan.setAnnualIncome(rs.getDouble("ANNUAL_INCOME"));
        loan.setStatus(rs.getString("STATUS"));
        loan.setAssigneeId(rs.getInt("ASSIGNEE_ID")); 
        return loan;
    };

    public List<LoanApplication> findDraftApplications() {
        String sql = "SELECT * FROM LOAN_APPLICATIONS WHERE STATUS = 'DRAFT'";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<LoanApplication> findByAssigneeId() { // Changed from bankRepId
        String sql = "SELECT * FROM LOAN_APPLICATIONS WHERE STATUS = 'UNDER_REVIEW' ";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int assignToRepresentative(Long applicationId, int assigneeId) {
        String sql = "UPDATE LOAN_APPLICATIONS SET ASSIGNEE_ID = ?, STATUS = 'UNDER_REVIEW' WHERE APPLICATION_ID = ?"; 
        return jdbcTemplate.update(sql, assigneeId, applicationId);
    }

    public int updateStatus(Long applicationId, String status, int assigneeId) {
        String sql = "UPDATE LOAN_APPLICATIONS SET ASSIGNEE_ID = ?, STATUS = ? WHERE APPLICATION_ID = ?";
        return jdbcTemplate.update(sql, assigneeId, status, applicationId.longValue());
    }
}