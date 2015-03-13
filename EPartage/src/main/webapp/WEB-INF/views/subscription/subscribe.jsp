<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">Inscription</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<div class="page">
			<h1>S'inscrire</h1>
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
							<td><form:label path="firstName">Prénom</form:label></td>
							<td><form:input path="firstName" name="firstName" type="text"/></td>
							<form:errors path="firstName" />
						</tr>
						<tr>
							<td><form:label path="birthDate">Date de naissance</form:label></td>
							<td><form:input path="birthDate" name="birthDate" type="text"/></td>
							<form:errors path="birthDate" />
						</tr>
						<tr>
							<td><form:label path="adress">Adresse</form:label></td>
							<td><form:input path="adress" name="adress" type="text"/></td>
							<form:errors path="adress" />
						</tr>
						<tr>
							<td><form:label path="email">E-mail</form:label></td>
							<td><form:input path="email" name="email" type="text"/></td>
							<form:errors path="email" />
						</tr>
						<tr>
							<td><form:label path="password">Mot de passe</form:label></td>
							<td><form:input path="password" name="password" type="text"/></td>
							<form:errors path="password" />
						</tr>
						<tr>
							<td><form:label path="password">Confirmation</form:label></td>
							<td><form:input path="password" name="password" type="text"/></td>
							<form:errors path="password" />
						</tr>
						<tr>
							<td><label>Hobbies</label></td>
							<td>
								<select id="selector" class="tokenizer" multiple tabindex="-1" >
						        	<option>red</option>
						          	<option>blue</option>
						          	<option>green</option>
						        </select>
						        <input id="selectedValues" type="hidden" />
							</td>
						</tr>
						<tr>
							<td><form:label path="promo">Confirmation</form:label></td>
							<td><form:input path="promo" name="promo" type="text"/></td>
							<form:errors path="promo" />
						</tr>
						<tr>
							<td></td>
							<td><input id="informationSubmit" class="submit" type="submit" value="S'inscrire"></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


