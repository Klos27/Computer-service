package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import services.HelperService;
import services.Mail;

@WebServlet(urlPatterns = "/auth/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/forgot-password.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email = request.getParameter("email");
		UserDao db;
		PrintWriter out = response.getWriter();

		if(HelperService.isEmpty(email)) {

			db = new UserDao();
			db.open();
			
			if(db.isEmail(email) == "DONE") {
				
				Mail sendEmail = new Mail();
				String newPassword = UUID.randomUUID().toString();
				sendEmail.sendEmail(email, "Forgot password", "Your new password: " + newPassword);
				db.setNewPassword(email, newPassword);
				
			} else {
				response.setStatus(403);
				out.print("There is no email like that!");
			}
			
			db.close();
		
		} else {
			response.setStatus(403);
			out.print("You need to enter all fields!");
		}
		out.close();

	}
	
}
