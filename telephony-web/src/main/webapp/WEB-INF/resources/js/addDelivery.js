
require.config({

    baseUrl : 'resources/js',
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
            deps : [    'bootstrap']
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
        }
    }
});

require(['app', 'jquery-ui', 'mustache', 'jquery-cookie', 'rest', 'auth', 'jquery-validate'],
    function(App, $, Mustache, cookies, rest, auth) {

        Telephony.Auth.authorize('user1@gmail.com', 'rfaysdhaiufsiuf');

        var numberOfElements = 0;
        var products = [];
        var taxes = [];

        var setTaxes = function(_taxes) {
            taxes = _taxes;
        };

        var getTaxes = function() {
            return taxes;
        };

        var getTax = function(id) {
            var t = null;
            for (var i = 0; i < taxes.length;i++) {
                if (taxes[i].id == id)
                    return taxes[i];
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

        var removeProduct = function(i) {
            products.splice(i,1);
        };

        var decrementNumberOfElements = function() {
            numberOfElements--;
            if (numberOfElements <0) numberOfElements = 0;
        };

        var incrementNumberOfElements = function() {
          numberOfElements++;
        };

        var getNumberOfElements = function() {
            return numberOfElements;
        };

        var setNumberOfElements = function(n) {
           numberOfElements = n;
        };

        var clearDeliveryForm = function() {
            getDeliveryFormValidator().resetForm();

            $('#add-delivery-form label.state-error').removeClass('state-error');
            $('#add-delivery-form label.state-success').removeClass('state-success');

            $('#add-delivery-form input').val('');
            $('#add-delivery-form select').val(0);
        };

        var clearProductForm = function() {
            getAddProductValidator().resetForm();

            $('#add-product-form label.state-error').removeClass('state-error');
            $('#add-product-form label.state-success').removeClass('state-success');

            $('#add-product-form input').val('');
            $('#add-product-form select').val(0);
        };

        var clearProductTable = function() {

        };

        var clearForms = function() {
            setProducts([]);
            clearDeliveryForm();
            clearProductForm();
            refreshProductsTable();
        };

        var saveDelivery = function() {

        };

        var setAddProductFormValues = function (product) {
            $('#imei').val(product.imei);
            $('#producer').val(product.producer);
            $('#model').val(product.model);
            $('#color').val(product.color);
            $('#price_in').val(product.priceIn);
            $('#price_out').val(product.currentPrice.rate);
            $('#tax').val(product.productTax.taxId);
        };

        var createView = function() {

            var authData = Telephony.Auth.getAuthObj();
            authData.filters = { page : 0, perPage : 100 };

            var authData2 = Telephony.Auth.getAuthObj();
            authData2.filters = { page : 0, perPage : 100, activeAt : new Date().getTime() };

            Telephony.Rest.Stores.Fetch(
                authData,
                function(jqXHR, status) {
                    var resp = jqXHR.responseJSON;
                    stores = resp.stores;
                },
                function() { },
                false
            );

            Telephony.Rest.Taxes.Fetch(
                authData2,
                function(jqXHR, status) {
                    var resp = jqXHR.responseJSON;
                    taxes = resp.taxes;

                    setTaxes(taxes);
                },
                function() { },
                false
            );

            Telephony.Rest.Contacts.Fetch(
                authData,
                function(jqXHR, status) {
                    var resp = jqXHR.responseJSON;
                    contacts = resp.contacts;
                },
                function() { },
                false
            );

            var params2 = {
                taxes : taxes,
                contacts : contacts,
                stores : stores
            };

            var tmpl = $('#add-delivery-template').html();

            var rendered = Mustache.render(tmpl, params2);

            $('#add-delivery-content').html(rendered);

            if ($.fn.datepicker) {
                $('.date').each(function() {

                    var $this = $(this);
                    var dataDateFormat = $this.attr('data-dateformat') || 'dd.mm.yy';

                    $this.datepicker({
                        dateFormat : dataDateFormat,
                        prevText : '<i class="fa fa-chevron-left"></i>',
                        nextText : '<i class="fa fa-chevron-right"></i>'
                    });
                });
            }

            refreshProductsTable();

            $('.tooltip').tooltip();

            $('a#add-product-btn').click(function() {

                if (validateAddProductForm()) {

                    var obj = getProductObj();
                    addProduct(obj);
                    clearProductForm();
                    refreshProductsTable();
                }
            });

            $('#save-delivery').click(function() {
                validateDeliveryForm();
            });

            $('#clear-form').click(function() {
                clearForms();
            });
        };

        var getProductObj = function() {
            var now = new Date().getTime();

            var obj = {};
            obj.imei = $('#imei').val();
            obj.producer = $('#producer').val();
            obj.model = $('#model').val();
            obj.color = $('#color').val();
            obj.priceIn = $('#price_in').val();

            obj.currentPrice = {};
            obj.currentPrice.from = now;
            obj.currentPrice.rate = $('#price_out').val();

            obj.productTax = {};
            obj.productTax.from = now;
            obj.productTax.taxId = $('#tax').val();

            return obj;
        };

        var refreshProductsTable = function() {

            var products = getProducts();

            if (products.length == 0) {
                var tmpl2 = $('#delivery-add-product-empty-info').html();

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

                $('.edit-product').click(function(e) {
                    editProduct(e);
                });

                $('.delete-product').click(function(e) {
                    deleteProduct(e);
                });

            }

        };

        var editProduct = function(e) {
            var index = $(e.target).attr('product-index');
            var product = getProduct(index);
            setAddProductFormValues(product);
        };

        var deleteProduct = function(e) {
            var index = $(e.target).attr('product-index');
            removeProduct(index);
            refreshProductsTable();
        };

        var getDeliveryFormValidator = function() {
            var deliveryValidator =  $('#add-delivery-form').validate({
                rules : {
                    label : {
                        required : true,
                        minLength : 3
                    },
                    store : {
                        required : true
                    },
                    contact : {
                        required : true
                    },
                    date_in : {
                        required : true
                    }
                }
            });

            return deliveryValidator;
        };

        var validateDeliveryForm = function() {
            return getDeliveryFormValidator().form();
        };

        var getAddProductValidator = function() {

            var _data = Telephony.Auth.getAuthObj();
            _data.imei = $('#imei').val();

            $.validator.addMethod('isImeiAvailable', function(value, element) {

                var isAvailable = null;

                Telephony.Rest.Products.IsImeiAvailable(
                    _data,
                    function(resp,b) {

                        isAvailable = !resp.isAvailable;
                    },
                    function(a,b,c) {},
                    false
                );

                return isAvailable;

            }, 'Podany IMEI jest już u bazie');

            var addProductValidator = $('#add-product-form').validate({
                rules : {
                    imei : {
                        required : true,
                        minlength : 15,
                        maxlength : 15,
                        isImeiAvailable : true
                    },
                    producer : {
                        required : true,
                        minlength : 2
                    },
                    model : {
                        required : true,
                        minlength : 2
                    },
                    color : {
                        required : true,
                        minlength : 2
                    },
                    price_in : {
                        required : true,
                        min : 0,
                        max : 20000
                    },
                    price_out : {
                        required : true,
                        min : 0,
                        max : 20000
                    },
                    tax : {
                        required : true
                    }
                }
            });

            return addProductValidator;
        };

        var validateAddProductForm = function() {

            return getAddProductValidator().form();
        };

        var tmpl = $('#add-delivery-submenu').html();

        var rendered = Mustache.render(tmpl, {});

        $('#submenu').html(rendered);

        createView();

    }
);
