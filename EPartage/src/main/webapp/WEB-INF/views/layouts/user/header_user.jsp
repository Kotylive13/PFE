<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<div class="fixedHeader">
	<div class="centerHeader">
		<p id="logo"><a href="" title="accueil">e-Partage</a></p>
		<form id="searchForm" method="post" action="">
	  		<input id="searchBar" type="text" placeholder="Chercher des personnes, des groupes ou d'autres choses"/>
	  		<input id="searchButton" type="image" src="<c:url value="/images/magnifying-glass.png" />" title="Recherche">
		</form>
		<p id="option" onclick="slideOptions('#options', 'fast')" title="Options"><img src="<c:url value="/images/option.png" />" alt="" onmouseover="hover(this, '<c:url value="/images/dark_option.png" />')" onmouseout="unhover(this, '<c:url value="/images/option.png" />')" /></p>
		<a id="messaging" href="${pageContext.request.contextPath}/message/receivedMessagesList.htm" title="Messagerie"><img src="<c:url value="/images/envelop.png" />" alt="" onmouseover="hover(this, '<c:url value="/images/dark_envelop.png" />')" onmouseout="unhover(this, '<c:url value="/images/envelop.png" />')" /></a>
		<a id="profil" href="" title="Profil"><img class="smallPicture" src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${sessionScope.userSession.id}" alt="" /></a>
	    <p id="status" >Bienvenue ${sessionScope.userSession.firstName}</p>
	</div>
</div>
<div id="options">
	<div id="optionsCenter">
		<p id="deconnection"><a href="${pageContext.request.contextPath}/authentication/logout.htm">Déconnexion</a></p>
	</div>
</div>
