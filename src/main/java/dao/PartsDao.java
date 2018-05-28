package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Parts;

public class PartsDao extends DAOManager{

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
}
