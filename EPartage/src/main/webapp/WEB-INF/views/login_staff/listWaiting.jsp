<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Affichage des personnes en attente</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<c:if test="${not empty type}">
	        <div class="popup">
	            <p class="<c:out value="${type}"/>"><c:out value="${message}"/></p>
	        </div>
		</c:if>
		<div class="page">
			<c:if test="${!empty sessionScope.adminSession}">
				<c:choose>
					<c:when test="${not empty listStudents}">
						<h1>Liste des personne en attente de validation d'inscription</h1>
						<ul>
							<c:forEach var="student" items="${listStudents}">
								<li>
									<form:form method="POST" modelAttribute="admin"
										action="${pageContext.request.contextPath}/login_staff/waitingUsers/validateUser.htm?id=${student.id}">
										<c:out value="${student.firstName}" />
										<c:out value="${student.lastName}" />
										<form:select name="groupPost" items="${groupMap}" path="" />
										<input name="action" class="submit" type="submit" value="Valider">
										<input name="action" class="submit" type="submit" value="Refuser">
									</form:form>
								</li>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<h1>Aucune personne en attente de validation d'inscription</h1>
					</c:otherwise>
				</c:choose>
			</c:if>
			<a href="${pageContext.request.contextPath}/login_staff/index.htm">Retour au menu</a><br/>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>