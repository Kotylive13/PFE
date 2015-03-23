<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">Profile de </tiles:putAttribute>
	<tiles:putAttribute name="content">

<div class="information">
					<table>
						<tr>
							<td>Numéro étudiant</td>
							<td>m1105106</td>
						</tr>
						<tr>
							<td>Nom</td>
							<td>Mothais</td>
						</tr>
						<tr>
							<td>Prénom</td>
							<td>Philippe</td>
						</tr>
						<tr>
							<td>Date de naissance</td>
							<td>10/07/1991</td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td>philippe.mothais@gmail.com</td>
						</tr>
						<tr>
							<td>Mot de passe</td>
							<td>bonjour</td>
						</tr>
						<tr>
							<td>Adresse</td>
							<td>2, rue Lamartine - 13700 Marignane</td>
						</tr>
						<tr>
							<td>Téléphone</td>
							<td>08 47 20 00 01</td>
						</tr>
						<tr>
							<td>Centres d'intérêt</td>
							<td>Informatique, jeux vidéos</td>
						</tr>
						<tr>
							<td>Formation actuelle</td>
							<td>M2 ISL</td>
						</tr>
						<tr>
							<td>Année d'inscription sur la plateforme</td>
							<td>2015</td>
						</tr>
						<tr>
							<td>Année d'inscription à l'université</td>
							<td>2011</td>
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
				</div>

	</tiles:putAttribute>
</tiles:insertDefinition>