package services;

import dao.WorkerDAO;
import models.Request;

import java.util.List;

public class WorkerService {

    private WorkerDAO workerDAO = new WorkerDAO();

    public List<Request> showNewRequests() {
        return workerDAO.showNewRequests();
    }
}
