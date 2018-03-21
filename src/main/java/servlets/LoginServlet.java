package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.UserValidationService;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	private UserValidationService userValidationService = new UserValidationService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean isUserValid = userValidationService.isUserValid(username, password);
		
		if(isUserValid) {

			request.getSession().setAttribute("username", username);
				
		} else {
			response.setStatus(403);
			PrintWriter out = response.getWriter();
			out.print("Invalid credentials!");
			out.flush();	
		}
			
	}

}
