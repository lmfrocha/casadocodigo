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
<meta charset="UTF-8">

</head>
<body>
	<div>
		<h3>Cadastro de Permissões para ${usuario.nome }</h3>	
		
		<h2> Lista de Usuários</h2>
		
		<form:form action="${s:mvcUrl('UC#atualizaRoles').build() }" method="post" commandName="usuario" enctype="multipart/form-data">
		
		<div>
			<table class="table table-bordered table-striped table-hover">
			<tr>
			     <th>Permissões:</th>
			      
			     <td>
			     	<form:checkboxes items="${roles }" path="roles" />
					<form:errors path="roles" />
			     </td>
			    
			</tr>
			</table>
			<input type="hidden" name="id" value="${usuario.id}" />
			<button type="submit" class="btn btn-primary">Atualizar Roles</button>
		</div>
	</form:form>
	
	</div>


</body>

</tags:pageTemplate>