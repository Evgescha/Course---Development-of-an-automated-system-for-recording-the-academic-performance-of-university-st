<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Error</title>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" href="images/favicon.png">

	<!-- font -->
	<link href="https://fonts.googleapis.com/css2?family=Spartan:wght@400;500;600;700;900&display=swap" rel="stylesheet"> 
	<!-- end font -->

	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/ionicons.min.css">
	<link rel="stylesheet" href="css/magnific-popup.css">
	<link rel="stylesheet" href="css/fakeLoader.min.css">
	<link rel="stylesheet" href="css/swiper-bundle.min.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>


<jsp:include page="header.html" />

	<center>
		<h1>Error</h1>
		<h2><%=exception.getMessage() %><br/> </h2>
	</center>	
</body>
</html>