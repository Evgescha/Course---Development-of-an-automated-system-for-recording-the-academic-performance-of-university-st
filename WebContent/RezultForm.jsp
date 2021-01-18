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
		<form action="rezultUpdate" method="post">
			<table border="1" cellpadding="5">
				<c:if test="${entity != null}">
					<input type="hidden" name="id" value='${entity.id}' />
				</c:if>			 
				   
			    <tr>
			    	<th>Лотерея:</th>
			    	<td><select name="lottery"  required>
				    	<c:forEach var="type" items="${lotteries}">
					 		<option name="lottery" value="${type.id}">${type.type.name} №${type.id} (${type.dates})</option>				    		
				    	</c:forEach>
					</select>
					</td>
            	</tr>
            
            	<tr>
			    	<th>Выигрышная комбинация:</th>
			    	<td><input type="text" name="numbers" value='${entity.numbers}'  required></td>
            	</tr>
            
            	<tr>
			    	<th>Выигшравший билет:</th>
			    	<td><select name="winner" required>
				    	<c:forEach var="ticket" items="${tickets}">
					 		<option name="winner" value="${ticket.id}">Билет ${ticket.lottery.type.name} №${ticket.id}, комбинация (${ticket.numbers})</option>				    		
				    	</c:forEach>
					</select>
					</td>
            	</tr>
                       	
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Сохранить"   class="button button-secondary w-auto"/></td>
				</tr>
			</table>
		</form>
		
		 <h3 class="mt-5">
        	<a href="rezult" class="button ">Назад</a>   	
        </h3>
	</div>
</body>
</html>
