package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import services.HelperService;
import services.Mail;
 
@WebServlet(urlPatterns = "/admin/add-worker")
public class AddWorkerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/add-worker.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminDao db;
		PrintWriter out = response.getWriter();

		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		
	    if(HelperService.isEmpty(first_name) && HelperService.isEmpty(last_name) &&  HelperService.isEmpty(email)) {
			
				db = new AdminDao();
				Mail sendEmail = new Mail();
				db.open();
				
				String password = UUID.randomUUID().toString();

				if(db.addWorker(first_name, last_name, password, email, 3) == "DONE") {
					out.print("DONE");	
					sendEmail.sendEmail(email, "Worker password", "Your worker password: " + password);
				} else {
					response.setStatus(403);
					out.print("Email is in use!");
				}
				
				db.close();
				
				
		} else {
			response.setStatus(403);
			out.print("You need to enter all fields!");				
		}
			
		out.close();
			
	}

}
