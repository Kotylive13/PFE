<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">Profile de </tiles:putAttribute>
	<tiles:putAttribute name="content">

		<div class="information">
			<form:form modelAttribute="studentProfile">
				<table>
					<tr>
						<td>Numéro étudiant</td>
						<td>${studentProfile.numStudent}</td>
					</tr>
					<tr>
						<td>Nom</td>
						<td>${studentProfile.lastName}</td>
					</tr>
					<tr>
						<td>Prénom</td>
						<td>${studentProfile.firstName}</td>
					</tr>
					<tr>
						<td>Date de naissance</td>
						<td>${studentProfile.birthDate}</td>
					</tr>
					<tr>
						<td>E-mail</td>
						<td>${studentProfile.email}</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td>Mot de passe</td> -->
<!-- 						<td>bonjour</td> -->
<!-- 					</tr> -->
					<tr>
						<td>Adresse</td>
						<td>${studentProfile.adress}</td>
					</tr>
					<tr>
						<td>Téléphone</td>
						<td>${studentProfile.phone}</td>
					</tr>
					<tr>
						<td>Centres d'intérêt</td>
						<td>Informatique, jeux vidéos</td>
					</tr>
					<tr>
						<td>Formation actuelle</td>
						<td>${studentProfile.promo}</td>
					</tr>
					<tr>
						<td>Année d'inscription sur la plateforme</td>
						<td>${studentProfile.inscriptAppDate}</td>
					</tr>
					<tr>
						<td>Année d'inscription à l'université</td>
						<td>${studentProfile.inscriptUnivDate}</td>
					</tr>
					<tr>
						<td>Parcours académique</td>
						<td>01/01/2011 - 01/02/2011 M1 Informatique</td>
					</tr>
					<tr>
						<td>Expériences professionnelles</td>
						<td>01/01/2011 - 01/02/2011 Mc Donalds</td>
					</tr>
				</table>
			</form:form>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>