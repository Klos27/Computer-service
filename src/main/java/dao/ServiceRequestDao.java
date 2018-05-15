package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.User;
import models.ServiceRequest;

public class ServiceRequestDao extends DAOManager {
	
    public ServiceRequestDao() {
    	super();
    }
    
    public String createNewRequest(User user, String computer_producer, String computer_serial, String computer_description) {
    	    	
        try {

            PreparedStatement ps = conn.prepareStatement(
                    "insert into service_request(id, id_client, description, status, start_date, end_date) values (NULL, ?, ?, '0', NOW(), NULL)");
        	ps.setString(1, String.valueOf(user.getId()));
        	ps.setString(2, computer_producer + "\n" + computer_serial + "\n" + computer_description);
           	ps.executeUpdate();
        	ps.close();
            
            return "DONE";      
            
        } catch (SQLException e) {
            System.err.println("Error in inserting new service request!");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Error in ServiceRequestDao!");
			e.printStackTrace();
            return null;
		}
                
    }
    
    public ArrayList<ServiceRequest> getAllServiceRequests(int userID) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        
        ArrayList<ServiceRequest> result = new ArrayList<>();
        
        try {
            open();
            stmt = conn.createStatement();
            
            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `service_request` where id_client = " + userID + " order by id desc");

            while(rs.next()) {
                result.add(new ServiceRequest(rs.getInt("id"), rs.getInt("id_client") , rs.getString("description"), rs.getInt("status"), rs.getDate("start_date"), rs.getDate("end_date")));
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
        return result;
    }
    
    public ServiceRequest getServiceRequest(int userId, int requestId) throws SQLException {
    	Statement stmt = null;
        ResultSet rs = null;
        
        ServiceRequest serviceRq = null;
     
        try {
            open();
            stmt = conn.createStatement();
            
            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `service_request` where id = " + requestId + " and id_client = " + userId);
            if(rs.next())
            	serviceRq = new ServiceRequest(rs.getInt("id"), rs.getInt("id_client") , rs.getString("description"), rs.getInt("status"), rs.getDate("start_date"), rs.getDate("end_date"));      
      
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
        return serviceRq ;
    }
}
