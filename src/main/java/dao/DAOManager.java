package dao;

import java.sql.*;

public class DAOManager {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/computer_service?useUnicode=yes&characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    protected Connection conn;
 	
    public DAOManager() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("No Mysql driver!");
            e.printStackTrace();
        }
    }  
    
    public void open() {
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Can't estabilish connection!");
            e.printStackTrace();
        }    
    } 

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Can't close connection!");
            e.printStackTrace();
        }
    }

}
