<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="detail" value="/message/detail.htm" />
<c:url var="received" value="/message/receivedMessagesList.htm" />
<c:url var="sent" value="/message/sentMessagesList.htm" />

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">
			Messages list
		</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<h1>Boite de réception</h1>
		
		<a href="${received}">Réception(${nbOfUnconsultedMessages}) | </a>
		<a href="${sent}">Envoyés</a>

		<h2>Messages reçus</h2>
		
		<ul>
			<c:forEach items="${receivedMessages}" var="rm">
				<c:set var="m" value="${rm.message}"/>
				<li>
					<a href="${detail}?id=${m.idMessage.sender}&date=${m.idMessage.dateM.getTime()}">
						${m.idMessage.dateM} : 
					</a>
					${m.content} : ${m.author.firstName} ${m.author.lastName} :
					<c:choose>
					<c:when test="${rm.isConsulted()}">
						Lu
					</c:when>
					<c:otherwise>
						Non lu
					</c:otherwise>
				</c:choose>
				</li>
			</c:forEach>
			</ul>

	</tiles:putAttribute>
</tiles:insertDefinition>