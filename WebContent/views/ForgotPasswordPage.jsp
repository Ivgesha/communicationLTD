<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forgot password</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body
	class="d-flex flex-column justify-content-between align-items-center">
	<div class="container">
		<div class="container d-flex justify-content-center flex-column">
			<h1>Forgot password</h1>
			<form
				action="${pageContext.request.contextPath}/ForgotPasswordController"
				method="POST">
				<div class="form-group">
					<label for="exampleInputText">Username</label> <input
						name="username" type="text" class="form-control" value = "<%= session.getAttribute("username") %>"
						id="exampleInputText">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail">Email</label> <input
						name="email" type="email" class="form-control" value = "<%= session.getAttribute("email") %>"
						id="exampleInputEmail">
				</div>
				<button type="submit" class="btn btn-primary" name = "SendMailBtn" value = "sendEmail">Send mail</button>
				<div class="form-group">
					<label for="exampleInputEmailVal">Verification value</label> <input
						name="EmailValue" type="text" class="form-control"
						id="exampleInputEmailVal">
				</div>

				<button type="submit" class="btn btn-primary" name = "submitBtn" value = "submit">Submit</button>
			</form>
		</div>

	</div>
</body>
</html>