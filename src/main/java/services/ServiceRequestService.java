package services;

import dao.ServiceRequestDao;
import models.Employee;
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
    
    public List<ServiceRequest> showExistingRequestsWithWorkers() {
        return serviceRequestDao.showExistingRequestsWithWorkers();
    }
    
    public List<Employee> showAvailableWorkers() {
        return serviceRequestDao.showAvailableWorkers();
    }

    public void takeRequest(int id_employee, int id_service_request) {
        serviceRequestDao.takeRequest(id_employee, id_service_request);
    }
    
    public void updateRequest(int id_employee, int id_service_request) {
        serviceRequestDao.updateRequest(id_employee, id_service_request);
    }

    public ServiceRequest getRequest(int id) {
        return serviceRequestDao.getServiceRequest(id);
    }

    public void changeRequestStatus(int reqId, int status) {
        serviceRequestDao.changeRequestStatus(reqId, status);
    }
}
