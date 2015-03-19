<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Authentification</tiles:putAttribute>
	<tiles:putAttribute name="header">
		<div class="header">
			<div class="centerHeader">
				<p id="logo"><a href="">e-Partage</a></p>		
				<form:form method="POST" modelAttribute="admin" action="${pageContext.request.contextPath}/login_staff/authentication/login.htm">
					<input id="connection" type="submit" value="Connexion">
				  	<form:input id="password" tabindex="2" type="password" path="password" placeholder="Mot de passe"/>
				  	<form:input id="login" tabindex="1" type="text" path="login" placeholder="Login" />
				</form:form>
				
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="navBar">
	</tiles:putAttribute>
	<tiles:putAttribute name="content">
	</tiles:putAttribute>
	
</tiles:insertDefinition>