<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title>SimpledDev - Spring MVC</title>
</head>
<body>
	<h3>Editar Carro</h3>
	<form:form action="../editar" method="put" commandName="carro">
		<form:hidden path="id" />
		<table>
			<tr>
				<td>Nome: <form:input path="nome" /></td>
				<td>Ano: <form:input path="ano" /></td>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>