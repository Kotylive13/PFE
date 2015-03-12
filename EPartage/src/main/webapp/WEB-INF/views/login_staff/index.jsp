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
		<p>Bienvenue dans l'interface administration</p>

		
		<c:if test="${empty sessionScope.adminSession}">
			<div>
			<spring:url value='/login_staff/connection.htm' var="var" />
			<a href="${var}">Se connecter</a>
			</div>
		</c:if>
		
		<c:if test="${!empty sessionScope.adminSession}">
			<p>Vous êtes connecté(e) en tant qu'administrateur</p>

			<div>
			<spring:url value='/login_staff/logout.htm' var="var" />
			<a href="${var}">Deconnexion</a>
			</div>
		</c:if>
		
	</tiles:putAttribute>
</tiles:insertDefinition>

