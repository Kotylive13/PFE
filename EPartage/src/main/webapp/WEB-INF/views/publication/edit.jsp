<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="detail" value="/workspace/group/detail.htm" />

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">
			Publication
		</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<p>Etider une publication</p>
		

	</tiles:putAttribute>
</tiles:insertDefinition>