<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<%-- rediriger le contrôleur hello --%>
<c:choose>
    <c:when test="${!empty sessionScope.adminSession}">
       <c:redirect url="/login_staff/index.htm"/>
    </c:when>
    <c:when test="${!empty sessionScope.userSession}">
        <c:redirect url="/workspace/index.htm"/>
    </c:when>
    <c:otherwise>
        <c:redirect url="/authentication/connection.htm"/>
    </c:otherwise>
</c:choose>
