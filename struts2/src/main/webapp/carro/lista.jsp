<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>SimpleDev - Struts 2</title>
</head>
<body>
	<h3>Lista de carros</h3>
	<table>
		<tr>
			<td>Id</td>
			<td>Nome</td>
			<td>Ano de fabricação</td>
		</tr>
		<c:forEach items="${carros}" var="carro">
			<tr>
				<td>${carro.id}</td>
				<td>${carro.nome}</td>
				<td>${carro.ano}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<h5>
		<a href="novo">Novo</a>
	</h5>
</body>
</html>