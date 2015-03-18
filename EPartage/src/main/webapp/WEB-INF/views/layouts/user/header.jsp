<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<div class="header">
	<div class="centerHeader">
		<p id="logo"><a href="">e-Partage</a></p>
		<form:form method="POST" modelAttribute="student" action="${pageContext.request.contextPath}/authentication/login.htm">
			<input id="connection" type="submit" value="Connexion">
		  	<form:input id="password" type="password" path="password" placeholder="mot de passe"/>
		  	<form:input id="login" type="text" path="email" placeholder="Adresse mail" />
		  	<a id="forgetPassword" href="">Mot de passe oublié ?</a>
		</form:form>
	</div>
</div>



<div class="fixedHeader">
	<div class="centerHeader">
		<p id="logo"><a href="" title="accueil">e-Partage</a></p>
		<form id="searchForm" method="post" action="">
	  		<input id="searchBar" type="text" placeholder="Chercher des personnes, des groupes ou d'autres choses"/>
	  		<input id="searchButton" type="image" src="<c:url value="/images/magnifying-glass.png" />" title="Recherche">
		</form>
		<p id="option" onclick="slideOptions('#options', 'fast')" title="Options"><img src="<c:url value="/images/option.png" />" alt="" onmouseover="hover(this, '<c:url value="/images/dark_option.png" />')" onmouseout="unhover(this, '<c:url value="/images/option.png" />')" /></p>
		<a id="messaging" href="" title="Messagerie"><img src="<c:url value="/images/envelop.png" />" alt="" onmouseover="hover(this, '<c:url value="/images/dark_envelop.png" />')" onmouseout="unhover(this, '<c:url value="/images/envelop.png" />')" /></a>
		<a id="profil" href="" title="Profil"><img class="smallPicture" src="<c:url value="/images/profil.jpg" />" alt="" /></a>
	    <p id="status" >Bienvenue Marcel</p>
	</div>
</div>
<div id="options">
	<div id="optionsCenter">
		<p id="deconnection">Déconnexion</p>
	</div>
</div>
