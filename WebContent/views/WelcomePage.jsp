<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome (title) </title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>

<form action="${pageContext.request.contextPath}/Welcome" method="POST">

		<input type="submit" value="LogIn" name ="btnLogin"><br> 
		<input type="submit" value="Register" name = "btnRegister"><br>
		<input type="submit" value="Change password" name = "btnChangePassword"><br>
		<input type="submit" value="Forgot password" name = "btnForgotPassWord"><br>
</form>

</body>
</html>