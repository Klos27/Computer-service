package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import models.User;

public class UserDao extends DAOManager {
	
    public UserDao() {
    	super();
    }
    
    public User login(String email, String password) {
    	
        User user = null;
        
        try {
        	password = DigestUtils.sha256Hex(password);
        	
        	PreparedStatement ps = conn.prepareStatement(  
        			"select * from users where email=? and password=?");  
        	ps.setString(1, email);  
        	ps.setString(2, password);  
        			      
        	ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("role"));
            }
        			
        } catch (SQLException e) {
            System.err.println("Can't get the user!");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Error in hashing password!");
			e.printStackTrace();
            return null;
		} 
        
        return user;
        
    }   	
    
    public String register(String first_name, String last_name, String password, String email, int role) {
    	    	
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
            System.err.println("Error in inserting the user!");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Error in hashing password!");
			e.printStackTrace();
            return null;
		}
                
    }
    
}
