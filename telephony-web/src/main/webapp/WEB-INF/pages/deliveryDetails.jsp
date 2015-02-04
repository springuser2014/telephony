<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script data-main="/telephony-web/resources/js/deliveryDetails.js" src='<c:url value="../resources/js/libs/require-2.1.10.js"/>'>
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

        <div id="edit-delivery-content"></div>

    </div>

</div>

<script id="edit-delivery-submenu" type="x-tmpl-mustache">

    <div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

        <a class="btn btn-warning" href='<c:url value="/deliveries"/>'>Powrót</a>

    </div>
</script>


<script id="edit-delivery-template" type="x-tmpl-mustache">

<div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

    <header role="heading">
        <h2> Szczegóły dostawy </h2>
    </header>

    <div>
        <div id='delivery-details' class='smart-form'>

            <div class="row">
                <div class="col col-3">
                    <h5>Nazwa</h5>
                </div>
                <div class="col col-3" id="label">

                </div>
            </div>

            <div class="row">
                <div class="col col-3">
                    <h5>Magazyn</h5>
                </div>
                <div class="col col-3" id="store">

                </div>
           </div>

            <div class="row">
                <div class="col col-3">
                    <h5>Data dostawy</h5>
                </div>
                <div class="col col-3" id="date_in">

                </div>
            </div>

            <div class="row">
                <div class="col col-3">
                    <h5>Kontakt</h5>
                </div>
                <div class="col col-3" id="contact">

                </div>
            </div>
        </div>

        <legend>Produkty</legend>

        <div id='products-list'>
        </div>

    </div>

</div>

</script>


<script id="delivery-products-table" type="x-tmpl-mustache">

    <table class='table table-bordered' id='products-table'>
        <thead>
            <tr>
                <th class='imei'>IMEI</th>
                <th class='producent'>Producent</th>
                <th class='model'>Model</th>
                <th class='color'>Kolor</th>
                <th class='price-in'>Cena zakupu</th>
                <th class='price-out'>Cena sprzedaży</th>
                <th class='tax'>Podatek</th>
            </tr>
        </thead>
        <tbody id='products-content'>
        </tbody>

    </table>

    <div id='delivery-table-pagination'>
    </div>
</script>

<script id="delivery-product-row" type="x-tmpl-mustache">

    {{#products}}
    <tr>
        <td>{{imei}}</td>
        <td>{{producer}}</td>
        <td>{{model}}</td>
        <td>{{color}}</td>
        <td>{{priceIn}}</td>
        <td>
            {{price}}
        </td>
        <td>
            VAT {{tax}} %
        </td>

    </tr>
    {{/products}}

</script>

<script id="delivery-table-pagination-template" type="x-tmpl-mustache">

    {{#pagination}}
    <ul class="pagination">
    {{#first}}
    <li id="showFirst" class="first {{#disabled}}disabled{{/disabled}}">
    <a href="#" class="changePage" page=0>First</a>
    </li>
    {{/first}}
    {{#previous}}
    <li id="showPrevious" class="prev {{#disabled}}disabled{{/disabled}}">
    <a href="#" class="changePage" page={{page}}>Previous</a>
    </li>
    {{/previous}}

    {{#pages}}
    {{#isActive}}
    <li class="active">
    <a class="changePage" href="#" page={{page}}>{{label}}</a>
    </li>
    {{/isActive}}
    {{^isActive}}
    <li>
    <a class="changePage" href="#" page={{page}}>{{label}}</a>
    </li>
    {{/isActive}}
    {{/pages}}

    {{#next}}
    <li id="showNext" class="next {{#disabled}}disabled{{/disabled}}">
    <a href="#" class="changePage" page={{page}}>Next</a>
    </li>
    {{/next}}
    {{#last}}
    <li id="showLast" class="last {{#disabled}}disabled{{/disabled}}">
    <a href="#" class="changePage" page={{page}}>Last</a>
    </li>
    {{/last}}
    </ul>
    {{/pagination}}

</script>

<script id="delivery-edit-product-empty-info" type="x-tmpl-mustache">

    <div class="alert alert-info alert-block">
        Lista produktów jest pusta
    </div>

</script>

<script id="delivery-edit-delivery-success" type="x-tmpl-mustache">

    <div class="alert alert-success alert-block">
        Dostawa została zaktualizowana w bazie danych
        <button class="close" data-dismiss="alert">
            ×
        </button>
    </div>

</script>

<script id="delivery-edit-delivery-error" type="x-tmpl-mustache">

    <div class="alert alert-danger alert-block">
        Wystąpił problem podczas zapisu danych, spóbuj ponownie
        <button class="close" data-dismiss="alert">
            ×
        </button>
    </div>

</script>

<script id="delivery-edit-element" type="x-tmpl-mustache">

     <div class="row">
            <section class="col col-2">
                <label class="input">
                    <i class="icon-prepend fa fa-dollar"></i>
                    <input type="text" id="sum_from" name="sum_from" placeholder="Cena zakupu"/>
                    <b class="tooltip tooltip-bottom-right">Cena zakupu </b>
                </label>
            </section>
        </div>

        <div class="row">
            <section class="col col-2">
                <label class="input">
                    <i class="icon-prepend fa fa-dollar"></i>
                    <input type="text" id="sum_from" name="sum_from" placeholder="Cena zakupu"/>
                    <b class="tooltip tooltip-bottom-right">Cena sprzedaży </b>
                </label>
            </section>
        </div>

</script>


<div id="no-products-dialog" title="Nie masz żadnych produktów" style="display:none">
    Nie masz dodanych żadnych produktów na liście dostawy
</div>


</body>
</html>