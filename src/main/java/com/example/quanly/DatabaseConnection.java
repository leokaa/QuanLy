package com.example.quanly;
import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.String;
public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){

        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost:3306/user";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
