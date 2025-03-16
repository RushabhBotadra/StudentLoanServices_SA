package com.studentloanservices.sls.dao;

import com.studentloanservices.sls.model.User;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAO {
    
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> rowMapper = (rs, rowNum) -> {
        User rep = new User();
        rep.setUserId(rs.getInt("USER_ID"));
        rep.setUserName(rs.getString("USER_NAME"));
        rep.setEmail(rs.getString("EMAIL"));
//        rep.setPassword(rs.getString("PASSWORD")); // Stored as encrypted
        rep.setPassword(rs.getString("PASSWORD"));
        rep.setPhoneNumber(rs.getLong("PHONE_NUMBER"));
        return rep;
    };

    public int save(User rep) {
        String sql = "INSERT INTO \"USER\" (USER_NAME, EMAIL, PASSWORD, PHONE_NUMBER) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, rep.getUserName(), rep.getEmail(), rep.getPassword(), rep.getPhoneNumber());
    }
    
    public int insertBankRepresentative(int userId) {
        String sql = "INSERT INTO BANK_REPRESENTATIVE (USER_ID) VALUES (?)";
        try {
            return jdbcTemplate.update(sql, userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert bank representative for USER_ID " + userId + ": " + e.getMessage(), e);
        }
    }

    public User findByUserName(String userName) {
        String sql = "SELECT * FROM \"USER\" WHERE USER_NAME = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, userName);
    }
    
    public User findByEmail(String email) {
        String sql = "SELECT * FROM \"USER\" WHERE EMAIL = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, email);
    }
}