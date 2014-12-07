angular.module('etudiantApp.EtudiantFactory', [])
	.factory('EtudiantModel', function($resource) {
		var rootUrl = "../rest/service/";
		return {
			InfoEtudiant: function() {
				return $resource(rootUrl+'getInfoEtudiant', {}, {
					get: {method:'GET', isArray:false}
				});
			},
			getSession:function  () {
				return $resource(rootUrl+'getSessionEtudiant', {}, {
					get: {method:'GET', isArray:false}
				});
			}
		}

});

