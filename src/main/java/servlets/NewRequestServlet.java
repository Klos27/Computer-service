package servlets;

import models.Request;
import services.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/service/new-requests")
public class NewRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WorkerService workerService = new WorkerService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		List<Request> requests = workerService.showNewRequests();
		System.out.println(requests);
		request.setAttribute("requests", requests);
		request.getRequestDispatcher("/WEB-INF/views/new-requests.jsp").forward(request, response);
	}



}
