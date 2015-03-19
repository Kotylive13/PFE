<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<%-- <tiles:insertDefinition name="masterAdmin.page"> --%>
<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Liste des catégories</tiles:putAttribute>
	<tiles:putAttribute name="content">

<!-- 		<div class="page"> -->
		
		<c:if test="${!empty sessionScope.adminSession}">

			<c:choose>
				<c:when test="${not empty listCategory}">
					<h1>Liste des groupes.</h1><br/><br/>
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
											<td width=100><input name="action" type="submit" value="Voir les sous-categories"></td>
											<td width=100><input name="action" type="submit" value="Ajouter sous-categorie"></td>
											
										</tr>
								</form:form>
							</table>						
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<h1>Aucune catégorie</h1>
				</c:otherwise>
			</c:choose>
			
			<br/><br/>
			<a href="${pageContext.request.contextPath}/login_staff/index.htm">Retour au menu</a><br/>
			
		</c:if>
		
<!-- 		</div> -->

	</tiles:putAttribute>
</tiles:insertDefinition>