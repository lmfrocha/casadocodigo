<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">    
    

<head>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
<meta charset="ISO-8859-1">

</head>
<body>
	<div class="container">
		<h2>Cadastro de Usuário</h2>
	   <form:form action="${s:mvcUrl('UC#gravar').build() }" method="post" commandName="usuario" enctype="multipart/form-data">
	     <div class="form-group">
	     	<label>Nome: </label>
	     	<form:input path="nome" cssClass="form-control"/> 
	     	<form:errors path="nome"/>     	
	     	
	     </div>
	     
	     <div class="form-group">
	      <label>Email:</label>
	      <form:input path="email" cssClass="form-control"/>
	      <form:errors path="email"/>
	     	
	     </div>
	     
	     <div class="form-group">
                <label>Senha</label>
                <form:password path="senha" cssClass="form-control"  />
                <form:errors path="senha"/>
            </div>
            
            <div class="form-group">
                <label>Confirme a Senha</label>
                <form:password path="confirmaSenha" cssClass="form-control"/>
                <form:errors path="confirmaSenha" />
                
            </div>
            
            
            <button type="submit" class="btn btn-primary">Cadastrar Usuário</button>
	    
	   
	   
	   
	   </form:form>
	
	</div>


</body>

</tags:pageTemplate>