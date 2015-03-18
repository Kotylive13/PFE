<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Accueil</tiles:putAttribute>
	
	<tiles:putAttribute name="navBar">
	</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<div class="page">
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
					</tr>
				</table>
			</div>		
		</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>