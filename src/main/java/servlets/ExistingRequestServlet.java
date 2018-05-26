package servlets;

import models.ServiceRequest;
import models.User;
import services.ServiceRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/service/existing-requests")
public class ExistingRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceRequestService serviceRequestService = new ServiceRequestService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");


		List<ServiceRequest> requests = serviceRequestService.showExistingRequests(user.getId());
		//System.out.println(requests);
		request.removeAttribute("existingRequests");
		request.setAttribute("existingRequests", requests);
		request.getRequestDispatcher("/WEB-INF/views/existing-requests.jsp").forward(request, response);
	}



}
