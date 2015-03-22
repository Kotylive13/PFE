<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Ajouter un nouveau groupe</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<h1>Modification d'un groupe</h1>
			<div class="form">
				<form:form id="modifyGroup" method="POST" modelAttribute="group" 
 					action="${pageContext.request.contextPath}/login_staff/group/modifyGroup.htm?name=${group.name}">

					<table>
						<tr>
							<td><form:label path="name">Nom du groupe</form:label></td>
							<td><form:input path="name" name="name" type="text" value="${group.name}"/></td>
						</tr>
						<tr>
							<td><form:label path="description">Description</form:label></td>
							<td><form:textarea path="description" name="description" type="text" value="${group.description}" rows="10" /></td>
						</tr>

						<tr>
							<td></td>
							<td>
								<input id="modifyGroup" class="submit" type="submit">
								<a href="${pageContext.request.contextPath}/login_staff/index.htm"><button type="button" class="submit">Annuler</button></a>
							</td>
						</tr>
						
					</table>
				</form:form>
			</div>
		</c:if>
	</tiles:putAttribute>
	
</tiles:insertDefinition>