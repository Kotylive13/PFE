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
							<td><form:input path="numStudent" name="numStudent" /></td>
							<td><form:errors path="numStudent" cssClass="error" />
							<td>
						</tr>
							<tr>
							<td><form:label path="inscriptUnivDate">Date d'inscription à l'université</form:label></td>
							<td><form:input path="inscriptUnivDate" /></td>
							<td><form:errors path="inscriptUnivDate" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="lastName">Nom</form:label></td>
							<td><form:input path="lastName" name="lastName" type="text" /></td>
							<td><form:errors path="lastName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="firstName">Prénom</form:label></td>
							<td><form:input path="firstName" name="firstName"
									type="text" /></td>
							<td><form:errors path="firstName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="birthDate">Date de naissance</form:label></td>
							<td><form:input path="birthDate" /></td>
							<td><form:errors path="birthDate" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="email">E-mail</form:label></td>
							<td><form:input path="email"
									placeholder="Ex : name@mail.com" /></td>
							<td><form:errors path="email" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="password">Mot de passe</form:label></td>
							<td><form:password path="password" /></td>
							<td><form:errors path="password" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="adress">Adresse</form:label></td>
							<td><form:input path="adress" /></td>
							<td><form:errors path="adress" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="phone">Téléphone</form:label></td>
							<td><form:input path="phone" /></td>
							<td><form:errors path="phone" cssClass="error" /></td> 
							<td><label>Hobbies</label></td>
							<td>
								<select id="selector" class="tokenizer" multiple tabindex="-1" >
						        	<c:forEach items="${hobbies}" var="hobby">
									    <option>${hobby.nameH}</option>
									</c:forEach>
						        </select>
						        <input id="selectedValues" type="hidden" />
							</td>
						</tr>
						<tr>
							<td>Centres d'intérêt</td>
							<td><textarea placeholder="Natation, equitation, ..." name ="hobbies"></textarea></td>
							<td><form:errors path="" cssClass="error" /></td>
						</tr>


						<tr>
							<td></td>
							<td><input id="informationSubmit" type="submit"
								value="S'inscrire"></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


