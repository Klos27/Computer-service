package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PartsDao;
import dao.ServiceRequestDao;
import dao.ServicesDao;
import models.Parts;
import models.ServiceRequest;
import models.Services;
import models.User;

@WebServlet("/user/existing-requests")
public class AllServiceRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			request.setAttribute("requestError", "You are not logged in!");
		}
		else
		{
			String idstr = request.getParameter("id");
			int reqId = 0;
			try {
				reqId = Integer.parseInt(idstr);
			}
			catch(Exception e) {
				reqId = 0;
			}
			if(reqId == 0) {
				// Show all service requests
				ServiceRequestDao srd = new ServiceRequestDao();
				ArrayList<ServiceRequest> requests = null;
				try {
					requests = srd.getAllServiceRequests(user.getId());
				} catch (SQLException e) {
					request.setAttribute("userNotification", "ServerError: Can't connect with database to get user's requests.");
					//e.printStackTrace();
				}
				request.removeAttribute("userExistingRequests");
				request.setAttribute("userExistingRequests", requests);
			}
			else {
				// Show specific service request details
				ServiceRequestDao srd = new ServiceRequestDao();
				
				ServiceRequest serviceRequest = null;
				try {
					serviceRequest = srd.getServiceRequest(user.getId(), reqId);
				} catch (SQLException e) {
					request.setAttribute("userNotification", "ServerError: Can't connect with database to get request's details.");
					//e.printStackTrace();
				}			
				
				if(serviceRequest == null)
					// Check if request belong to this user
					request.setAttribute("requestError", "You are not owner of this service request or this request does not exist");
				else {
					//=========== SERVICE Details
					
					request.setAttribute("requestDetails", serviceRequest);

					//=========== SERVICE OPERATIONS
					
					float servicesSum = 0f;
					ServicesDao servicesDao =  new ServicesDao();
					ArrayList<Services> servicesList = null;
					try {
						servicesList = servicesDao.getAllServices(reqId);
					} catch (SQLException e) {
						request.setAttribute("userNotificationServices", "ServerError: Can't connect with database to get services list.");
						//e.printStackTrace();
					}
					for(Services service : servicesList) {
						servicesSum += service.getPrice();
					}			
					request.setAttribute("requestServicesList", servicesList);
					request.setAttribute("requestServicesSum", servicesSum);
					
					//=========== SERVICE PARTS

					float partsSum = 0f;
					PartsDao partsDao = new PartsDao();
					ArrayList<Parts> partsList = null;
					try {
						partsList = partsDao.getAllParts(reqId);
					} catch (SQLException e) {
						request.setAttribute("userNotificationParts", "ServerError: Can't connect with database to get parts list.");
						//e.printStackTrace();
					}
					for(Parts parts : partsList) {
						partsSum += parts.getPrice();
					}	
					request.setAttribute("requestPartsList", partsList);
					request.setAttribute("requestPartsSum", partsSum);
					
					//=========== PAYMENT SECTION
					
					//TODO return payment details
					request.removeAttribute("requestPayment");
					request.setAttribute("requestPayment", partsSum + servicesSum);
					
					//=========== CHAT
					
					//TODO Chat
					request.removeAttribute("requestChat");
					request.setAttribute("requestChat", "requestChat");
				}
			}			
		}
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/WEB-INF/views/browse-requests.jsp").forward(request, response);
	}
}
