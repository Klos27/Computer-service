package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
import services.ServiceRequestService;

@WebServlet("/service/confirm-payment")
public class ConfirmPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			response.sendRedirect("/welcome");
			//request.setAttribute("errorNotification", "You are not logged in!");
			//request.getRequestDispatcher("/WEB-INF/views/error-page.jsp").forward(request, response);
		}
		else if(user.getRole() == 0 || user.getRole() == 3) {
			response.sendRedirect("/welcome");
			//request.setAttribute("errorNotification", "You don't have permissions to view this page");
			//request.getRequestDispatcher("/WEB-INF/views/error-page.jsp").forward(request, response);
		}
		else
		{
			// Show all payments
			PaymentDao paymentDao = new PaymentDao();
			ArrayList<Payment> paymentsList = null;
			try {
				paymentsList = paymentDao.getUnpaidInvoices();
			} catch (SQLException e) {
				request.setAttribute("userNotification", "ServerError: Can't connect with database to get payments List.");
				//e.printStackTrace();
			}
			request.removeAttribute("userExistingRequests");
			request.setAttribute("paymentsList", paymentsList);
			request.setCharacterEncoding("UTF-8");
			request.getRequestDispatcher("/WEB-INF/views/confirm-payment.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idstr = request.getParameter("invoiceId");
		int invoiceId = 0;
		try {
			invoiceId = Integer.parseInt(idstr);
		}
		catch(Exception e) {
			invoiceId = 0;
		}
		
		if(invoiceId == 0) {
			request.removeAttribute("userNotification");
			request.setAttribute("userNotification", "Wrong invoice number");
			doGet(request, response);
		}
		else {
			PaymentDao paymentDao = new PaymentDao();
			try {
				paymentDao.confirmPayment(invoiceId);
				
				ServiceRequestService serviceRequestService = new ServiceRequestService();
				serviceRequestService.changeRequestStatus(serviceRequestService.getRequestByInvoice(invoiceId).getId(), 4);
				
				// SEND NOTIFICATION TO USER
				NotificationService notificationService = new NotificationService();
				notificationService.confirmPaymentNotification(invoiceId);
				
				request.removeAttribute("userNotification");
				request.setAttribute("userNotification", "Confirmed payment for invoice no: " + invoiceId);
			} catch (SQLException e) {
				request.removeAttribute("userNotification");
				request.setAttribute("userNotification", "Error: something went wrong while trying to confirm invoice number: " + invoiceId);
				//e.printStackTrace();
			}
			doGet(request, response);
		}
	}
}
