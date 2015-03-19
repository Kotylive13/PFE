<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<div class="header">
	<div class="centerHeader">
		<p id="logo"><a href="${pageContext.request.contextPath}/authentication/connection.htm" href="accueil">e-Partage</a></p>
		<form:form method="POST" modelAttribute="student" action="${pageContext.request.contextPath}/authentication/login.htm">
			<input id="connection" type="submit" value="Connexion">
		  	<form:input id="password" type="password" path="password" placeholder="mot de passe" tabindex="2"/>
		  	<form:input id="login" type="text" path="email" placeholder="Adresse mail" tabindex="1"/>
		  	<a id="forgetPassword" href="">Mot de passe oublié ?</a>
		</form:form>
	</div>
</div>
