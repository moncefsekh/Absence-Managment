angular.module('enseignantApp.GroupesFactory', [])
	.factory('GroupesModel', function($resource) {
		var rootUrl = "";
		return {
			groupes: function() {
				return $resource(rootUrl+'groupes.json', {}, {
					get: {method:'GET', isArray:true}
				});
			}
		}
});

