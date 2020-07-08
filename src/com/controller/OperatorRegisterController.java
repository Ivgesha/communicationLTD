package com.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.PasswordHandler;
import com.dao.CustomerDAO;
import com.dao.LoginDAOImpl;
import com.dao.LoginDao;
import com.entity.Customer;
import com.entity.Login;

public class OperatorRegisterController extends HttpServlet {
	private Login user = null;
	private String hashedPass;
	private byte[] generatedSalt;
	private String saltString;
	private String dataPassword;
	String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
	char currentCharacter;
	boolean numberPresent = false;
	boolean upperCasePresent = false;
	boolean lowerCasePresent = false;
	boolean specialCharacterPresent = false;
	CustomerDAO customerDAO = null;
	RequestDispatcher dispatcher = null;
	HttpSession session;
	private static final long serialVersionUID = 1L;
	LoginDao loginDao = null;
	PasswordHandler passwordHandler = null;

	public OperatorRegisterController() {

		loginDao = new LoginDAOImpl();
		passwordHandler = new PasswordHandler();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int i = 0;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String validationPassword = req.getParameter("validationPassword");
		// setting all needed values for check to false
		numberPresent = false;
		upperCasePresent = false;
		lowerCasePresent = false;
		specialCharacterPresent = false;
		

		
		// checking if the password is good 
		if (passwordHandler.passwordComparison(password, validationPassword)) {
			// the password and the validationPassword are the same
			if (password.length() >= 10) {
				for (i = 0; i < password.length(); i++) {
					currentCharacter = password.charAt(i);
					if (Character.isDigit(currentCharacter)) {
						numberPresent = true;
					} else if (Character.isUpperCase(currentCharacter)) {
						upperCasePresent = true;
					} else if (Character.isLowerCase(currentCharacter)) {
						lowerCasePresent = true;
					} else if (specialChars.contains(String.valueOf(currentCharacter))) {
						specialCharacterPresent = true;
					}
				}

				if (numberPresent == true && upperCasePresent == true && lowerCasePresent == true
						&& specialCharacterPresent == true) {
					try {
						generatedSalt = PasswordHandler.generateSalt();
						saltString = PasswordHandler.toHexString(generatedSalt);
						dataPassword = saltString + password;
						// hashing the password and using the username as a key.
						hashedPass = PasswordHandler.HmacSHA1(dataPassword, username);
						user = new Login();
						user.setUsername(username);
						user.setPassword(hashedPass);
						user.setSalt(saltString);
						user.setEmail(email);
						if(loginDao.insertUser(user)) {
							// if we managed to insert correctly, well be back to index.jsp // 

							resp.sendRedirect("index.jsp");
							return;	
						}else {
							resp.sendRedirect("views/operator-register.jsp");
							return;
							
						}

					} catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// password missing on of the specialChar,upperChar,lowerChar,digitChar
					System.out.println(
							"password missing one of the folowing: specialChar,upperChar,lowerChar, digitChar\n");
					resp.sendRedirect("views/operator-register.jsp");
					return;
				}

			} else {
				// password to short
				System.out.println("password to short");
				resp.sendRedirect("views/operator-register.jsp");
				return;

			}

		} else {

			// the password and the validationPassword dont match
			System.out.println("password and validationPassword dosent match");
			resp.sendRedirect("views/operator-register.jsp");
			return;
			}
		 

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		
		String username = (String) session.getAttribute("username");
		String action = request.getParameter("email");
		String DiscardPasswordBtn = request.getParameter("DiscardPasswordBtn");
		if("DiscardPasswordVal".equals(DiscardPasswordBtn)) {
			System.out.println("DiscardPasswordBtn preesed\n");
			System.out.println("pressend discard, try to pring username " + username);
			
			
			session.setAttribute("username", username);
			
			dispatcher = request.getRequestDispatcher("/views/ChangePasswordPage.jsp");
			dispatcher.forward(request, response);
			return;
			
			
			
			
			
		}
		
		if(action != null){
			handleSendEmail(request, response);
			
		}

		if (action.equals("EDIT"))
			handleFrogetOperatorPassword(request, response);

	}
	
	public void handleSendEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String message = req.getParameter("message");
		
		//SendMail sendMail = new SendMail(email, " FROM: WHO SENDING THE MAIL", message, "localhost:8443");
		//sendMail.sendMailToClient();

		// TODO set the operator password to null , send a new hashed value

		// TODO add a new page to enter the value send by mail , and redirecting
		// to change password page if values are matched
	}
	
	
	
	
	

	public void handleFrogetOperatorPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		String username = (String) httpSession.getAttribute("username");

	}

}
