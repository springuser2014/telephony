<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

	<!-- CSS TELEPHONY LIBRARIES -->
	<link href='<c:url value="/resources/telephony/css/telephony.css"/>' rel="stylesheet" type="text/css"/>

	<!-- CSS EXTERNAL LIBRARIES  -->
	<link href='<c:url value="/resources/css/bootstrap.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/bootstrap-theme.css"/>' rel="stylesheet" type="text/css"/>

	<!-- JAVASCRIPT EXTERNAL LIBRARIES  -->
	<script src='<c:url value="/resources/js/bootstrap.js"/>'>
	</script>
	
	<script src='<c:url value="/resources/js/jquery-1.11.0.min.js"/>'>
	</script>
	
	<script src='<c:url value="/resources/js/require-2.1.10.js"/>'>
	</script>
	
	<script src='<c:url value="/resources/js/underscore-1.5.2.js"/>'>
	</script>

	<script src='<c:url value="/resources/js/bootstrap.js"/>'>
	</script>
	
	<!-- JAVASCRIPT TELEPHONY LIBRARIES  -->
	<script src='<c:url value="/resources/js/telephony.js"/>'>
	</script>
</head>

<body>
	<jsp:include page="top.jsp"/>

    <div class="container">

      <div class="starter-template">
        <h1>Bootstrap starter template</h1>
        <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
      </div>

    </div>
    
    <jsp:include page="bottom.jsp"/>	
</body>
</html>