angular.module('responsableApp.ParametresFactory', [])
	.factory('ParametresModel', function($resource) {
		var rootUrl = "../rest/service/";
		//var rootUrl = "http://localhost:8080/GSA_TLSI_Backend/rest/service/";
		return $resource(rootUrl+'getParametres', {}, {
			get:{method:'GET'}
		});
			
	});

