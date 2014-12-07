angular.module('enseignantApp.SectionsFactory', [])
	.factory('SectionsModel', function($resource) {
		var rootUrl = "";
		return {
			sections: function() {
				return $resource(rootUrl+'sections.json', {}, {
					get: {method:'GET', isArray:true}
				});
			}
		}
});

