<<<<<<< HEAD
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="detail" value="/workspace/group/detail.htm" />

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">
			Publication
		</tiles:putAttribute>
	<tiles:putAttribute name="content">

		<p>Etider une publication</p>

		<form:form method="POST" modelAttribute="publication"
			action="${pageContext.request.contextPath}/publication/edit.htm">

			<table>

				<tr>
					<td>Titre</td>
					<td><form:input path="title" /></td>
					<td><form:errors path="title" cssClass="error" />
					<td>
				</tr>
				<tr>
					<td>Contenu à publier :</td>
					<td><form:textarea path="content" rows="5" cols="30" /></td>
					<td><form:errors path="content" cssClass="error" />
					<td>
				</tr>
				<tr>
					<td><form:label path="content">Fichier</form:label></td>
					<td><input type="file" name="file" id="file"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Publier"></td>
				</tr>
			</table>
		</form:form>


	</tiles:putAttribute>

</tiles:insertDefinition>
