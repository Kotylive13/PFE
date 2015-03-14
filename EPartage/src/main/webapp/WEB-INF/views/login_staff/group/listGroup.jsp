<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="masterAdmin.page">
	<tiles:putAttribute name="title">Liste des groupes</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<c:if test="${!empty sessionScope.adminSession}">

			<c:choose>
				<c:when test="${not empty listGroups}">
					<h1>Liste des groupes.</h1><br/><br/>
					<ul>
						<c:forEach var="group" items="${listGroups}">
							<li>
								<c:out value="${group.name}" />
								<c:out value="${group.description}" />	
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<h1>Aucun groupe.</h1>
				</c:otherwise>
			</c:choose>
			
		</c:if>

	</tiles:putAttribute>
</tiles:insertDefinition>