<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<%-- <tiles:insertDefinition name="masterAdmin.page"> --%>
<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Liste des sous-catégories</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<h1 class="large">Liste des sous-catégories</h1>
			<h2 class="large">Groupe : <c:out value="${param.groupCategory}" /> => Catégorie : <c:out value="${param.nameCategory}" /></h2>
			<c:choose>
				<c:when test="${not empty listSubcategory}">
					<div class="toolbar">
						<a href="${pageContext.request.contextPath}/login_staff/category/listCategory.htm"><button class="submit">Précédent</button></a>
					</div>
						
					<div class="listSubcategory">
						<table>
							<tr>
								<td>Sous-catégorie</td>
								<td><img class="extraSmallPicture" src="<c:url value='/images/trash.png' />" alt="" title="Supprimer"/></td>
							</tr>
							<c:forEach var="subcategory" items="${listSubcategory}">
								<form:form method="POST" modelAttribute="subcategory"
									action="${pageContext.request.contextPath}/login_staff/subcategory/managementSubcategory.htm?subcategory=${subcategory.idSubcategory.subcategory}&group=${subcategory.idSubcategory.group}&category=${subcategory.idSubcategory.category}">
									<tr>
										<td><c:out value="${subcategory.idSubcategory.subcategory}" /></td>
										<td>
											<button type="submit" name="action" value="Supprimer" title="Supprimer">
											    <img src="<c:url value="/images/trash.png" />" />
											</button>
										</td>
									</tr>
								</form:form>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div class="information">
						<p>Aucune sous-catégorie</p>
						<a href="${pageContext.request.contextPath}/login_staff/category/listCategory.htm"><button class="submit">Précédent</button></a>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	</tiles:putAttribute>
	
</tiles:insertDefinition>