<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="visitor">
	<tiles:putAttribute name="title">Authentification</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<div class="mainRibbon">
			<div class="contentMainRibbon">
				<h1>e-Partage</h1>
				<p>Le réseau social d'Aix-Marseille Université</p>
				<a href="/EPartage/subscription/subscribe.htm">Inscription</a>
				<a href="/EPartage/welcome/contact.htm">Contact</a>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>