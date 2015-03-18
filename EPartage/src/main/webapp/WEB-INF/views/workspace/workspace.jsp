<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="detail" value="/workspace/group/detail.htm" />

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">
			Espace de travail
		</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<p>Espace de travail</p>
		
		<p>Mes groupes</p>
		<c:forEach items="${groupsList}" var="g">
			<div><a href="${detail}?name=${g.name}">${g.name}</a></div>
		</c:forEach>
		
		<a href="/EPartage/publication/edit.htm">Ajouter une publication</a>

	</tiles:putAttribute>
</tiles:insertDefinition>