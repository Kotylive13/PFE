<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Accueil</tiles:putAttribute>
	
	<tiles:putAttribute name="navBar">
	</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">

		
<!-- 			<FONT color="green"> <p> Vous êtes connecté en tant qu'administrateur <br/><br/> </p></FONT> -->
			
<!-- 			<FONT color="blue"> <p> I/ Utilisateurs <br/><br/></p> </FONT> -->
<%-- 			<a href="${pageContext.request.contextPath}/login_staff/waitingUsers/listWaiting.htm">1/ Voir les utilisateurs en attente</a><br/><br/> --%>
<!-- 			<FONT color="blue"> <p> II/ Groupes <br/><br/></p> </FONT> -->
<%-- 			<a href="${pageContext.request.contextPath}/login_staff/group/addGroup.htm">1/ Ajouter un groupe</a><br/><br/> --%>
<%-- 			<a href="${pageContext.request.contextPath}/login_staff/group/listGroup.htm">2/ Voir les groupes</a><br/><br/> --%>
<!-- 			<FONT color="blue"> <p> III/ Catégories <br/><br/></p> </FONT> -->
<%-- 			<a href="${pageContext.request.contextPath}/login_staff/category/addCategory.htm">1/ Ajouter une catégorie à un groupe</a><br/><br/> --%>
<%-- 			<a href="${pageContext.request.contextPath}/login_staff/category/listCategory.htm">1/ Voir les catégories</a><br/><br/> --%>

<!-- 			<br/> -->
			
<%-- 			<a href="${pageContext.request.contextPath}/login_staff/authentication/logout.htm">Déconnexion</a><br/> --%>
		

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
						<td class="bloc">
							<h1>Gestion des catégories</h1>
							<ul>
								<li><a href="${pageContext.request.contextPath}/login_staff/category/addCategory.htm">Ajouter une catégorie à un groupe</a></li>
								<li><a href="${pageContext.request.contextPath}/login_staff/category/listCategory.htm">Voir les catégories</a></li>
							</ul>
						</td>
					</tr>
				</table>
			</div>		

		</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>
