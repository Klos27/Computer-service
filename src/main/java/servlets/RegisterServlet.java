package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import services.HelperService;
 
@WebServlet(urlPatterns = "/auth/register")
public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDao db;
		PrintWriter out = response.getWriter();

		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String password = request.getParameter("password");
		String repassword = request.getParameter("password2");
		String email = request.getParameter("email");
		
	    if(HelperService.isEmpty(first_name) && HelperService.isEmpty(last_name) && HelperService.isEmpty(password) && HelperService.isEmpty(email)) {
			
			if(password.equals(repassword)) {

				db = new UserDao();
				db.open();

				out.print(db.register(first_name, last_name, password, email, 0));			
					
				db.close();
			
			} else {
				response.setStatus(403);
				out.print("Passwords do not match!");
			}
				
		} else {
			response.setStatus(403);
			out.print("You need to enter all fields!");				
		}
			
		out.close();
			
	}

}
