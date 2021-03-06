<%@page import="com.test.project.Hotel"%>
<%@page import="java.util.List"%>
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
	<form action="/rateHotel" method="post">
		<label>Hotel Name:</label> <select name="hotelId"
			style="width: 234px; height: 17px;">
			<% List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");  %>

			<%for(Hotel hotel: hotels) { %>
			<option value="<%=hotel.getId()%>"><%=hotel.getName()%></option>
			<%} %>

		</select><br
		>
		<input name="stars" id="stars" type="number" placeholder="stars" min="0" max ="5" required>
		<br>
		 <input type="submit" value="submit">
	</form>
</body>

</html>