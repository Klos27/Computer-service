package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Parts;

public class PartsDao extends DAOManager{

	// get all parts for specific requestId
	public ArrayList<Parts> getAllParts(int requestId) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Parts> result = new ArrayList<>();
        
        try {
            open();
            stmt = conn.createStatement();
            
            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `service_request_parts` left join `parts` on service_request_parts.id_part = parts.id where service_request_parts.id_service_request = " + requestId);

            while(rs.next()) {
                result.add(new Parts(rs.getInt("id"), rs.getString("name") , rs.getFloat("part_price")));
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

	// get all parts list in database
    public ArrayList<Parts> getAllParts() {
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<Parts> result = new ArrayList<>();

        try {
            open();
            stmt = conn.createStatement();

            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `parts`");

            while(rs.next()) {
                result.add(new Parts(rs.getInt("id"), rs.getString("name") , rs.getFloat("price")));
            }

        } catch (SQLException e) {
            System.err.println("Error at executing query");
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
    
 // get all parts list in database
    public ArrayList<Parts> getParts(int partId, String partName, float priceFrom, float priceTo) {
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<Parts> result = new ArrayList<>();

        // build query
        String query = "SELECT * FROM `parts` where name like \"%" + partName + "%\"";
        
        if(partId > 0)
        	query = query + " AND id = " + partId;
        
        if(priceFrom > 0. && priceTo > 0. && priceFrom > priceTo) {
        	float tmp = priceFrom;
        	priceFrom = priceTo;
        	priceTo = tmp;
        }
        
        if(priceFrom > 0. && priceTo > 0.)
        	query = query + " AND price BETWEEN " + priceFrom + " AND " + priceTo;
        else if(priceFrom > 0.)
        	query = query + " AND price > " + priceFrom;
        else if(priceTo > 0.)
        	query = query + " AND price < " + priceTo;
        
        try {
            open();
            stmt = conn.createStatement();

            // Execute operation
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                result.add(new Parts(rs.getInt("id"), rs.getString("name") , rs.getFloat("price")));
            }

        } catch (SQLException e) {
            System.err.println("Error at executing query");
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
    
    
    // get part info
	public Parts getPart(int partId) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        
        Parts result = null;
        
        try {
            open();
            stmt = conn.createStatement();
            
            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `parts` where id = " + partId);

            while(rs.next()) {
                result = new Parts(rs.getInt("id"), rs.getString("name") , rs.getFloat("price"));
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
    
    public String addPart(String partName, float partPrice) throws SQLException {
        try {
        	open();
            PreparedStatement ps = conn.prepareStatement(
                    "insert into parts(id, name, price) values (NULL, ?, ?)");
        	ps.setString(1, partName);
        	ps.setString(2, String.valueOf(partPrice));
           	ps.executeUpdate();
        	ps.close();
            return "DONE";      
            
        } catch (SQLException e) {
            System.err.println("Error while adding new part!");
            throw e;
        } catch (Exception e) {
            System.err.println("Error in PartsDao!");
            throw e;
		}
        finally {
        	close();
        }
    }
    public String updatePart(int partId, String partName, float partPrice) throws SQLException {
        try {
        	open();
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE parts SET name = '" + partName + "', price = '" + partPrice + "' where id = " + partId);
           	ps.executeUpdate();
        	ps.close();
            return "DONE";      
            
        } catch (SQLException e) {
            System.err.println("Error while adding new part!");
            throw e;
        } catch (Exception e) {
            System.err.println("Error in PartsDao!");
            throw e;
		}
        finally {
        	close();
        }
    }
    
    
}
