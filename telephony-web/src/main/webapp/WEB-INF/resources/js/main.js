
require.config({
	
	baseUrl : 'resources/js',
	paths : {		
		
		'bootstrap' : 'bootstrap/bootstrap',
		'bootstrap-progressbar' : 'plugin/bootstrap-progressbar',
		'bootstrap-slider' : 'plugin/bootstrap-slider',
		'bootstrap-tagsinput' : 'plugin/bootstrap-tags',
		'bootstrap-timepicker' : 'plugin/bootstrap-timepicker',
		'bootstraptree' : 'plugin/bootstraptree',
		'jquery-bootstrap-wizard' : 'plugin/bootstrap-wizard',
		'jquery-ui' : 'libs/jquery-ui-1.10.3.min',
		'jquery' : 'libs/jquery-1.11.0.min',
		'mustache' : 'libs/mustache.min',
		'underscore' : 'libs/underscore-1.5.2',
		'backbone' : 'libs/backbone-1.1.0',		
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
		'jquery-fullcalendar' : 'plugin/fullcalendar/jquery.fullcalendar.min'

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

require(['app', 'jquery-ui', 'mustache'],
function(App, $, mustache) {

	var authData = {
		"username" : "user1@gmail.com",
		"password" : "rfaysdhaiufsiuf"
	};

	$.ajax({
		url: 'http://127.0.0.1:8080/telephony-ws/rest/session/initialize',
		type: 'POST',
		headers : {
			'Content-type' : 'application/json'
			//'Content-type' : 'text/plain; charset=UTF-8'
		},
		dataType : 'json',
		data : JSON.stringify(authData),
		complete : function(jqXHR, status) {
			alert(status)
		}
	});


	if ($.fn.datepicker) {
		$('#asdasd').each(function() {

			var $this = $(this);
			var dataDateFormat = $this.attr('data-dateformat') || 'dd.mm.yy';

			$this.datepicker({
				dateFormat : dataDateFormat,
				prevText : '<i class="fa fa-chevron-left"></i>',
				nextText : '<i class="fa fa-chevron-right"></i>'
			});
		});
	}
	
});
