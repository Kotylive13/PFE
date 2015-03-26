<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:url var="detail" value="/workspace/group/subcategory/detail.htm" />

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">Recherche</tiles:putAttribute>
	
	<tiles:putAttribute name="content">
		<h1 class="large">Résultat de votre recherche</h1>
		<h2 class="large">Résultat pour : ${param.keywords}</h2>
		<c:choose>
		    <c:when test="${empty groups}">
		    	<div class="information">
					<p>Aucun groupe ne correspond à la recherche.</p>
				</div>
		    </c:when>
		    <c:otherwise>		        
				<ul>
					<c:forEach items="${groups}" var="group">
						<li class="information">
							<div class="headerInformation">
								<img id="avatar" class="smallPicture" src="${pageContext.request.contextPath}/workspace/group/avatar.htm?nameG=${groupsUrl[group.name]}"
									title="${group.name}" />
								<p class="author">
									<a href="${pageContext.request.contextPath}/workspace/group/detail.htm?nameG=${groupsUrl[group.name]}">${group.name}</a>
									<span class="optionInformation">
										<img id="optionInformation_<c:out value="${groupsUrl[group.name]}"/>"
											onclick="slideElement('#contentInformation_<c:out value="${groupsUrl[group.name]}"/>', '#optionInformation_<c:out value="${groupsUrl[group.name]}"/>', 'slow');"
											class="extraSmallPicture" src="<c:url value="/images/arrow-down.png"/>" />
									</span>
								</p>
							</div>
							<div id="contentInformation_<c:out value="${groupsUrl[group.name]}"/>" class="contentInformation">
								<div class="margin"></div>
								<table>
									<tr>
										<td>Description</td>
										<td>${group.description}</td>
									</tr>
								</table>
							</div>
						</li>
					</c:forEach>
				</ul>
		    </c:otherwise>
		</c:choose>
	</tiles:putAttribute>
</tiles:insertDefinition>