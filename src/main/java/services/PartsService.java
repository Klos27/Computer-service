package services;

import dao.PartsDao;
import dao.ServiceRequestDao;
import models.Parts;

import java.sql.SQLException;
import java.util.List;

public class PartsService {

    private PartsDao partsDao = new PartsDao();
    private ServiceRequestDao serviceRequestDao = new ServiceRequestDao();

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

    public void addPartToRequest(int reqId, int partId) {
        Double partCurrentPrice = partsDao.getCurrentPartPrice(partId);
        serviceRequestDao.addPartToRequest(reqId, partId, partCurrentPrice);
    }

}
