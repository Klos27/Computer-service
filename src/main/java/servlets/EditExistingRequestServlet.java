package servlets;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PartsDao;
import dao.PaymentDao;
import dao.ServiceRequestDao;
import dao.ServicesDao;
import models.Parts;
import models.Payment;
import models.ServiceRequest;
import models.Services;
import models.User;
import services.NotificationService;
import services.PartsService;
import services.ServiceRequestService;
import services.ServicesService;

@WebServlet("/service/existing-requests/edit")
public class EditExistingRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceRequestDao srd = new ServiceRequestDao();
		HttpSession session = request.getSession(true);
		ServicesService ss =  new ServicesService();
		User user = (User) session.getAttribute("user");

		boolean redirected = false;

		if(user == null) {
			request.setAttribute("requestError", "You are not logged in!");
		} else {
			String idStr = request.getParameter("requestId");
			if (idStr != null) {
				int reqId = Integer.parseInt(idStr);
				ServiceRequest	serviceRequest = srd.getServiceRequest(reqId);

				if(serviceRequest == null) {
					// Check if request belong to this user
					request.setAttribute("requestError", "Request doesn't exist");
				} else {
					request.setAttribute("requestDetails", serviceRequest);



					float servicesSum = 0f;

					ArrayList<Services> servicesList = null;

					servicesList = ss.getAllServices(reqId);

					for(Services service : servicesList) {
						servicesSum += service.getPrice();
					}
					request.setAttribute("requestServicesList", servicesList);
					request.setAttribute("requestServicesSum", servicesSum);

					//=========== SERVICE PARTS

					float partsSum = 0f;
					PartsService partsService = new PartsService();
					List<Parts> partsList = null;

					partsList = partsService.getAllParts(reqId);

					for(Parts parts : partsList) {
						partsSum += parts.getPrice();
					}
					request.setAttribute("requestPartsList", partsList);
					request.setAttribute("requestPartsSum", partsSum);
//
					List<Parts> allParts = partsService.getAllParts();
					request.setAttribute("allParts", allParts);

					List<Services> allServices = ss.getAllServices();
					request.setAttribute("allServices", allServices);

//
//					//=========== CHAT
//
//					//TODO Chat
//					request.removeAttribute("requestChat");
//					request.setAttribute("requestChat", "requestChat");
				}
			}


			// zeby nie wywalalo illegal state costam, ze 2 razy przekierowuje
			redirected = checkActionParameter(request, response);
		}

		if (!redirected) {
			request.setCharacterEncoding("UTF-8");
			request.getRequestDispatcher("/WEB-INF/views/browse-requests-edit.jsp").forward(request, response);
		}
	}

	private boolean checkActionParameter(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServiceRequestDao srd = new ServiceRequestDao();
		PaymentDao paymentDao = new PaymentDao();
		String actionParam = request.getParameter("action");
		String reqIdStr = request.getParameter("requestId");


		if (actionParam != null && reqIdStr != null) {
			int reqId = Integer.parseInt(reqIdStr);

			if (actionParam.equals("end")) {
				int status = 3;
				srd.changeRequestStatus(reqId, status);
				try {
					// SEND NOTIFICATION
					NotificationService notificationService = new NotificationService();
					notificationService.updateRequestStatus(reqId, 3);
					paymentDao.generateNewInvoice(reqId);
				} catch (SQLException e) {
					//e.printStackTrace();
				}
//				doGet(request, response);
				request.setCharacterEncoding("UTF-8");
				request.getRequestDispatcher("/service/existing-requests").forward(request, response);
				return true;
			}
		}
		return false;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addServiceOperation(request, response);
		addPart(request, response);

	}

	private boolean addServiceOperation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServicesService servicesService = new ServicesService();
		String serviceIdParam = request.getParameter("addService");
		String reqIdStr = request.getParameter("requestId");


		if (serviceIdParam != null && reqIdStr != null) {
			int reqId = Integer.parseInt(reqIdStr);
			servicesService.addServiceToRequest(reqId, Integer.parseInt(serviceIdParam));
			doGet(request, response);

			return true;
		}
		return false;
	}

	private boolean addPart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PartsService partsService = new PartsService();
		String partIdParam = request.getParameter("addPart");
		String reqIdStr = request.getParameter("requestId");


		if (partIdParam != null && reqIdStr != null) {
			int reqId = Integer.parseInt(reqIdStr);
			partsService.addPartToRequest(reqId, Integer.parseInt(partIdParam));
			doGet(request, response);

			return true;
		}
		return false;
	}

}

