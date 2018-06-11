package dao;

import models.ServiceRequest;
import models.User;
import models.UserContract;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;

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
   
   public User getUser(int requestId) {
	   ServiceRequestDao serviceRequestDao = new ServiceRequestDao();
	   ServiceRequest request = serviceRequestDao.getServiceRequest(requestId);
	   if(request != null) {
		   try {
		       	User result = null;
		       	open();
		       	Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from users where id = " + request.getId_client());
		       	
		        if(rs.next()) {
		        	result = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("role"), rs.getString("address"), rs.getString("phone"));
		        }
		        rs.close();
		        close();
		        return result;        
	       } catch (SQLException e) {
	           System.err.println("Error in getting user info password!");
	           e.printStackTrace();
	           return null;
	       } catch (Exception e) {
	           System.err.println("Error in UserDao");
				e.printStackTrace();
	           return null;
	       }    
	   }
	   return null;
	}

	public ArrayList<UserContract> getUserContractsList()  {
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<UserContract> result = new ArrayList<>();

		try {
			open();
			stmt = conn.createStatement();

			// Execute operation
			rs = stmt.executeQuery(String.format("SELECT c.id, id_user, first_name, last_name, role, date_start, date_end, salary" +
					"  FROM `users_contracts` uc JOIN `users` u ON uc.id_user = u.id" +
					" JOIN `contracts` c ON uc.id_contract = c.id WHERE role > 0"));

			while (rs.next()) {
				int contractId = rs.getInt("id");
				int userId = rs.getInt("id_user");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int role = rs.getInt("role");
				Date dateStart = rs.getDate("date_start");
				Date dateEnd = rs.getDate("date_end");
				Double salary = rs.getDouble("salary");
				UserContract userContract = new UserContract(contractId, userId, firstName, lastName, role, dateStart, dateEnd, salary);
				result.add(userContract);
			}

		} catch (SQLException e) {
			System.err.println("Error at executing query");
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
