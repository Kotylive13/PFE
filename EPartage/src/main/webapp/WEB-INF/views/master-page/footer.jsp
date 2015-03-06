<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:useBean id="date" class="java.util.Date" />

<hr />
<div class="footerC">
	<b>Copyright <fmt:formatDate value="${date}" pattern="yyyy" />
		e-Partage, Asma - Koty - Jhon - Amaury - Yoann - Phiphi
	</b>
</div>

