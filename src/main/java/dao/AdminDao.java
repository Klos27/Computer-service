package dao;

import models.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdminDao extends DAOManager {
	
    public AdminDao() {
    	super();
    }
    
    public String addWorker(String first_name, String last_name, String password, String email, int role) {
    	    	
        try {
            open();
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
		} finally {
            close();
        }
                
    }
    
	public List<User> showAllUsers(String filter) {
		
		
        List<User> result = new ArrayList<>();
        
        try {
            open();
            PreparedStatement ps;
            
            if(filter != null && (filter.equals("0")|| filter.equals("3") || filter.equals("2"))) {
            	ps = conn.prepareStatement(
	            		"SELECT * FROM users where role = ?");
	            ps.setString(1, filter);
            } else {
	            ps = conn.prepareStatement(
	            		"SELECT * FROM users where role != 1"); 	
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("role"), rs.getString("address"), rs.getString("phone")));
            }
        } catch (SQLException e) {
            System.err.println("Couldnt get users!");
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
        return result;
	}
    
	public String setRole(String id, String role ) {
	    	
		try {
			open();
			PreparedStatement ps = conn.prepareStatement(  
				"update users set role = ? where id = ?");  
	        	
			ps.setString(1, role);  
			ps.setString(2, id);  

			int results = ps.executeUpdate();
			if(results > 0) {
				return "DONE";
			}
	        
			ps.close();
	  
			return null;
	                        
		} catch (SQLException e) {
			System.err.println("Error in changing role!");
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}


	public String endContract(int contractId) {

		try {

			String dateStr = java.time.LocalDate.now()+"";
			open();
			PreparedStatement ps = conn.prepareStatement(
					String.format("update `contracts` set date_end = '%s' where id = %s", dateStr, contractId ));




			int results = ps.executeUpdate();
			if(results > 0) {
				return "DONE";
			}

			ps.close();

			return null;

		} catch (SQLException e) {
			System.err.println("Error while ending contract!");
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}
}
