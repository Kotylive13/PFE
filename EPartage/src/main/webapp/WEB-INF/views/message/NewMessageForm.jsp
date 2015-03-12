<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name="master.page">
	<tiles:putAttribute name="title">
			New message
		</tiles:putAttribute>
	<tiles:putAttribute name="body">

		<p>Nouveau message</p>
		
		<c:if test="${!empty badReceivers}">
			<p>Les destinataires suivants sont incorrects :</p>
			<ul>
			<c:forEach items="${badReceivers}" var="r">
				<li>${r}</li>
			</c:forEach>
			</ul>
		</c:if>
		
		<form:form method="POST" modelAttribute="message"
			action="${pageContext.request.contextPath}/message/newmessage.htm">
			<table>
				<tr>
					<td><input type="text" name="receiversList" /></td>
					<c:if test="${!empty error}">
						<td><c:out value="${error}"/></td>
					</c:if>			
				</tr>
				<tr>
					<td><form:textarea path="content" maxlength="2048"/></td>
					<td><form:errors cssClass="error" path="content" /></td>
				</tr>
			</table>
			<input type="submit" value="Envoyer" />
		</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>