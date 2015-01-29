<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script data-main="/telephony-web/resources/js/addDelivery.js" src='<c:url value="/resources/js/libs/require-2.1.10.js"/>'>
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

        <div id="add-delivery-content"></div>

    </div>

</div>

<script id="add-delivery-submenu" type="x-tmpl-mustache">

    <div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

        <a class="btn btn-warning" href='<c:url value="/deliveries"/>'>Powrót</a>

    </div>
</script>


<script id="add-delivery-template" type="x-tmpl-mustache">

<div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

    <header role="heading">
        <h2> Nowa dostawa </h2>
    </header>

    <div>
        <div id='delivery-add'>
            <form id="add-delivery-form">
                <fieldset class="smart-form">
                    <div class="row">
                        <section class="col col-3">
                            <label class="input">
                                <input type="text" name="label" id="label" placeholder="Nazwa"/>
                                <b class="tooltip tooltip-bottom-right">Nazwa dostawy</b>
                            </label>
                        </section>

                        <section class="col col-3">
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
{{#add}}
                        <section class="col col-2">
                            <a class="btn btn-info btn-sm" id='add-new-store' href='<c:url value="/addStore"/>'>Dodaj nowy magazyn</a>
                        </section>
{{/add}}
                    </div>

                    <div class="row">

                        <section class="col col-3">
                            <label class="input">
                                <i class="icon-prepend fa fa-calendar"></i>
                                <input type="text" class="date" id="date_in" name="date_in" placeholder="Data dostawy"/>
                                <b class="tooltip tooltip-top-right">Data dostawy</b>
                            </label>
                        </section>

                        <section class="col col-3">
                            <label class="select">
                                <select name="contact" id="contact" class="valid">
                                    <option value="0" selected="" disabled="">kontakt</option>
                                    {{#contacts}}
                                        <option value="{{id}}">{{label}}</option>
                                    {{/contacts}}
                                </select>
                                <i></i>
                            </label>
                        </section>
{{#add}}
                        <section class="col col-2">
                            <a class="btn btn-info btn-sm" id='add-new-contact' href='<c:url value="/addContact"/>'>Dodaj nowy kontakt</a>
                        </section>
{{/add}}
                    </div>
                </fieldset>
            </form>
        </div>

        <legend>Dodaj produkt</legend>

        <div id='add-product'>
            <form id="add-product-form">
                <fieldset class="smart-form">
                    <div class="row">
                        <section class="col col-2">
                            <label class="input">
                                <input type="text" name="imei" id="imei" placeholder="IMEI"/>
                                <b class="tooltip tooltip-bottom-right">Unikalny IMEI produktu</b>
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="input">
                                <input type="text" name="producer" id="producer" placeholder="Producent"/>
                                <b class="tooltip tooltip-bottom-right">Producent</b>
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="input">
                                <input type="text" name="model" id="model" placeholder="Model"/>
                                <b class="tooltip tooltip-bottom-right">Model</b>
                            </label>
                        </section>

                        <section class="col col-2">
                            <label class="input">
                                <input type="text" name="color" id="color" placeholder="Kolor"/>
                                <b class="tooltip tooltip-bottom-right">Kolor</b>
                            </label>
                        </section>
                     </div>
                     <div class='row'>

                         <section class="col col-2">
                            <label class="input">
                                <input type="text" name="price_in" id="price_in" placeholder="Cena zakupu"/>
                                <b class="tooltip tooltip-bottom-right">Cena zakupu</b>
                            </label>
                         </section>

                        <section class="col col-2">
                            <label class="input">
                                <input type="text" name="price_out" id="price_out" placeholder="Cena sprzedaży"/>
                                <b class="tooltip tooltip-bottom-right">Cena sprzedaży</b>
                            </label>
                         </section>

                         <section class="col col-2">
                            <label class="select">
                                <select name="tax" id="tax" class="valid">
                                    <option value="0" selected="" disabled="">Podatek</option>
                                    {{#taxes}}
                                        <option value="{{id}}">VAT {{rate}} %</option>
                                    {{/taxes}}
                                </select>
                                <i></i>
                            </label>
                        </section>

                        <section class="col col-2">
                            <a id='add-product-btn' class="btn btn-info btn-sm">Dodaj</a>
                        </section>

                    </div>
                </fieldset>
            </form>
        </div>

        <legend>Produkty</legend>

        <div id='products-list'>
        </div>

        <div class='bottom-buttons'>
            <fieldset class="smart-form">

                <div class='row' id='add-delivery-form-bottom'>

                <section class="col col-2">
                    <a class="btn btn-info btn-sm" id="clear-form">Czyść formularz</a>
                </section>

                <section class="col col-2">
                    <a class="btn btn-success btn-sm" id="save-delivery">Zapisz dostawę</a>
                </section>

                </div>

            </fieldset>
        </div>
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
                <th>Akcje</th>
            </tr>
        </thead>
        <tbody id='products-content'>
        </tbody>
    </table>

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

<script id="delivery-add-product-empty-info" type="x-tmpl-mustache">

    <div class="alert alert-info alert-block">
        Nie został dodany jeszcze żaden produkt
    </div>

</script>

<script id="delivery-add-element" type="x-tmpl-mustache">
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

</body>
</html>