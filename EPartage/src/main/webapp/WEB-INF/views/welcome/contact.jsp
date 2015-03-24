<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="visitor">
	<tiles:putAttribute name="title">Contacter l'administrateur</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<div class="page">
		
			<h1>Envoyer un message à l'administrateur</h1>	
			
			<div class="form">
				<form:form id="contactFormVisitor" method="POST"
					action="${pageContext.request.contextPath}/welcome/contact.htm">
					<table>
						<tr>
							<td>Nom</td>
							<td>
								<input name="lastName" id="lastName" value="${param.lastName}"/>
		 						<c:if test="${!empty lastNameError}">
									<span id="content.errors" class="error"><c:out value="${lastNameError}"/></span>
								</c:if>
		 					</td>	
						</tr>
						<tr>
							<td>Prénom</td>
							<td>
								<input name="firstName" id="firstName" value="${param.firstName}"/>
		 						<c:if test="${!empty firstNameError}">
									<span id="content.errors" class="error"><c:out value="${firstNameError}"/></span>
								</c:if>
		 					</td>	
						</tr>
						<tr>
							<td>Email</td>
							<td>
								<input name="email" id="email" value="${param.email}"/>
		 						<c:if test="${!empty emailError}">
									<span id="content.errors" class="error"><c:out value="${emailError}"/></span>
								</c:if>
		 					</td>	
						</tr>
						<tr>
							<td>Objet</td>
							<td>
								<input name="object" id="object" value="${param.object}"/>
		 						<c:if test="${!empty objectError}">
									<span id="content.errors" class="error"><c:out value="${objectError}"/></span>
								</c:if>
		 					</td>	
						</tr>
						<tr>
							<td>Message</td>
							<td>
								<textarea name="message" id="message" maxlength="2048" rows="10">${param.message}</textarea>
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
		
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>