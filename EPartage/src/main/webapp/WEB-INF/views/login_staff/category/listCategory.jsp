<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Liste des catégories</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<h1>Listes des catégories</h1>
			<c:choose>
				<c:when test="${not empty listCategory}">
					<ul>
						<c:forEach var="category" items="${listCategory}">
							<li>
							<table>
								<form:form method="POST"
									modelAttribute="category"
									action="${pageContext.request.contextPath}/login_staff/category/managementCategory.htm?nameCategory=${category.idCategory.name}&groupCategory=${category.idCategory.group}">
										<tr>
											<td width=150><c:out value="${category.idCategory.group}" /></td>
											<td width=150><c:out value="${category.idCategory.name}" /></td>
											<td width=100><input name="action" type="submit" value="Supprimer"></td>
										</tr>
								</form:form>
							</table>						
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<div class="information">
						<p>Aucune catégorie</p>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	</tiles:putAttribute>
	
</tiles:insertDefinition>