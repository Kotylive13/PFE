<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Liste des catégories</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<h1 class="large">Listes des catégories</h1>
			<c:choose>
				<c:when test="${not empty listCategory}">
					<div class="listCategory">
						<table>
							<tr>
								<td>Groupe</td>
								<td>Catégorie</td>
								<td><img class="extraSmallPicture" src="<c:url value='/images/eyes.png' />" alt="" title="Voir les sous-categories"/></td>
								<td><img class="extraSmallPicture" src="<c:url value='/images/add.png' />" alt="" title="Ajouter une sous-categorie"/></td>
								<td><img class="extraSmallPicture" src="<c:url value='/images/trash.png' />" alt="" title="Supprimer"/></td>
							</tr>
							<c:forEach var="category" items="${listCategory}">
								<form:form method="POST" modelAttribute="category"
									action="${pageContext.request.contextPath}/login_staff/category/managementCategory.htm?nameCategory=${category.idCategory.name}&groupCategory=${category.idCategory.group}">
										<tr>
											<td><c:out value="${category.idCategory.group}" /></td>
											<td><c:out value="${category.idCategory.name}" /></td>
											<td>
												<button type="submit" name="action" value="Voir les sous-categories" title="Voir les sous-categories">
												    <img src="<c:url value="/images/eyes.png" />" />
												</button>
											</td>
											<td>
												<button type="submit" name="action" value="Ajouter sous-categorie" title="Ajouter une sous-categorie">
												    <img src="<c:url value="/images/add.png" />" />
												</button>
											</td>
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
						<p>Aucune catégorie</p>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	</tiles:putAttribute>
	
</tiles:insertDefinition>