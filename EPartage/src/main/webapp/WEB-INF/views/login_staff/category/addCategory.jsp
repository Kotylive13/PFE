<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<%-- <tiles:insertDefinition name="masterAdmin.page"> --%>
<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Ajouter un nouveau groupe</tiles:putAttribute>
	<tiles:putAttribute name="content">
	
<!-- 		<div class="page"> -->
		
		<c:if test="${!empty sessionScope.adminSession}">

				<div class="form">
					<form:form method="POST" modelAttribute="category" 
	 					action="${pageContext.request.contextPath}/login_staff/category/addCategory.htm">
	
						<table>
							<tr>
								<td><form:label path="idCategory.name">Nom de la catégorie</form:label></td>
								<td><form:input path="idCategory.name" name="idCategory.name" type="text"/></td>
							</tr>
							<tr>
								<td><form:label path="idCategory.group">Nom du groupe</form:label></td>
								<td><form:select name="idCategory.group" items="${groupMap}" path="" /></td>
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
<!-- 		</div> -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>