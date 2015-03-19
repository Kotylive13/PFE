<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="detail" value="/workspace/group/subcategory/detail.htm" />

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">
			Espace de travail
		</tiles:putAttribute>
	<tiles:putAttribute name="content">
	
		<c:set var="group" value="${subcategory.group}"/>
		<p>Groupe : ${group.name}</p>
				
		<c:set var="nameG" value="${urlParams[group.name]}"/>		
		<c:forEach items="${group.categories}" var="cat">
		<div>
			<a href="#">${cat.idCategory.name}</a>
			<c:set var="nameC" value="${urlParams[cat.idCategory.name]}"/>
			<c:forEach items="${cat.subcategories}" var="sub">
				<c:set var="nameS" value="${urlParams[sub.idSubcategory.subcategory]}"/>
				<a href="${detail}?nameG=${nameG}&nameC=${nameC}&nameS=${nameS}"> | ${sub.idSubcategory.subcategory}</a>	
			</c:forEach>
		</div>
		</c:forEach>
		
		<p>Liste des publications</p>
		<c:forEach items="${subcategory.publications}" var="p">
			<p>Titre publication : ${p.title}</p>
			<p>Liste des commentaires</p>
			<c:forEach items="${p.comments}" var="com">
				<div>${com.content}</div>
			</c:forEach>
		</c:forEach>

	</tiles:putAttribute>
</tiles:insertDefinition>