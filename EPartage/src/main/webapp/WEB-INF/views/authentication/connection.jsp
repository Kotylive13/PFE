<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">Authentification</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<div class="mainRibbon">
			<div class="contentMainRibbon">
				<h1>e-Partage</h1>
				<p>Un réseau social au sein d'Aix-Marseille Université</p>
				<a href="/EPartage/subscription/subscribe.htm">Inscription</a>
				<a href="">Contact</a>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>


