package org.example.demo1jdbc.services;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
    private static final String URL = "jdbc:mysql://localhost:3306/demoDB";
    private static final String USER = "root";
    private static final String PASSWORD = "Mrdarcy1!mrdarcy";

    public static Connection getConnection() {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
