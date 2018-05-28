package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Employee;
import models.ServiceRequest;
import models.User;
import models.ServiceRequest;

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
    
    public ArrayList<ServiceRequest> getAllServiceRequests(int userID) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        
        ArrayList<ServiceRequest> result = new ArrayList<>();
        
        try {
            open();
            stmt = conn.createStatement();
            
            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `service_request` where id_client = " + userID + " order by id desc");

            while(rs.next()) {
                result.add(new ServiceRequest(rs.getInt("id"), rs.getInt("id_client") , rs.getString("description"), rs.getInt("status"), rs.getDate("start_date"), rs.getDate("end_date")));
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
    
    public ServiceRequest getServiceRequest(int userId, int requestId) throws SQLException {
    	Statement stmt = null;
        ResultSet rs = null;
        
        ServiceRequest serviceRq = null;
     
        try {
            open();
            stmt = conn.createStatement();
            
            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `service_request` where id = " + requestId + " and id_client = " + userId);
            if(rs.next())
            	serviceRq = new ServiceRequest(rs.getInt("id"), rs.getInt("id_client") , rs.getString("description"), rs.getInt("status"), rs.getDate("start_date"), rs.getDate("end_date"));      
      
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
        return serviceRq ;
    }
    public List<ServiceRequest> showNewRequests() {
        List<ServiceRequest> requests = new ArrayList<>();
        try {
            open();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from service_request where status = 0");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_clients = rs.getInt("id_client");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                Date start_date = rs.getDate("start_date");
                Date end_date = rs.getDate("end_date");
                ServiceRequest request = new ServiceRequest(id, id_clients, description, status, start_date, end_date);
                requests.add(request);
            }
        } catch (SQLException e) {
            System.err.println("Can't get requests!");
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
        return requests;
    }

    public List<ServiceRequest> showExistingRequests(int workerId) {
        List<ServiceRequest> requests = new ArrayList<>();
        try {
            open();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from service_request sr JOIN service_request_employee sre ON sr.id = sre.id_service_request where status = 1 AND id_employee =" + workerId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_clients = rs.getInt("id_client");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                Date start_date = rs.getDate("start_date");
                Date end_date = rs.getDate("end_date");
                ServiceRequest request = new ServiceRequest(id, id_clients, description, status, start_date, end_date);
                requests.add(request);
            }
        } catch (SQLException e) {
            System.err.println("Can't get requests!");
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
        return requests;
    }

    public List<ServiceRequest> showExistingRequestsWithWorkers() {
        List<ServiceRequest> requests = new ArrayList<>();
        try {
            open();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from service_request sr JOIN service_request_employee sre ON sr.id = sre.id_service_request where status = 1;");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_clients = rs.getInt("id_client");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                Date start_date = rs.getDate("start_date");
                Date end_date = rs.getDate("end_date");
                int id_employee = rs.getInt("id_employee");
                ServiceRequest request = new ServiceRequest(id, id_clients, description, status, start_date, end_date, id_employee);
                requests.add(request);
            }
        } catch (SQLException e) {
            System.err.println("Can't get requests!");
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
        return requests;
    }
    
    public List<Employee> showAvailableWorkers() {
        List<Employee> requests = new ArrayList<>();
        try {
            open();
            PreparedStatement ps = conn.prepareStatement(
                    "select users.first_name, users.id, count(users.id) as counted from users join service_request_employee on users.id = service_request_employee.id_employee group by users.id order by counted desc;");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	String firstname = rs.getString("first_name");
                int id = rs.getInt("id");
                int id_employee = rs.getInt("counted");
                Employee request = new Employee(firstname, id, id_employee);
                requests.add(request);
            }
        } catch (SQLException e) {
            System.err.println("Can't get requests!");
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
        return requests;
    }
    
    public void takeRequest(int id_employee, int id_service_request) {
        try {
            open();
            PreparedStatement ps = conn.prepareStatement(
                    String.format("insert into service_request_employee(id_service_request , id_employee)" +
                            " values (%d, %d)",id_service_request, id_employee));

            ps.executeUpdate();
            ps.close();

            ps = conn.prepareStatement(
                    String.format("update service_request set status = 1 where id = " + id_service_request));

            ps.executeUpdate();
            ps.close();


        } catch (SQLException e) {
            System.err.println("Error in inserting new service request!");
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Error in ServiceRequestDao!");
            e.printStackTrace();

        }
        finally {
            close();
        }

    }
    
    public void updateRequest(int id_employee, int id_service_request) {
        try {
            open();
           
            PreparedStatement ps = conn.prepareStatement(
                    String.format("update service_request_employee set id_employee = " + id_employee + " where id_service_request = " + id_service_request));

            ps.executeUpdate();
            ps.close();


        } catch (SQLException e) {
            System.err.println("Error in inserting new service request!");
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Error in ServiceRequestDao!");
            e.printStackTrace();

        }
        finally {
            close();
        }

    }
}
