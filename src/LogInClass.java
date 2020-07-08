import java.io.IOException;
import java.io.PrintWriter;

import javax.net.ssl.SSLContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.dao.CustomerDAO;
import com.dao.CustomerDAOImpl;

public class LogInClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public CustomerDAO customerDao = new CustomerDAOImpl();
	RequestDispatcher dispatcher = null;

	public LogInClass() {
		System.out.println("entered constuctor\n");

		/*
		 * try { SSLContext ctx = SSLContext.getInstance("TLSv1.2");
		 * ctx.init(null, null, null); SSLContext.setDefault(ctx);
		 * System.out.println("TLSv1.2 established\n"); } catch (Exception e) {
		 * System.out.println("TLSv1.2 didnt established\n");
		 * System.out.println(e.getMessage()); }
		 */

	}

	// dosent entering
	// because we callde the func as post reqyest so it woun enter get ever.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String btn = request.getParameter("LoginButten");

		if ("LoginBtn".equals(btn)) {
			System.out.println("button clicked! ");

		} else {
			System.out.println("no button clicked");
		}

	}

	// entering after pressing click
	// the post request is the safer way to work with
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String btn = request.getParameter("LoginButten");
		String username = (String) request.getParameter("uname");
		String password = (String) request.getParameter("pass");

		System.out.println("username -> " + username + " and password -> " + password + "\n");
		if ("LoginBtn".equals(btn)) {
			System.out.println("button clicked!\n");
			// checking the username and password, if its true ( correct )
			// we are entering the mainPage
			if (customerDao.authenticate(username, password) == true) {
				dispatcher = request.getRequestDispatcher("/views/SystemPage.jsp");
				dispatcher.forward(request, response);

			} else {
				System.out.println("entered the massege dialog\n");
				// nothing poping, just redirecting back to our page. ( when we
				// get incorrect register )
				dispatcher = request.getRequestDispatcher("/views/LoginPage.jsp");
				dispatcher.forward(request, response);

			}
		}

		// PrintWriter out = response.getWriter();
		System.out.println("entered doPost");

	}

}
