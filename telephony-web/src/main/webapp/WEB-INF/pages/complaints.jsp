<%@ page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<c:if test="${environment == 'PROD'}">
	<jsp:include page="include/header_css_prod.jsp" />
</c:if>

<c:if test="${environment == 'TEST'}">
	<jsp:include page="include/header_css_test.jsp" />
</c:if>
</head>

<body>
	
	<jsp:include page="top.jsp"/>

</body>
</html>