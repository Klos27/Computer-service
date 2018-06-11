package dao;

import models.Services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServicesDao extends DAOManager {

    public ArrayList<Services> getAllServices(int requestId) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<Services> result = new ArrayList<>();

        try {
            open();
            stmt = conn.createStatement();

            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `service_request_services` left join `services` on service_request_services.id_service = services.id where service_request_services.id_service_request = " + requestId);

            while (rs.next()) {
                result.add(new Services(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("service_price")));
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

    public ArrayList<Services> getAllServices() {
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<Services> result = new ArrayList<>();

        try {
            open();
            stmt = conn.createStatement();

            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `services`");

            while (rs.next()) {
                result.add(new Services(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price")));
            }

        } catch (SQLException e) {
            System.err.println("Error at executing query");
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public ArrayList<Services> getServices(int serviceIdInt, String serviceName, String serviceDescription,
                                           float priceFrom, float priceTo) {
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<Services> result = new ArrayList<>();

        // build query
        String query = "SELECT * FROM `services` where name like \"%" + serviceName + "%\" AND description like \"%" + serviceDescription + "%\"";

        if (serviceIdInt > 0)
            query = query + " AND id = " + serviceIdInt;

        if (priceFrom > 0. && priceTo > 0. && priceFrom > priceTo) {
            float tmp = priceFrom;
            priceFrom = priceTo;
            priceTo = tmp;
        }

        if (priceFrom > 0. && priceTo > 0.)
            query = query + " AND price BETWEEN " + priceFrom + " AND " + priceTo;
        else if (priceFrom > 0.)
            query = query + " AND price >= " + priceFrom;
        else if (priceTo > 0.)
            query = query + " AND price <= " + priceTo;

        try {
            open();
            stmt = conn.createStatement();

            // Execute operation
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                result.add(new Services(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price")));
            }

        } catch (SQLException e) {
            System.err.println("Error at executing query");
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public String addService(String name, String description, float priceFloat) throws SQLException {
        try {
            open();
            PreparedStatement ps = conn.prepareStatement(
                    "insert into services(id, name, description,  price) values (NULL, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, String.valueOf(priceFloat));
            ps.executeUpdate();
            ps.close();
            return "DONE";

        } catch (SQLException e) {
            System.err.println("Error while adding new part!");
            throw e;
        } catch (Exception e) {
            System.err.println("Error in PartsDao!");
            throw e;
        } finally {
            close();
        }
    }

    public Services getService(int serviceId) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;

        Services result = null;

        try {
            open();
            stmt = conn.createStatement();

            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `services` where id = " + serviceId);

            while (rs.next()) {
                result = new Services(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"));
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

    public String updateService(int serviceId, String name, String description, float price) throws SQLException {
        try {
            open();
            name = name.replace("'", "\\'");
            description = description.replace("'", "\\'");
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE services SET name = '" + name + "', description = '" + description + "', price = '" + price + "' where id = " + serviceId);
            ps.executeUpdate();
            ps.close();
            return "DONE";
        } catch (SQLException e) {
            System.err.println("Error while updating services!");
            throw e;
        } catch (Exception e) {
            System.err.println("Error in ServicesDao!");
            throw e;
        } finally {
            close();
        }
    }

    public double getCurrentServicePrice(int serviceId) {
        Statement stmt = null;
        ResultSet rs = null;

        Double result = null;

        try {
            open();
            stmt = conn.createStatement();

            // Execute operation
            rs = stmt.executeQuery("SELECT price FROM `services` where id = " + serviceId);

            if (rs.next()) {
                result = rs.getDouble("price");
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
