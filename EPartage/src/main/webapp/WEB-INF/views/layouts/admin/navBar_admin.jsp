<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<div id="manageUsers">
	<h1	onclick="slideElement('#contentManageUsers', '#chevronManageUsers', 'slow');">
		Gestion des utilisateurs
		<img id="chevronManageUsers" class="extraSmallPicture"
		src="<c:url value="/images/arrow-up.png"/>" />
	</h1>
	<div id="contentManageUsers">
		<ul class="adminNavBar">
			<li><a
				href="${pageContext.request.contextPath}/login_staff/user/listWaiting.htm">Utilisateurs
					en attente</a></li>
			<li><a href="${pageContext.request.contextPath}/login_staff/user/listUsers.htm">Ajouter un groupe à un utilisateur</a></li>
		</ul>
	</div>
</div>
<div id="manageGroups">
	<h1	onclick="slideElement('#contentManageGroups', '#chevronManageGroups', 'slow');">
		Gestion des groupes
		<img id="chevronManageGroups" class="extraSmallPicture"
		src="<c:url value="/images/arrow-up.png"/>" />
	</h1>
	<div id="contentManageGroups">
		<ul class="adminNavBar">
			<li><a
				href="${pageContext.request.contextPath}/login_staff/group/addGroup.htm">Ajouter
					un groupe</a></li>
			<li><a
				href="${pageContext.request.contextPath}/login_staff/group/listGroup.htm">Voir
					les groupes</a></li>
		</ul>
	</div>
</div>
<div id="manageCategories">
	<h1	onclick="slideElement('#contentManageCategories', '#chevronManageCategories', 'slow');">
		Gestion des catégories
		<img id="chevronManageCategories" class="extraSmallPicture"
		src="<c:url value="/images/arrow-up.png"/>" />
	</h1>
	<div id="contentManageCategories">
		<ul class="adminNavBar">
			<li><a
				href="${pageContext.request.contextPath}/login_staff/category/addCategory.htm">Ajouter
					une catégorie à un groupe</a></li>
			<li><a
				href="${pageContext.request.contextPath}/login_staff/category/listCategory.htm">Voir
					les catégories</a></li>
		</ul>
	</div>
</div>