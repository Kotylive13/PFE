<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<%-- <tiles:insertDefinition name="masterAdmin.page"> --%>
<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Liste des sous-catégories</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<c:if test="${!empty sessionScope.adminSession}">

			<c:choose>
				<c:when test="${not empty listSubcategory}">
					<h1>Liste des sous-catégories</h1><br/><br/>
					<ul>
						<c:forEach var="subcategory" items="${listSubcategory}">
							<li>
							<table>
								
								<form:form method="POST"
									modelAttribute="subcategory"
									action="${pageContext.request.contextPath}/login_staff/subcategory/managementSubcategory.htm">
									<tr>
										<td width=150><c:out value="${subcategory.idSubcategory.subcategory}" /></td>
										
										<td width=150><c:out value="${subcategory.idSubcategory.group}" /></td>
										
										<td width=150><c:out value="${subcategory.idSubcategory.category}" /></td>
										
<!-- 										<td width=100><input name="action" type="submit" value="Supprimer"></td> -->
									</tr>
								</form:form>
							</table>						
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
					<h1>Aucune sous-catégorie</h1>
				</c:otherwise>
			</c:choose>
			
			<br/><br/>
			<a href="${pageContext.request.contextPath}/login_staff/index.htm">Retour au menu</a><br/>
			
		</c:if>
		
	</tiles:putAttribute>
</tiles:insertDefinition>