<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script data-main="/telephony-web/resources/js/productDetails.js" src='<c:url value="../resources/js/libs/require-2.1.10.js"/>'>
    </script>

    <c:if test="${environment == 'PROD'}">
        <jsp:include page="include/header_css_prod.jsp" />
    </c:if>

    <c:if test="${environment == 'TEST'}">
        <jsp:include page="include/header_css_test.jsp" />
    </c:if>
</head>

<body>

<jsp:include page="top.jsp"/>
<jsp:include page="include/common_templates.jsp"/>

<div class="container theme-showcase">

    <div class="row show-grid">
        <div id="submenu"></div>
    </div>

    <div class="row show-grid">
        <div id="product-details-content"></div>
    </div>

</div>

<script id="edit-delivery-submenu" type="x-tmpl-mustache">

    <div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

        <a class="btn btn-warning" href='<c:url value="/products"/>'>Powrót</a>

    </div>
</script>

<script id="product-details-template" type="x-tmpl-mustache">

<div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

    <header role="heading">
        <h2> Szczegóły produktu </h2>
    </header>

    {{#product}}
    <div>
        <div id='delivery-details' class='smart-form'>

            <div class="row">
                <div class="col col-3">
                    <h5>IMEI</h5>
                </div>
                <div class="col col-3">
                {{imei}}
                </div>
            </div>

            <div class="row">
                <div class="col col-3">
                    <h5>Producent</h5>
                </div>
                <div class="col col-3">
                {{producer}}
                </div>
           </div>

            <div class="row">
                <div class="col col-3">
                    <h5>Model</h5>
                </div>
                <div class="col col-3">
                {{model}}
                </div>
            </div>

            <div class="row">
                <div class="col col-3">
                    <h5>Cena zakupu</h5>
                </div>
                <div class="col col-3">
                    {{priceIn}}
                </div>
            </div>

            <div class="row">
                <div class="col col-3">
                    <h5>Cena sprzedaży</h5>
                </div>
                <div class="col col-3">
                    {{price}}
                </div>
            </div>

            <div class="row">
                <div class="col col-3">
                    <h5>Podatek</h5>
                </div>
                <div class="col col-3">
                    VAT {{tax}} %
                </div>
            </div>

            {{#delivery}}
                <legend>Dostawa</legend>

                <div class="row">
                    <div class="col col-3">
                        <h5>Nazwa</h5>
                    </div>
                    <div class="col col-3">
                        {{label}}
                    </div>
                </div>

                <div class="row">
                    <div class="col col-3">
                        <h5>Dzień</h5>
                    </div>
                    <div class="col col-3">
                        {{date}}
                    </div>
                </div>
                <div class="row">
                    <div class="col col-3">
                        <h5>Kontakt</h5>
                    </div>
                    <div class="col col-3">
                        {{contactLabel}}
                    </div>
                </div>
                <div class="row">
                    <div class="col col-3">
                        <h5>Magazyn</h5>
                    </div>
                    <div class="col col-3">
                        {{storeLabel}}
                    </div>
                </div>

            {{/delivery}}

            {{#sale}}

                <legend>Sprzedaż</legend>

                   <div class="row">
                    <div class="col col-3">
                        <h5>Nazwa</h5>
                    </div>
                    <div class="col col-3">
                        {{label}}
                    </div>
                </div>

                <div class="row">
                    <div class="col col-3">
                        <h5>Dzień</h5>
                    </div>
                    <div class="col col-3">
                        {{date}}
                    </div>
                </div>
                <div class="row">
                    <div class="col col-3">
                        <h5>Kontakt</h5>
                    </div>
                    <div class="col col-3">
                        {{contactLabel}}
                    </div>
                </div>
                <div class="row">
                    <div class="col col-3">
                        <h5>Magazyn</h5>
                    </div>
                    <div class="col col-3">
                        {{storeLabel}}
                    </div>
                </div>

            {{/sale}}

        </div>
        {{/product}}

<br/>
    </div>

</div>

</script>

</body>
</html>