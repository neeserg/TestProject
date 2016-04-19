<%@page import="com.test.project.Hotel"%>
<html>
<head>
<title>Register</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>
	<form action="/registerUser" method="post">
		<input name="fName" id="fName" type="text"placeholder="First Name" required><br>
		<input name="lName" id="lName" type="text" placeholder="Last Name" required><br>
		<input name="email" id="email" type="email" placeholder="Email" required><br>
		<input type = "password" name ="password" id ="password" placeholder ="password" required><br>
		<input name="age" id="age" type="number" placeholder="age" required><br>

		
		 <input type="submit" value="submit">
	</form>
	<%String error = (String) request.getAttribute("Error"); %>
	<p> <%=error%><p>
</body>

</html>