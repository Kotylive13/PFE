<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:url var="detail" value="/workspace/group/subcategory/detail.htm" />

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">
			${group.name}
		</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<div class="title">
			<img class="largePicture" src="${pageContext.request.contextPath}/workspace/group/avatar.htm?nameG=${groupsUrl[group.name]}" alt="" />
			<p>${group.name}</p>
		</div>

		<div class="menuBar">
			<ul class="menu">
				<c:set var="nameG" value="${urlParams[group.name]}" />
				<c:forEach items="${group.categories}" var="cat">
					<li><a href="#">${cat.idCategory.name}</a> <c:set var="nameC"
							value="${urlParams[cat.idCategory.name]}" />
						<ul>
							<c:forEach items="${cat.subcategories}" var="sub">
								<c:set var="nameS"
									value="${urlParams[sub.idSubcategory.subcategory]}" />
								<li><a
									href="${detail}?nameG=${nameG}&nameC=${nameC}&nameS=${nameS}">${sub.idSubcategory.subcategory}</a></li>
							</c:forEach>
						</ul></li>
				</c:forEach>
			</ul>
		</div>

		<div class="information">
			<p>${group.description}</p>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>