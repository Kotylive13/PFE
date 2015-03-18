<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>


<div class="fixedHeader">
	<div class="centerHeader">
		<p id="logo"><a href="" title="accueil">e-Partage</a></p>
		<form id="searchForm" method="post" action="">
	  		<input id="searchBar" type="text" tabindex="1" placeholder="Chercher des personnes, des groupes ou d'autres choses"/>
	  		<input id="searchButton" type="image" tabindex="2" src="<c:url value="/images/magnifying-glass.png" />" title="Recherche">
		</form>
		<p id="option" onclick="slideOptions('#options', 'fast')" title="Options"><img src="<c:url value="/images/option.png" />" alt="" onmouseover="hover(this, '<c:url value="/images/dark_option.png" />')" onmouseout="unhover(this, '<c:url value="/images/option.png" />')" /></p>
	    <p id="status">Bienvenue ${sessionScope.adminSession.role}</p>
	</div>
</div>
<div id="options">
	<div id="optionsCenter">
		<p id="deconnection"><a href="${pageContext.request.contextPath}/login_staff/authentication/logout.htm">Déconnexion</a></p>
	</div>
</div>