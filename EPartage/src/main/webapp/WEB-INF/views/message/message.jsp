<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:url var="received" value="/message/receivedMessagesList.htm" />
<c:url var="sent" value="/message/sentMessagesList.htm" />

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">Détails message</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
	
		<h1>Messagerie</h1>
		<h2>Détail message</h2>
			
		<div class="information">
			<table>
				<tr>
					<td class="smallMarge">De :</td>
					<td>${message.author.firstName} ${message.author.lastName}</td>
				</tr>
				<tr>
					<td class="smallMarge">À :</td>
					<td>
						<c:forEach items="${message.receivers}" var="r">
							${r.firstName} ${r.lastName}; 
						</c:forEach></td>
				</tr>
				<tr>
					<td class="smallMarge">Date :</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${message.idMessage.dateM}" /></td>
				</tr>
			</table>
			<p class="contentMessage">${message.content}</p>
			<a href="${pageContext.request.contextPath}/message/receivedMessagesList.htm"><button type="button" class="submit">Retour</button></a>
		</div>

	</tiles:putAttribute>

</tiles:insertDefinition>