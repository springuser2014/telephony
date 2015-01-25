<%@ page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

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

<div class="container theme-showcase">
    <div class="row show-grid">
        <div class="col-md-4">
            <div id="content"></div>
        </div>
    </div>
</div>
<script id="deliveries-list-template" type="x-tmpl-mustache">

<h2> {{listlabel}} </h2>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Nazwa</th>
            <th>Data dodania</th>
        </tr>
    </thead>
    <tbody>
    {{#deliveries}}
    <tr>
        <td>{{label}}</td>
        <td>{{dateIn}}</td>
    </tr>
    {{/deliveries}}
    </tbody>

</table>

</script>

<script id="hello-template" type="x-tmpl-mustache">
Hello {{ name }}!
</script>


</body>
</html>