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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import dao.PartsDao;
import dao.PaymentDao;
import dao.ServicesDao;
import models.Parts;
import models.Payment;
import models.Services;
import models.User;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

@WebServlet("/user/download-invoice")
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public static final String DEST = "results/zugferd/pdf/basic%05d.pdf";
    public static final String ICC = "resources/data/sRGB_CS_profile.icm";
    public static final String FONT = "resources/fonts/OpenSans-Regular.ttf";
    public static final String FONTB = "resources/fonts/OpenSans-Bold.ttf";
 
    protected Font font10 = FontFactory.getFont(FONT,10, 2);
    protected Font font10b = FontFactory.getFont(FONT,10, 1);
    protected Font font12 = FontFactory.getFont(FONT,12, 2);
    protected Font font12b = FontFactory.getFont(FONT,12, 1);
    protected Font font14 = FontFactory.getFont(FONT,14, 2);
    protected Font font14b = FontFactory.getFont(FONT,14, 1);
    protected Font font16b = FontFactory.getFont(FONT,16, 1);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		
		String idstr = request.getParameter("invoice");
		String reqstr = request.getParameter("request");

		int invoiceId = 0;
		int requestId = 0;
		try {
			invoiceId = Integer.parseInt(idstr);
			requestId = Integer.parseInt(reqstr);
		}
		catch(Exception e) {
			invoiceId = 0;
			requestId = 0;
		}
		if(invoiceId == 0) {
			response.sendRedirect("/user/existing-requests");
		}
		else {
			// Get payment info from database
			PaymentDao paymentDao = new PaymentDao();
			Payment payment = null;
			try {
				payment = paymentDao.getPaymentInvoice(user.getId(), requestId, invoiceId);
			} catch (SQLException e) {
				request.setAttribute("errorNotification", "ServerError: Can't connect with database to get payment details.");
				request.getRequestDispatcher("/WEB-INF/views/error-page.jsp").forward(request, response);
				//e.printStackTrace();
			}			
			if(payment == null) {
				// This invoice doesn't belong to this user
				request.setAttribute("errorNotification", "This invoice does not belong to you or this invoice does not exist!");
				request.getRequestDispatcher("/WEB-INF/views/error-page.jsp").forward(request, response);
			}
			else {
				// Download invoice
				// Prepare data from database
				
				//=========== SERVICE OPERATIONS
				ServicesDao servicesDao =  new ServicesDao();
				ArrayList<Services> servicesList = null;
				try {
					servicesList = servicesDao.getAllServices(requestId);
				} catch (SQLException e) {
					request.setAttribute("errorNotification", "ServerError: Can't connect with database to get services list.");
					request.getRequestDispatcher("/WEB-INF/views/error-page.jsp").forward(request, response);
					//e.printStackTrace();
				}
		
				//=========== SERVICE PARTS
				PartsDao partsDao = new PartsDao();
				ArrayList<Parts> partsList = null;
				try {
					partsList = partsDao.getAllParts(requestId);
				} catch (SQLException e) {
					request.setAttribute("errorNotification", "ServerError: Can't connect with database to get parts list.");
					request.getRequestDispatcher("/WEB-INF/views/error-page.jsp").forward(request, response);
					//e.printStackTrace();
				}

				// Generate PDF
				response.setContentType("application/pdf");
		        try {
		            Document document = new Document();
		            PdfWriter.getInstance(document, response.getOutputStream());
		            document.open();
		            
		            // Invoice Title
		            if(payment != null && payment.getCreation_date() != null)
		            	document.add(getInvoiceTitle(invoiceId, payment.getCreation_date().toString()));
		            else
		            	document.add(getInvoiceTitle(invoiceId, " "));
		            
		            // Seller / Buyer
			        PdfPTable table = new PdfPTable(3);
			        table.setWidthPercentage(100);
			   
			        PdfPCell seller = getPartyAddress("From:",
			                "PPPM Computer Service",
			                "Warszawska 24",
			                "31-155 Cracow",
			                "+48 111-222-333",
			                "contact@pppmcomputer.com");
			        table.addCell(seller);
			        
			        PdfPCell emptyCell = new PdfPCell();
			        emptyCell.setBorder(PdfPCell.NO_BORDER);
			        table.addCell(emptyCell);
			     
			        PdfPCell buyer = getPartyAddress("To:",
			                user.getFirst_name() + " " + user.getLast_name(),
			                user.getAddress(),
			                "",
			                user.getPhone(),
			                user.getEmail());
			        table.addCell(buyer);
			        
			        document.add(table);
			        
			        // List service operations
			        document.add(new Paragraph("Service operations: ", font14b)); 
			        document.add(addSpace());
				    document.add(getServiceOperations(servicesList));
 
				    // List service parts
				    document.add(new Paragraph("Service parts: ", font14b));    
				    document.add(addSpace());
				    document.add(getServiceParts(partsList));
				    
				    // Payment info
				    document.add(new Paragraph("Payment info: ", font14b));    
				    document.add(addSpace());
				    document.add(getPaymentInfo(payment,"ISI Bank 11 2222 3333 4444 5555 6666 7777", "Service request " + requestId + " invoice " + invoiceId));
				   
				    document.add(addSpace());
				    document.add(addSpace());
				    document.add(getSignatures("PPPM Computer Service", user.getFirst_name() + " " + user.getLast_name()));
				    
		            document.close();
		        } catch (DocumentException e) {
		        	request.setAttribute("errorNotification", "Something went wrong while generating PDF, please contact with service");
					request.getRequestDispatcher("/WEB-INF/views/error-page.jsp").forward(request, response);
		        	//e.printStackTrace();
		        } catch (Exception e) {
		        	request.setAttribute("errorNotification", "Server error while generating PDF, please contact with service");
					request.getRequestDispatcher("/WEB-INF/views/error-page.jsp").forward(request, response);
		        	//e.printStackTrace();
		        }
			}
		}
	}

	public PdfPTable getInvoiceTitle(int invoiceId, String date) {
		PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        
        PdfPCell invoiceNoCell = new PdfPCell(new Phrase("Invoice number: " + invoiceId, font16b));
        invoiceNoCell.setBorder(PdfPCell.NO_BORDER);
        invoiceNoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(invoiceNoCell);        
        
        PdfPCell dateCell = new PdfPCell(new Phrase(date, font16b));
        dateCell.setBorder(PdfPCell.NO_BORDER);
        dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(dateCell);
        
        return table;
	}
	
	public PdfPCell getPartyAddress(String who, String name, String line1, String line2, String phone, String email) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.addElement(new Paragraph(who, font14b));
        cell.addElement(new Paragraph(name, font12));
        cell.addElement(new Paragraph(line1, font12));
        cell.addElement(new Paragraph(line2, font12));
        cell.addElement(new Paragraph(phone, font12));
        cell.addElement(new Paragraph(email, font12));
        return cell;
    }
	
	public Paragraph addSpace() {
		return new Paragraph(" ", font10);
	}
	
    public PdfPCell getCell(String value, Font font, int alignment, boolean border) {
        PdfPCell cell = new PdfPCell(new Phrase(value, font));
        cell.setHorizontalAlignment(alignment);
        if(!border)
        	cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
	
    public PdfPCell getCell(String value, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(value, font));
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
    
    public PdfPCell getCell(String value, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(value, font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }
    
	public PdfPTable getServiceOperations(ArrayList<Services> servicesList) {
		float servicesSum = 0f;
		PdfPTable table = new PdfPTable(new float[]{1.5f,7f,1.5f});
        table.setWidthPercentage(100);
        
        // Add table header
        table.addCell(getCell("Operation No", font12, Element.ALIGN_CENTER));
        table.addCell(new Phrase("Operation name", font12));
        table.addCell(getCell("Operation Price", font12, Element.ALIGN_CENTER));
        
        if(servicesList != null) {
	        // Add table records
			for(Services service : servicesList) {
		        table.addCell(getCell(String.valueOf(service.getId()), font12, Element.ALIGN_CENTER));
		        table.addCell(new Phrase(service.getName(), font12));
		        table.addCell(getCell(String.valueOf(service.getPrice()), font12, Element.ALIGN_RIGHT));
		        
				servicesSum += service.getPrice();
			}	
        }
        
		// Add sum
		table.addCell(getCell("SUM", font12, Element.ALIGN_CENTER));
        table.addCell(new Phrase("", font12));
		table.addCell(getCell(String.valueOf(servicesSum), font12, Element.ALIGN_RIGHT));
        
        return table;
	}
	
	public PdfPTable getServiceParts(ArrayList<Parts> partsList) {
		float servicesSum = 0f;
		PdfPTable table = new PdfPTable(new float[]{1.5f,7f,1.5f});
        table.setWidthPercentage(100);
        
        // Add table header
        table.addCell(getCell("Part No", font12, Element.ALIGN_CENTER));
        table.addCell(new Phrase("Part name", font12));
        table.addCell(getCell("Part Price", font12, Element.ALIGN_CENTER));
        
        if(partsList != null) {
        	// Add table records
			for(Parts part : partsList) {
			    table.addCell(getCell(String.valueOf(part.getId()), font12, Element.ALIGN_CENTER));
		        table.addCell(new Phrase(part.getName(), font12));
			    table.addCell(getCell(String.valueOf(part.getPrice()), font12, Element.ALIGN_RIGHT));
		        
				servicesSum += part.getPrice();
			}	
        }
        
		// Add sum
	    table.addCell(getCell("SUM", font12, Element.ALIGN_CENTER));
        table.addCell(new Phrase("", font12));
	    table.addCell(getCell(String.valueOf(servicesSum), font12, Element.ALIGN_RIGHT));
        return table;
	}
	
	public PdfPTable getPaymentInfo(Payment payment, String accountNo, String title) {
		PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        
        table.addCell(getCell("Account:", font12, Element.ALIGN_LEFT, false));
        table.addCell(getCell("Total: " + payment.getAmount(), font12, Element.ALIGN_RIGHT, false));      
        
        table.addCell(getCell(accountNo, font12, Element.ALIGN_LEFT, false));
        
        if(payment.getStatus() == 0)
        	table.addCell(getCell("Status: UNPAID", font12, Element.ALIGN_RIGHT, false));     
        else
        	table.addCell(getCell("Status: PAID", font12, Element.ALIGN_RIGHT, false));  

        table.addCell(getCell("Title: " + title, font12, Element.ALIGN_LEFT, false));
        
        if(payment.getStatus() == 0) 
        	table.addCell(getCell("Payment deadline: 14 days", font12, Element.ALIGN_RIGHT, false)); 
        
        return table;
	}
	
	private PdfPTable getSignatures(String signature1, String signature2) {
		PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        
        PdfPCell cell = new PdfPCell(new Phrase("_________________________", font14));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        
        table.addCell(getCell(signature1, font14, Element.ALIGN_LEFT, false));
        table.addCell(getCell(signature2, font14, Element.ALIGN_RIGHT, false));
        
		return table;
	}
}