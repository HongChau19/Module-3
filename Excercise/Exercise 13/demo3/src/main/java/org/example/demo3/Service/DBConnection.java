package org.example.demo3.Service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    public DBConnection() {}

    public static Connection getConnection() {

        try{
            Properties properties = new Properties();
            InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);

            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.username");
            PASSWORD = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
