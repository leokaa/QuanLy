package com.example.quanly;
import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.String;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String HOST = "localhost";
    private static int POST = 3306;
    private static String DB_name = "quanly1";
    private static String User = "root";
    private static String Pass = "";

    private static Connection connection;

    public static Connection getConnect() throws SQLException {
        connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s", HOST, DB_name), User, Pass);
        return connection;
    }
}
