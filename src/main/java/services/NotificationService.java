package services;

import dao.UserDao;
import models.ServiceRequest;
import models.User;

public class NotificationService {
	
	private Mail mailService;
	
	public NotificationService() {
		mailService = new Mail();
	}
	
	public void updateRequestStatus(int requestId, int requestNewStatus) {
		String message = "";
		String title = "Service request " + requestId + " status update!";
		
		switch(requestNewStatus) {
			case 1:
			case 2:
				message = "Your request number " + requestId + " has been started.";
				break;
			case 3:
				message = "Your request number " + requestId + " has been ended and now we are waiting for payment."
						+ "Check your account for more information.";
				break;
			case 4:
				message = "Your request number " + requestId + " has been paid successfully."
						+ " Now please wait for your PC delivery or if you choose pick-up please"
						+ " come and get your computer.";
				break;
			default:
				message = "Your request number " + requestId + " has been updated.";
				break;
		}
		
		message = message + "\nBest regards, PPPM - Computer Service.\nwww.pppm.com";
		
		// SEND NOTIFICATION TO USER
		UserDao userDao = new UserDao();
		User userMail = userDao.getUser(requestId);
		mailService.sendEmail(userMail.getEmail(), title, message);
	}	
	
	public void confirmPaymentNotification(int invoiceId) {
		ServiceRequest serviceRequest = null;
		ServiceRequestService serviceRequestService = new ServiceRequestService();
		serviceRequest = serviceRequestService.getRequestByInvoice(invoiceId);
		updateRequestStatus(serviceRequest.getId(), 4);
	}
	
}
