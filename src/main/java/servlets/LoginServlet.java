package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import models.User;
import services.HelperService;
import services.UserValidationService;

@WebServlet(urlPatterns = "/auth/login")
public class LoginServlet extends HttpServlet {

	private UserValidationService userValidationService = new UserValidationService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao db;
		User user;
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(HelperService.isEmpty(email) && HelperService.isEmpty(password)) {
			
			db = new UserDao();
			db.open();

			user = db.login(email, password);
				
			if(user != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user",  user);
			
				out.print("user");
			} else {
				response.setStatus(403);
				out.print("Wrong credentials!");
			}
			
			db.close();
				
		} else {
			response.setStatus(403);
			out.print("You need to enter all fields!");
		}
		
		out.close();
		
	}

}
