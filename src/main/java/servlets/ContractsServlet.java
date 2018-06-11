package servlets;

import models.User;
import models.UserContract;
import services.AdminService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/service/contracts")
public class ContractsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AdminService adminService = new AdminService();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			request.setAttribute("requestError", "You are not logged in!");
		} else {

			List<UserContract> userContractList = adminService.getContractList();
			request.setAttribute("userContractList", userContractList);
			request.getRequestDispatcher("/WEB-INF/views/contracts.jsp").forward(request, response);


		}
	}
}