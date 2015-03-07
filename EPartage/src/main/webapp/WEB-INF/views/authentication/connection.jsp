<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<form method="post" action="<c:url value="/EPartage/user/login"/>">
		<!-- CHANGER VALUE !!! -->
		<fieldset>
			<legend>Connexion</legend>

			<!-- ---------------- ATTENTE DE CONNEXION ----------------- -->

			<c:if test="${empty sessionScope.userSession}">

				<!-- EMAIL -->
				<label for="email">Adresse email </label>
				<input type="email" id="email" name="email"
					placeholder="Ex : name@mail.com" size="30" maxlength="60" />
				<br />

				<!-- MOT DE PASSE -->
				<label for="password">Mot de passe </label>
				<input type="password" id="password" name="password" size="30"
					maxlength="60" />

				<!-- VALIDATION -->
				<input type="submit" value="Connexion" />

				<p>
					<a href="<c:url value=""/>">Mot de passe oublié ?</a>
				</p>
				<!-- CHANGER VALUE !!! -->

			</c:if>

			<!-- ---------------- UTILISATEUR CONNECTE ----------------- -->

			<%-- Vérification de la présence d'un objet utilisateur en session --%>
			<c:otherwise>

				<%-- Si l'utilisateur existe en session, alors on affiche son login. --%>
				<p class="succes">Vous êtes connecté(e) avec l'adresse :
					${sessionScope.userSession.login}</p>
				<p>
					<a href="<c:url value="/EPartage/user/logout"/>">Deconnexion</a>
				</p>
			</c:otherwise>
		</fieldset>
	</form>
</div>