<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
<title>404</title>
</head>
<body>
	<h1>Url non trouvée</h1>
	<div>
		<img src="<c:url value="/images/error404.png" />" alt="" />
	</div>
	<p>

		<c:url value="/workspace/index.htm" var="index" />
		<a href="${index}">Retour à la page d'accueil</a>
	</p>
</body>
</html>