package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import models.User;
import services.AdminService;
import services.HelperService;
import services.Mail;

@WebServlet(urlPatterns = "/admin/list")
public class ListServlet extends HttpServlet {
	
	AdminService adminService = new AdminService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filterRole = request.getParameter("role");

		List<User> requests;
		requests = adminService.showAllUsers(filterRole);

		request.setAttribute("users", requests);

		request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String role = request.getParameter("role");
		String id = request.getParameter("id");
		
		adminService.setRole(id, role);

	}

}
