<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Ajouter un nouveau groupe</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<h1>Modification d'un groupe</h1>
			<div class="form">
				<form:form id="modifyGroup" method="POST" modelAttribute="group"  enctype="multipart/form-data"
 					action="${pageContext.request.contextPath}/login_staff/group/modifyGroup.htm?gname=${group.name}">

					<table>
						<tr>
							<td>Image du groupe</td>
							<td><input name="file" type="file" accept="image/jpeg, image/gif, image/png"/>
						        <c:if test="${!empty errorFile}">
									<span class="error"><c:out value="${errorFile}"/></span>
								</c:if></td>
						</tr>
						<tr>
							<td><form:label path="name">Nom du groupe</form:label></td>
							<td><form:input path="name" name="name" type="text" value="${group.name}"/>
						        <c:if test="${!empty errorName}">
									<span class="error"><c:out value="${errorName}"/></span>
								</c:if></td>
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