<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp" %>

<style>
    .error {
        color: red; font-weight: bold;
    }
</style>

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">Inscription</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<div class="page">
		<h2>S'inscrire</h2>
			<div class="form">
				<form:form id="updateInformationForm" method="POST" modelAttribute="student" 
					action="${pageContext.request.contextPath}/subscription/subscribe.htm">

						<table>
							<tr>
								<td><form:label path="numStudent">Numéro étudiant</form:label></td>
								<td><form:input path="numStudent" name="numStudent" type="text"/></td>
								<form:errors path="numStudent" />
							</tr>
							<tr>
								<td><form:label path="lastName">Nom</form:label></td>
								<td><form:input path="lastName" name="lastName" type="text"/></td>
								<form:errors path="lastName" />
							</tr>
							<tr>
								<td><form:label path="firstName">Nom</form:label></td>
								<td><form:input path="firstName" name="firstName" type="text"/></td>
								<form:errors path="firstName" />
							</tr>
							<tr>
								<td>Date de naissance</td>
								<td><input id="" type="text"/></td>
							</tr>
							<tr>
								<td>E-mail</td>
								<td><input id="" type="text"/></td>
							</tr>
							<tr>
								<td>Mot de passe</td>
								<td><input id="" type="password"/></td>
							</tr>
							<tr>
								<td>Adresse</td>
								<td><textarea placeholder="Exprimez-vous"></textarea></td>
							</tr>
							<tr>
								<td>Téléphone</td>
								<td><input id="" type="text"/></td>
							</tr>
							<tr>
								<td>Centres d'intérêt</td>
								<td><textarea placeholder="Exprimez-vous"></textarea></td>
							</tr>
							<tr>
								<td>Formation actuelle</td>
								<td><input id="" type="text"/></td>
							</tr>
							<tr>
								<td>Année d'inscription sur la plateforme</td>
								<td><input id="" type="text"/></td>
							</tr>
							<tr>
								<td>Année d'inscription à l'université</td>
								<td><input id="" type="text"/></td>
							</tr>
							<tr>
								<td>Parcours académique</td>
								<td><textarea placeholder="Exprimez-vous"></textarea></td>
							</tr>
							<tr>
								<td>Expériences professionnelles</td>
								<td><textarea placeholder="Exprimez-vous"></textarea></td>
							</tr>
							<tr>
								<td></td>
								<td><input id="informationSubmit" type="submit"></td>
						</table>
					</form:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


