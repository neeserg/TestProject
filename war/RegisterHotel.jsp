
<html>
<head>

<title>Register Hotel</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
<body>

<form method ="post" action = "/registerHotel">
<label>Enter Hotel:</label>
<input name ="HotelName" type = "text" id = "HotelName" required>
<br><label>Enter Number of Rooms:</label>
<input name = "Rooms" type ="number" id ="Rooms" required>
<br><label>Enter Number of Star the Hotel has:</label>
<input name = "Stars" type ="number" id ="Stars" min ="0" max="5" required>

<br><input type ="submit" value ="submit">
</form>

</body>
</html>