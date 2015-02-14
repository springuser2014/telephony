
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
		'jquery-storage-api' : 'libs/jquery.storageapi',
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
			deps : [ 'bootstrap' ]
		},

		'jquery-ui' : {
			exports : '$',
			deps : ['jquery']
		},

		'jquery-cookie' : {
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


require(['app', 'jquery-ui', 'mustache', 'jquery-cookie', 'rest', 'auth','moment', 'jquery-storage-api'],
function(App, $, Mustache, cookies, rest, auth) {

	var contactsLoaded = false;
	var storesLoaded = false;
	var taxesLoaded = false;
	var productsLoaded = false;

	var areContactsLoaded = function() {
		return contactsLoaded;
	};

	var setContactsLoaded = function(_loaded) {
		contactsLoaded = _loaded;
	};

	var areStoresLoaded = function() {
		return storesLoaded;
	};

	var setStoresLoaded = function(_loaded) {
		storesLoaded = _loaded;
	};

	var areTaxesLoaded = function() {
		return taxesLoaded;
	};

	var setTaxesLoaded = function(_loaded) {
		taxesLoaded = _loaded;
	};

	var areProductsLoaded = function() {
		return productsLoaded;
	};

	var setProductsLoaded = function(_loaded) {
		productsLoaded = _loaded;
	};

	var contacts = [];
	var stores = [];
	var filters = {};
	var oldFilters = {};
	var page = 0;
	var perPage = 5;
	var numberOfResults = 0;

	var products = [];
	var selectedProducts = [];

	var setProducts = function(prods) {
		products = prods;
	};

	var getProducts = function() {
		return products;
	};

	var setSelectedProducts = function(prods) {
		selectedProducts = prods;
	};

	var getSelectedProducts = function() {
		return selectedProducts;
	};

	var getOldFilters = function() {
		return oldFilters;
	};

	var setOldFilters = function(v) {
		oldFilters = v;
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

	var incrementCurrentPage =  function() {
		page++;
	};

	var decrementCurrentPage =  function() {
		page--;
		if (page < 1) page = 1;
	};

	var getCurrentPerPage = function() {
		return perPage;
	};

	var setCurrentPerPage = function(v) {
		perPage = v;
	};

	var incrementCurrentPerPage = function() {
		perPage++;
	};

	var decrementCurrentPage = function() {
		perPage--;
		if (perPage < 1) perPage = 1;
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

	var getContacts = function() {
		return contacts;
	};

	var setTaxes = function(_taxes) {
		taxes = _taxes;
	};

	var getTaxes = function() {
		return taxes;
	};

	var calculateNumberOfPages = function() {
		return Math.ceil(getNumberOfResults() / getCurrentPerPage());
	}

	var clearSearchForm = function() {
		$('#imei').val('');
		$('#producer').val('');
		$('#model').val('');
		$('#color').val('');
		$('#store').val(0);
		$('#status').val(0);
	};

	var getCurrentFormFilterValues = function() {

		var obj = {
			ignoreIds : getSelectedProducts(),
			imei : ($('#imei')!= null? $('#imei').val() : null),
			producer : ($('#producer') != null ? $('#producer').val() : null),
			model : ($('#model') != null ? $('#model').val() : null),
			color : ($('#color') != null ? $('#color').val() : null),
			status : 'IN_STORE',
			storeId : ($('#filter_store') != null ? $('#filter_store').val() : null),
			page : getCurrentPage(),
			perPage : getCurrentPerPage()
		};

		return obj;
	};

	var refreshProductsTable = function() {
		var place = $('#products-list');

		if (getSelectedProducts().length == 0) {
			var tmpl = $('#add-sale-add-product-empty-info').html();
			var ren = Mustache.render(tmpl, {});
			place.html(ren);
		} else {

		}
	};

	var checkIfAllDataIsLoaded = function() {

		if (areContactsLoaded() &&
			areStoresLoaded() &&
			areTaxesLoaded() &&
			areProductsLoaded()) {

			var tmpl = $('#add-sale-template-top').html();

			var vars = {
				contacts : getContacts(),
				stores : getStores(),
				taxes : getTaxes()
			};

			var ren = Mustache.render(tmpl, vars);

			$('#submenu1').html(ren);

			var params1 = {
				products : getProducts()
			};

			for (var i = 0; i < params1.products.length; i++) {
				var d = params1.products[i];

				var date = new Date();
				date.setTime(d.dateIn);
				var dateStr = moment(date).format('YYYY-MM-DD');
				d.dateIn = dateStr;
			}

			params1.pagination = buildPaginationObj();

			var template = $('#products-list-template').html();

			var rendered = Mustache.render(template, params1);

			$('#found-products').html(rendered);

			$('li:not(.disabled) .changePage').click(function(e) {
				var page = $(e.target).attr('page');
				var p = parseInt(page);

				setCurrentPage(p);
				fetchAndUpdateView();
			});

			$('.delete-sale').click(function(e) {
				var sale = $(e.target).attr('sale-id');
				var id = parseInt(sale);

				var authData = Telephony.Auth.getAuthObj();
				authData.salesId = id;

				Telephony.Rest.Sales.Delete(
					authData,
					function(jqxhr,status) {
						// TODO handle problems

						var resp = jqxhr.responseJSON;

						if (resp.success) {
							showSuccessDeleteDialog();
							fetchAndUpdateView();
						} else {
							showErrorDeleteDialog();
						}
					},
					function(jqxhr,status,error) {
						// TODO handle problems
					},
					false
				);

			});

			$('.add-product-to-list').click(function(e) {
				var productId = $(e.target).attr('product-id');

				getSelectedProducts().push(productId);
			});

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

			$('#filter-results').click(function() {
				fetchAndUpdateView();
			});

			$('#clear-form').click(function() {
				clearSearchForm();
			});

			$('.tooltip').tooltip();

			refreshProductsTable();
		}
	};

	var displayTopFormView = function() {

		var authData = Telephony.Auth.getAuthObj();
		authData.filters = { page : 0, perPage : 100 };

		Telephony.Rest.Stores.Fetch(
			authData,
			function(jqxhr, status) {
				var resp = jqxhr.responseJSON;
				// TODO handle problems
				setStores(resp.stores);

				setStoresLoaded(true);

				checkIfAllDataIsLoaded();
			},
			function(jqxhr, status, error) {
				// TODO handle problems
			},
			true
		);

		Telephony.Rest.Taxes.Fetch(
			authData,
			function(jqxhr, status) {
				var resp = jqxhr.responseJSON;
				// TODO handle problems
				setTaxes(resp.taxes);

				setTaxesLoaded(true);

				checkIfAllDataIsLoaded();
			},
			function(jqxhr, status, error) {
				// TODO handle problems
			},
			true
		);

		Telephony.Rest.Contacts.Fetch(
			authData,
			function(jqxhr, status) {
				var resp = jqxhr.responseJSON;
				// TODO handle problems
				setContacts(resp.contacts);

				setContactsLoaded(true);

				checkIfAllDataIsLoaded();
			},
			function(jqxhr, status, error) {
				// TODO handle problems
			},
			true
		);
	};

	var fetchAndUpdateView = function() {

		var authData = Telephony.Auth.getAuthObj();
		authData.filters = getCurrentFormFilterValues();

		Telephony.Rest.Products.Fetch(
			authData,
			function(jqxhr, status) {

				// TODO handle problems

				var resp = jqxhr.responseJSON;

				setNumberOfResults(resp.countTotal);

				setProducts(resp.products);

				setProductsLoaded(true);

				checkIfAllDataIsLoaded();
			},
			function(jqxhr,status,error) {
				// TODO handle problems
			},
			true
		);
	};

	var showSuccessDeleteDialog = function() {
		var tmpl = $('#sale-delivery-success').html();
		var ren = Mustache.render(tmpl, {});
		$('#submenu').html($('#submenu').html() + ren);

		$('button.close').click(function(e) {
			$(e.target).parent().hide();
		});
	};

	var showErrorDeleteDialog = function() {
		var tmpl = $('#sale-delivery-error').html();
		var ren = Mustache.render(tmpl, {});
		$('#submenu').html($('#submenu').html() + ren);

		$('button.close').click(function(e) {
			$(e.target).parent().hide();
		});
	};

	Telephony.Auth.authorize('user1@gmail.com', 'rfaysdhaiufsiuf');

	//var createFiltersView = function() {
    //
	//	var authData = Telephony.Auth.getAuthObj();
	//	authData.filters = { page : 0, perPage : 30 };
    //
	//	Telephony.Rest.Stores.Fetch(
	//		authData,
	//		function(jqxhr,status) {
	//			// TODO handle problems
	//			var resp = jqxhr.responseJSON;
	//			setStores(resp.stores);
	//		},
	//		function(jqxhr,status,error) {
	//			// TODO handle problems
	//		},
	//		false
	//	);
    //
	//	Telephony.Rest.Contacts.Fetch(
	//		authData,
	//		function(jqxhr,status) {
	//			// TODO handle problems
	//			var resp = jqxhr.responseJSON;
	//			setContacts(resp.contacts);
	//		},
	//		function(jqxhr,status,error) {
	//			// TODO handle problems
	//		},
	//		false
	//	);
    //
	//	var params2 = {
	//		contacts : contacts,
	//		stores : stores
	//	};
    //
	//	var template = $('#products-search-form-template').html();
    //
	//	var rendered = Mustache.render(template, params2);
    //
	//	$('#search-products-filters').html(rendered);
    //
	//	if ($.fn.datepicker) {
	//		$('.date').each(function() {
    //
	//			var $this = $(this);
	//			var dataDateFormat = $this.attr('data-dateformat') || 'dd.mm.yy';
    //
	//			$this.datepicker({
	//				dateFormat : dataDateFormat,
	//				prevText : '<i class="fa fa-chevron-left"></i>',
	//				nextText : '<i class="fa fa-chevron-right"></i>'
	//			});
	//		});
	//	}
    //
	//	$('#filter-results').click(function() {
	//		fetchAndUpdateView();
	//	});
    //
	//	$('#clear-form').click(function() {
	//		clearSearchForm();
	//	});
    //
	//	$('.tooltip').tooltip();
	//};

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

	//createFiltersView();

	displayTopFormView();

	var tmpl = $('#add-sale-submenu').html();

	var ren = Mustache.render(tmpl, {});

	$('#submenu0').html(ren);

	fetchAndUpdateView();


});


