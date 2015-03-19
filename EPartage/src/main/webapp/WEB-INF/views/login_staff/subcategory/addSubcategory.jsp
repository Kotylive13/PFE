<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<%-- <tiles:insertDefinition name="masterAdmin.page"> --%>
<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Ajouter une sous-catégorie</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<c:if test="${!empty sessionScope.adminSession}">

				<div class="form">
					<form:form method="POST" modelAttribute="subcategory" 
	 					action="${pageContext.request.contextPath}/login_staff/subcategory/addSubcategory.htm?groupname=${group}&category=${category}">
	
						<table>
							<tr>
								<td><form:label path="idSubcategory.subcategory">Nom de la sous-catégorie</form:label></td>
								<td><form:input path="idSubcategory.subcategory" name="idSubcategory.subcategory" type="text"/></td>
							</tr>
							<tr>
								<td></td>
								<td><input class="submit" type="submit"></td>
							</tr>
						</table>
					</form:form>
				</div>
				
				<a href="${pageContext.request.contextPath}/login_staff/index.htm">Retour au menu</a><br/>

			</c:if>

	</tiles:putAttribute>
</tiles:insertDefinition>