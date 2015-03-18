<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Liste des groupes</tiles:putAttribute>
	<tiles:putAttribute name="content">
		
		<c:if test="${!empty sessionScope.adminSession}">


<%-- 			<c:choose> --%>
<%-- 				<c:when test="${not empty listGroups}"> --%>
<!-- 					<h1>Liste des groupes.</h1><br/><br/> -->
<!-- 					<ul> -->
<%-- 						<c:forEach var="group" items="${listGroups}"> --%>
<!-- 							<li> -->
<!-- 							<table> -->
<%-- 								<form:form method="POST" --%>
<%-- 										modelAttribute="group" --%>
<%-- 										action="${pageContext.request.contextPath}/login_staff/group/managementGroup.htm?name=${group.name}"> --%>
<!-- 										<tr> -->
<%-- 											<td width=150><c:out value="${group.name}" /></td> --%>
<%-- 											<td width=200><c:out value="${group.description}" /></td> --%>
<!-- 											<td width=100><input name="action" type="submit" value="Modifier"></td> -->
<!-- 											<td width=100><input name="action" type="submit" value="Supprimer"></td> -->
<!-- 										</tr> -->
<%-- 								</form:form> --%>
<!-- 							</table>						 -->
<!-- 							</li> -->
<%-- 						</c:forEach> --%>
<!-- 					</ul> -->
<%-- 				</c:when> --%>
<%-- 				<c:otherwise> --%>
<!-- 					<h1>Aucun groupe</h1> -->
<%-- 				</c:otherwise> --%>
<%-- 			</c:choose> --%>
			
<!-- 			<br/><br/> -->
<%-- 			<a href="${pageContext.request.contextPath}/login_staff/index.htm">Retour au menu</a><br/> --%>
			
			
			
			<div class="page">
				<div class="content">
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
							<h2>Aucun groupe</h2>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath}/login_staff/index.htm">Retour au menu</a>
				</div>
			</div>

		</c:if>
		
	</tiles:putAttribute>
</tiles:insertDefinition>