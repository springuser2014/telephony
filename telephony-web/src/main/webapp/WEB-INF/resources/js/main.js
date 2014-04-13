
require.config({
	
	baseUrl : 'resources/js',
	paths : {		
		
		jquery : 'libs/jquery-1.11.0.min',
		underscore : 'libs/underscore-1.5.2',
		backbone : 'libs/backbone-1.1.0'
	},
	
	shim: {
	    'backbone': {
	        deps: ['jquery','underscore'],
	        exports: 'Backbone'
	    }
	}
});

require([
 'app'
 ], function(App) {
	alert(1);
	//App.initialize();
}
);