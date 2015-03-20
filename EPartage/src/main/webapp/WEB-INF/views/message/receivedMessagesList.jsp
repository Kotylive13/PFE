<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:url var="newmess" value="/message/newmessage.htm" />
<c:url var="detail" value="/message/detail.htm" />
<c:url var="received" value="/message/receivedMessagesList.htm" />
<c:url var="sent" value="/message/sentMessagesList.htm" />

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">Boîte de réception</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<h1 class="large">Messagerie</h1>
		<h2 class="large">Messages reçus</h2>
		
		<%@ include file="/WEB-INF/views/layouts/user/messagingOptions.jsp"%>
		
		<div class="listMessage">
			<table>
				<tr>
					<td><img class="smallPicture" src="<c:url value='/images/user.png' />" alt="" title="Avatar"/></td>
					<td>Message</td>
					<td>Date de publication</td>
				</tr>
				<c:forEach items="${receivedMessages}" var="rm">
					<c:choose>
						<c:when test="${rm.isConsulted()}">
							<tr class="read">
								<c:set var="m" value="${rm.message}"/>
								<td>
									<img class="smallPicture" src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${m.author.id}"
										title="${m.author.firstName} ${m.author.lastName}" />
								</td>
								<td>
									<a href="${detail}?id=${m.idMessage.sender}&date=${m.idMessage.dateM.getTime()}">${fn:substring(m.content, 0, 80)}</a>
								</td>
								<td>
									<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${m.idMessage.dateM}" />
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr class="notRead">
								<c:set var="m" value="${rm.message}"/>
								<td>
									<img class="smallPicture" src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${m.author.id}"
										title="${m.author.firstName} ${m.author.lastName}" />
								</td>
								<td>
									<a href="${detail}?id=${m.idMessage.sender}&date=${m.idMessage.dateM.getTime()}">${m.content}</a>
								</td>
								<td>
									${m.idMessage.dateM}
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>