<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do Código</title>
</head>
<body>
	<h1>Casa do Código</h1>
	
	<form:form action="${s:mvcUrl('PC#grava').build() }" method="post" 
			commandName="produto">
		<div>
			<label>Título</label>
			<form:input type="text" name="titulo" />
			<form:errors path="titulo"></form:errors>
		</div>
		<div>
	        <label>Descrição</label>
			<form:textarea rows="10" cols="20" name="descricao"></textarea>
	        <form:errors path="descricao"></form:errors>
		</div>
		<div>
			<label>Páginas</label>
			<form:input type="text" name="paginas" />
			<form:errors path="paginas"></form:errors>
		</div>
		<div>
			<label>Data de Lançamento</label>
			<form:input type="text" name="dataLancamento" />
			<form:errors path="dataLancamento"></form:errors>
		</div>
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<form:input type="text" name="precos[${status.index}].valor" />
				<form:input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}"/>
			</div>
		</c:forEach>

		<button type="submit">Cadastrar</button>
	</form:form>
</body>
</html>