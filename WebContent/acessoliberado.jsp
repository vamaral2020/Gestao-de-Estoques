
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:useBean id="calcula" class="beans.BeansCursoJsp"
	type="beans.BeansCursoJsp" scope="page" />
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1"  >
<title >Insert title here</title>
<link rel="stylesheet" href="resource/css/Cadastro.css">
</head>
<body>
	<h1>Seja bem vindo ao curso de JSP-Acesso liberado!</h1>
	
	<div class="cadastro">
	
	<a href="salvarUsuario?acao=listartodos" ><img src="resource/img/cadastro.png" alt="Excluir" title="Cadastro de Usuario"></a>
	<a href="CadastrarProdutos.jsp" ><img src="resource/img/produto.png" alt="Cadastro de produto" title="Cadastro de Produto"></a>
	
	</div>
	

</body>

</html>