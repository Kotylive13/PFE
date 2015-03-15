<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="masterAdmin.page">
	<tiles:putAttribute name="title">Ajouter un nouveau groupe</tiles:putAttribute>
	<tiles:putAttribute name="content">
	
		<div class="page">
		
		<c:if test="${!empty sessionScope.adminSession}">

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
								<td><form:input path="description" name="description" type="text" value="${group.description}"/></td>
							</tr>
	
							<tr>
								<td></td>
								<td><input id="modifyGroup" type="submit"></td>
							</tr>
							
						</table>
					</form:form>
				</div>
				
				<a href="${pageContext.request.contextPath}/login_staff/index.htm">Retour au menu</a><br/>

			</c:if>
		</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>