<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Ajouter un nouveau groupe</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<h1>Modification d'un groupe</h1>			
			<h2>${group.name}</h2>
			<div class="form">
				<form:form id="modifyGroup" method="POST" modelAttribute="group"  enctype="multipart/form-data"
 					action="${pageContext.request.contextPath}/login_staff/group/modifyGroup.htm?gname=${groupsUrl[group.name]}">
					<table>
						<tr>
							<td><form:label path="description">Description</form:label></td>
							<td>
								<form:textarea path="description" name="description" type="text" value="${group.description}" maxlength="1024" rows="10" />
								<form:errors cssClass="error" path="description" />
							</td>
						</tr>
						<tr>
							<td>Image du groupe</td>
							<td><input name="file" type="file" accept="image/jpeg, image/gif, image/png"/>
						        <c:if test="${!empty errorFile}">
									<span class="error"><c:out value="${errorFile}"/></span>
								</c:if></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input id="modifyGroup" class="submit" type="submit" value="Valider">
								<a href="${pageContext.request.contextPath}/login_staff/index.htm"><button type="button" class="submit">Annuler</button></a>
							</td>
						</tr>
						
					</table>
				</form:form>
			</div>
		</c:if>
	</tiles:putAttribute>
	
</tiles:insertDefinition>