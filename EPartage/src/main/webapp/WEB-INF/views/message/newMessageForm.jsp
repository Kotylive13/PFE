<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">Nouveau message</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
	
		<h1>Messagerie</h1>
		<h2>Nouveau message</h2>
		
		<c:if test="${!empty badReceivers}">
			<p>Les destinataires suivants sont incorrects :</p>
			<ul>
			<c:forEach items="${badReceivers}" var="r">
				<li>${r}</li>
			</c:forEach>
			</ul>
		</c:if>
		
		<div class="form">
			<form:form method="POST" modelAttribute="message"
				action="${pageContext.request.contextPath}/message/newmessage.htm">
				<table>
					<tr>
						<td>Destinataires</td>
						<td>
							<select id="multipleTokens" class="multipleTokens" multiple="multiple" >
								<c:forEach items="${users}" var="user">
									<option value="<c:out value="${user.id}"/>">${user.firstName} ${user.lastName}</option>
								</c:forEach>
	 						</select>
	 						<input name="receiversList" id="selectedValues" type="hidden" />
	 						<c:if test="${!empty error}">
								<c:out value="${error}"/>
							</c:if>	
	 					</td>	
					</tr>
					<tr>
						<td>Message</td>
						<td>
							<form:textarea path="content" maxlength="2048" rows="10"/>
							<form:errors cssClass="error" path="content" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" class="submit" value="Envoyer" />
							<a href="${pageContext.request.contextPath}/message/receivedMessagesList.htm"><button type="button" class="submit">Annuler</button></a>
						</td>
					</tr>
				</table>
			</form:form>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>