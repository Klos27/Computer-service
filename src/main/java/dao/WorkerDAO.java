package dao;

import models.Request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkerDAO extends DAOManager{

    public WorkerDAO() {
        super();
    }
    public List<Request> showNewRequests() {
        List<Request> requests = new ArrayList<>();
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
                Request request = new Request(id, id_clients, description, status, start_date, end_date);
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

    public List<Request> showExistingRequests(int workerId) {
        List<Request> requests = new ArrayList<>();
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
                Request request = new Request(id, id_clients, description, status, start_date, end_date);
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



}
