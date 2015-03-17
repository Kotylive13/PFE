<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">
			Espace de travail
		</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<p>Espace de travail</p>
		
		<p>Mes groupes</p>
		<c:forEach items="${groupsList}" var="g">
			<div><a href="#">${g.name}</a></div>
			<c:forEach items="${g.categories}" var="c">
				<div><a href="#">${c.idCategory.name}</a></div>
				<c:forEach items="${c.subcategories}" var="s">
					<div><a href="#">${s.idSubcategory.subcategory}</a></div>
					<c:forEach items="${s.publications}" var="p">
						<div>${p.title}</div>
						<c:forEach items="${p.opinions}" var="op">
							<div>${op.value}</div>
						</c:forEach>
						<c:forEach items="${p.comments}" var="com">
							<div>${com.content}</div>
						</c:forEach>
					</c:forEach>
				</c:forEach>
			</c:forEach>
		</c:forEach>

	</tiles:putAttribute>
</tiles:insertDefinition>