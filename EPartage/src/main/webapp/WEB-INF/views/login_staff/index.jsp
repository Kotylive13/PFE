<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<title>Accueil</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />" />
	</head>
	<body>
	
		<%@ include file="/WEB-INF/views/layouts/admin/header_admin.jsp" %>
		
		<c:if test="${!empty type}">
			<div id="popup" class="<c:out value='${type}'/>">
	            <p class="messagePopup"><c:out value="${message}"/></p>
	            <p class="optionsPopup" onclick="slideUp('#popup');"><img class="veryExtraSmallPicture" src="<c:url value="/images/cross.png" />" alt="" /></p>
			</div>
		</c:if>
		
		<div class="page">		
			<c:if test="${!empty sessionScope.adminSession}">
			<h1>Panneau d'administration</h1>
			<table class="blocs">
				<tr>
					<td class="bloc">
						<h1>Gestion des utilisateurs</h1>
						<ul>
							<li><a href="${pageContext.request.contextPath}/login_staff/waitingUsers/listWaiting.htm">Utilisateurs en attente</a></li>
						</ul>
					</td>
					<td class="bloc">
						<h1>Gestion des groupes</h1>
						<ul>
							<li><a href="${pageContext.request.contextPath}/login_staff/group/addGroup.htm">Ajouter un groupe</a></li>
							<li><a href="${pageContext.request.contextPath}/login_staff/group/listGroup.htm">Voir les groupes</a></li>
						</ul>
					</td>
					<td class="bloc">
						<h1>Gestion des catégories</h1>
						<ul>
							<li><a href="${pageContext.request.contextPath}/login_staff/category/addCategory.htm">Ajouter une catégorie à un groupe</a></li>
							<li><a href="${pageContext.request.contextPath}/login_staff/category/listCategory.htm">Voir les catégories</a></li>
						</ul>
					</td>
				</tr>
			</table>
		</c:if>
		</div>
		<%@ include file="/WEB-INF/views/layouts/scripts.jsp" %>
	</body>
</html>
