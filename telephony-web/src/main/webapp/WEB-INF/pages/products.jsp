<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script data-main="/telephony-web/resources/js/products.js" src='<c:url value="/resources/js/libs/require-2.1.10.js"/>'>
    </script>

    <c:if test="${environment == 'PROD'}">
        <jsp:include page="include/header_css_prod.jsp" />
    </c:if>

    <c:if test="${environment == 'TEST'}">
        <jsp:include page="include/header_css_test.jsp" />
    </c:if>
</head>
<body>

<jsp:include page="include/common_templates.jsp"/>
<jsp:include page="top.jsp"/>

<div class="container theme-showcase">
    <div class="row show-grid">
        <div id="submenu"></div>
    </div>

    <div class="row show-grid">
        <div id="deliveries-content"></div>
    </div>

    <div class="row show-grid">
        <div class="jarviswidget" id="submenu2"></div>
    </div>
</div>

<script id="products-search-form-template" type="x-tmpl-mustache">
    <div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

        <header role="heading">
            <h2> Szukaj </h2>
        </header>

        <div id='deliveries-search-form'>
            <table class="table table-bordered">
                <form id="deliveries-search-form">
                    <fieldset class="smart-form">
                        <div class="row">
                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="imei" id="imei" placeholder="IMEI"/>
                                    <b class="tooltip tooltip-top-left">Identyfikator IMEI</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="producer" id="producer" placeholder="Producent"/>
                                    <b class="tooltip tooltip-top-left">Producent</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="model" id="model" placeholder="Model"/>
                                    <b class="tooltip tooltip-top-left">Model</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <input type="text" name="color" id="color" placeholder="Kolor"/>
                                    <b class="tooltip tooltip-top-left">Kolor</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <a class="btn btn-info btn-sm" id='filter-results'>Filtruj wyniki</a>
                            </section>

                            <section class="col col-2">
                                <a class="btn btn-success btn-sm" id='clear-form'>Czyść formularz</a>
                            </section>
                        </div>

                        <div class="row">

                            <section class="col col-2">
                                <label class="select">
                                    <select name="store" id="store" class="valid">
                                        <option value="0" selected="" disabled="">magazyn</option>
                                        {{#stores}}
                                            <option value="{{storeId}}">{{label}}</option>
                                        {{/stores}}
                                    </select>
                                    <i></i>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="select">
                                    <select name="status" id="status" class="valid">
                                        <option value="" selected="" disabled="">status produktu</option>
                                        <option value="IN_STORE">w magazynie</option>
                                        <option value="SOLD">sprzedane</option>
                                    </select>
                                    <i></i>
                                </label>
                            </section>
{{#asd}}
                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-dollar"></i>
                                    <input type="text" id="sum_from" name="sum_from" placeholder="od"/>
                                    <b class="tooltip tooltip-bottom-right">Suma sprzedaży (od)</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-dollar"></i>
                                    <input type="text" id="sum_to" name="sum_to" placeholder="do"/>
                                    <b class="tooltip tooltip-bottom-right">Suma sprzedaży (do)</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-calendar"></i>
                                    <input type="text" class="date" id="date_from" name="date_from" placeholder="od"/>
                                    <b class="tooltip tooltip-top-left">Data sprzedaży (od)</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-calendar"></i>
                                    <input type="text" class="date" id="date_to" name="date_to" placeholder="do"/>
                                    <b class="tooltip tooltip-top-right">Data sprzedaży (do)</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-ellipsis-vertical"></i>
                                    <input type="text" name="minNumberOfProducts" id="minNumberOfProducts" placeholder="od"/>
                                    <b class="tooltip tooltip-bottom-right">Liczba sprzedanych produktów (od)</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-ellipsis-vertical"></i>
                                    <input type="text" name="maxNumberOfProducts" id="maxNumberOfProducts" placeholder="do"/>
                                    <b class="tooltip tooltip-bottom-right">Liczba sprzedanych produktów (do)</b>
                                </label>
                            </section>
{{/asd}}
                        </div>
                    </fieldset>
                </form>
            </table>
        </div>
    </div>
</script>
<script id="products-list-template" type="x-tmpl-mustache">

    <div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

    <header role="heading">
        <h2> Produkty </h2>
    </header>

    <div>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>IMEI</th>
                <th>Producent</th>
                <th>Model</th>
                <th>Kolor</th>
                <th>Cena zakupu</th>
                <th>Cena sprzedaży</th>
                <th>Podatek</th>
                <th>Status produktu</th>
                <th>Akcje</th>
            </tr>
        </thead>
        <tbody>
        {{#products}}
        <tr>
            <td>{{imei}}</td>
            <td>{{producer}}</td>
            <td>{{model}}</td>
            <td>{{color}}</td>
            <td>{{priceIn}}</td>
            <td>
            {{#price}}
                {{price}}
            {{/price}}
            </td>
            <td>VAT {{tax}} %</td>
            <td>
            {{#saleId}}
            Sprzedany
            {{/saleId}}
            {{^saleId}}
            W magazynie
            {{/saleId}}
            </td>
            <td>
                <a class="btn btn-info btn-xs" href="<c:url value="/productDetails/"/>{{id}}">Szczegóły</a>
            </td>
        </tr>
        {{/products}}
        </tbody>

    </table>

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

    </div>

    </div>

</script>

<!-- TODO extract pagination to reusable component -->

<script id="products-add-template" type="x-tmpl-mustache">
    <a class="btn btn-warning" href='<c:url value="/addSale"/>'>Dodaj sprzedaż</a>
</script>

<script id="sale-delivery-success" type="x-tmpl-mustache">

    <div class="alert alert-success alert-block">
        Sprzedaż została anulowana
        <button class="close" data-dismiss="alert">
            ×
        </button>
    </div>

</script>

<script id="sale-delivery-error" type="x-tmpl-mustache">

    <div class="alert alert-danger alert-block">
        Wystąpił problem podczas usuwania sprzedaży, spóbuj ponownie
        <button class="close" data-dismiss="alert">
            ×
        </button>
    </div>

</script>

</body>
</html>