package services;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Mail {
	
    String sendrmailid = "docelu.smtp@gmail.com";	  
    final String uname = "docelu.smtp@gmail.com";
    final String pwd = "Javadocelu1";
    String smtphost = "smtp.gmail.com";
    Session sessionobj;
    
    public Mail() {
    	
        Properties propvls = new Properties();
        propvls.put("mail.transport.protocol", "smtps");
        propvls.put("mail.smtp.host", smtphost);
        propvls.put("mail.smtp.port", "587");
        propvls.put("mail.smtp.auth", "true");
        propvls.put("mail.smtp.starttls.enable", "true");

        sessionobj = Session.getInstance(propvls,
        new javax.mail.Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(uname, pwd);
        	}
        });
    }
    
	public void sendEmail(final String to, final String title, final String description) {
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        executor.execute(new Runnable() {
            @Override
            public void run() {
            	try {
   	   		   	   	Message messageobj = new MimeMessage(sessionobj);
   	   		   	   	messageobj.setFrom(new InternetAddress(sendrmailid));
   	   		   	   	messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
   	   		   	   	messageobj.setSubject("Computer Service - " + title);
   	   		   	   	messageobj.setText(description);
   	   		   	   	Transport.send(messageobj);
   	   	      	} catch (MessagingException exp) {
   	   	      		throw new RuntimeException("Couldnt send the email.");
   	   	      	}	
            }
        });
	      
	}
	
}