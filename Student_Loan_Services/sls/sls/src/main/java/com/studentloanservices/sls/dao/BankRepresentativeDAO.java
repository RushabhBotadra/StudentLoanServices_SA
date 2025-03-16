package com.studentloanservices.sls.dao;

import com.studentloanservices.sls.model.BankRepresentative;
import com.studentloanservices.sls.model.BankRepresentativeDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BankRepresentativeDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BankRepresentativeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertBankRepresentative(BankRepresentative representative) {
        String sql = "INSERT INTO BANK_REPRESENTATIVE (USER_ID) VALUES (?)";
        try {
            jdbcTemplate.update(sql, representative.getUserId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert bank representative for USER_ID " + representative.getUserId() + ": " + e.getMessage(), e);
        }
    }

  
    public List<BankRepresentativeDetails> findAllRepresentatives() {
        String sql = "SELECT br.EMPLOYEE_ID, br.USER_ID, u.USER_NAME, u.EMAIL, u.PHONE_NUMBER " +
                     "FROM BANK_REPRESENTATIVE br " +
                     "JOIN \"USER\" u ON br.USER_ID = u.USER_ID";
        return jdbcTemplate.query(sql, new BankRepresentativeRowMapper());
    }

    private static class BankRepresentativeRowMapper implements RowMapper<BankRepresentativeDetails> {
        @Override
        public BankRepresentativeDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            BankRepresentativeDetails rep = new BankRepresentativeDetails();
            rep.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
            rep.setUserId(rs.getInt("USER_ID"));
            rep.setUserName(rs.getString("USER_NAME"));
            rep.setEmail(rs.getString("EMAIL"));
            rep.setPhoneNumber(rs.getLong("PHONE_NUMBER"));
            return rep;
        }
    }
}