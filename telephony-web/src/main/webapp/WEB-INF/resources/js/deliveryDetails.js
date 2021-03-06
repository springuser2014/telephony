
require.config({

    baseUrl : '../resources/js',
    paths : {

        'bootstrap' : 'bootstrap/bootstrap',
        'bootstrap-progressbar' : 'plugin/bootstrap-progressbar',
        'bootstrap-tagsinput' : 'plugin/bootstrap-tags',
        'bootstrap-timepicker' : 'plugin/bootstrap-timepicker',
        'bootstrap-tree' : 'plugin/bootstrap-tree',
        'jquery-bootstrap-wizard' : 'plugin/bootstrap-wizard',
        'jquery-ui' : 'libs/jquery-ui-1.10.3.min',
        'jquery' : 'libs/jquery-1.11.0.min',
        'jquery-cookie' : 'libs/jquery.cookie',
        'mustache' : 'libs/mustache.min',
        'underscore' : 'libs/underscore-1.5.2',
        'backbone' : 'libs/backbone-1.1.0',
        'moment' : 'libs/moment',
        'smartwidgets' : 'smartwidgets/jarvis.widget',
        'notification' : 'notification/SmartNotification.min',
        'jquery-ui-easy-pie-chart' : 'plugin/easy-pie-chart/jquery.easy-pie-chart.min',
        'jquery-sparkline' : 'plugin/sparkline/jquery.sparkline.min',
        'jquery-validate' : 'plugin/jquery-validate/jquery.validate.min',
        'jquery-maskedinput' : 'plugin/masked-input/jquery.maskedinput.min',
        'select2': 'plugin/select2/select2.min',
        'bootstrap-slider' : 'plugin/bootstrap-slider/bootstrap-slider.min',
        'jquery-msie-fix' : 'plugin/msie-fix/jquery.mb.browser.min',
        'fastclick' : 'plugin/fastclick/fastclick',
        'jquery-fullcalendar' : 'plugin/fullcalendar/jquery.fullcalendar.min',
        'auth' : 'auth',
        'session' : 'session',
        'complaint' : 'complaint',
        'contact' : 'contact',
        'delivery' : 'delivery',
        'sale' : 'sale',
        'store' : 'store',
        'user' : 'user',
        'product' : 'product',
        'rest' : 'rest'
    },

    shim: {

        // TODO : add other modules dependencies

        'app' : {
            deps : ['bootstrap']
        },

        'jquery-ui' : {
            exports : '$',
            deps : ['jquery']
        },

        'jquery-cookie' : {
            exports : '$',
            deps : ['jquery']
        },

        'jquery-validate' : {
            exports : '$',
            deps : ['jquery']
        },

        'underscore' : {
            exports : '_'
        },

        'backbone': {
            deps: ['jquery','underscore'],
            exports: 'Backbone'
        },

        'bootstrap' : {
            deps : ['jquery']
        },

        'auth' : {
            deps: ['jquery', 'rest']
        },

        'moment' : {
            exports : 'moment'
        }
    }

});

require(['app', 'jquery-ui', 'mustache', 'jquery-cookie', 'rest', 'auth', 'jquery-validate', 'moment'],
    function(App, $, Mustache, cookies, rest, auth) {

        Telephony.Auth.authorize('user1@gmail.com', 'rfaysdhaiufsiuf');

        var numberOfResults = 0;
        var products = [];
        var taxes = [];
        var stores = [];
        var contacts = [];
        var delivery = null;
        var page = 0;
        var perPage = 5;

        var setDelivery = function(_delivery) {
            delivery = _delivery;
        };

        var getDelivery = function() {
            return delivery;
        };

        var getEditedProductIndex = function() {
            return editedProductIndex;
        };

        var setTaxes = function(_taxes) {
            taxes = _taxes;
        };

        var getTaxes = function() {
            return taxes;
        };

        var setStores = function(_stores) {
            stores = _stores;
        };

        var getStores = function() {
            return stores;
        };

        var setContacts = function(_contacts) {
            contacts = _contacts;
        };

        var getTax = function(id) {
            for (var i = 0; i < taxes.length;i++) {
                if (taxes[i].id == id)
                    return taxes[i];
            }
        };

        var getContact = function(id) {
            for (var i = 0; i < contacts.length;i++) {
                if (contacts[i].id == id)
                    return contacts[i];
            }
        };

        var getStore = function(id) {
            for (var i = 0; i < stores.length;i++) {
                if (stores[i].id == id)
                    return stores[i];
            }
        };

        var getProducts = function() {
            return products;
        };

        var getProduct = function(i) {
            return products[i];
        };

        var addProduct = function(p) {
            products.push(p);
        };

        var setProducts = function(prods) {
            products = prods;
        };

        var calculateNumberOfPages = function() {
            return Math.ceil(getNumberOfResults() / getCurrentPerPage());
        }

        var getCurrentPerPage = function() {
            return perPage;
        };

        var getNumberOfResults = function() {
            return numberOfResults;
        };

        var setNumberOfResults = function(val) {
            numberOfResults = val;
        };

        var getCurrentPage = function() {
            return page;
        };

        var setCurrentPage = function(v) {
            page = v;
        };

        var getDeliveryId = function() {
            var splitted = document.URL.split('/');
            var id = splitted[splitted.length-1];
            return parseInt(id);
        };

        var updateDeliveryView = function() {
            var delivery = getDelivery();

            var d = new Date();
            d.setTime(delivery.dateIn);
            var dateStr = moment(d).format('YYYY-MM-DD');

            $('#label').html(delivery.label);
            $('#date_in').html(dateStr);
            $('#contact').html(getContact(delivery.contactId).label);
            $('#store').html(getStore(delivery.storeId).label);
        };

        var fetchDeliveryData = function()  {

            var authData = Telephony.Auth.getAuthObj();

            authData.deliveryId = getDeliveryId();

            Telephony.Rest.Deliveries.Details(
                authData,
                function(jqxhr, status) {
                    var resp = jqxhr.responseJSON;

                    // TODO handle problems
                    setDelivery(resp.delivery);

                },
                function(jqxhr, status, error) {
                    // TODO handle problems
                },
                false
            );

        };

        var buildPaginationObj = function() {

            var pages = [];
            var nop = calculateNumberOfPages();

            for (var i = 0; i < nop; i++) {
                var o = {};
                o.page = i
                o.label = i+1;

                if (i == getCurrentPage()) {
                    o.isActive = true;
                } else {
                    o.isActive= false;
                }

                pages.push(o);
            }

            var firstDisabled = getCurrentPage() == 0;
            var previousDisabled = getCurrentPage() == 0;
            var nextDisabled = (getCurrentPage() == (nop - 1));
            var lastDisabled = (getCurrentPage() == (nop - 1));

            var obj = {
                first : {
                    disabled : firstDisabled
                },
                previous : {
                    page : getCurrentPage() - 1,
                    disabled : previousDisabled
                },
                next : {
                    page : getCurrentPage() + 1,
                    disabled : nextDisabled
                },
                last : {
                    page : nop - 1,
                    disabled : lastDisabled
                },
                pages : pages
            };

            return obj;
        };

        var getCurrentFormFilterValues = function() {

            var deliveriesIds = [];
            deliveriesIds.push(getDeliveryId());

            var obj = {
                page : getCurrentPage(),
                perPage : getCurrentPerPage(),
                deliveriesIds : deliveriesIds
            };

            return obj;
        };

        var fetchProductsDataAndUpdateView = function() {

            var authData = Telephony.Auth.getAuthObj();

            authData.filters = getCurrentFormFilterValues();

            Telephony.Rest.Products.Fetch(
                authData,
                function(jqxhr,status) {
                    // TODO handle problems
                    var resp = jqxhr.responseJSON;

                    setNumberOfResults(resp.countTotal);

                    setProducts(resp.products);

                    var params = {
                        products : resp.products
                    } ;

                    var params3 ={
                        pagination : buildPaginationObj()
                    };

                    var template  = $('#delivery-products-table').html();

                    var rendered = Mustache.render(template, {});

                    $('#products-list').html(rendered);

                    var template2 = $('#delivery-product-row').html();
                    var rendered2 = Mustache.render(template2, params);
                    $('#products-content').html(rendered2);

                    var template3 = $('#delivery-table-pagination-template').html();
                    var rendered3 = Mustache.render(template3, params3);

                    $('#delivery-table-pagination').html(rendered3);

                    $('li:not(.disabled) .changePage').click(function(e) {
                        var page = $(e.target).attr('page');
                        var p = parseInt(page);

                        setCurrentPage(p);
                        fetchProductsDataAndUpdateView();
                    });

                },
                function(jqxhr,status,error) {
                    // TODO handle problems
                },
                false
            );
        };

        var createView = function() {

            var authData = Telephony.Auth.getAuthObj();
            authData.filters = { page : 0, perPage : 100 };

            Telephony.Rest.Stores.Fetch(
                authData,
                function(jqXHR, status) {
                    // TODO handle problems
                    var resp = jqXHR.responseJSON;
                    setStores(resp.stores);
                },
                function(jqxhr,status,error) {
                    // TODO handle problems
                },
                false
            );

            fetchDeliveryData();

            Telephony.Rest.Taxes.Fetch(
                authData,
                function(jqXHR, status) {
                    // TODO handle problems
                    var resp = jqXHR.responseJSON;
                    taxes = resp.taxes;

                    setTaxes(taxes);
                },
                function(jqxhr,status,error) {
                    // TODO handle problems
                },
                false
            );

            Telephony.Rest.Contacts.Fetch(
                authData,
                function(jqXHR, status) {
                    // TODO handle problems
                    var resp = jqXHR.responseJSON;
                    setContacts(resp.contacts);
                },
                function(jqxhr,status,error) {
                    // TODO handle problems
                },
                false
            );

            var params2 = {
                taxes : taxes,
                contacts : contacts,
                stores : stores
            };

            var tmpl = $('#edit-delivery-template').html();

            var rendered = Mustache.render(tmpl, params2);

            $('#edit-delivery-content').html(rendered);

            if ($.fn.datepicker) {
                $('.date').each(function() {

                    var $this = $(this);
                    var dataDateFormat = 'yy-mm-dd';

                    $this.datepicker({
                        dateFormat : dataDateFormat,
                        prevText : '<i class="fa fa-chevron-left"></i>',
                        nextText : '<i class="fa fa-chevron-right"></i>'
                    });
                });
            }

            //refreshProductsTable();
            fetchProductsDataAndUpdateView();

            updateDeliveryView();
        };

        var refreshProductsTable = function() {

            var products = getProducts();

            if (products.length == 0) {
                var tmpl2 = $('#delivery-edit-product-empty-info').html();

                var rendered2 = Mustache.render(tmpl2, {});

                $('#products-list').html(rendered2);

            } else {

                var placeholder = $('#products-list').html('');
                var tmpl1= $('#delivery-products-table').html();
                var rendered = Mustache.render(tmpl1, {});
                placeholder.html(rendered);

                var tmpl2 = $('#delivery-product-row').html();
                var products = getProducts();
                var paramz = [];

                for(var i = 0; i < products.length ; i++) {
                    var params = products[i];
                    var tax = getTax(params.productTax.taxId);
                    params.index = i;
                    params.productTax.rate = tax.rate;

                    paramz.push(params);
                }

                rendered = Mustache.render(tmpl2, {products : paramz });
                $('#products-content').html(rendered);


            }
        };

        var tmpl = $('#edit-delivery-submenu').html();

        var rendered = Mustache.render(tmpl, {});

        $('#submenu').html(rendered);

        createView();

        $('#no-products-dialog').dialog({
            autoOpen : false,
            width : 500,
            resizable : false,
            modal : true,
            title : 'Brak produktów',
            buttons : [{
                html : "<i class='fa fa-times'></i>&nbsp; Okay",
                "class" : "btn btn-default",
                click : function() {
                    $(this).dialog("close");
                }
            }]
        });

    }
);
