package services;

import dao.WorkerDAO;
import models.Request;

import java.util.List;

public class WorkerService {

    private WorkerDAO workerDAO = new WorkerDAO();

    public List<Request> showNewRequests() {
        return workerDAO.showNewRequests();
    }
    public List<Request> showExistingRequests(int workerId) {
        return workerDAO.showExistingRequests(workerId);
    }

    public void takeRequest(int id_employee, int id_service_request) {
        workerDAO.takeRequest(id_employee, id_service_request);
    }
}
