<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp" %>

<div class="header">
	<div class="centerHeader">
		<p id="logo"><a href="">e-Partage</a></p>
		<form:form method="POST" modelAttribute="admin" action="${pageContext.request.contextPath}/login_staff/authentication/login.htm">
			<input id="connection" type="submit" value="Connexion">
		  	<form:input id="password" type="password" path="password" placeholder="Mot de passe"/>
		  	<form:input id="login" type="text" path="login" placeholder="Login" />
		</form:form>		
	</div>
</div>