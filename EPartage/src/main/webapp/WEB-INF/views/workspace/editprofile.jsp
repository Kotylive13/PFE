<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">Modification du profil</tiles:putAttribute>
	<tiles:putAttribute name="content">
			<h1>Modification du profil</h1>
			<div class="form">
				<form:form id="updateInformationForm" method="POST"
					enctype="multipart/form-data"
					modelAttribute="student"
					action="${pageContext.request.contextPath}/workspace/editprofile.htm">

					<table>
						<tr>
							<td><form:label path="numStudent">Numéro étudiant</form:label></td>
							<td><form:input path="numStudent" name="numStudent" />
							<form:errors path="numStudent" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="inscriptUnivDate">Date d'inscription à l'université</form:label></td>
							<td><form:input path="inscriptUnivDate" type="text" />
							<form:errors path="inscriptUnivDate" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="lastName">Nom</form:label></td>
							<td><form:input path="lastName" name="lastName" type="text" />
							<form:errors path="lastName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="firstName">Prénom</form:label></td>
							<td><form:input path="firstName" name="firstName"
									type="text" />
							<form:errors path="firstName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label>Image de profil</label></td>
							<td>
								<input name="file" type="file" accept="image/jpeg, image/gif, image/png"/>
						        <c:if test="${!empty errorFile}">
									<span class="error"><c:out value="${errorFile}"/></span>
								</c:if>	
							</td>
						</tr>
						<tr>
							<td><form:label path="birthDate">Date de naissance</form:label></td>
							<td><form:input path="birthDate" type="text" />
							<form:errors path="birthDate" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="email">E-mail</form:label></td>
							<td><form:input path="email" type="text" />
							<form:errors path="email" cssClass="error" /></td>
						</tr>
						<tr>
							<td><label>Mot de passe</label></td>
							<td><input name="oldPassword" type="password"/>
							<c:if test="${!empty errorOldPassword}">
								<span class="error"><c:out value="${errorOldPassword}"/></span>
							</c:if>
							</td>
						</tr>
						<tr>
							<td><label>Nouveau mot de passe (laisser vide si non souhaité)</label></td>
							<td><input name="newPassword" type="password" value=""/></td>
						</tr>
						<tr>
							<td><label>Confirmation nouveau mot de passe (laisser vide si non souhaité)</label></td>
							<td><input name="confirmation" type="password" value=""/>
							<c:if test="${!empty errorConfirmation}">
								<span class="error"><c:out value="${errorConfirmation}"/></span>
							</c:if>
							</td>
						</tr>
						<tr>
							<td><form:label path="adress">Adresse</form:label></td>
							<td><form:input path="adress" type="text" />
							<form:errors path="adress" cssClass="error" /></td>
						</tr>
						
						<tr>
							<td><form:label path="promo">Promotion</form:label></td>
							<td><form:input path="promo" type="text" />
							<form:errors path="promo" cssClass="error" /></td>
						</tr>
						
						<tr>
							<td><form:label path="phone">Téléphone</form:label></td>
							<td><form:input path="phone" />
							<form:errors path="phone" cssClass="error" /></td>
						</tr>
						
						<tr>
							<td><label>Centres d'intérêt</label></td>
							<td>							
								<select id="tokenizer" class="tokenizer" multiple>
									<c:if test="${!empty hobbies}">
										<c:forEach items="${hobbies}" var="hobby">
									    	<option>${hobby.nameH}</option>
										</c:forEach>
									</c:if>
						        </select>
						        <input name="hobbies" id="selectedValues" type="hidden" />
						        <c:if test="${!empty errorHobbies}">
									<span class="error"><c:out value="${errorHobbies}"/></span>
								</c:if>			
							</td>
						</tr>
						
						<tr>
							<td>	
					        </td>
							<td><input id="informationSubmit" class="submit" type="submit"
								value="S'inscrire"></td>
						</tr>
					</table>
				</form:form>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


