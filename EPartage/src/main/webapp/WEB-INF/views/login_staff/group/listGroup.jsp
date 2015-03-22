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
							<li class="information">
								<div class="headerInformation">
									<img id="avatar" class="smallPicture" src="${pageContext.request.contextPath}/workspace/group/avatar.htm?nameG=${groupsUrl[group.name]}" title="${group.name}" />
									<p class="author">
										${group.name}
										<span class="optionInformation">
											<img id="optionInformation_<c:out value="${group.name}"/>"
												onclick="slideElement('#contentInformation_<c:out value="${group.name}"/>', '#optionInformation_<c:out value="${group.name}"/>', 'slow');"
												class="extraSmallPicture" src="<c:url value="/images/arrow-down.png"/>" />
										</span>
									</p>
								</div>
								<div id="contentInformation_<c:out value="${group.name}"/>" class="contentInformation">
									<div class="margin"></div>
									<table>
										<tr>
											<td>Description</td>
											<td>${group.description}</td>
										</tr>
										<tr>
											<td>
											</td>
											<td>
												<form:form method="POST"
													modelAttribute="group"
													action="${pageContext.request.contextPath}/login_staff/group/managementGroup.htm?name=${group.name}">
													<input name="action" class="submit" type="submit" value="Modifier">
													<input name="action" class="submit" type="submit" value="Supprimer">
												</form:form>
											</td>
										</tr>
									</table>
								</div>						
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