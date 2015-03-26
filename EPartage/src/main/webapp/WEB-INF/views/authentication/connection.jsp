<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<tiles:insertDefinition name="visitor">
	<tiles:putAttribute name="title">Authentification</tiles:putAttribute>
	<tiles:putAttribute name="content">
		<div class="mainRibbon">
			<div class="contentMainRibbon">
				<h1>e-Partage</h1>
				<p>Un réseau social au sein d'Aix-Marseille Université</p>
				<p class="button"><a href="/EPartage/subscription/subscribe.htm">Inscription</a></p>
				<p class="button"><a href="/EPartage/welcome/contact.htm">Contact</a></p>
				<p class="link"><a href="http://philippe.mothais.etu.perso.luminy.univ-amu.fr/e-Partage/index.html">En savoir plus</a></p>				
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>