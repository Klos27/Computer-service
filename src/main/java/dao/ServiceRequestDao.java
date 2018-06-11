package dao;

import models.Employee;
import models.Message;
import models.ServiceRequest;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public ServiceRequest getServiceRequest(int requestId) {
        Statement stmt = null;
        ResultSet rs = null;

        ServiceRequest serviceRq = null;

        try {
            open();
            stmt = conn.createStatement();

            // Execute operation
            rs = stmt.executeQuery("SELECT * FROM `service_request` where id = " + requestId);
            if(rs.next())
                serviceRq = new ServiceRequest(rs.getInt("id"), rs.getInt("id_client") , rs.getString("description"), rs.getInt("status"), rs.getDate("start_date"), rs.getDate("end_date"));

        } catch (SQLException e) {
            System.err.println("Error at executing query");
            e.printStackTrace();
        } finally {
            close();
        }
        return serviceRq ;
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
            		"select sr.id, sr.id_client, sr.description, sr.status, sr.start_date, sr.end_date, sre.id_employee, (select users.first_name from users where users.id = sre.id_employee) as \"srefirst\", (select users.last_name from users where users.id = sre.id_employee) as \"srelast\" from service_request sr JOIN service_request_employee sre ON sr.id = sre.id_service_request where status = 1");
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_clients = rs.getInt("id_client");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                Date start_date = rs.getDate("start_date");
                Date end_date = rs.getDate("end_date");
                int id_employee = rs.getInt("id_employee");
                String firstname = rs.getString("srefirst");
                String lastname = rs.getString("srelast");
                ServiceRequest request = new ServiceRequest(id, id_clients, description, status, start_date, end_date, id_employee, firstname, lastname);
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
                    "SELECT users.first_name, users.last_name, users.id, 0 as \"counted\" FROM users where users.id not in (select service_request_employee.id_employee from service_request_employee) and users.role = 3 union select users.first_name, users.last_name, users.id, count(users.id) as counted from users join service_request_employee on users.id = service_request_employee.id_employee group by users.id order by counted desc");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	String firstname = rs.getString("first_name");
            	String lastname = rs.getString("last_name");
                int id = rs.getInt("id");
                int id_employee = rs.getInt("counted");
                Employee request = new Employee(firstname, lastname, id, id_employee);
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
    
    public List<Employee> showAvailableWorkersWithDate(String month, String year) {
    	 List<Employee> requests = new ArrayList<>();
         try {
             open();
             PreparedStatement ps = conn.prepareStatement(
                     "select users.first_name, users.last_name, users.id, count(service_request_services.id_service_request) as \"zamowienia\", sum(service_request_services.service_price) as \"zarobek\" from users join service_request_employee on users.id = service_request_employee.id_employee join service_request on service_request_employee.id_service_request = service_request.id join service_request_services on service_request.id = service_request_services.id_service_request where MONTH(service_request.start_date) = " + month + " AND YEAR(service_request.start_date) = " + year + " group by users.id");

             ResultSet rs = ps.executeQuery();
             while (rs.next()) {
             	String firstname = rs.getString("first_name");
             	String lastname = rs.getString("last_name");
                int id = rs.getInt("id");
                int orders = rs.getInt("zamowienia");
                double earnings = rs.getDouble("zarobek");
                Employee request = new Employee(firstname, lastname, id, orders, earnings);
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
    
    public void updateRequest(String id_employee, String id_service_request) {
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
    
    public String sendMessage(int id_user, int id_service_request, String content) {
    	
        try {
     	
     	Date date = new Date(System.currentTimeMillis());
        	PreparedStatement ps = conn.prepareStatement(
                    "insert into service_request_messages(id_user, id_service_request, date, content) values (?, ?, ?, ?)");
        	ps.setInt(1, id_user);
        	ps.setInt(2, id_service_request);
        	ps.setDate(3, date);
        	ps.setString(4, content);
        	ps.executeUpdate();
        	ps.close();
            
            return "DONE";      
            
        } catch (SQLException e) {
            System.err.println("Error in inserting the message!");
            e.printStackTrace();
            return null;
        }      
    }
    
    public List<Message> getMessages(int id_service_request) {
 	    	
 	   List<Message> messages = new LinkedList<Message>();
 	               
        try {       	
        	PreparedStatement ps = conn.prepareStatement(  
        			"select srm.id, srm.id_user, srm.id_service_request, srm.date, srm.content, users.first_name, users.last_name from service_request_messages as srm inner join users ON srm.id_user = users.id where id_service_request=? ORDER BY srm.id DESC");  
        	ps.setInt(1, id_service_request);  
        			      
        	ResultSet rs = ps.executeQuery();
         while(rs.next()) {
         	messages.add(new Message(rs.getInt("id"), rs.getInt("id_user"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("id_service_request"), rs.getDate("date"), rs.getString("content")));
         }
        			
        } catch (SQLException e) {
            System.err.println("Can't get the messages!");
            e.printStackTrace();
            return null;
        }
        
        return messages;
    }

    public void changeRequestStatus(int reqId, int status) {
        try {
            open();

            PreparedStatement ps = conn.prepareStatement(
                    String.format("update service_request set status = " + status + " where id = " + reqId));
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

	public ServiceRequest getRequestByInvoice(int invoiceId) {
		Statement stmt = null;
        ResultSet rs = null;
        
        ServiceRequest serviceRq = null;
     
        try {
            open();
            stmt = conn.createStatement();
            
            // Execute operation
            rs = stmt.executeQuery("SELECT sr.id, sr.id_client, sr.description, sr.status, sr.start_date, sr.end_date FROM payments p left join service_request sr on p.id_service_request = sr.id where p.id =" + invoiceId);
            if(rs.next())
            	serviceRq = new ServiceRequest(rs.getInt("id"), rs.getInt("id_client") , rs.getString("description"), rs.getInt("status"), rs.getDate("start_date"), rs.getDate("end_date"));      
     
        } catch (SQLException e) {
            System.err.println("Error at executing query");
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
					rs.close();
				} catch (SQLException e) {
				}
            }
            if (stmt != null) {
                try {
					stmt.close();
				} catch (SQLException e) {
				}
            }
            close();
        }
        return serviceRq ;
	}


    public void addServiceToRequest(int reqId, int serviceId, double price) {
        try {
            open();
            PreparedStatement ps = conn.prepareStatement(
                    String.format("INSERT INTO `service_request_services` (`id_service_request`, `id_service`, `service_price`)" +
                                    " VALUES ('%s', '%s', '%s')", reqId, serviceId, price));
            ps.executeUpdate();
            ps.close();


        } catch (SQLException e) {
            System.err.println("Error in inserting adding service!");
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Error in ServiceRequestDao!");
            e.printStackTrace();

        }
        finally {
            close();
        }

    }

    public void deleteServiceFromRequest(int reqId, int serviceId) {
        try {
            open();
            PreparedStatement ps = conn.prepareStatement(
                    String.format("DELETE FROM service_request_services WHERE" +
                            " id_service_request = %s AND id_service = %s LIMIT 1", reqId, serviceId));
            ps.executeUpdate();
            ps.close();


        } catch (SQLException e) {
            System.err.println("Error in inserting adding service!");
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Error in ServiceRequestDao!");
            e.printStackTrace();

        }
        finally {
            close();
        }

    }

    public void addPartToRequest(int reqId, int partId, double price) {
        try {
            open();
            PreparedStatement ps = conn.prepareStatement(
                    String.format("INSERT INTO `service_request_parts` (`id_service_request`, `id_part`, `part_price`)" +
                            " VALUES ('%s', '%s', '%s')", reqId, partId, price));
            ps.executeUpdate();
            ps.close();


        } catch (SQLException e) {
            System.err.println("Error in inserting adding service!");
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Error in ServiceRequestDao!");
            e.printStackTrace();

        }
        finally {
            close();
        }

    }

    public void deletePartFromRequest(int reqId, int partId) {
        try {
            open();
            PreparedStatement ps = conn.prepareStatement(
                    String.format("DELETE FROM service_request_parts WHERE" +
                            " id_service_request = %s AND id_part = %s LIMIT 1", reqId, partId));
            ps.executeUpdate();
            ps.close();


        } catch (SQLException e) {
            System.err.println("Error in inserting adding service!");
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
