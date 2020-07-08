import java.io.IOException;
import java.io.PrintWriter;

import javax.net.ssl.SSLContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	
	
	public WelcomeClass(){

	System.out.println("entered constructor\n");
		
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// maybe we can switch the button from if's to switch case? 
		String loginButten = (String)request.getParameter("btnLogin");
		String registerButten =(String)request.getParameter("btnRegister");
		String changePassword = (String)request.getParameter("btnChangePassword");
		String forgotPassword = (String)request.getParameter("btnForgotPassWord");
		
		
		// if value. equal the name 
		if("LogIn".equals(loginButten)) {
			// passing to LogIn Page
			dispatcher = request.getRequestDispatcher("/views/LoginPage.jsp");
			dispatcher.forward(request, response);
		}if ("Register".equals(registerButten)) {
			dispatcher = request.getRequestDispatcher("/views/RegisterPage.jsp");
			dispatcher.forward(request, response);
			
		}if("Change password".equals(changePassword)) {
			
			dispatcher = request.getRequestDispatcher("/views/ChangePasswordPage.jsp");
			dispatcher.forward(request, response);
			
		}if("Forgot password".equals(forgotPassword)) {
			dispatcher = request.getRequestDispatcher("/views/ForgotPasswordPage.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
