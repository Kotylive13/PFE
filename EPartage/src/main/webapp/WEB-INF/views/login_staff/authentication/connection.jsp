<%@ page contentType="text/html; charset=utf-8"%>
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