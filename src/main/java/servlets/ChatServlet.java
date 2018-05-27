package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ServiceRequestDao;
import dao.UserDao;
import models.User;
import services.HelperService;
import services.UserValidationService;

@WebServlet(urlPatterns = "/user/chat")
public class ChatServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceRequestDao db;
    	response.setContentType("application/json");
    	response.setCharacterEncoding("utf-8");
    	PrintWriter out = response.getWriter();
		
		int id_service_request = Integer.parseInt(request.getParameter("id_service_request"));

		if(HelperService.isEmpty(request.getParameter("id_service_request"))) {
			
			db = new ServiceRequestDao();
		    Gson g = new Gson();
			db.open();
			out.print(g.toJson(db.getMessages(id_service_request)));
			db.close();

		} else {
			response.setStatus(403);
			out.print("You need to enter all fields!");
		}

		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceRequestDao db;
		PrintWriter out = response.getWriter();

		int id_user = Integer.parseInt(request.getParameter("id_user"));
		int id_service_request = Integer.parseInt(request.getParameter("id_service_request"));
		String content = request.getParameter("content");
		
		if(HelperService.isEmpty(request.getParameter("id_user")) && 
			HelperService.isEmpty(request.getParameter("id_service_request")) &&
			HelperService.isEmpty(content)) {
			
			db = new ServiceRequestDao();
			db.open();

			db.sendMessage(id_user, id_service_request, content);
			
			db.close();
				
		} else {
			response.setStatus(403);
			out.print("You need to enter all fields!");
		}
		
		out.close();
		
	}

}
