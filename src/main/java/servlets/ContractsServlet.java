package servlets;

import models.User;
import models.UserContract;
import services.AdminService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
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

			List<User> usersWithoutContract = adminService.getWorkersWithoutContract();
			request.setAttribute("usersWithoutContract", usersWithoutContract);




			List<UserContract> userContractList = adminService.getContractList();
			request.setAttribute("userContractList", userContractList);
			request.getRequestDispatcher("/WEB-INF/views/contracts.jsp").forward(request, response);





		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		endContract(request, response);
		addContract(request, response);
        doGet(request, response);
	}

	private void addContract(HttpServletRequest request, HttpServletResponse response) {
		AdminService adminService = new AdminService();




		String userIdStr = request.getParameter("userId");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		String salaryStr = request.getParameter("salary");

		if (userIdStr != null) {

			if (startDate == null || endDate == null || salaryStr == null ||
					startDate.equals("") || endDate.equals("") || salaryStr.equals("")) {

				request.setAttribute("userNotification", "Wrong data");
			} else {
				int userId = Integer.parseInt(userIdStr);
				Double salary = Double.parseDouble(salaryStr);
				if (salary > 0) {
					adminService.addContract(userId, startDate, endDate, salary);
				}
			}
		}
	}

	private boolean endContract(HttpServletRequest request, HttpServletResponse response) {
		AdminService adminService = new AdminService();
		String contractIdParam = request.getParameter("endContract");
		String endDateParam = request.getParameter("endDate");
		Date now = new Date();
		Date endDate = null;
		if (endDateParam != null) {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				endDate = formatter.parse(endDateParam);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (contractIdParam != null && endDate.after(now)) {
			int contractId = Integer.parseInt(contractIdParam);
			adminService.endContract(contractId);

			return true;
		}
		return false;
	}
}