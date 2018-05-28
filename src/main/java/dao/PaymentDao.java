package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Payment;
import models.ServiceRequest;

public class PaymentDao extends DAOManager {
    public Payment getPayment(int userId, int requestId) throws SQLException {
    	Statement stmt = null;
        ResultSet rs = null;
        
        Payment payment = null;
     
        try {
            open();
            stmt = conn.createStatement();
            
            // Execute operation
            rs = stmt.executeQuery("SELECT p.id, p.amount, p.status, p.creation_date, p.id_service_request FROM payments p LEFT JOIN service_request sr"
            		+ " ON p.id_service_request = sr.id where sr.id = " + requestId + " and sr.id_client =" + userId);
            if(rs.next())
            	payment = new Payment(rs.getInt("id"), rs.getFloat("amount"), rs.getInt("status"), rs.getDate("creation_date"), rs.getInt("id_service_request"));      
      
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
    
    public Payment getPaymentInvoice(int userId, int requestId, int invoiceId) throws SQLException {
    	Statement stmt = null;
        ResultSet rs = null;
        
        Payment payment = null;
     
        try {
            open();
            stmt = conn.createStatement();
            // Execute operation
            rs = stmt.executeQuery("SELECT p.id, p.amount, p.status, p.creation_date, p.id_service_request FROM payments p LEFT JOIN service_request sr"
            		+ " ON p.id_service_request = sr.id WHERE p.id = " + invoiceId + " AND sr.id_client = " + userId + " AND p.id_service_request = " + requestId);
            if(rs.next())
            	payment = new Payment(rs.getInt("id"), rs.getFloat("amount"), rs.getInt("status"), rs.getDate("creation_date"), rs.getInt("id_service_request"));      
      
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
    
    public ArrayList<Payment> getUnpaidInvoices() throws SQLException {
    	Statement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Payment> result = new ArrayList<>();
        try {
            open();
            stmt = conn.createStatement();
            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM payments p WHERE p.status = 0 ");
            while(rs.next()) {
                result.add(new Payment(rs.getInt("id"), rs.getFloat("amount"), rs.getInt("status"), rs.getDate("creation_date"), rs.getInt("id_service_request")));
            }
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
        return result ;
    }

	public int confirmPayment(int invoiceId) throws SQLException {
		Statement stmt = null;
        int result = 0;
        
        try {
            open();
            stmt = conn.createStatement();
            result = stmt.executeUpdate("UPDATE payments p SET status = '1' WHERE p.id =" + invoiceId);
        } catch (SQLException e) {
            System.err.println("Error at executing query");
            //e.printStackTrace();
        } finally {
            if (stmt != null) {
				stmt.close();
            }
            close();
        }
        return result ;
	}
}
