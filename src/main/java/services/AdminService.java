package services;

import dao.AdminDao;
import models.User;

import java.util.List;

public class AdminService {

    private AdminDao adminDao = new AdminDao();

    public List<User> showAllUsers(String filter) {
        return adminDao.showAllUsers(filter);
    }
    
    public String addWorker(String first_name, String last_name, String password, String email, int role) {
    	return adminDao.addWorker(first_name, last_name, password, email, role);
    }
    
    public String setRole(String id, String role) {
        return adminDao.setRole(id, role);
    }
    
}
