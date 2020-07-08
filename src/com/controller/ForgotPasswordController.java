package com.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.LoginDAOImpl;
import com.dao.LoginDao;
import com.entity.Login;
import com.model.PasswordHandler;
import com.util.Mail;

import java.math.BigInteger;
import java.security.MessageDigest;

public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PasswordHandler passwordHandler;
	String generatedValue;
	String sha1 = null;
	LoginDao loginDao;
	Login user;
	Mail mail;
	HttpSession httpSession;

	public ForgotPasswordController() {
		mail = new Mail();

	}

	// entering the page.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		httpSession = request.getSession();

	}

	// pressing submit
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		httpSession = req.getSession();
		String sendMailBtn = req.getParameter("SendMailBtn");
		String verifyBtn = req.getParameter("submitBtn");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String verifyString = req.getParameter("EmailValue");

		// pressed on send Email
		if ("sendEmail".equals(sendMailBtn)) {
			System.out.println("entered to sendMailButton");
			loginDao = new LoginDAOImpl();
			user = null;
			user = loginDao.getUserNameEmail(username, email);
			if (user == null) {
				System.out.println("username or password dosent match");
				resp.sendRedirect("views/ForgotPasswordPage.jsp");
				return;

			} else {
				
				// the user is correct, now we will generate and send the sha1
				
				// uesr != null
				// check if the username and the email are correct
				passwordHandler = new PasswordHandler();

				passwordHandler.setGeneratedString();

				generatedValue = passwordHandler.getGeneratedString();
				sha1 = passwordHandler.sha1Algo(generatedValue);
				//System.out.println("user.getUsername() " + user.getUsername());
				//System.out.println("user.getEmail() " + user.getEmail());
				//System.out.println("sha1 " + sha1);

				// sending the mail with the generated sha1
				mail.sendMail(user.getUsername(), user.getEmail(), sha1);

				httpSession.setAttribute("username", username);
				httpSession.setAttribute("email", email);
				// redirect to the same page.
				resp.sendRedirect("views/ForgotPasswordPage.jsp");
				return;
				

			}

			// pressed on verify the sha1 from the email
		} else if ("submit".equals(verifyBtn)) {
			//System.out.println("pressed on verify ");
			//System.out.println("sha1 -> " + sha1);
			//System.out.println("verifyString ->" + verifyString);
			if (sha1 != null) {
				if (sha1.equals(verifyString)) {

					System.out.println("you got the correct sha1 String ");

					httpSession.setAttribute("username", username);
					httpSession.setAttribute("email", email);
					resp.sendRedirect("views/NewPasswordPage.jsp");
					return;

				} else {
					System.out.println("incorrect sha1");
					resp.sendRedirect("views/ForgotPasswordPage.jsp");
					return;

				}

			}else {
				
				System.out.println("you still didnt insert username and email correctly");
				resp.sendRedirect("views/ForgotPasswordPage.jsp");
				return;

			}

		}

	}

}
