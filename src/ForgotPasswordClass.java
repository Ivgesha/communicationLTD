import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgotPasswordClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	
	
	
	public ForgotPasswordClass() {
		System.out.println("entered ForgotPasswordClass  c'tor \n");		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String submitButten = (String)request.getParameter("btnSubmit");
		String sendEmailButton = (String)request.getParameter("btnSend");
		
		if("submit".equals(submitButten)) {
			System.out.println("clicke on submit");
			// here we need to pass the username's id\ user name\email.
			// maybe even open a new textLine and ask for user name for us to know who's the user who trying to change password
			dispatcher = request.getRequestDispatcher("/views/ChangePasswordPage.jsp");
			dispatcher.forward(request, response);
			
		}if("Send reset to email".equals(sendEmailButton)) {
			// need to implement the func to send email and get number
			System.out.println("clicked on send email button");
			
		}
		
		
	}
	
	
}
