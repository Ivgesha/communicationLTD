  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body
	class="d-flex flex-column justify-content-between align-items-center">

	<%
		// get the http session
		String httpSession = (String) session.getAttribute("username");
		// if the user allready logged in redirect to customers list
		if (httpSession != null) {
			response.sendRedirect("CustomerController?action=LIST");
		}
		String status = request.getParameter("status");
		if (status != null) {
			if (status.equals("false")) {
				out.print("Invalid username or password");
			} else if (status.equals("error")) {
				out.print("Some error occured , please try agien later");
			}
		}
	%>
	<h1>Communication LTD</h1>
	<div class="container d-flex flex-column align-items-center ">
		<div class="container d-flex justify-content-around">
			<button type="button" class="btn btn-outline-primary"
				onclick="window.location.href='views/LoginPage.jsp'">Login</button>
			<button type="button" class="btn btn-outline-primary"
				onclick="window.location.href='views/operator-register.jsp'">Register</button>
		</div>

	</div>


</body>
</html>