<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body
	class="d-flex flex-column justify-content-between align-items-center">
	<div class="container">

		<form action="${pageContext.request.contextPath}/LoginController" method="post">
			<div class="card">
				<div class="card-header">Login</div>
			</div>

			<div class="card-body">
				<div class="form-group">
					<input type="text" name="username" class="form-control"
						placeholder="Enter username" />
				</div>

				<div class="form-group">
					<input type="password" name="password" class="form-control"
						placeholder="Enter password" />
				</div>
			</div>

			<div class="card-body">
			<input type="submit" value="Login" name = "LoginBtn" class="btn btn-primary" />
			<input type="submit" value="Forgot password" name = "ForgotPasswordBtn" class="btn btn-outline-secondary" />
			</div>

		</form>

	</div>
</body>
</html>