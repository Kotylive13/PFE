<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="masterAdmin.page">
	<tiles:putAttribute name="title">Liste des groupes</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<div class="page">
		
		<c:if test="${!empty sessionScope.adminSession}">

			<c:choose>
				<c:when test="${not empty listGroups}">
					<h1>Liste des groupes.</h1><br/><br/>
					<ul>
						<c:forEach var="group" items="${listGroups}">
							<li>
								<form:form method="POST"
										modelAttribute="group"
										action="${pageContext.request.contextPath}/login_staff/group/managementGroup.htm?name=${group.name}">
									<c:out value="${group.name}" />
									<c:out value="${group.description}" />
									<input name="action" type="submit" value="Modifier">
									<input name="action" type="submit" value="Supprimer">
								</form:form>							
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<h1>Aucun groupe.</h1>
				</c:otherwise>
			</c:choose>
			
			<a href="${pageContext.request.contextPath}/login_staff/index.htm">Retour au menu</a><br/>
			
		</c:if>
		
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>