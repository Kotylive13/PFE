<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>500</title>
</head>
<body>
	<p>Désolé une erreur s'est produite</p>
	<div>
		<img src="../images/oops.jpg" alt="" />
	</div>
	<spring:url value='/' var="var" />

	<p>
		<a href="${var}">Return to index page</a>
	</p>
</body>
</html>