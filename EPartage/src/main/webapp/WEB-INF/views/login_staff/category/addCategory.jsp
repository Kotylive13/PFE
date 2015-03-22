<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Ajouter un nouveau groupe</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<h1>Ajout d'une catégorie</h1>
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
							<td>
								<input class="submit" type="submit">
								<a href="${pageContext.request.contextPath}/login_staff/index.htm"><button type="button" class="submit">Annuler</button></a>
							</td>
						</tr>
					</table>
				</form:form>
			</div>
		</c:if>
	</tiles:putAttribute>
	
</tiles:insertDefinition>