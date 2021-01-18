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
	<form action="lotteryUpdate" method="post">
			<table border="1" cellpadding="5">
				</caption>
				<c:if test="${entity != null}">
					<input required type="hidden" name="id" value='${entity.id}' />
				</c:if>			 
				   
			    <tr>
			    	<th>Тип лотереи:</th>
			    	<td><select required name="lottery_type">
				    	<c:forEach var="type" items="${lotteryTypes}">
					 		<option name="lottery_type" value="${type.id}">${type.name}</option>				    		
				    	</c:forEach>
					</select>
					</td>
            	</tr>
            
            	<tr>
			    	<th>Дата проведения:</th>
			    	<td><input required type="date" name="dates" value='${entity.dates}' ></td>
            	</tr>
            
            
                <tr>
			    	<th>Приз:</th>
			    	<td><select name="prize">
				    	<c:forEach var="prize" items="${prizes}">
					 		<option value="${prize.id}">${prize.name}</option>				    		
				    	</c:forEach>
					</select>
					</td>
            	</tr>
            
            	
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Сохранить"  class="button button-secondary w-auto"/></td>
				</tr>
			</table>
		</form>
		
		 <h3 class="mt-5">
        	<a href="lottery" class="button ">Назад</a>   	
        </h3>
	</div>
</body>
</html>
