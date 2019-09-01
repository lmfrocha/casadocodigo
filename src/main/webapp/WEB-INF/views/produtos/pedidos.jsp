<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

<meta charset="ISO-8859-1">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do Código</title>
</head>
<body>
    <table class="table table-bordered table-striped table-hover">
			<tr>
				<th>ID</th>
				<th>Valor</th>
				<th>Data Pedido</th> 
				<th>Titulo</th>
				
			</tr>
			<c:forEach items="${pedidos }" var="pedido">
				<tr>
					<td>${pedido.id } </td>
					<td>${pedido.valor }</td>
					<td><fmt:formatDate value="${pedido.data.time }" pattern="dd/MM/yyyy"/></td>
					<td>
							<c:forEach items="${pedido.produtos }" var="produto">
							
							( ${produto.titulo } ) 
							 		
							</c:forEach>
							
					</td>
					
				</tr>
			</c:forEach>
		</table>	


</body>
</html>

</tags:pageTemplate>