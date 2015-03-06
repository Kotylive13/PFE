<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">
			Liste des utilisateurs
		</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<p>Bienvenue sur le site e-Partage de Aix Marseille Université</p>

		<display:table name="users" pagesize="20" class="displaytag" id="row" requestURI="list.htm">
			<display:column property="id" title="ID" sortable="true"
				style="width:80px" />
			<display:column property="firstName" title="Prénom" sortable="true"
				style="width:300px " />

		</display:table>
	</tiles:putAttribute>
</tiles:insertDefinition>




