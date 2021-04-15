package com.eugeneze.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBPool implements ConnectionPool {

    String user;
    String url;
    String password;

    public DBPool(String user, String url, String password) {
        this.user = user;
        this.url = url;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
