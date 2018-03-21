package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("password2");
		String email = request.getParameter("email");

		if(password.equals(repassword)) {
			System.out.println(username + ' ' + password + ' ' + repassword + ' ' + email);
		} else {
			response.setStatus(403);
			PrintWriter out = response.getWriter();
			out.print("Passwords do not match!");
			out.flush();
		}
		
	}

}
