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

@WebServlet(urlPatterns = "/user/change-password")
public class ChangePasswordServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/change-password.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession(true);
    	User user = (User) session.getAttribute("user");
		UserDao db;
		PrintWriter out = response.getWriter();

		String password = request.getParameter("password");
		String new_password = request.getParameter("new_password");

		db = new UserDao();
		db.open();
		String changePassword = db.changePassword(user.getId(), password, new_password);
		
		if(changePassword != "DONE") {
			response.setStatus(403);
			out.print("Wrong password!");
		}
		
		db.close();
		
		out.close();
		
	}

}
