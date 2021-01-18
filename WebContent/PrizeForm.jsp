<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Редактор</title>
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

	<div align="center"  class="vission-section section">
		<form action="prizeUpdate" method="post">
			<table border="1" cellpadding="5">
				<c:if test="${entity != null}">
					<input type="hidden" name="id"  required value="<c:out value='${entity.id}' />" />
				</c:if>
				<tr>
					<th>Название приза:</th>
					<td><input required type="text" name="name" size="45" value="<c:out value='${entity.name}' />" /></td>
				</tr>
				<tr>
					<th>Описание:</th>
					<td><input required type="text" name="description" size="45" value="<c:out value='${entity.description}' />" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input required type="submit" value="Сохранить"  class="button button-secondary w-auto"/>
						</td>
				</tr>
			</table>
		</form>
		 <h3 class="mt-5">
        	<a href="prize" class="button ">Назад</a>   	
        </h3>
	</div>
</body>
</html>
