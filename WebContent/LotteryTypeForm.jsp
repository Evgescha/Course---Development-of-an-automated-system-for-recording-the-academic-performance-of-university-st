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
	<form action="lotteryTypeUpdate" method="post">
			<table border="1" cellpadding="5">
				<caption>
					<h2>
						<c:if test="${entity != null}">Редактировать</c:if>
						<c:if test="${entity == null}">Добавить</c:if>
					</h2>
				</caption>
				<c:if test="${entity != null}">
					<input required type="hidden" name="id" value="<c:out value='${entity.id}' />" />
				</c:if>
				<tr>
					<th>Название вида:</th>
					<td><input required type="text" name="name" size="45" value="<c:out value='${entity.name}' />" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input required type="submit" value="Сохранить"  class="button button-secondary w-auto"/></td>
				</tr>
			</table>
		</form>
		
		 <h3 class="mt-5">
        	<a href="lottery_type" class="button ">Назад</a>   	
        </h3>
	</div>
</body>
</html>
