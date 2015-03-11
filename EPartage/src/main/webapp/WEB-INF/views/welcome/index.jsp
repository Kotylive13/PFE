<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">
			Accueil
		</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<p>Bienvenue sur le site e-Partage de Aix Marseille Université</p>

		
		<c:if test="${empty sessionScope.userSession}">
			<div>
			<spring:url value='/authentication/connection.htm' var="var" />
			<a href="${var}">Se connecter</a>
			</div>
			
			<div>
			<spring:url value='/subscription/subscribe.htm' var="var" />
			<a href="${var}">S'inscrire</a>
			</div>
		</c:if>
		
		<c:if test="${!empty sessionScope.userSession}">
		
			<p>Vous êtes connecté(e) avec l'adresse : ${sessionScope.userSession.email}</p>

			<div>
			<spring:url value='/authentication/logout.htm' var="var" />
			<a href="${var}">Deconnexion</a>
			</div>
			
			
		</c:if>
		
	</tiles:putAttribute>
</tiles:insertDefinition>

