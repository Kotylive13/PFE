<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<title><tiles:insertAttribute name="title" /></title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/favicon.ico" />" />
	</head>
	<body>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="popup" />
		<tiles:insertAttribute name="content" />
		<tiles:insertAttribute name="scripts" />
	</body>
</html>