<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Группы</title>
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
	
    <div align="center" class="vission-section section">

		<h1  class="text-center mb-5">Группы</h1>        
	
        <table border="1" cellpadding="5">
            <tr>
                <th>ИД</th>
                <th>Название</th>
            </tr>
            <c:forEach var="entity" items="${list}">
                <tr>
                    <td><c:out value="${entity.id}" /></td>
                    <td><c:out value="${entity.name}" /></td>
                    <td>
                    	<a class="button button-secondary"  href="groupEdit?id=<c:out value='${entity.id}' />">Редактировать</a>           	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a class="button button-secondary"  href="groupDelete?id=<c:out value='${entity.id}' />">Удалить</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h3 class="mt-5">
        	<a href="groupEdit" class="button ">Добавить запись</a>   	
        </h3>
    </div>	
</body>
</html>
