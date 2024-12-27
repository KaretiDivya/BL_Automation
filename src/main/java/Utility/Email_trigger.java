package Utility;

import java.io.File;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class Email_trigger {

	 public static void sendEmail(String to, String subject, String htmlContent, String attachmentPath) {
	        try {
	            System.setProperty("mail.debug", "true"); // Enable debug logs
	            System.out.println("Sending Email...");

	            // Create a new HtmlEmail object
	            HtmlEmail email = new HtmlEmail();
	            email.setHostName("smtp.gmail.com");
	            email.setSmtpPort(587); // TLS port for Gmail
	            email.setAuthenticator(new DefaultAuthenticator("divyakareti22@gmail.com", "eicr guqc elge aton"));
	            email.setStartTLSRequired(true);
	            email.setFrom("divyakareti22@gmail.com"); // Sender's email address
	            email.setSubject(subject); // Email subject

	            // Setting HTML content for the email body
	            email.setMsg(htmlContent);

	            // Add recipient email
	            email.addTo(to);

	            // Attach a file if provided
	            if (attachmentPath != null && !attachmentPath.isEmpty()) {
	                email.attach(new File(attachmentPath));
	            }

	            // Send the email
	            email.send();

	            System.out.println("Email sent successfully.");
	        } catch (EmailException e) {
	            System.err.println("Failed to send email: " + e.getMessage());
	        }
	    }

	    public static void main(String[] args) {
	        // Example usage of the sendEmail method
	        String to = "divya.k@dhruvts.com";  // Replace with recipient email
	        String subject = "Test HTML Report";
	        String htmlContent = "<html><body>"
	                + "<h1>Test Report</h1>"
	                + "<p>This is a test email with an HTML body and an attachment.</p>"
	                + "</body></html>";
	        String attachmentPath = "C:\\WorkSpace\\BatchLoaderAutomationSuite\\reports\\Batchloader Reports.html";  // Replace with your file path

	        // Call the reusable sendEmail function
	        sendEmail(to, subject, htmlContent, attachmentPath);
	    }
	}
   
//        try {
//            System.out.println("Sending Email...");
//
//            // Set up Outlook SMTP server properties
//            Properties props = new Properties();
//            props.put("mail.smtp.host", "smtp.office365.com"); // Outlook SMTP server
//            props.put("mail.smtp.port", "587"); // TLS port
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
//
//            // Outlook credentials
//            String username = "divya.k@dhruvts.com"; // Replace with your Outlook email
//            String password = "Chitti@12345"; // Replace with your Outlook password or app password
//
//
//            // Get the Session object
//            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password);
//                }
//            });
//
//            // Create the email content
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("divya.k@dhruvts.com")); // Replace with recipient's email
//            message.setSubject("Test HTML Report");
//
//            // Set up the email body
//            String htmlContent = "<html><body>"
//                                + "<h1>Test Report</h1>"
//                                + "<p>This is a test email with an HTML body and an attachment.</p>"
//                                + "</body></html>";
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setContent(htmlContent, "text/html");
//
//            // Attach the report file
//            MimeBodyPart attachmentPart = new MimeBodyPart();
//            attachmentPart.attachFile(new File("C:\\path\\to\\your\\report.html")); // Replace with the path to your attachment
//
//            // Create a multipart message for the email
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
//            multipart.addBodyPart(attachmentPart);
//
//            // Set the content of the email message
//            message.setContent(multipart);
//
//            // Send the email
//            Transport.send(message);
//            System.out.println("Email sent successfully.");
//
//        } catch (Exception e) {
//            System.err.println("Failed to send email: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}




       