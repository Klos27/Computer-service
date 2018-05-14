package services;

import dao.ServiceRequestDao;
import models.ServiceRequest;

import java.util.List;

public class ServiceRequestService {

    private ServiceRequestDao serviceRequestDao = new ServiceRequestDao();

    public List<ServiceRequest> showNewRequests() {
        return serviceRequestDao.showNewRequests();
    }
    public List<ServiceRequest> showExistingRequests(int workerId) {
        return serviceRequestDao.showExistingRequests(workerId);
    }

    public void takeRequest(int id_employee, int id_service_request) {
        serviceRequestDao.takeRequest(id_employee, id_service_request);
    }
}
