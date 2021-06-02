package com.nicomincuzzi.frameworkless.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgreSQLJDBC implements JDBCDriver {

    @Override
    public Connection connect() throws Exception {
        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();
        props.setProperty("user", "fred");
        props.setProperty("password", "secret");
        props.setProperty("ssl", "true");
        try {
            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
}
