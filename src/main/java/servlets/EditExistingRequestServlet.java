package servlets;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

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

@WebServlet("/service/existing-requests/edit")
public class EditExistingRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceRequestDao srd = new ServiceRequestDao();
		HttpSession session = request.getSession(true);

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
//
//					//=========== PAYMENT SECTION
//
//					PaymentDao paymentDao = new PaymentDao();
//					Payment payment = null;
//					try {
//						payment = paymentDao.getPayment(user.getId(), reqId);
//					} catch (SQLException e) {
//						request.setAttribute("userNotificationPayment", "ServerError: Can't connect with database to get payment details.");
//						//e.printStackTrace();
//					}
//					request.removeAttribute("requestPayment");
//					request.setAttribute("requestPayment", payment);
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
		String actionParam = request.getParameter("action");
		String reqIdStr = request.getParameter("requestId");


		if (actionParam != null && reqIdStr != null) {
			int reqId = Integer.parseInt(reqIdStr);

			if (actionParam.equals("end")) {
				int status = 3;
				srd.changeRequestStatus(reqId, status);
//				doGet(request, response);
				request.setCharacterEncoding("UTF-8");
				request.getRequestDispatcher("/service/existing-requests").forward(request, response);
				return true;
			}


		}
		return false;
	}
}

