
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
    },

});

require(['app', 'jquery-ui', 'mustache', 'jquery-cookie', 'rest', 'auth', 'jquery-validate', 'moment'],
    function(App, $, Mustache, cookies, rest, auth) {

        Telephony.Auth.authorize('user1@gmail.com', 'rfaysdhaiufsiuf');

        var productsToAdd = [];
        var productsToEdit = [];
        var productsToDelete = [];

        var numberOfElements = 0;
        var products = [];
        var taxes = [];
        var editedProductIndex = null;
        var editedProductId = null;
        var delivery = null;

        var getProductsToAdd = function() {
            return productsToAdd;
        };

        var getProductsToEdit = function() {
            return productsToEdit;
        };

        var getProductsToDelete = function() {
            return productsToDelete;
        };

        var setProductsToAdd = function(_productsToAdd) {
            productsToAdd = _productsToAdd;;
        };

        var setProductsToEdit = function(_productsToEdit) {
            productsToEdit = _productsToEdit;
        };

        var setProductsToDelete = function(_productsToDelete) {
            productsToDelete = _productsToDelete;
        };

        var setProductToAdd = function(i, productToAdd) {
            productsToAdd[i] = productToAdd;
        };

        var setProductToEdit = function(i, productToEdit) {
            productsToEdit[i] = productToEdit;
        };

        var setProductToDelete = function(i, productToDelete) {
            productsToDelete[i] = productToDelete;
        };

        var setDelivery = function(_delivery) {
            delivery = _delivery;
        };

        var getDelivery = function() {
            return delivery;
        };

        var getEditedProductIndex = function() {
            return editedProductIndex;
        };

        var setEditedProductIndex = function(v) {
            editedProductIndex = v;
        };

        var getEditedProductId = function() {
            return editedProductId;
        };

        var setEditedProductId = function(v) {
            editedProductId = v;
        };

        var setTaxes = function(_taxes) {
            taxes = _taxes;
        };

        var getTaxes = function() {
            return taxes;
        };

        var getTax = function(id) {
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

        var addProductToAdd = function(p) {
            productsToAdd.push(p);
        };

        var setProducts = function(prods) {
            products = prods;
        };

        var setProduct = function(i, obj) {
            products[i] = obj;
        };

        var removeProduct = function(i) {
            products.splice(i,1);
        };

        var removeProductToAdd = function(obj) {
            var index = null;
            for (var i = i; i < productsToAdd.length; i++) {
                if (_.isEqual(obj, productsToAdd[i])) {
                    index = i;
                }
            }

            productsToAdd.splice(index, 1);
        };

        var setEditingProduct = function (index,obj) {
            var index = null;
            for (var i = i; i < productsToEdit.length; i++) {
                if (_.isEqual(obj, productsToAdd[i])) {
                    index = i;
                }
            }
        }

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

        var getDeliveryId = function() {
            var splitted = document.URL.split('/');
            var id = splitted[splitted.length-1];
            return parseInt(id);
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
                    setProducts(resp.delivery.products);
                },
                function(jqxhr, status, error) {
                    // TODO handle problems
                },
                false);

        };

        var clearDeliveryForm = function() {
            getDeliveryFormValidator().resetForm();

            $('#edit-delivery-form label.state-error').removeClass('state-error');
            $('#edit-delivery-form label.state-success').removeClass('state-success');

            $('#edit-delivery-form input').val('');
            $('#edit-delivery-form select').val(0);
        };

        var clearProductForm = function() {
            getAddProductValidator().resetForm();

            $('#edit-product-form label.state-error').removeClass('state-error');
            $('#edit-product-form label.state-success').removeClass('state-success');

            $('#edit-product-form input').val('');
            $('#edit-product-form select').val(0);
        };

        var clearForms = function() {
            setProducts([]);
            setEditedProductId(null);
            setEditedProductIndex(null);
            clearDeliveryForm();
            clearProductForm();
            refreshProductsTable();
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

            fetchDeliveryData();

            var authData = Telephony.Auth.getAuthObj();
            authData.filters = { page : 0, perPage : 100 };

            var authData2 = Telephony.Auth.getAuthObj();
            authData2.filters = { page : 0, perPage : 100, activeAt : new Date().getTime() };

            Telephony.Rest.Stores.Fetch(
                authData,
                function(jqXHR, status) {
                    // TODO handle problems
                    var resp = jqXHR.responseJSON;
                    stores = resp.stores;
                },
                function(jqxhr,status,error) {
                    // TODO handle problems
                },
                false
            );

            Telephony.Rest.Taxes.Fetch(
                authData2,
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
                    contacts = resp.contacts;
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

            refreshProductsTable();

            $('.tooltip').tooltip();

            $('a#edit-product-btn').click(function() {

                if (validateAddProductForm()) {

                    // adding new product
                    if (getEditedProductIndex() == null
                        && getEditedProductId() == null) {

                        var obj = getProductObj();
                        addProduct(obj);
                        addProductToAdd(obj);
                        clearProductForm();
                        refreshProductsTable();
                    } else if(getEditedProductIndex() != null
                        && getEditedProductId() == null) { // editing new product

                        index = getEditedProductIndex();
                        var obj = getProductObj();
                        setProduct(index, obj);
                        clearProductForm();
                        setEditedProductIndex(null);
                        setEditedProductId(null);
                        refreshProductsTable();
                    } else if (getEditedProductIndex() != null
                        && getEditedProductId() != null) { // editing old product

                        index = getEditedProductIndex();
                        var obj = getProductObj();
                        setProduct(index,obj);
                        setEditingProduct(index,obj);
                    }
                }
            });

            $('#save-changes').click(function() {

                if (getProducts().length == 0) {
                    $('#no-products-dialog').dialog('open');
                    return;
                }

                if (validateDeliveryForm()) {

                    var auth = Telephony.Auth.getAuthObj();
                    var delivery = getDelivery();

                    auth.deliveryDto = getDeliveryObj();
                    auth.deliveryDto.id = delivery.id;
                    auth.deliveryDto.productsToAdd = getProductsToAdd();
                    auth.deliveryDto.productsToEdit = getProductsToEdit();
                    auth.deliveryDto.productsToDelete = getProductsToDelete();

                    Telephony.Rest.Deliveries.Edit(
                        auth,
                        function(jqxhr, status) {

                            // TODO handle problems

                            var resp = jqxhr.responseJSON;

                            if (resp.success) {
                                clearForms();

                                var tmpl = $('#delivery-edit-delivery-success').html();
                                var ren = Mustache.render(tmpl, {});
                                $('#submenu').html($('#submenu').html() + ren);

                                $('button.close').click(function(e) {
                                    $(e.target).parent().hide();
                                });

                                createView();

                            } else {
                                var tmpl = $('#delivery-edit-delivery-error').html();
                                var ren = Mustache.render(tmpl, {});
                                $('#submenu').html($('#submenu').html() + ren);

                                $('button.close').click(function(e) {
                                    $(e.target).parent().hide();
                                });
                            }
                        },
                        function(jqxhr, status, error) {
                            // TODO handle problems
                        },
                        false
                    );
                }
            });

            $('#clear-form').click(function() {
                clearForms();
            });

            setDeliveryForm(getDelivery());
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

        var getDeliveryObj = function() {

            var obj = {
               label : $('#label').val(),
               dateIn : $('#date_in').val(),
               storeId : $('#store').val(),
               contactId : $('#contact').val()
            };

            return obj;
        };

        var setDeliveryForm = function(obj) {
            var date = new Date();
            date.setTime(parseInt(obj.dateIn));
            var dateStr = moment(date).format('YYYY-MM-DD');

            $('#label').val(obj.label);
            $('#date_in').val(dateStr);
            $('#store').val(obj.storeId);
            $('#contact').val(obj.contactId);
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

                $('.edit-product').click(function(e) {
                    editProductClick(e);
                });

                $('.edit-new-product').click(function(e) {
                    editNewProductClick(e);
                });

                $('.delete-product').click(function(e) {
                    deleteProductClick(e);
                });

                $('.delete-new-product').click(function(e) {
                    deleteNewProductClick(e);
                });
            }
        };

        var editNewProductClick = function(e) {
            var index = $(e.target).attr('product-index');
            var product = getProduct(index);
            setEditedProductIndex(index);
            setEditedProductId(null);
            setAddProductFormValues(product);
        };

        var editProductClick = function(e) {
            var index = $(e.target).attr('product-index');
            var id = $(e.target).attr('product-id');
            var product = getProduct(index);
            setEditedProductIndex(index);
            setEditedProductId(id);
            setAddProductFormValues(product);
        };

        var deleteNewProductClick = function(e) {
            var index = $(e.target).attr('product-index');
            var prod = getProduct(index);
            removeProductToAdd(prod);
            removeProduct(index);
            refreshProductsTable();
        };

        var deleteProductClick = function(e) {
            var index = $(e.target).attr('product-index');
            var id = $(e.target).attr('product-id');
            removeProduct(index);
            getProductsToDelete().push(id);
            refreshProductsTable();
        };

        var getDeliveryFormValidator = function() {
            var deliveryValidator =  $('#edit-delivery-form').validate({
                rules : {
                    label : {
                        required : true,
                        minlength : 3
                    },
                    store : {
                        required : true
                    },
                    contact : {
                        required : true
                    },
                    date_in : {
                        required : true,
                        dateISO : true
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
                    function(jqxhr,status) {
                        // TODO handle problems
                        var resp = jqxhr.responseJSON;
                        isAvailable = resp.isAvailable;
                    },
                    function(jqxhr,status,error) {
                        // TODO handle problems
                    },
                    false
                );

                return isAvailable;

            }, 'Podany IMEI jest już u bazie');

            $.validator.addMethod('isImeiUniqueInDelivery', function(value,element) {

                var prods = getProducts();

                for (var i = 0; i < prods.length; i++) {
                    prod = prods[i];

                    if (value.trim() == prod.imei.trim() && i != getEditedProductIndex()) {
                        return false;
                    }
                }

                return true;

            }, 'Podany IMEI został już dodany do listy');

            var addProductValidator = $('#edit-product-form').validate({
                rules : {
                    imei : {
                        required : true,
                        minlength : 15,
                        maxlength : 15,
                        isImeiAvailable : true,
                        isImeiUniqueInDelivery : true
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
