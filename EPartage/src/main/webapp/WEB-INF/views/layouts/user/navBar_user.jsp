<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="navBar">
	<div id="navBar">
		<div id="lightInformation">
			<h1	onclick="slideElement('#contentLightInformation', '#chevronLightInformation', 'slow');">
				Mon profil
				<img id="chevronLightInformation" class="extraSmallPicture"
				src="<c:url value="/images/arrow-up.png"/>" alt="" />
			</h1>
			<div id="contentLightInformation">
				<a href=""><img class="largePicture"
					src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${sessionScope.userSession.id}"
					alt="" />
				</a>
				<ul>
					<li>Année : Master 2 ?</li>
					<li>Parcours : ${sessionScope.userSession.promo}</li>
					<li>Université : Luminy</li>
					<li>E-mail : ${sessionScope.userSession.email}</li>
					<li>Date de naissance : <fmt:formatDate pattern="dd/MM/yyyy"
						value="${sessionScope.userSession.birthDate}" /></li>
				</ul>
				<p>
					<a href="">Modifier</a>
				</p>
			</div>
		</div>
		<div id="messaging">
		    <h1 onclick="slideElement('#contentMessaging', '#chevronMessaging', 'slow');">
		    	Ma messagerie
		    	<img id="chevronMessaging" class="extraSmallPicture"
				src="<c:url value="/images/arrow-up.png"/>" alt="" />
		    </h1>
		    <div id="contentMessaging">
			    <ul class="withoutTop">
			    	<li><a href="${pageContext.request.contextPath}/login_staff/index.htm">Nouveau message</a></li>
			    	<li><a href="${pageContext.request.contextPath}/login_staff/index.htm">Boîte de réception (${nbOfUnconsultedMessages})</a></li>
			    	<li><a href="${pageContext.request.contextPath}/login_staff/index.htm">Boîte d'envoi</a></li>
			   	</ul>
			</div>
		</div>
		<div id="group">
			<h1 onclick="slideElement('#contentGroup', '#chevronGroup', 'slow');">
				Mes groupes<img id="chevronGroup" class="extraSmallPicture"
				src="<c:url value="/images/arrow-up.png"/>" alt="" />
			</h1>
			<div id="contentGroup">
				<ul class="withTop">
					<li><a href="">M2 ISL</a></li>
					<li><a href="">Aix Marseille Université</a></li>
					<li><a href="">Les nights codeurs</a></li>
					<li><a href="">Junior Aix-Marseille</a></li>
				</ul>
				<p>
					<a href="">Voir tout</a>
				</p>
			</div>
		</div>
		<div id="link">
			<h1 onclick="slideToggle('#contentLink');">Liens</h1>
			<div id="contentLink">
				<ul class="withTop">
					<li><a href="">Contact</a></li>
				</ul>
				<p>Copyright e-Partage 2015</p>
			</div>
		</div>
	</div>
</div>