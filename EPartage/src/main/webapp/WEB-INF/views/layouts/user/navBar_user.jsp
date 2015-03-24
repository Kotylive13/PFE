<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<div id="lightInformation">
	<h1	onclick="slideElement('#contentLightInformation', '#chevronLightInformation', 'slow');">
		Mon profil
		<img id="chevronLightInformation" class="extraSmallPicture"
		src="<c:url value="/images/arrow-up.png"/>" alt="" />
	</h1>
	<div id="contentLightInformation">
		<a href="${pageContext.request.contextPath}/workspace/showProfile.htm?userId=${sessionScope.userSession.id}"><img class="largePicture"
			src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${sessionScope.userSession.id}"
			alt="" />
		</a>
		<ul>
			<li>Numéro étudiant : ${sessionScope.userSession.numStudent}</li>
			<li>Date de naissance : <fmt:formatDate pattern="dd/MM/yyyy"
				value="${sessionScope.userSession.birthDate}" />
			</li>
			<li>E-mail : ${sessionScope.userSession.email}</li>
			<li>Année d'inscription à l'université : ${sessionScope.userSession.inscriptUnivDate}</li>
			<li>Promotion : ${sessionScope.userSession.promo}</li>
			<li>Date d'inscription à l'application : <fmt:formatDate pattern="dd/MM/yyyy"
							value="${sessionScope.userSession.inscriptAppDate}" /></li>
		</ul>
		<p>
			<a href="${pageContext.request.contextPath}/workspace/editprofile.htm">Modifier</a>
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
	    <ul>
	    	<li><a href="${pageContext.request.contextPath}/message/newmessage.htm">Nouveau message</a></li>
	    	<li><a href="${pageContext.request.contextPath}/message/receivedMessagesList.htm">Boîte de réception (${nbOfUnconsultedMessages})</a></li>
	    	<li><a href="${pageContext.request.contextPath}/message/sentMessagesList.htm">Boîte d'envoi</a></li>
	   	</ul>
	</div>
</div>
<div id="group">
	<h1 onclick="slideElement('#contentGroup', '#chevronGroup', 'slow');">
		Mes groupes<img id="chevronGroup" class="extraSmallPicture"
		src="<c:url value="/images/arrow-up.png"/>" alt="" />
	</h1>
			<div id="contentGroup">
				<c:choose>
				    <c:when test="${!empty groupsList}">
						<ul>
							<c:forEach items="${groupsList}" var="group">
						    	<li><a href="${pageContext.request.contextPath}/workspace/group/detail.htm?nameG=${groupsUrl[group.name]}">${group.name}</a></li>
							</c:forEach>
						</ul>
						<p>
							<a href="">Voir tout</a>
						</p>
					</c:when>
					<c:otherwise>
				        <p>Aucun groupe</p>
				    </c:otherwise>
				</c:choose>
			</div>
		</div>
		<div id="link">
			<h1 onclick="slideElement('#contentLink', '#chevronLink', 'slow');">
				Liens<img id="chevronLink" class="extraSmallPicture"
				src="<c:url value="/images/arrow-up.png"/>" alt="" />
			</h1>
			<div id="contentLink">
				<ul>
					<li><a href="${pageContext.request.contextPath}/workspace/contact.htm">Contact</a></li>
				</ul>
				<p>Copyright e-Partage 2015</p>
			</div>
		</div>
