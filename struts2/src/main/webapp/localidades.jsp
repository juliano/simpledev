<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Struts 2 Simples - Combos Dependentes</title>
</head>
<body>
	<h3>Struts 2 Simples - Combos Dependentes - Estado e Cidade</h3>

	<select name="estados" id="estados">
		<option>Selecione o estado</option>
		<c:forEach items="${estados}" var="est">
			<option value="${est}">${est}</option>
		</c:forEach>
	</select>
	<br>

	<select name="cidades" id="cidades">
		<option>Selecione o estado</option>
	</select>

	<script language="JavaScript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
	<script language="JavaScript" src="/js/populaCidades.js"	type="text/javascript"></script>
</body>
</html>