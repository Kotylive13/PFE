<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="masterAdmin.page">
	<tiles:putAttribute name="title">Affichage des personnes en attente</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<c:choose>
				<c:when test="${not empty listStudents}">
					<h1>1/ Liste des personne en attente de validation.</h1>
					<ul>


						<c:forEach var="student" items="${listStudents}">
							<li><form:form method="POST"
									modelAttribute="admin"
									action="${pageContext.request.contextPath}/login_staff/waitingUsers/validateUser.htm?id=${student.id}">
									<c:out value="${student.firstName}" />
									<c:out value="${student.lastName}" />
									<input id="connection" type="submit" value="Valider">
								</form:form>
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<h1>2/ Aucune personne en attente de validation.</h1>
				</c:otherwise>
			</c:choose>
		</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>