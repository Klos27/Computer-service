package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ServiceRequestDao;
import models.User;
import services.HelperService;

@WebServlet("/user/new-request")
public class AddServiceRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/add-service-request.jsp").forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		ServiceRequestDao db;
		User user;
		PrintWriter out = response.getWriter();

		String computer_producer = request.getParameter("computer_producer");
		String computer_serial = request.getParameter("computer_serial");
		String computer_description = request.getParameter("computer_description");

	    if(HelperService.isEmpty(computer_producer) && HelperService.isEmpty(computer_serial) && HelperService.isEmpty(computer_description)) {
	    	HttpSession session = request.getSession(true);
			if( (user = (User) session.getAttribute("user")) != null) {
				db = new ServiceRequestDao();
				db.open();

				out.print(db.createNewRequest(user, computer_producer, computer_serial, computer_description));			
					
				db.close();
				
			}
			else {
				response.setStatus(403);
				out.print("User isn't logged in!");				
			}
			
		} else {
			response.setStatus(403);
			out.print("You need to enter all fields!");				
		}
			
		out.close();
	
	}

}
