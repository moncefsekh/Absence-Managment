angular.module('enseignantApp.EnseignementsFactory', [])
	.factory('EnseignementsModel', function($resource) {
		var rootUrl = "";
		return {
			enseignements: function() {
				return $resource(rootUrl+'enseignements.json', {}, {
					get: {method:'GET', isArray:true}
				});
			}
		}
});

