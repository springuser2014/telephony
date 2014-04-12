<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <c:if test="${environment == 'PROD'}">
	    <jsp:include page="include/header_css_prod.jsp" />
	    <jsp:include page="include/header_js_prod.jsp" />
	</c:if>
    
    <c:if test="${environment == 'TEST'}">
	    <jsp:include page="include/header_css_test.jsp" />
	    <jsp:include page="include/header_js_test.jsp" />
	</c:if>    
</head>

<body>
    <jsp:include page="top.jsp"/>
    
    <div class="container">

      <div class="starter-template">
        <h1>Bootstrap starter template index22222</h1>
        <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
      </div>

    </div>

    <jsp:include page="bottom.jsp"/>	
</body>
</html>