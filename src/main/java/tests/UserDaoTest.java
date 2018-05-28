package tests;

import static org.junit.Assert.*;

import java.util.Random;
import com.mysql.jdbc.exceptions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dao.DAOManager;
import dao.UserDao;
import models.User;

import java.sql.*;

public class UserDaoTest extends DAOManager {
	
    public UserDaoTest() {
    	super();
    }
    
    UserDao db;
    
    @Test
    public void register_createValidUser() {
    	db = new UserDao();
		db.open();
    	String done = db.register("jan", "kowalski", "abc", getSaltString()+ "@gmail.com", 0);
    	assertEquals(done, "DONE");
    	db.close();
    }

    @Test
    public void login_tryToLoginWithCorrectMail() {
    	String test = getSaltString(); //one e-mail for register and login
    	db = new UserDao();
    	User user = new User();
    	db.open();
    	String done = db.register("jan", "kowalski", "a", test + "@gmail.com", 0);
    	user = db.login(test + "@gmail.com", "a");
    	assertNotNull(user);
    	db.close();
    }
    
    /* different e-mail in register, different in login, won't work! */
    @Test
    public void login_tryToLoginWithInvalidMail() {
    	String test = getSaltString(); //two diff e-mails
    	String test2 = getSaltString(); // -||- 
    	db = new UserDao();
    	User user = new User();
    	db.open();
    	String done = db.register("jan", "kowalski", "a", test + "@gmail.com", 0);
    	user = db.login(test2 + "@gmail.com", "a");
    	assertNull(user);
    	db.close();
    }
    
    @Ignore
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { 
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
