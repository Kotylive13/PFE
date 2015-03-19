<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Liste des groupes</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<h1>Liste des groupes</h1>
			<c:choose>
				<c:when test="${not empty listGroups}">
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
					<div class="information">
						<p>Aucun groupe</p>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	</tiles:putAttribute>
	
</tiles:insertDefinition>