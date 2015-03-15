<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
		
		<h2>Détail message</h2>
		
		<p>De : ${message.author.firstName} ${message.author.lastName}</p>
		<p>A :
		<c:forEach items="${message.receivers}" var="r">
			${r.firstName} ${r.lastName} 
		</c:forEach>
		</p>
		<p>Date : ${message.idMessage.dateM}</p>
		<p>Contenu : ${message.content}</p>

	</tiles:putAttribute>
</tiles:insertDefinition>