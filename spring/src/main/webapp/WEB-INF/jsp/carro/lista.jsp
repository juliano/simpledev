<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title>SimpledDev - Spring MVC</title>
</head>
<body>
	<h3>Lista de carros</h3>
	<table>
		<tr>
			<td>Id</td>
			<td>Nome</td>
			<td>Ano de fabricação</td>
		</tr>
		<c:forEach items="${carroList}" var="carro">
			<tr>
				<td>${carro.id}</td>
				<td>${carro.nome}</td>
				<td>${carro.ano}</td>
				<td><a href="editar/${carro.id}">Editar</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<h5>
		<a href="novo">Novo</a>
	</h5>
</body>
</html>