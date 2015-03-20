<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:url var="detail" value="/message/detail.htm" />
<c:url var="received" value="/message/receivedMessagesList.htm" />
<c:url var="sent" value="/message/sentMessagesList.htm" />

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">Boîte d'envoi</tiles:putAttribute>
	
	<tiles:putAttribute name="content">

		<h1 class="large">Messagerie</h1>
		<h2 class="large">Messages envoyés</h2>
		
		<%@ include file="/WEB-INF/views/layouts/user/messagingOptions.jsp"%>
		
		<div class="listMessage">
			<table>
				<tr>
					<td><img class="smallPicture" src="<c:url value='/images/user.png' />" alt="" title="Avatar"/></td>
					<td>Message</td>
					<td>Date de publication</td>
				</tr>
				<c:forEach items="${sentMessages}" var="m">
					<tr class="read">
						<td>
							<c:choose>
								<c:when test="${m.receivers.size() == 1}" >
									<c:forEach items="${m.receivers}" var="r">
										<img class="smallPicture" src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${r.id}"
											title="${r.firstName} ${r.lastName}" />
									</c:forEach>
								</c:when>
								<c:otherwise>
									<img class="smallPicture" src="<c:url value='/images/users.png' />"
									title="Multiple destinataires" />
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="${detail}?id=${m.idMessage.sender}&date=${m.idMessage.dateM.getTime()}">${fn:substring(m.content, 0, 100)}</a>
						</td>
						<td>
							<fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${m.idMessage.dateM}" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</tiles:putAttribute>
	
</tiles:insertDefinition>