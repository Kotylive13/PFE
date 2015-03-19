<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">
			New message
		</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<p>Nouveau message</p>
		
		<c:if test="${!empty badReceivers}">
			<p>Les destinataires suivants sont incorrects :</p>
			<ul>
			<c:forEach items="${badReceivers}" var="r">
				<li>${r}</li>
			</c:forEach>
			</ul>
		</c:if>
		
		<form:form method="POST" modelAttribute="message"
			action="${pageContext.request.contextPath}/message/newmessage.htm">
			<table>
				<tr>
					<td>
						<select id="multipleTokens" class="multipleTokens" multiple="multiple" >
							<c:forEach items="${users}" var="user">
								<option value="<c:out value="${user.email}"/>">${user.firstName} ${user.lastName}</option>
							</c:forEach>
 						</select>
 						<input name="receiversList" id="selectedValues" type="hidden" />
 					</td>
					<c:if test="${!empty error}">
						<td><c:out value="${error}"/></td>
					</c:if>			
				</tr>
				<tr>
					<td><form:textarea path="content" maxlength="2048"/></td>
					<td><form:errors cssClass="error" path="content" /></td>
				</tr>
			</table>
			<input type="submit" value="Envoyer" />
		</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>