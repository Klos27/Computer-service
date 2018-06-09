package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.ServiceRequest;
import services.ServiceRequestService;

@WebServlet("/service/statistics")
public class StatisticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceRequestService serviceRequestService = new ServiceRequestService();
	private String month;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> requests = serviceRequestService.showAvailableWorkersWithDate(month);
		request.removeAttribute("showAvailWithDate");
		request.setAttribute("showAvailWithDate", requests);
		request.getRequestDispatcher("/WEB-INF/views/statistics.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		month = request.getParameter("monthval");
		//System.out.println(month);
	}


}

