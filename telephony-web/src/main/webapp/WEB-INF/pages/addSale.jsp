<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script data-main="/telephony-web/resources/js/addSale.js" src='<c:url value="/resources/js/libs/require-2.1.10.js"/>'>
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
        <div id="submenu0"/></div>
    </div>

    <div class="row show-grid">
        <div id="submenu1"></div>
    </div>

    <div class="row show-grid">
        <div id="selected-products"></div>
    </div>

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

        <legend>Szukaj produktów</legend>

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
                                <a class="btn btn-info btn-sm" id='filter-results'>Filtruj wyniki</a>
                            </section>
                        </div>
                    </fieldset>
                </form>
            </table>
        </div>

</script>

<script id="products-list-template" type="x-tmpl-mustache">

    <legend>Produkty</legend>

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
                <a class="btn btn-success btn-xs add-sale" sale-id="{{id}}">Dodaj do listy</a>
                <a class="btn btn-info btn-xs" href="<c:url value="/productDetails/"/>{{id}}">Szczegóły</a>
                <a class="btn bg-color-magenta btn-xs txt-color-white" href="<c:url value="/editProduct/"/>{{id}}">Edytuj produkt</a>
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


<script id="add-sale-submenu" type="x-tmpl-mustache">

    <div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

        <a class="btn btn-warning" href='<c:url value="/sales"/>'>Powrót</a>

    </div>
</script>


<script id="add-sale-template-top" type="x-tmpl-mustache">

<div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

    <header role="heading">
        <h2> Nowa sprzedaż </h2>
    </header>

    <div>
        <div id='delivery-add'>
            <form id="add-delivery-form">
                <fieldset class="smart-form">
                    <div class="row">
                        <section class="col col-3">
                            <label class="input">
                                <input type="text" name="label" id="label" placeholder="Nazwa"/>
                                <b class="tooltip tooltip-bottom-right">Nazwa sprzedaży</b>
                            </label>
                        </section>

                        <section class="col col-3">
                            <label class="input">
                                <i class="icon-prepend fa fa-calendar"></i>
                                <input type="text" class="date" id="date_in" name="date_in" placeholder="Data sprzedaży"/>
                                <b class="tooltip tooltip-top-right">Data sprzedaży</b>
                            </label>
                        </section>

                        <section class="col col-3">
                            <label class="select">
                                <select name="store" id="store" class="valid">
                                    <option value="0" selected="" disabled="">magazyn sprzedaży</option>
                                    {{#stores}}
                                        <option value="{{storeId}}">{{label}}</option>
                                    {{/stores}}
                                </select>
                                <i></i>
                            </label>
                        </section>

                        <section class="col col-3">
                            <label class="select">
                                <select name="contact" id="contact" class="valid">
                                    <option value="0" selected="" disabled="">sprzedawca</option>
                                    {{#contacts}}
                                        <option value="{{id}}">{{label}}</option>
                                    {{/contacts}}
                                </select>
                                <i></i>
                            </label>
                        </section>

                    </div>
                </fieldset>
            </form>
        </div>

        <legend>Wybrane produkty</legend>

        <div id='products-list'>
        </div>

        <div id='search-products-filters'>

        <legend>Szukaj produktów</legend>

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
                                <label class="select">
                                    <select name="store" id="filter_store" class="valid">
                                        <option value="0" selected="" disabled="">magazyn</option>
                                        {{#stores}}
                                            <option value="{{storeId}}">{{label}}</option>
                                        {{/stores}}
                                    </select>
                                    <i></i>
                                </label>
                            </section>

                            <section class="col col-2">
                                <a class="btn btn-info btn-sm" id='filter-results'>Filtruj wyniki</a>
                            </section>
                        </div>
                    </fieldset>
                </form>
            </table>
        </div>


        </div>

        <div id='found-products'></div>

        <div class='bottom-buttons'>
            <fieldset class="smart-form">

                <div class='row' id='add-delivery-form-bottom'>

                <section class="col col-2">
                    <a class="btn btn-info btn-sm" id="clear-form">Czyść formularz</a>
                </section>

                <section class="col col-2">
                    <a class="btn btn-success btn-sm" id="save-sale">Zapisz sprzedaż</a>
                </section>

                </div>

            </fieldset>
        </div>
    </div>

    </div>
</div>

</script>


<script id="add-sale-products-table" type="x-tmpl-mustache">

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
                <th>Akcje</th>
            </tr>
        </thead>
        <tbody id='products-content'>
        </tbody>
    </table>

</script>

<script id="add-sale-product-row" type="x-tmpl-mustache">

    {{#products}}
    <tr>
        <td>{{imei}}</td>
        <td>{{producer}}</td>
        <td>{{model}}</td>
        <td>{{color}}</td>
        <td>{{priceIn}}</td>
        <td>
            {{#currentPrice}}
                {{rate}}
            {{/currentPrice}}
        </td>
        <td>
            {{#productTax}}
            VAT {{rate}} %
            {{/productTax}}
        </td>
        <td>
            <a class="btn btn-success btn-xs edit-product" product-index="{{index}}">Edytuj</a>
            {{^sale_id}}
            <a class="btn btn-danger btn-xs delete-product product-index="{{index}}">Usuń</a>
            {{/sale_id}}
        </td>
    </tr>
    {{/products}}

</script>

<script id="add-sale-add-product-empty-info" type="x-tmpl-mustache">

    <div class="alert alert-info alert-block">
        Nie został wybrany jeszcze żaden produkt
    </div>

</script>

<script id="add-sale-add-delivery-success" type="x-tmpl-mustache">

    <div class="alert alert-success alert-block">
        Dostawa została zapisana w bazie danych
        <button class="close" data-dismiss="alert">
            ×
        </button>
    </div>

</script>

<script id="add-sale-add-delivery-error" type="x-tmpl-mustache">

    <div class="alert alert-danger alert-block">
        Wystąpił problem podczas zapisu danych, spóbuj ponownie
        <button class="close" data-dismiss="alert">
            ×
        </button>
    </div>

</script>

<div id="no-products-dialog" title="Nie masz żadnych produktów" style="display:none">
    Nie masz dodanych żadnych produktów na liście dostawy
</div>


</body>
</html>