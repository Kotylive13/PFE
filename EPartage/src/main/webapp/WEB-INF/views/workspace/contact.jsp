<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">Contacter l'administrateur</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<h1>Envoyer un message à l'administrateur</h1>
	
		<div class="form">
			<form:form method="POST"
				action="${pageContext.request.contextPath}/workspace/contact.htm">
				<table>
					<tr>
						<td>Objet</td>
						<td>
							<input name="object" id="object" value="${objectOld}"/>
	 						<c:if test="${!empty objectError}">
								<span id="content.errors" class="error"><c:out value="${objectError}"/></span>
							</c:if>
	 					</td>	
					</tr>
					<tr>
						<td>Message</td>
						<td>
							<textarea name="message" id="message" maxlength="2048" rows="10">${messageOld}</textarea>
	 						<c:if test="${!empty messageError}">
								<span id="content.errors" class="error"><c:out value="${messageError}"/></span>
							</c:if>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" class="submit" value="Envoyer" />
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>