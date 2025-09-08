package org.example.demo5.service;

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
        try {
            Properties properties = new Properties();
            // Lấy input stream từ file db.properties
            InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");

            // Nếu không tìm thấy file, inputStream sẽ là null
            if (inputStream == null) {
                System.err.println("Lỗi: Không tìm thấy tệp db.properties!");
                return null;
            }

            properties.load(inputStream);

            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.username");
            PASSWORD = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            // In chi tiết lỗi để dễ dàng gỡ lỗi
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}