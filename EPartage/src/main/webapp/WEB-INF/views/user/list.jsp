<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<tiles:insertDefinition name="user">
	<tiles:putAttribute name="title">
			Liste des utilisateurs
		</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<p>Bienvenue sur le site e-Partage de Aix Marseille Université</p>

		<display:table name="users" pagesize="20" class="displaytag" id="row" requestURI="list.htm">
			<display:column property="id" title="ID" sortable="true"
				style="width:80px" />
			<display:column property="firstName" title="Prénom" sortable="true"
				style="width:300px " />

		</display:table>
	</tiles:putAttribute>
</tiles:insertDefinition>




