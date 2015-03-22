<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:url var="detail" value="/workspace/group/subcategory/detail.htm" />

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">
			Recherche
		</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<h1>Recherche</h1>
		
		<c:choose>
		    <c:when test="${empty groups}">
		        <p>Aucun groupe ne correspond à la recherche.</p>
		    </c:when>
		    <c:otherwise>		        
				<div class="listMessage">
					<table>
						<tr>
							<td><img class="smallPicture" src="<c:url value='/images/user.png' />" alt="" title="Avatar"/></td>
							<td>Groupe</td>
							<td>Description</td>
						</tr>
						<c:forEach items="${groups}" var="group">
							<tr>
								<td>
									<img class="smallPicture" src="${pageContext.request.contextPath}/workspace/group/avatar.htm?nameG=${groupsUrl[group.name]}"/>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/workspace/group/detail.htm?nameG=${groupsUrl[group.name]}">${group.name}</a>
								</td>
								<td>
									${group.description}
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
		    </c:otherwise>
		</c:choose>

	</tiles:putAttribute>
</tiles:insertDefinition>