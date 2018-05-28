package services;

import dao.ServicesDao;
import models.Services;

import java.sql.SQLException;
import java.util.ArrayList;

public class ServicesService {
    ServicesDao servicesDao = new ServicesDao();

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
}
