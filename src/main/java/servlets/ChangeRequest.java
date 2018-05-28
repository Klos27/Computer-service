package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import models.ServiceRequest;
import models.User;
import services.ServiceRequestService;

@WebServlet("/service/existing-requests")
public class ChangeRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceRequestService serviceRequestService = new ServiceRequestService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		List<ServiceRequest> requests = serviceRequestService.showExistingRequestsWithWorkers();
		List<Employee> requests2 = serviceRequestService.showAvailableWorkers();

		request.removeAttribute("existingReqWithWorkers");
		request.setAttribute("existingReqWithWorkers", requests);
		request.removeAttribute("showAvail");
		request.setAttribute("showAvail", requests2);
		request.getRequestDispatcher("/WEB-INF/views/change-request.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_worker = request.getParameter("${request.id}");
		String id_request = request.getParameter("reqid");
		int id_worker_int = Integer.parseInt(id_worker);
		int id_request_int = Integer.parseInt(id_request);
		serviceRequestService.updateRequest(id_worker_int, id_request_int);
	}
}