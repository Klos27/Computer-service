package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import models.Message;
import models.User;

public class AdminDao extends DAOManager {
	
    public AdminDao() {
    	super();
    }
    
    public String addWorker(String first_name, String last_name, String password, String email, int role) {
    	    	
        try {
        	
        	PreparedStatement ps = conn.prepareStatement(  
        			"select * from users where email=?");  
        	ps.setString(1, email);  
        			      
        	ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	return "E-mail is already in use!";
            }
            ps.close();

        	password = DigestUtils.sha256Hex(password);

        	ps = conn.prepareStatement(
                    "insert into users(first_name, last_name, password, email, role) values (?, ?, ?, ?, ?)");
        	ps.setString(1, first_name);
        	ps.setString(2, last_name);
        	ps.setString(3, password);
        	ps.setString(4, email);
        	ps.setInt(5, role);
        	ps.executeUpdate();
        	ps.close();
            
            return "DONE";      
            
        } catch (SQLException e) {
            System.err.println("Error in inserting the worker!");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Error in hashing password!");
			e.printStackTrace();
            return null;
		}
                
    }
    
}
