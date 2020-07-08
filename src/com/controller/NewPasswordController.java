package com.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.LoginDAOImpl;
import com.dao.LoginDao;
import com.entity.Login;
import com.model.PasswordHandler;

public class NewPasswordController extends HttpServlet {
	public PasswordHandler passwordHandler; 
	public HttpSession httpSession;
	public char currentCharacter;
	public boolean numberPresent;
	public boolean upperCasePresent;
	public boolean lowerCasePresent;
	public String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
	public boolean specialCharacterPresent;
	public byte[] generatedSalt;
	public String saltString;
	public String dataPassword;
	public String hashedPass;
	public Login user;
	public LoginDao loginDao;
	private static final long serialVersionUID = 1L;
	
	
	public NewPasswordController() {
		passwordHandler = new PasswordHandler();
		loginDao = new LoginDAOImpl();
	}
	
	
	// clicking on submit
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("entered doPost of NewPasswordController");
		httpSession = req.getSession();
		String username = req.getParameter("username");
		// or 
		//String username1 = (String) httpSession.getAttribute("username");
		
		// checks if the password is good and can be used
		System.out.println("entered doPost of NewPasswordController");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");
		int i = 0;
		if(passwordHandler.passwordComparison(newPassword, confirmPassword)) {
			if(newPassword.length() >= 10) {
				for (i = 0; i < newPassword.length(); i++) {
					currentCharacter = newPassword.charAt(i);
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
						dataPassword = saltString + newPassword;
						// hashing the password and using the username as a key.
						hashedPass = PasswordHandler.HmacSHA1(dataPassword, username);
						user = new Login();
						user.setUsername(username);
						user.setPassword(hashedPass);
						user.setSalt(saltString);
						loginDao.updateUser(user);
						// returning the vars to false for the next time
						numberPresent = false;
						upperCasePresent = false;
						lowerCasePresent = false;
						specialCharacterPresent = false;
						
						// tried to end session. 
						httpSession = null;
						// if everything is correct we will redirect to customerList
						resp.sendRedirect("index.jsp");
						return;
						
					} catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	
				}else {
							System.out.println("The specialChar/Digit/UpperCase/LowerCase is missing.");
							// special\digit\ upper\lower char is missing.
							resp.sendRedirect("views/NewPasswordPage.jsp");
							return;
						}

				
			}else {
				System.out.println("The password is shorter than 10 characters.");
				// password is shorter then 10
				resp.sendRedirect("views/NewPasswordPage.jsp");
				return;
			}
			
			
			
		}else {
			System.out.println("The confirmPassword dosent match.");
			// password dosent match
			resp.sendRedirect("views/NewPasswordPage.jsp");
			return;
		}
		
		// if for some reason you get here, it will redirect you back to the same page
		resp.sendRedirect("views/NewPasswordPage.jsp");
		return;
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		httpSession = request.getSession();
		
		
	}

}
