import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterClass extends HttpServlet{
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	
	
	public RegisterClass() {
		System.out.println("entered Register c'tor\n");
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String registerButton = (String)request.getParameter("btnRegister");
		
		if("Register".equals(registerButton)) {
			System.out.println("button presesd ");
			dispatcher = request.getRequestDispatcher("/views/WelcomePage.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		
	}

}
