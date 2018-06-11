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
    
    public List<Employee> showAvailableWorkersWithDate(String month, String year) {
        return serviceRequestDao.showAvailableWorkersWithDate(month, year);
    }
    
    public void takeRequest(int id_employee, int id_service_request) {
        serviceRequestDao.takeRequest(id_employee, id_service_request);
    }
    
    public void updateRequest(String id_employee, String id_service_request) {
        serviceRequestDao.updateRequest(id_employee, id_service_request);
    }

    public ServiceRequest getRequest(int id) {
        return serviceRequestDao.getServiceRequest(id);
    }

    public void changeRequestStatus(int reqId, int status) {
        serviceRequestDao.changeRequestStatus(reqId, status);
    }
    
    public ServiceRequest getRequestByInvoice(int invoiceId) {
    	return serviceRequestDao.getRequestByInvoice(invoiceId);
    }

    public void deletePartFromRequest(int reqId, int partId) {
        serviceRequestDao.deletePartFromRequest(reqId, partId);
    }
    public void deleteServiceFromRequest(int reqId, int serviceId) {
        serviceRequestDao.deleteServiceFromRequest(reqId, serviceId);
    }
}
