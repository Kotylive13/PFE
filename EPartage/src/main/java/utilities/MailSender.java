package utilities;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class for sending emails
 * 
 * @author Asma
 * @version 1.0
 */

public class MailSender {

	/**
	 * Send emails
	 * 
	 * @param to
	 *            to
	 * @param subject
	 *            subject
	 * @param body
	 *            body
	 */
	public static void sendEmail(String to, String subject, String body) {

		// Create all the needed properties
		Properties connectionProperties = new Properties();
		// SMTP host
		connectionProperties.put("mail.smtp.host", "smtp.gmail.com");
		// Is authentication enabled
		connectionProperties.put("mail.smtp.auth", "true");
		// Is StartTLS enabled
		connectionProperties.put("mail.smtp.starttls.enable", "true");
		// SSL Port
		connectionProperties.put("mail.smtp.socketFactory.port", "465");
		// SSL Socket Factory class
		connectionProperties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		// SMTP port, the same as SSL port :)
		connectionProperties.put("mail.smtp.port", "465");

		System.out.print("Creating the session...");

		// Compte gmail : Luminy.annuaire@gmail.com
		// password : nnu1987

		// Create the session
		Session session = Session.getDefaultInstance(connectionProperties,
				new javax.mail.Authenticator() { // Define
					// the
					// authenticator
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"Luminy.annuaire@gmail.com", "annu1987");
					}
				});

		System.out.println("done!");

		// Create and send the message
		try {
			// Create the message
			Message message = new MimeMessage(session);
			// Set sender
			message.setFrom(new InternetAddress("Luminy.annuaire@gmail.com"));
			// Set the recipients
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			// Set message subject
			message.setSubject(subject);
			// Set message text
			message.setText(body);

			System.out.print("Sending message...");
			// Send the message
			Transport.send(message);

			System.out.println("done!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
