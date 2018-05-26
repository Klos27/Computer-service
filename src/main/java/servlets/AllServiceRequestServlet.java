package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PartsDao;
import dao.ServiceRequestDao;
import dao.ServicesDao;
import models.Parts;
import models.ServiceRequest;
import models.Services;
import models.User;

@WebServlet("/user/existing-requests")
public class AllServiceRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		StringBuffer text = new StringBuffer("");
		
		if(user == null) {
			text.append("You are not logged in!");
		}
		else
		{
			String idstr = request.getParameter("id");
			int reqId = 0;
			try {
				reqId = Integer.parseInt(idstr);
			}
			catch(Exception e) {
				reqId = 0;
			}
			if(reqId == 0) {
				// show all service requests
				
				text.append("<style> td { text-align:center; vertical-align:middle; padding: 5px;} .description { text-align: justify; } </style>").append("\n");			
				text.append("<table width=\"880\" border=\"1\" align=\"center\">").append("\n");
				text.append("<tr>").append("\n");
				text.append("<td width=\"80\">ID</td>").append("\n");
				text.append("<td width=\"100\">Status</td>").append("\n");
				text.append("<td width=\"100\">Start date</td>").append("\n");
				text.append("<td width=\"100\">End date</td>").append("\n");
				text.append("<td width=\"500\">Description</td>").append("\n");
				text.append("</tr>").append("\n");

				//====== get data from DB
				
				ServiceRequestDao srd = new ServiceRequestDao();
				ArrayList<ServiceRequest> list = null;
				try {
					list = srd.getAllServiceRequests(user.getId());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for(ServiceRequest sr : list) {
					text.append("<tr>").append("\n");
					
					text.append("<td><a href=?id=" + sr.getId() + ">" + sr.getId() + "</a></td>").append("\n");
					switch(sr.getStatus()) {
						case 0:
							text.append("<td>New</td>").append("\n");
							break;
						case 1:
							text.append("<td>Checking</td>").append("\n");
							break;
						case 2:
							text.append("<td>In porgress</td>").append("\n");
							break;
						case 3:
							text.append("<td>Waiting for payment</td>").append("\n");
							break;
						case 4:
							text.append("<td>Ended</td>").append("\n");
							break;
						default:
							text.append("<td>----</td>").append("\n");
					}
					text.append("<td>" + sr.getStart_date() + "</td>").append("\n");
					
					if(sr.getEnd_date() == null)
						text.append("<td>----</td>").append("\n");
					else
						text.append("<td>" + sr.getEnd_date() + "</td>").append("\n");
					
					text.append("<td class=\"description\">" + sr.getDescription() + "</td>").append("\n");
					
					text.append("</tr>").append("\n");
				}			
				//======
				text.append("</table>").append("\n");
			}
			else {
				// show specific service request
				ServiceRequestDao srd = new ServiceRequestDao();
				
				ServiceRequest sr = null;
				try {
					sr = srd.getServiceRequest(user.getId(), reqId);
				} catch (SQLException e) {
					//e.printStackTrace();
				}			
				
				if(sr == null)
					text.append("You are not owner of this service request or this request does not exist").append("\n");
				else {
					text.append("<style> td { text-align:center; vertical-align:middle; padding: 5px;} .description { text-align: justify; } </style>").append("\n");			
					text.append("<table width=\"880\" border=\"1\" align=\"center\">").append("\n");
					text.append("<tr>").append("\n");
					text.append("<td width=\"80\">ID</td>").append("\n");
					text.append("<td width=\"100\">Status</td>").append("\n");
					text.append("<td width=\"100\">Start date</td>").append("\n");
					text.append("<td width=\"100\">End date</td>").append("\n");
					text.append("<td width=\"500\">Description</td>").append("\n");
					text.append("</tr>").append("\n");
					
					text.append("<tr>").append("\n");
					
					text.append("<td>" + sr.getId() + "</td>").append("\n");
					switch(sr.getStatus()) {
						case 0:
							text.append("<td>New</td>").append("\n");
							break;
						case 1:
							text.append("<td>Checking</td>").append("\n");
							break;
						case 2:
							text.append("<td>In porgress</td>").append("\n");
							break;
						case 3:
							text.append("<td>Waiting for payment</td>").append("\n");
							break;
						case 4:
							text.append("<td>Ended</td>").append("\n");
							break;
						default:
							text.append("<td>----</td>").append("\n");
					}
					text.append("<td>" + sr.getStart_date() + "</td>").append("\n");
					
					if(sr.getEnd_date() == null)
						text.append("<td>----</td>").append("\n");
					else
						text.append("<td>" + sr.getEnd_date() + "</td>").append("\n");
					
					text.append("<td class=\"description\">" + sr.getDescription() + "</td>").append("\n");
					text.append("</tr>").append("\n");
					text.append("</table>").append("\n");
					
					//=========== SERVICE OPERATIONS
					text.append("<br/><br/><p>SERVICE OPERATIONS:</p><br/>").append("\n");
					float operationsSum = 0f;
					ServicesDao servicesDao =  new ServicesDao();
					ArrayList<Services> servicesList = null;
					try {
						servicesList = servicesDao.getAllServices(reqId);
					} catch (SQLException e) {
						//e.printStackTrace();
					}
					
					text.append("<table width=\"880\" border=\"1\" align=\"center\">").append("\n");
					text.append("<tr>").append("\n");
					text.append("<td width=\"80\">ID</td>").append("\n");
					text.append("<td width=\"150\">Name</td>").append("\n");
					text.append("<td width=\"550\">Description</td>").append("\n");
					text.append("<td width=\"100\">Price</td>").append("\n");
					text.append("</tr>").append("\n");
					
					for(Services services : servicesList) {
						text.append("<tr>").append("\n");
						text.append("<td>"+ services.getId() + "</td>").append("\n");
						text.append("<td>" + services.getName() + "</td>").append("\n");
						text.append("<td class=\"description\">" + services.getDescription() + "</td>").append("\n");
						text.append("<td>" + services.getPrice() + "</td>").append("\n");
						text.append("</tr>").append("\n");
						operationsSum += services.getPrice();
					}			
					text.append("<tr>").append("\n");
					text.append("<td>SUM</td>").append("\n");
					text.append("<td>-</td>").append("\n");
					text.append("<td>-</td>").append("\n");
					text.append("<td>" + operationsSum + "</td>").append("\n");
					text.append("</tr>").append("\n");
					
					
					text.append("</table>").append("\n");
					//=========== SERVICE PARTS
					text.append("<br/><br/><p>SERVICE PARTS:</p><br/>").append("\n");
					float partsSum = 0f;
					PartsDao partsDao = new PartsDao();
					ArrayList<Parts> partsList = null;
					try {
						partsList = partsDao.getAllParts(reqId);
					} catch (SQLException e) {
						//e.printStackTrace();
					}
					text.append("<table width=\"880\" border=\"1\" align=\"center\">").append("\n");
					text.append("<tr>").append("\n");
					text.append("<td width=\"150\">ID</td>").append("\n");
					text.append("<td width=\"580\">Name</td>").append("\n");
					text.append("<td width=\"150\">Price</td>").append("\n");
					text.append("</tr>").append("\n");
					
					for(Parts parts : partsList) {
						text.append("<tr>").append("\n");
						text.append("<td>"+ parts.getId() + "</td>").append("\n");
						text.append("<td>" + parts.getName() + "</td>").append("\n");
						text.append("<td>" + parts.getPrice() + "</td>").append("\n");
						text.append("</tr>").append("\n");
						partsSum += parts.getPrice();
					}			
					text.append("<tr>").append("\n");
					text.append("<td>SUM</td>").append("\n");
					text.append("<td>-</td>").append("\n");
					text.append("<td>" + partsSum + "</td>").append("\n");
					text.append("</tr>").append("\n");
					
					text.append("</table>").append("\n");
					//=========== PAYMENT SECTION
					text.append("<br/><br/><p>PAYMENTS:</p><br/>[[Payments section]]").append("\n");
					//=========== CHAT	
					text.append("<br/><br/><p>CHAT:</p><br/>Chat will be there").append("\n");
				}
			}			
		}
		
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("userRequests", text);
		request.getRequestDispatcher("/WEB-INF/views/browse-requests.jsp").forward(request, response);
	}
}
