package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Payment;

public class PaymentDao extends DAOManager {
    public Payment getPayment(int userId, int requestId) throws SQLException {
    	Statement stmt = null;
        ResultSet rs = null;
        
        Payment payment = null;
     
        try {
            open();
            stmt = conn.createStatement();
            
            // Execute operation
            rs = stmt.executeQuery("SELECT p.id, p.amount, p.status FROM payments p LEFT JOIN service_request sr"
            		+ " ON p.id_service_request = sr.id where sr.id = " + requestId + " and sr.id_client =" + userId);
            if(rs.next())
            	payment = new Payment(rs.getInt("id"), rs.getFloat("amount"), rs.getInt("status"));      
      
        } catch (SQLException e) {
            System.err.println("Error at executing query");
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            close();
        }
        return payment ;
    }
}
