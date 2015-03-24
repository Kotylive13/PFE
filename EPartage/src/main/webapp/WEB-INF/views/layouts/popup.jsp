<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<c:if test="${!empty type}">
	<div id="popup" class="<c:out value='${type}'/>">
		<p id="messagePopup"><c:out value="${message}"/>
			<img id="optionsPopup" class="veryExtraSmallPicture" onclick="slideUp('#popup');" src="<c:url value="/images/cross.png" />" alt="" />
		</p>
	</div>
</c:if>

<%--<c:if test="${!empty param.type}">
	<div id="popup" class="<c:out value='${param.type}'/>">
		<p id="messagePopup"><c:out value="${param.message}"/>
			<img id="optionsPopup" class="veryExtraSmallPicture" onclick="slideUp('#popup');" src="<c:url value="/images/cross.png" />" alt="" />
		</p>
	</div>
</c:if> --%>