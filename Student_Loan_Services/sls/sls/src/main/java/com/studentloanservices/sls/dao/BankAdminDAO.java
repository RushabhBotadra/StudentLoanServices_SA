// src/main/java/com/studentloanservices/sls/dao/BankAdminDAO.java
package com.studentloanservices.sls.dao;

import com.studentloanservices.sls.model.BankAdminDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BankAdminDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BankAdminDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BankAdminDetails> findAllAdmins() {
        String sql = "SELECT ba.ADMIN_ID, ba.USER_ID, ba.AUTHORITY_LEVEL, u.USER_NAME, u.EMAIL, u.PHONE_NUMBER " +
                     "FROM BANK_ADMIN ba " +
                     "LEFT JOIN \"USER\" u ON ba.USER_ID = u.USER_ID";
        return jdbcTemplate.query(sql, new BankAdminRowMapper());
    }

    private static class BankAdminRowMapper implements RowMapper<BankAdminDetails> {
        @Override
        public BankAdminDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            BankAdminDetails admin = new BankAdminDetails();
            admin.setAdminId(rs.getInt("ADMIN_ID"));
            admin.setUserId(rs.getInt("USER_ID"));
            admin.setUserName(rs.getString("USER_NAME"));
            admin.setEmail(rs.getString("EMAIL"));
            admin.setPhoneNumber(rs.getLong("PHONE_NUMBER"));
            admin.setAuthorityLevel(rs.getString("AUTHORITY_LEVEL"));
            return admin;
        }
    }
}