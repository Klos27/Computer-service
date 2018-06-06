package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ServicesDao;
import models.Services;

@WebServlet("/service/operations")
public class ServicesPricesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		
		String serviceId = request.getParameter("f_operation_id");
		String serviceName = request.getParameter("f_operation_name");
		String serviceDescription = request.getParameter("f_operation_description");
		String servicePriceFrom = request.getParameter("f_operation_price_from");
		String servicePriceTo = request.getParameter("f_operation_price_to");

		request.setAttribute("f_name_value", serviceName);
		request.setAttribute("f_description_value", serviceDescription);
		
		if(serviceId == null && serviceName == null && servicePriceFrom == null && servicePriceTo == null && serviceDescription == null) {
			// Show services
			ServicesDao servicesDao = new ServicesDao();
			ArrayList<Services> servicesList = null;
			servicesList = servicesDao.getAllServices();
			
			request.removeAttribute("operationsList");
			request.setAttribute("operationsList", servicesList);	
		}
		else {
			float priceFrom = -1f;
			float priceTo = -1f;
			int serviceIdInt = -1;
			if(servicePriceFrom != null) {
				try {
					servicePriceFrom = servicePriceFrom.replace(",", ".");
					priceFrom = Float.parseFloat(servicePriceFrom);
					if(priceFrom > 0.)
						request.setAttribute("f_price_value_from", servicePriceFrom);
				}
				catch(NumberFormatException e) {
					priceFrom = -1f;
				}
			}
			if(servicePriceTo != null) {
				try {
					servicePriceTo = servicePriceTo.replace(",", ".");
					priceTo = Float.parseFloat(servicePriceTo);
					if(priceTo > 0.)
						request.setAttribute("f_price_value_to", servicePriceTo);
				}
				catch(NumberFormatException e) {
					priceTo = -1f;
				}
			}
			if(serviceId != null) {
				try {
					serviceIdInt = Integer.parseInt(serviceId);
					if(serviceIdInt > 0)
						request.setAttribute("f_id_value", serviceId);
				}
				catch(NumberFormatException e) {
					serviceIdInt = -1;
				}
			}
			// Show services
			ServicesDao servicesDao = new ServicesDao();
			ArrayList<Services> servicesList = null;
			servicesList = servicesDao.getServices(serviceIdInt, serviceName, serviceDescription, priceFrom, priceTo);
			
			request.removeAttribute("operationsList");
			request.setAttribute("operationsList", servicesList);	
		}
		
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/WEB-INF/views/services-prices.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("formAction");
		
		if(action != null && action.equals("add")) {
			String name = request.getParameter("operation_name");
			String description = request.getParameter("operation_description");
			String price = request.getParameter("operation_price");
			if(price != null) {
				price = price.replace(",",".");
			}
			float priceFloat = 0f;
			boolean everythingGood = true;
			
			try {
				priceFloat = Float.parseFloat(price);
			}
			catch(NumberFormatException e) {
				priceFloat = 0f;
				everythingGood = false;
			}
			
			if(everythingGood && name != null && !name.equals("") && priceFloat > 0. && description != null && !description.equals("")) {
				ServicesDao servicesDao = new ServicesDao();
				try {
					String info = servicesDao.addService(name, description, priceFloat);
					request.removeAttribute("userNotification");
					request.setAttribute("userNotification", info);
				} catch (SQLException e) {
					request.removeAttribute("userNotification");
					request.setAttribute("userNotification", "Error while adding new service to Database");
				}
				finally {
					doGet(request, response);
				}
			}
			else {
				request.removeAttribute("userNotification");
				request.setAttribute("userNotification", "Wrong parameters, try again");
				request.setAttribute("price_value", price);
				request.setAttribute("description_value", description);
				request.setAttribute("name_value", name);
				doGet(request, response);
			}
		}
		else if(action != null && action.equals("edit")){
			String name = request.getParameter("operation_name");
			String price = request.getParameter("operation_price");
			String description = request.getParameter("operation_description");
			String serviceId = request.getParameter("operation_id");
			
			if(price != null) {
				price = price.replace(",",".");
			}
			
			float priceFloat = 0f;
			int serviceIdInt = 0;
			boolean everythingGood = true;
			
			try {
				priceFloat = Float.parseFloat(price);
				serviceIdInt = Integer.parseInt(serviceId);
			}
			catch(NumberFormatException e) {
				priceFloat = 0f;
				serviceIdInt = 0;
				everythingGood = false;
			}
			
			if(everythingGood && name != null && !name.equals("") && priceFloat > 0.) {
				ServicesDao servicesDao = new ServicesDao();
				try {
					String info = servicesDao.updateService(serviceIdInt, name, description, priceFloat);
					request.removeAttribute("userNotificationEdit");
					request.setAttribute("userNotificationEdit", info);
				} catch (SQLException e) {
					request.removeAttribute("userNotificationEdit");
					request.setAttribute("userNotificationEdit", "Error while updating service info");
				}
				finally {
					doGet(request, response);
				}
			}
			else {
				request.removeAttribute("userNotificationEdit");
				request.setAttribute("userNotificationEdit", "Wrong parameters, try again");
				request.setAttribute("edit_price_value", price);
				request.setAttribute("edit_name_value", name);
				request.setAttribute("edit_description_value", description);
				request.setAttribute("edit_id_value", serviceId);
				doGet(request, response);
			}
			
		}
		else if(action != null && action.equals("fillForm")){
			
			String edit = request.getParameter("fillId");
	 		int serviceId = 0;
	 		
	 		if(edit != null) {
	 			try {
	 				serviceId = Integer.parseInt(edit);
	 			}
	 			catch(NumberFormatException e) {
	 				serviceId = 0;
				}
	 		}
	 		if(serviceId > 0) {
	 			ServicesDao servicesDao = new ServicesDao();
				Services service = null;
				try {
					service = servicesDao.getService(serviceId);
					if(service != null) {
						request.removeAttribute("userNotificationEdit");
						request.setAttribute("userNotificationEdit", "Fill form");
						request.setAttribute("edit_price_value", service.getPrice());
						request.setAttribute("edit_name_value", service.getName());
						request.setAttribute("edit_description_value", service.getDescription());
						request.setAttribute("edit_id_value", service.getId());
					}
				} catch (SQLException e) {
				}
			}
	 		doGet(request, response);
		}
		else {
			request.removeAttribute("userNotification");
			request.setAttribute("userNotification", "Wrong action, try again");
			doGet(request, response);
		}
	}
}
