package com.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public Mail() {
	}

	public void sendMail(String username, String recepient,String sha1String) {

		System.out.println("preparing to send email");
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		//String recepient = "evgenise093@gmail.com";
		String myAccountEmail = "communicationltdproj@gmail.com";
		String password = "Tomcat!234";

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}

		});

		Message message = prepareMessage(session, myAccountEmail, recepient,sha1String,username);

		try {
			Transport.send(message);
			System.out.println("message sent! ");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("mail didnt saved");
			e.printStackTrace();
		}

	}
	
	
	
	
	private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String sha1String,String username) {

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Restarting password for CommunicationLTD");
			message.setText("Hello " + username + ",\nWe recived your request to restart your password.\nPlease copy the String to verify.\nString to verify:  " + sha1String);
			return message;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}


}
