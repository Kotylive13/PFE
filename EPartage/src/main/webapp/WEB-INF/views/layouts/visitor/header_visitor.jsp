<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<div class="header">
	<div class="centerHeader">
		<p id="logo"><a href="${pageContext.request.contextPath}/authentication/connection.htm" title="accueil">e-Partage</a></p>
		<form:form method="POST" modelAttribute="student" action="${pageContext.request.contextPath}/authentication/login.htm">
			<input id="connection" type="submit" value="Connexion">
		  	<form:input id="password" type="password" path="password" placeholder="Mot de passe" tabindex="2"/>
		  	<form:input id="login" type="text" path="email" placeholder="Adresse mail" tabindex="1"/>
		  	<a id="forgetPassword" href="${pageContext.request.contextPath}/welcome/forgotpassword.htm">Mot de passe oublié ?</a>
		</form:form>
	</div>
</div>
