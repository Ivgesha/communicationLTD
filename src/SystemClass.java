import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class SystemClass extends HttpServlet{
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	
	

	public SystemClass() {
		System.out.println("entered SystemClass c'tor\n");
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String newCostomerButton = (String)request.getParameter("btnNewCustomer");
 
		if("Add new costumer".equals(newCostomerButton)) {
			 
			dispatcher = request.getRequestDispatcher("/views/AddNewCostumerPage.jsp");
			dispatcher.forward(request, response);
			
			
		}
		
		
		
	}
	
}
