<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="visitor">
	<tiles:putAttribute name="title">Mot de passe oublié</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<div class="page">
		
			<h1>Demande de réinitialisation du mot de passe</h1>	
			
			<div class="form">
				<form:form method="POST"
					action="${pageContext.request.contextPath}/welcome/forgotpassword.htm">
					<table>
						<tr>
							<td>Email</td>
							<td>
								<input name="email" id="email"/>
		 					</td>	
						</tr>
						<tr>
							<td>Numéro étudiant</td>
							<td>
								<input name="numStudent" id="numStudent"/>
		 						<c:if test="${!empty error}">
									<span id="content.errors" class="error"><c:out value="${error}"/></span>
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