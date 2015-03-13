<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="masterAdmin.page">
	<tiles:putAttribute name="title">Accueil</tiles:putAttribute>
	<tiles:putAttribute name="content">
	
		<p>Bienvenue dans l'interface administration</p>
		
		<c:if test="${!empty sessionScope.adminSession}">
			<p>Vous êtes connecté(e) en tant qu'administrateur</p>
			<a href="${pageContext.request.contextPath}/login_staff/waitingUsers/listWaiting.htm">Voir les utilisateurs en attente</a>
		</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>

