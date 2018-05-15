package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Services;

public class ServicesDao extends DAOManager{

	public ArrayList<Services> getAllServices(int requestId) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Services> result = new ArrayList<>();
        
        try {
            open();
            stmt = conn.createStatement();
            
            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `service_request_services` left join `services` on service_request_services.id_service = services.id where service_request_services.id_service_request = " + requestId);

            while(rs.next()) {
                result.add(new Services(rs.getInt("id"), rs.getString("name") , rs.getString("description"), rs.getFloat("service_price")));
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
}
