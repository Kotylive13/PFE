<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<div id="navBar">
	<div id="manageUsers">
	    <h1>Gestion des utilisateurs</h1>
	    <ul class="adminNavBar">
	    	<li><a href="${pageContext.request.contextPath}/login_staff/waitingUsers/listWaiting.htm">Utilisateurs en attente</a></li>
	   	</ul>
	</div>
	<div id="manageGroups">
	    <h1>Gestion des groupes</h1>
	    <ul class="adminNavBar">
	    	<li><a href="${pageContext.request.contextPath}/login_staff/group/addGroup.htm">Ajouter un groupe</a></li>
	    	<li><a href="${pageContext.request.contextPath}/login_staff/group/listGroup.htm">Voir les groupes</a></li>
	   	</ul>
	</div>
	<div id="manageCategories">
		<h1>Gestion des catégories</h1>
		<ul class="adminNavBar">
			<li><a href="${pageContext.request.contextPath}/login_staff/category/addCategory.htm">Ajouter une catégorie à un groupe</a></li>
			<li><a href="${pageContext.request.contextPath}/login_staff/category/listCategory.htm">Voir les catégories</a></li>
		</ul>
	</div>
</div>