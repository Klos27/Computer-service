package services;

public class UserValidationService {

	public boolean isUserValid(String username, String password) {
		if(username.equals("test123") && password.equals("test123"))
			return true;
		
		return false;
	}
	
}
