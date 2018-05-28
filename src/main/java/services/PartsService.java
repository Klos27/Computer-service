package services;

import dao.PartsDao;
import dao.ServiceRequestDao;
import models.Employee;
import models.Parts;
import models.ServiceRequest;

import java.sql.SQLException;
import java.util.List;

public class PartsService {

    private PartsDao partsDao = new PartsDao();

    public List<Parts> getAllParts() {
        return partsDao.getAllParts();
    }

    public List<Parts> getAllParts(int requestId) {
        try {
            return partsDao.getAllParts(requestId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
