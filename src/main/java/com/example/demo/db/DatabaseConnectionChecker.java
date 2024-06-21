package com.example.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseConnectionChecker {

    @Autowired
    private DataSource dataSource;

    public boolean isConnected() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(2);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}