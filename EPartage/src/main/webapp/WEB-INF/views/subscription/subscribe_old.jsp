<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">
			Subscribe
		</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<p>S'inscrire</p>
		<form:form method="POST" modelAttribute="user"
			action="${pageContext.request.contextPath}/subscription/subscribe.htm">
			<table>
				<tr>
					<td><form:label path="firstName">Prénom</form:label></td>
					<td><form:input path="firstName" size="30" maxlength="60" /></td>
					<form:errors path="firstName" />
				</tr>

				<tr>
					<td><form:label path="lastName">Nom</form:label></td>
					<td><form:input path="lastName" size="30" maxlength="60" /></td>
					<form:errors path="lastName" />
				</tr>
				<tr>
					<td><form:label path="adress">Adresse</form:label></td>
					<td><form:input path="adress" size="30" maxlength="60" /></td>
					<form:errors path="adress" />
				</tr>
				<tr>
					<td><form:label path="birthDate">Date de naissance</form:label></td>
					<td><form:input path="birthDate" size="30" maxlength="60" /></td>
					<form:errors path="birthDate" />
				</tr>
				<tr>
					<td><form:label path="phone">Téléphone</form:label></td>
					<td><form:input path="phone" size="30" maxlength="60" /></td>
					<form:errors path="phone" />
				</tr>
				<tr>
					<td><form:label path="email">Adresse email</form:label></td>
					<td><form:input path="email" placeholder="Ex : name@mail.com"
							size="30" maxlength="60" /></td>
							<form:errors path="email" />
				</tr>
				<tr>
					<td><form:label path="password">Mot de passe</form:label></td>
					<td><form:password path="password" size="30" maxlength="60" /></td>
					<form:errors path="password" />
				</tr>
				<tr>
					<td><form:label path="avatar">Photo : </form:label></td>
					<td><input type="file"></td>
					<form:errors path="avatar" />
				</tr>

			</table>
			<input type="submit" value="S'inscrire" />
		</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>


