<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<div class="header">
	<div class="centerHeader">
		<p id="logo"><a href="">e-Partage</a></p>
		<form:form method="POST" modelAttribute="student" action="${pageContext.request.contextPath}/authentication/login.htm">
			<input id="connection" type="submit" value="Connexion">
		  	<form:input id="password" type="password" path="password" placeholder="mot de passe"/>
		  	<form:input id="login" type="text" path="email" placeholder="Adresse mail" />
		  	<a id="forgetPassword" href="">Mot de passe oublié ?</a>
		</form:form>		
	</div>
</div>