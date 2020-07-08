<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New password</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body
	class="d-flex flex-column justify-content-between align-items-center">
	<div class="container">

		<form action="${pageContext.request.contextPath}/NewPasswordController" method="post">
			<div class="card">
				<div class="card-header">New password</div>
			</div>

			<div class="card-body">
			
			<div class="form-group">
					<label for="exampleInputText">Username</label> <input
						name="username" type="text" class="form-control" value = "<%= session.getAttribute("username") %>"
						id="exampleInputText" readonly>
				</div>
			
				<div class="form-group">
					<input type="password" name="newPassword" class="form-control"
						placeholder="Enter new password" />
				</div>

				<div class="form-group">
					<input type="password" name="confirmPassword" class="form-control"
						placeholder="Confirm password" />
				</div>
			</div>

			<div class="card-body">
			<input type="submit" value="Submit" name = "submitNewPasswordBtn" class="btn btn-primary" />
			
			</div>

		</form>

	</div>
</body>
</html>