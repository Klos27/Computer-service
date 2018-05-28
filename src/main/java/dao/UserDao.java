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
                user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("role"), rs.getString("address"), rs.getString("phone"));
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
    
    public User changeDetails(int id, String first_name, String last_name, String address, String phone) {
    	
        try {
        	
        	PreparedStatement ps = conn.prepareStatement(  
        			"update users set first_name = ?, last_name = ?, address = ?, phone = ? where id = ?");  
        	ps.setString(1, first_name);  
        	ps.setString(2, last_name);  
        	ps.setString(3, address);  
        	ps.setString(4, phone);  
        	ps.setInt(5, id);  

        	int results = ps.executeUpdate();
            ps.close();
            
            if(results > 0) {
            	
            	User user = null;
            	
            	ps = conn.prepareStatement(  
            			"select * from users where id = ?");  
            	ps.setInt(1, id);  
            			      
            	ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("role"), rs.getString("address"), rs.getString("phone"));
                }
            	
            	return user;

            } else {
            	return null;
            }            
                        
        } catch (SQLException e) {
            System.err.println("Error in changing details!");
            e.printStackTrace();
            return null;
        }
                
    }
    
   public String changePassword(int id, String password, String new_password) {
    	
        try {
        	
        	password = DigestUtils.sha256Hex(password);
        	new_password = DigestUtils.sha256Hex(new_password);
        	
        	PreparedStatement ps = conn.prepareStatement(  
        			"select * from users where id = ? and password = ?");  
        	ps.setInt(1, id);  
        	ps.setString(2,  password);	
        	
        	ResultSet rs = ps.executeQuery();            
            if(rs.next()) {
            	
            	ps = conn.prepareStatement(  
            			"update users set password = ? where id = ?");  
            	ps.setString(1, new_password);  
            	ps.setInt(2, id);  

            	int results = ps.executeUpdate();
            	if(results > 0) {
            		return "DONE";
            	}
            }
        	ps.close();
  
            return null;
                        
        } catch (SQLException e) {
            System.err.println("Error in changing details!");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Error in hashing password!");
			e.printStackTrace();
            return null;
		}
                
    }
   
   public String isEmail(String email) {
   	
       try {
       	
       	PreparedStatement ps = conn.prepareStatement(  
       			"select * from users where email = ?");  
       	ps.setString(1, email);  
       	
       	ResultSet rs = ps.executeQuery();            
        if(rs.next()) {
           	return "DONE";
        }
       	ps.close();  
       	return null;
           
       } catch (SQLException e) {
           System.err.println("Error in inserting the user!");
           e.printStackTrace();
           return null;
       }
               
   }
   
   public String setNewPassword(String email, String new_password) {
   	
       try {
       	
       	new_password = DigestUtils.sha256Hex(new_password);
       	
       	PreparedStatement ps = conn.prepareStatement(  
       			"select * from users where email = ?");  
       	ps.setString(1, email);  
       	
       	ResultSet rs = ps.executeQuery();            
           if(rs.next()) {
           	
           	ps = conn.prepareStatement(  
           			"update users set password = ? where email = ?");  
           	ps.setString(1, new_password);  
           	ps.setString(2, email);  

           	int results = ps.executeUpdate();
           	if(results > 0) {
           		return "DONE";
           	}
           }
       	ps.close();
 
           return null;
                       
       } catch (SQLException e) {
           System.err.println("Error in setting new password!");
           e.printStackTrace();
           return null;
       } catch (Exception e) {
           System.err.println("Error in hashing password!");
			e.printStackTrace();
           return null;
		}
               
   }
    
}
