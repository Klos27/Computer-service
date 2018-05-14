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

@WebServlet(urlPatterns = "/user/change-details")
public class ChangeDetailsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/change-details.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession(true);
    	User user = (User) session.getAttribute("user");
		UserDao db;
		PrintWriter out = response.getWriter();

		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		db = new UserDao();
		db.open();
		User changedDetails = db.changeDetails(user.getId(), first_name, last_name, address, phone);
		db.close();
		
		if(changedDetails != null) {
			session.setAttribute("user", changedDetails);
		}
		
		out.close();
		
	}

}
