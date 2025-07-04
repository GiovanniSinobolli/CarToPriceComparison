package com.example.pricetohpGraph;

import java.sql.*;

public class DBUtil {
    private static final String url = "jdbc:mysql://172.31.22.43:3306/Giovanni200589722";
    private static final String user = "Giovanni200589722";
    private static final String password = "CwL81sQH-W";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
