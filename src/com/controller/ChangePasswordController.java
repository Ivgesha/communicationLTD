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

public class ChangePasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public HttpSession session;
	public PasswordHandler passwordHandler;
	public LoginDao loginDao;
	public Login user, updatedUser;
	char currentCharacter;
	public boolean numberPresent = false;
	public boolean upperCasePresent = false;
	public boolean lowerCasePresent = false;
	public boolean specialCharacterPresent = false;
	public String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
	private byte[] generatedSalt;
	public String saltString;
	public String dataPassword;
	public String newHashedPass;

	public ChangePasswordController() {
		System.out.println("entered ChangePasswordController c'tor\n");

		loginDao = new LoginDAOImpl();
		user = new Login();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	
	
// pressing submit button
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session = request.getSession();
		String username = (String) session.getAttribute("username");
		int i = 0;
		String insertedPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		String hashedPass = null;
		boolean correctPass = false;
		user = loginDao.get(username);
		// check if we got the user 
		if (user == null) {
			// error
			System.out.println("Error.\nSomething went wrong");
			return;

		}
		
		// generating all the ecnryption we need
		String salt = user.getSalt();
		String currentPassword = salt + insertedPassword;
		passwordHandler = new PasswordHandler();
		try {
			System.out.println("entered the hash func in ChangePasswordController");
			hashedPass = PasswordHandler.HmacSHA1(currentPassword, username);

			if (hashedPass.equals(user.getPassword())) {
				correctPass = true;
				// the password the user entered is correct
				
			} else {
				correctPass = false;
				System.out.println("Error.\nNew password/confirm password are empty. ");
				response.sendRedirect("views/ChangePasswordPage.jsp");
				return;
			}
			
		} catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if we got the right password, now we can set new one
		if (correctPass == true) {
			// checks that the password is correct 
			if (passwordHandler.passwordComparison(newPassword, confirmPassword)) {
				if (newPassword.length() >= 10) {
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
							newHashedPass = PasswordHandler.HmacSHA1(dataPassword, username);
							updatedUser = new Login();
							updatedUser.setUsername(username);
							updatedUser.setPassword(newHashedPass);
							updatedUser.setSalt(saltString);

							loginDao.updateUser(updatedUser);
							// returning the vars to false for the next time
							numberPresent = false;
							upperCasePresent = false;
							lowerCasePresent = false;
							specialCharacterPresent = false;
							correctPass = false;
							session.setAttribute("username", username);

							response.sendRedirect("CustomerController?action=LIST");
							return;

						} catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {

						// you dont have uppercase\lowercase\digit\special char
						System.out.println(
								"password missing one of the folowing: specialChar,upperChar,lowerChar, digitChar\n");
						// need to add redirects
						//resp.sendRedirect("/OperatorRegisterController");
						response.sendRedirect("views/ChangePasswordPage.jsp");
						return;
					}

				} else {
					System.out.println("password to short");
					// need to add redirects
					// resp.sendRedirect("/OperatorRegisterController");
					response.sendRedirect("views/ChangePasswordPage.jsp");
					return;
					// password is too short
				}

			} else {

				// the confirmpass and new pass is different
				System.out.println("password and validationPassword dosent match");
				// need to add redirects
				// resp.sendRedirect("/OperatorRegisterController");
				response.sendRedirect("views/ChangePasswordPage.jsp");
				return;

			}

		} else {

			// password is not correct

			System.out.println("password and validationPassword dosent match");
			// need to add redirects
			// resp.sendRedirect("/OperatorRegisterController");
			response.sendRedirect("views/ChangePasswordPage.jsp");
			return;
		}

	}

}
