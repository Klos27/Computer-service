package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.User;

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
    
}
