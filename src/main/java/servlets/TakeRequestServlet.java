package servlets;

import models.Request;
import models.User;
import services.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/service/new-requests/take/")
public class TakeRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WorkerService workerService = new WorkerService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		int id_employee = user.getId();
		int id_service_request = Integer.parseInt(request.getParameter("requestId"));
		workerService.takeRequest(id_employee, id_service_request);
		response.sendRedirect("/service/new-requests");
//		request.getRequestDispatcher("/WEB-INF/views/new-requests.jsp").forward(request, response);
	}



}
