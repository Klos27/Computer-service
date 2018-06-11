package services;

import dao.ServiceRequestDao;
import dao.ServicesDao;
import models.Services;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServicesService {
    ServicesDao servicesDao = new ServicesDao();
    ServiceRequestDao serviceRequestDao = new ServiceRequestDao();

    public ArrayList<Services> getAllServices(){
        return servicesDao.getAllServices();
    }

    public ArrayList<Services> getAllServices(int requestId){
        try {
            return servicesDao.getAllServices(requestId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addServiceToRequest(int reqId, int serviceId) {
        Double partCurrentPrice = servicesDao.getCurrentServicePrice(serviceId);
        serviceRequestDao.addServiceToRequest(reqId, serviceId, partCurrentPrice);
    }
}
