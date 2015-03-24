<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="title">Affichage des personnes inscrites</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<c:if test="${!empty sessionScope.adminSession}">
			<h1>Liste des personnes</h1>
			<c:choose>
				<c:when test="${not empty listStudents}">
					<ul>
						<c:forEach var="student" items="${listStudents}">
							<li class="information">
								<div class="headerInformation">
									<img id="avatar" class="smallPicture" src="${pageContext.request.contextPath}/workspace/avatar.htm?id=${student.id}"
										title="${student.firstName} ${student.lastName}" />
									<p class="author">
										${student.firstName} ${student.lastName}
										<span class="optionInformation">
											<img id="optionInformation_<c:out value="${student.id}"/>"
												onclick="slideElement('#contentInformation_<c:out value="${student.id}"/>', '#optionInformation_<c:out value="${student.id}"/>', 'slow');"
												class="extraSmallPicture" src="<c:url value="/images/arrow-down.png"/>" />
										</span>
									</p>
								</div>
								<div id="contentInformation_<c:out value="${student.id}"/>" class="contentInformation">
									<div class="margin"></div>
									<table>
										<tr>
											<td>Numéro étudiant</td>
											<td>${student.numStudent}</td>
										</tr>
										<tr>
											<td>Date de naissance</td>
											<td>${student.birthDate}</td>
										</tr>
										<tr>
											<td>E-mail</td>
											<td>${student.email}</td>
										</tr>
										<tr>
											<td>Adresse</td>
											<td>${student.adress}</td>
										</tr>
										<tr>
											<td>Année d'inscription à l'université</td>
											<td>${student.inscriptUnivDate}</td>
										</tr>
										<tr>
											<td>Promotion</td>
											<td>${student.promo}</td>
										</tr>
										<tr>
											<td>Liste des groupes</td>
											<td>
												<c:forEach var="group" items="${student.groups}">
													${group.name} 
												</c:forEach>
											</td>
										</tr>
										<form:form method="POST" modelAttribute="admin"
											action="${pageContext.request.contextPath}/login_staff/user/addGrouptoUser.htm?id=${student.id}">
										<tr>
											<td>Groupe</td>											
											<td><select name="groupPost">											
													<c:forEach items="${groupList}" var="group">														
														<c:set var="contains" value="false" />
														<c:forEach var="item" items="${student.groups}">
														  <c:if test="${group.name eq item.name}">
														    <c:set var="contains" value="true" />
														  </c:if>
														</c:forEach>
														<c:if test="${contains eq false}">
															<option value="${group.name}">${group.name}</option>
														  </c:if>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td></td>
											<td>
												<input name="action" class="submit" type="submit" value="Ajouter Groupe">
											</td>
										</tr>
										</form:form>
									</table>
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>
				<div class="information">
					<p>Aucune personne inscrite sur la plateforme</p>
				</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	</tiles:putAttribute>
</tiles:insertDefinition>