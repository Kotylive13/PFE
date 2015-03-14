<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="masterAdmin.page">
	<tiles:putAttribute name="title">Accueil</tiles:putAttribute>
	<tiles:putAttribute name="content">
	
		<div class="page">
		
		<p>
			<h1>Bienvenue dans l'interface administration</h1><br/><br/>
		</p>
		
		<c:if test="${empty sessionScope.adminSession}">
			<FONT color="red"><p> Vous n"êtes pas administrateur <br/><br/></p></FONT>
		</c:if>
		
		<c:if test="${!empty sessionScope.adminSession}">
		
			<FONT color="green"> <p> Vous êtes connecté en tant qu'administrateur <br/><br/> </p></FONT>
			
			<FONT color="blue"> <p> MENU <br/><br/></p> </FONT>
		
			<a href="${pageContext.request.contextPath}/login_staff/waitingUsers/listWaiting.htm">1/ Voir les utilisateurs en attente</a><br/><br/>
			<a href="${pageContext.request.contextPath}/login_staff/group/addGroup.htm">2/ Ajouter un groupe</a><br/><br/>
			<a href="${pageContext.request.contextPath}/login_staff/group/listGroup.htm">3/ Voir les groupes</a><br/><br/>
			<br/>
			
			<a href="${pageContext.request.contextPath}/login_staff/authentication/logout.htm">Déconnexion</a><br/>
		
		</c:if>
		
		</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>

