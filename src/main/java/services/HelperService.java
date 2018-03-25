package services;

public class HelperService {

	public static boolean isEmpty(String data) {
		
		if(data != null && !data.isEmpty())
			return true;
		else 
			return false;
		
	}
	
}
