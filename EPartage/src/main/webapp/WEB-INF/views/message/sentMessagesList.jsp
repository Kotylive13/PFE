<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		
		<h2>Messages envoyés</h2>
			<ul>
			<c:forEach items="${sentMessages}" var="m">
				<li>
					<a href="${detail}?id=${m.idMessage.sender}&date=${m.idMessage.dateM.getTime()}">
						${m.idMessage.dateM} : 
					</a>
					${m.content} : 
					<c:forEach items="${m.receivers}" var="r">
						${r.firstName} ${r.lastName} 
					</c:forEach>
				</li>
			</c:forEach>
			</ul>

	</tiles:putAttribute>
</tiles:insertDefinition>