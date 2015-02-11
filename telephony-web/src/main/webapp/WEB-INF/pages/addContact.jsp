<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script data-main="/telephony-web/resources/js/addContact.js" src='<c:url value="/resources/js/libs/require-2.1.10.js"/>'>
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

        <div id='delivery-add-form'>

            <table class="table table-bordered">
                <form id="deliveries-search-form">
                    <fieldset class="smart-form">
                        <div class="row">
                            <section class="col col-3">
                                <label class="input">
                                    <input type="text" name="label" id="label" placeholder="Nazwa"/>
                                    <b class="tooltip tooltip-bottom-right">Nazwa dostawy</b>
                                </label>
                            </section>

                        </div>

                        <div class="row">
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
                        </div>

                        <div class="row">
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
                        </div>

                        <div class="row">
                            <section class="col col-3">
                                <label class="input">
                                    <i class="icon-prepend fa fa-calendar"></i>
                                    <input type="text" class="date" id="date_in" name="date_in" placeholder="Data dostawy"/>
                                    <b class="tooltip tooltip-top-right">Data dostawy</b>
                                </label>
                            </section>
                        </div>

                    </fieldset>
                </form>
            </table>

            <legend>Produkty</legend>

        </div>

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

<script id="deliveries-search-form-template" type="x-tmpl-mustache">
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
                                    <input type="text" name="label" id="label" placeholder="nazwa"/>
                                    <b class="tooltip tooltip-top-left">Nazwa dostawy</b>
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

                            <section class="col col-2">
                                <a class="btn btn-info btn-sm" id='filter-results'>Filtruj wyniki</a>
                            </section>

                            <section class="col col-2">
                                <a class="btn btn-success btn-sm" id='clear-form'>Czyść formularz</a>
                            </section>
                        </div>

                        <div class="row">

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-dollar"></i>
                                    <input type="text" id="sum_from" name="sum_from" placeholder="od"/>
                                    <b class="tooltip tooltip-bottom-right">Suma dostawy (od)</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-dollar"></i>
                                    <input type="text" id="sum_to" name="sum_to" placeholder="do"/>
                                    <b class="tooltip tooltip-bottom-right">Suma dostawy (do)</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-calendar"></i>
                                    <input type="text" class="date" id="date_from" name="date_from" placeholder="od"/>
                                    <b class="tooltip tooltip-top-left">Data dostawy (od)</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-calendar"></i>
                                    <input type="text" class="date" id="date_to" name="date_to" placeholder="do"/>
                                    <b class="tooltip tooltip-top-right">Data dostawy (do)</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-ellipsis-vertical"></i>
                                    <input type="text" name="minNumberOfProducts" id="minNumberOfProducts" placeholder="od"/>
                                    <b class="tooltip tooltip-bottom-right">Liczba dostarczonych produktów (od)</b>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-ellipsis-vertical"></i>
                                    <input type="text" name="maxNumberOfProducts" id="maxNumberOfProducts" placeholder="do"/>
                                    <b class="tooltip tooltip-bottom-right">Liczba dostarczonych produktów (do)</b>
                                </label>
                            </section>

                        </div>
                    </fieldset>
                </form>
            </table>
        </div>
    </div>

    <a class="btn btn-success" id="clear-form">Zapisz</a>

    <a class="btn btn-info" id="clear-form">Wyczyść formularz</a>
</script>

</body>
</html>