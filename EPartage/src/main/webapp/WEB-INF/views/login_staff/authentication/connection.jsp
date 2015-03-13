<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="masterAdmin.page">
	<tiles:putAttribute name="title">Authentification</tiles:putAttribute>
	<tiles:putAttribute name="content">
	<c:if test="${not empty type}">
			<p>
				<c:out value="${message}" />
			</p>
		</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>