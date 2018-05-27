package services;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Mail {
	
    String sendrmailid = "docelu.smtp@gmail.com";	  
   //Mention user name and password as per your configuration
    final String uname = "docelu.smtp@gmail.com";
    final String pwd = "Javadocelu1";
    //We are using relay.jangosmtp.net for sending emails
    String smtphost = "smtp.gmail.com";
    Session sessionobj;
    
    public Mail() {
    	
    	//Set properties and their values
        Properties propvls = new Properties();
        propvls.put("mail.transport.protocol", "smtps");
        propvls.put("mail.smtp.host", smtphost);
        propvls.put("mail.smtp.port", "587");
        propvls.put("mail.smtp.auth", "true");
        propvls.put("mail.smtp.starttls.enable", "true");

        
        //Create a Session object & authenticate uid and pwd
        sessionobj = Session.getInstance(propvls,
           new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(uname, pwd);
              }
        });
        //sessionobj.setDebug(true);
    }
    
	public String sendEmail(String to, String token) {
		
	      try {
		   	   //Create MimeMessage object & set values
		   	   Message messageobj = new MimeMessage(sessionobj);
		   	   messageobj.setFrom(new InternetAddress(sendrmailid));
		   	   messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		   	   messageobj.setSubject("Computer Service - forgot password");
		   	   messageobj.setText("Your new password: "+token);
		   	  //Now send the message
		   	   Transport.send(messageobj);
		   	   return "E-mail zosta³ wys³any!";
	      } catch (MessagingException exp) {
	    	  return "Nie uda³o siê wys³aæ e-maila";
	    	  //throw new RuntimeException(exp);
	      }	
	      
	}
	
}