<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<c:url value="/resources/imagens" var="contextPath" />
<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">    
    

<head>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
<meta charset="ISO-8859-1">

</head>
<body>
	<div>
		<h3>${message }</h3>	
		<h3> <a href="${s:mvcUrl('UC#form').build() }">Novo Usuario</a> </h3>
		<h2> Lista de Usuários</h2>
		
		<div>
			<table class="table table-bordered table-striped table-hover">
			<tr>
			     <th>Nome</th>
			     <th>Email</th>
			     <th>Role</th>
			     <th>Alterar Role</th>
			</tr>
				<c:forEach items="${usuarios }" var="usuario">
				   <tr>
				   		<td> ${usuario.nome } </td>
				   		<td> ${usuario.email } </td>
				   		<td>   
				   		      <c:forEach items="${usuario.roles }" var="role">
				   		        ${role.nome}
				   		      </c:forEach>
				   		</td>
				   		<td> 
				   			<form:form action="/casadocodigo/usuarios/usuarioRoles" method="POST">
				   			<input type="hidden" value="${usuario.id }" name="id"  />
				   			
				   			
				   			<input type="image" src="${contextPath }/adicionar.png" style=" width: 25px;height: 25px;"/>
				   			
				   			</form:form>
				   					
				   		 </td>
				   
				   </tr>
				   
				 
				
				</c:forEach>
			
			
			
			</table>
			
		</div>
		
		
	
	</div>


</body>

</tags:pageTemplate>