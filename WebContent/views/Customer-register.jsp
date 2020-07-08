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
	<h1>Add New Customer</h1>
	<div class="container d-flex justify-content-center">
		<form action="${pageContext.request.contextPath}/CustomerController"
			method="POST">
			<div class="form-group">
				<label for="exampleInputEmail1">Email address</label> <input
					name = "email" type="email" class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp"> <small id="emailHelp"
					class="form-text text-muted">We'll never share your email
					with anyone else.</small>
			</div>
			<div class="form-group">
				<label for="exampleInputText">Customer name</label> <input name="customerName" type="text"
					class="form-control" id="exampleInputText">
			</div>
			<div class="form-group">
				<label for="exampleInputText1">Internet package</label> <input name="internetPackage"
					type="text" class="form-control" id="exampleInputText1">
			</div>

			<div class="form-group">
				<label for="exampleInputText2">Sector</label> <input name="sector"
					type="text" class="form-control" id="exampleInputText2">
			</div>
			
			
			<div class="form-group">
				<label for="exampleInputText3">Customer ID</label> <input name="customerID"
					type="text" class="form-control" id="exampleInputText3">
			</div>


			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	
	<p>${message}</p>

</body>
</html>