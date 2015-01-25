
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
		
		'underscore' : {
			exports : '_'
		},
		
	    'backbone': {
	        deps: ['jquery','underscore'],
	        exports: 'Backbone'
	    },
	    
	    'bootstrap' : {	    	
	    	deps : ['jquery']
	    }
	}
});

require(['app', 'jquery-ui', 'mustache', 'rest'],
function(App, $, Mustache, rest) {

	var authData = {
		"username" : "user1@gmail.com",
		"sessionId" : "vugg6ghant1gm4vfjlbhe5das1",
		"filters" : {}
	};

	Telephony.Rest.Deliveries.Fetch(
		authData,
		function(jqXHR, status) {

			var resp = jqXHR.responseJSON;

			var params = {
				deliveries : resp.deliveries,
				listlabel : 'Dostawy'
			};

			var template = $('#deliveries-list-template').html();

			var rendered = Mustache.render(template, params);

			$('#content').html(rendered);

		},
		function() {

		}
	);

});
