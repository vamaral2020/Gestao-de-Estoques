<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PRODUTOS</title>
</head>
<body>

<h1>CADASTRO PRODUTO</h1>
	<h3 style="color: orange; text-align: center;">${msg}</h3>
	<img class="cadastro" src="resource/img/produto.png" alt="Excluir"
		title="Cadastro de Produto" width="150px" height="150px">

	<form action="Produto" method="post" id="fomrUser">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${produto.id }" class="field-long"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${produto.nome }"></td>
					</tr>

					<tr>
						<td>Quantidade:</td>
						<td><input type="number" id="qtde" name="qtde"
							value="${produto.qtde }"></td>
					</tr>

					<tr>
						<td>Valor:</td>
						<td><input type="number" id="valor" name="valor"
							value="${produto.valor }"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"><input
							type="submit" value="Cancelar"
							onclick="document.getElementById('fomrUser').action = 'produto?acao=reset'"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
<!-- tabela de usuarios cadastrados -->
	<div class="container">

		<table class="responsive-table">
			<caption>PRODUTOS CADASTRADO</caption>

			<tr>
				<th>ID</th>
				<th>NOME</th>
				<th>QUANTIDADE</th>
				<th>VALOR</th>
				<th>EXCLUIR</th>
				<th>EDITAR</th>

			</tr>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td style="width: 150px"><c:out value="${produto.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${produto.nome}"></c:out></td>
					<td style="width: 150px"><c:out value="${produto.qtde}"></c:out></td>
					<td style="width: 150px"><c:out value="${produto.valor}"></c:out></td>

					<td><a href="produto?acao=delete&produto=${produto.id}"><img
							src="resource/img/excluir.png" alt="Excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					<td><a href="produto?acao=editar&produto=${produto.id}"><img
							src="resource/img/edit-validated.png" alt="Editar" title="Editar"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>