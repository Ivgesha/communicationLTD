<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change password</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body
	class="d-flex flex-column justify-content-between align-items-center">
	<div class="container">
		<div class="container d-flex justify-content-center flex-column">
			<h1>Change password</h1>
			<form
				action="${pageContext.request.contextPath}/ChangePasswordController"
				method="POST">
				<div class="form-group">
					<label for="exampleInputText">Username</label> <input
					value = "<%= session.getAttribute("username") %>"
						name="username" type="text" class="form-control"
						id="exampleInputText" readonly>
				</div>
				<div class="form-group">
					<label for="exampleInputCurrentPassword">Current password</label> <input
						name="currentPassword" type="text" class="form-control"
						id="exampleInputCurrentPassword">
				</div>
				<div class="form-group">
					<label for="exampleInputNewPassword">New password</label> <input
						name="newPassword" type="password" class="form-control"
						id="exampleInputNewPassword">
				</div>
				<div class="form-group">
					<label for="exampleInputConfirmPassword">Confirm password</label> <input
						name="confirmPassword" type="password" class="form-control"
						id="exampleInputConfirmPassword">
				</div>

				<button type="submit" class="btn btn-primary" name = "submitBtn" values ="submitVal">Submit</button>
			</form>
		</div>

	</div>
</body>
</html>