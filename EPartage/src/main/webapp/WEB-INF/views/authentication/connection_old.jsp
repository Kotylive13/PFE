<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:putAttribute name="title">
			Authentification
		</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<p>Connexion</p>

		<c:if test="${not empty type}">
			<p>
				<c:out value="${message}" />
			</p>
		</c:if>

		<form:form method="POST" modelAttribute="student"
			action="${pageContext.request.contextPath}/authentication/login.htm">
			<table>
				<tr>
					<td><form:label path="email">Login</form:label></td>
					<td><form:input path="email" placeholder="Ex : name@mail.com"
							size="30" maxlength="60" /></td>
					<form:errors path="email" />
				</tr>
				<tr>
					<td><form:label path="password">Mot de passe</form:label></td>
					<td><form:password path="password" size="30" maxlength="60" /></td>
				</tr>
			</table>
			<input type="submit" value="Connexion" />
		</form:form>
		</tiles:putAttribute>
		


