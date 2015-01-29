
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


require(['app', 'jquery-ui', 'mustache', 'jquery-cookie', 'rest', 'auth'],
function(App, $, Mustache, cookies, rest, auth) {

	var contacts = [];
	var stores = [];
	var filters = {};
	var oldFilters = {};
	var page = 0;
	var perPage = 5;
	var numberOfResults = 0;

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

	var calculateNumberOfPages = function() {
		return Math.ceil(getNumberOfResults() / getCurrentPerPage());
	}

	var clearSearchForm = function() {
		$('#label').val('');
		$('#maxNumberOfProducts').val('');
		$('#minNumberOfProducts').val('');
		$('#sum_from').val('');
		$('#sum_to').val('');
		$('#date_from').val('');
		$('#date_to').val('');
		$('#store').val(0);
		$('#contact').val(0);
	};

	var getCurrentFormFilterValues = function() {

		var obj = {
			maxNumberOfProducts : ($('#maxNumberOfProducts').val().length > 0 ? $('#maxNumberOfProducts').val() : null),
			minNumberOfProducts : ($('#minNumberOfProducts').val().length > 0 ? $('#minNumberOfProducts').val() : null),
			label : ($('#label').val().length > 0 ? $('#label').val() : null),
			deliveryDateEnd : ($('#date_to').val().length > 0 ? $('#date_to').val() : null),
			deliveryDateStart : ($('#date_from').val().length > 0 ? $('#date_from').val() : null),
			sumFrom : ($('#sum_from').val().length > 0 ? $('#sum_from').val() : null),
			sumTo : ($('#sum_to').val().length > 0 ? $('#sum_to').val() : null),
			storeId : ($('#store').val()),
			contactId : ($('#contact').val()),
			page : getCurrentPage(),
			perPage : getCurrentPerPage()
		};

		return obj;
	};

	var changePage = function(e) {

		var asd= 1 ;
		alert(e);
	};

	var fetchAndUpdateView = function() {

		var authData = Telephony.Auth.getAuthObj();
		authData.filters = getCurrentFormFilterValues();

		Telephony.Rest.Deliveries.Fetch(
			authData,
			function(jqXHR, status) {

				var resp = jqXHR.responseJSON;

				var params1 = {
					deliveries : resp.deliveries
				};

				setNumberOfResults(resp.countTotal);

				params1.pagination = buildPaginationObj();

				var template = $('#deliveries-list-template').html();

				var rendered = Mustache.render(template, params1);

				$('#deliveries-content').html(rendered);

				$('li:not(.disabled) .changePage').click(function(e) {
					var page = $(e.target).attr('page');
					var p = parseInt(page);

					setCurrentPage(p);
					fetchAndUpdateView();
				});

			},
			function() {

			}
		);
	};

	var addDeliveryView = function() {
		var tmpl = $('#delivery-add-template').html();

		var rendered = Mustache.render(tmpl, {});

		$('#submenu2').html(rendered);
	};

	Telephony.Auth.authorize('user1@gmail.com', 'rfaysdhaiufsiuf');

	var createFiltersView = function() {

		var authData = Telephony.Auth.getAuthObj();
		authData.filters = {};

		Telephony.Rest.Stores.Fetch(
			authData,
			function(jqXHR, status) {
				var resp = jqXHR.responseJSON;
				stores = resp.stores;
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
			contacts : contacts,
			stores : stores
		};

		var template = $('#deliveries-search-form-template').html();

		var rendered = Mustache.render(template, params2);

		$('#submenu').html(rendered);

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

	createFiltersView();

	fetchAndUpdateView();

	addDeliveryView();

});


