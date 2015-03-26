<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">Profile de ${studentProfile.firstName} ${studentProfile.lastName}</tiles:putAttribute>

	<tiles:putAttribute name="content">
		<div class="title">
			<img class="authorPicture"
				src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${studentProfile.id}"
				alt="" />
			<p>${studentProfile.firstName}${studentProfile.lastName}</p>
		</div>

		<div class="menuBar">
			<ul class="menu">
				<li><a
					href="${pageContext.request.contextPath}/workspace/showProfile.htm?userId=${studentProfile.id}">Informations
						personnelles</a></li>
			</ul>
		</div>

		<h1>Informations personnelles</h1>
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
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${studentProfile.birthDate}" /></td>
					</tr>
					<tr>
						<td>E-mail</td>
						<td>${studentProfile.email}</td>
					</tr>

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
						<td>${hobbies}</td>
					</tr>
					<tr>
						<td>Formation actuelle</td>
						<td>${studentProfile.promo}</td>
					</tr>
					<tr>
						<td>Inscription sur ePartage</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${studentProfile.inscriptAppDate}" /></td>
					</tr>
					<tr>
						<td>Inscription à l'université</td>
						<td>${studentProfile.inscriptUnivDate}</td>
					</tr>
					<c:if test="${sessionScope.userSession.id == studentProfile.id}">
						<tr>
							<td></td>
							<td><a
								href="${pageContext.request.contextPath}/workspace/editprofile.htm"><button
										type="button" class="submit">Modifier</button></a></td>
						</tr>
					</c:if>
				</table>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>