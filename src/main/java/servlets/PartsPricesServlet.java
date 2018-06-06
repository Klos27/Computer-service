package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PartsDao;
import models.Parts;

@WebServlet("/service/parts")
public class PartsPricesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		
		String partId = request.getParameter("f_part_id");
		String partName = request.getParameter("f_part_name");
		String partPriceFrom = request.getParameter("f_part_price_from");
		String partPriceTo = request.getParameter("f_part_price_to");

		request.setAttribute("f_name_value", partName);
		
		if(partId == null && partName == null && partPriceFrom == null && partPriceTo == null) {
			// Show parts
			PartsDao partsDao = new PartsDao();
			ArrayList<Parts> partsList = null;
			partsList = partsDao.getAllParts();
			
			request.removeAttribute("partsList");
			request.setAttribute("partsList", partsList);	
		}
		else {
			float priceFrom = -1f;
			float priceTo = -1f;
			int partIdInt = -1;
			if(partPriceFrom != null) {
				try {
					partPriceFrom = partPriceFrom.replace(",", ".");
					priceFrom = Float.parseFloat(partPriceFrom);
					if(priceFrom > 0.)
						request.setAttribute("f_price_value_from", partPriceFrom);
				}
				catch(NumberFormatException e) {
					priceFrom = -1f;
				}
			}
			if(partPriceTo != null) {
				try {
					partPriceTo = partPriceTo.replace(",", ".");
					priceTo = Float.parseFloat(partPriceTo);
					if(priceTo > 0.)
						request.setAttribute("f_price_value_to", partPriceTo);
				}
				catch(NumberFormatException e) {
					priceTo = -1f;
				}
			}
			if(partId != null) {
				try {
					partIdInt = Integer.parseInt(partId);
					if(partIdInt > 0)
						request.setAttribute("f_id_value", partId);
				}
				catch(NumberFormatException e) {
					partIdInt = -1;
				}
			}
			// Show parts
			PartsDao partsDao = new PartsDao();
			ArrayList<Parts> partsList = null;
			partsList = partsDao.getParts(partIdInt, partName, priceFrom, priceTo);
			
			request.removeAttribute("partsList");
			request.setAttribute("partsList", partsList);	
		}
		
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/WEB-INF/views/parts-prices.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("formAction");
		
		if(action != null && action.equals("add")) {
			String name = request.getParameter("part_name");
			String price = request.getParameter("part_price");
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
			
			if(everythingGood && name != null && !name.equals("") && priceFloat > 0.) {
				PartsDao partsDao = new PartsDao();
				try {
					String info = partsDao.addPart(name, priceFloat);
					request.removeAttribute("userNotification");
					request.setAttribute("userNotification", info);
				} catch (SQLException e) {
					request.removeAttribute("userNotification");
					request.setAttribute("userNotification", "Error while adding new part to Database");
				}
				finally {
					doGet(request, response);
				}
			}
			else {
				request.removeAttribute("userNotification");
				request.setAttribute("userNotification", "Wrong parameters, try again");
				request.setAttribute("price_value", price);
				request.setAttribute("name_value", name);
				doGet(request, response);
			}
		}
		else if(action != null && action.equals("edit")){
			String name = request.getParameter("part_name");
			String price = request.getParameter("part_price");
			String partId = request.getParameter("part_id");
			
			if(price != null) {
				price = price.replace(",",".");
			}
			
			float priceFloat = 0f;
			int partIdInt = 0;
			boolean everythingGood = true;
			
			try {
				priceFloat = Float.parseFloat(price);
				partIdInt = Integer.parseInt(partId);
			}
			catch(NumberFormatException e) {
				priceFloat = 0f;
				partIdInt = 0;
				everythingGood = false;
			}
			
			if(everythingGood && name != null && !name.equals("") && priceFloat > 0.) {
				PartsDao partsDao = new PartsDao();
				try {
					String info = partsDao.updatePart(partIdInt, name, priceFloat);
					request.removeAttribute("userNotificationEdit");
					request.setAttribute("userNotificationEdit", info);
				} catch (SQLException e) {
					request.removeAttribute("userNotificationEdit");
					request.setAttribute("userNotificationEdit", "Error while updating part info");
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
				request.setAttribute("edit_id_value", partId);
				doGet(request, response);
			}
			
		}
		else if(action != null && action.equals("fillForm")){
			
			String edit = request.getParameter("fillId");
	 		int partId = 0;
	 		
	 		if(edit != null) {
	 			try {
	 				partId = Integer.parseInt(edit);
	 			}
	 			catch(NumberFormatException e) {
	 				partId = 0;
				}
	 		}
	 		if(partId > 0) {
	 			PartsDao partsDao = new PartsDao();
				Parts part = null;
				try {
					part = partsDao.getPart(partId);
					if(part != null) {
						request.removeAttribute("userNotificationEdit");
						request.setAttribute("userNotificationEdit", "Fill form");
						request.setAttribute("edit_price_value", part.getPrice());
						request.setAttribute("edit_name_value", part.getName());
						request.setAttribute("edit_id_value", part.getId());
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
