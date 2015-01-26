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

        <div id="submenu"></div>

    </div>

    <div class="row show-grid">

        <div id="deliveries-content"></div>

    </div>
</div>

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
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-dollar"></i>
                                    <input type="text" id="sum_to" name="sum_to" placeholder="do"/>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-calendar"></i>
                                    <input type="text" class="date" id="date_from" name="date_from" placeholder="od"/>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-calendar"></i>
                                    <input type="text" class="date" id="date_to" name="date_to" placeholder="do"/>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-ellipsis-vertical"></i>
                                    <input type="text" name="minNumberOfProducts" id="minNumberOfProducts" placeholder="od"/>
                                </label>
                            </section>

                            <section class="col col-2">
                                <label class="input">
                                    <i class="icon-prepend fa fa-ellipsis-vertical"></i>
                                    <input type="text" name="maxNumberOfProducts" id="maxNumberOfProducts" placeholder="do"/>
                                </label>
                            </section>

                        </div>
                    </fieldset>
                </form>
            </table>
        </div>
    </div>
</script>
<script id="deliveries-list-template" type="x-tmpl-mustache">

    <div class="jarviswidget jarviswidget-color-darken jarviswidget-sortable">

    <header role="heading">
        <h2> Dostawy </h2>
    </header>

    <div>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Nazwa</th>
                <th>Data dodania</th>
                <th>Magazyn</th>
                <th>Kontakt</th>
                <th>Liczba produktów</th>
                <th>Akcje</th>
            </tr>
        </thead>
        <tbody>
        {{#deliveries}}
        <tr>
            <td>{{label}}</td>
            <td>{{dateIn}}</td>
            <td>{{storeLabel}}</td>
            <td>{{contactLabel}}</td>
            <td>{{numberOfProducts}}</td>
            <td>
                <a class="btn btn-info btn-xs" delivery-id="{{id}}">Szczegóły</a>
                <a class="btn btn-success btn-xs">Edytuj</a>
                <a class="btn btn-danger btn-xs">Usuń</a>
            </td>
        </tr>
        {{/deliveries}}
        </tbody>

    </table>
    </div>

    </div>

</script>

</body>
</html>