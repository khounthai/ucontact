package com.ril;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ril.entity.Mail;

public class SendEmail {
	
	private Mail mail;


	public SendEmail(Mail mail) {
		this.mail = mail;
		
	}

	public boolean send() {

		try {

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", mail.getHost());
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			// java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl);
			Session mailSession = Session.getDefaultInstance(props, null);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(mail.getFrom()));
			InternetAddress address = (new InternetAddress(mail.getTo()));
			msg.setRecipient(Message.RecipientType.TO, address);
			msg.setSubject(mail.getSubject());
			msg.setSentDate(new Date());
			msg.setText(mail.getMessageText());

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(mail.getHost(), mail.getUser(), mail.getPass());
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;

		} catch (Exception ex) {

			System.out.println(ex);

		}
		return false;

	}

}
