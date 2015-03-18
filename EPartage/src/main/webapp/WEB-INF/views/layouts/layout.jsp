<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<title><tiles:insertAttribute name="title" /></title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="content" />
		<div class="script">
			<script type="text/javascript" src=<c:url value="/js/jquery-1.11.1.js" />></script>
			<script type="text/javascript" src=<c:url value="/js/jquery.validate.js" />></script>
			<script type="text/javascript" src=<c:url value="/js/messages_fr.js" />></script>
			<script type="text/javascript" src=<c:url value="/js/autosize.js" />></script>
			<script type="text/javascript" src=<c:url value="/js/validate.js" />></script>
			<script type="text/javascript" src=<c:url value="/js/jquery-scrolltofixed.js" />></script>
			<script type="text/javascript" src=<c:url value="/js/select2.js" />></script>
			<script type="text/javascript" src=<c:url value="/js/script.js" />></script>
		</div>
	</body>
</html>