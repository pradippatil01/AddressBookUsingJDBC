package com.bridgelabz.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection getConnection() throws InvalidException {
        String jdbcURL = "jdbc:mysql://localhost:3306/AddressBook_Services?useSSL=false";
        String username = "root";
        String password = "root";
        Connection connection;
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            return connection;
        } catch (SQLException exception) {
            throw new InvalidException("SQL_CONNECTION_ERROR",
                    InvalidException.ExceptionType.SQL_EXCEPTION);
        }
    }
}
