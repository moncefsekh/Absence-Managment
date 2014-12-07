angular.module('etudiantApp.ActualitesFactory', [])
	.factory('ActualitesModel', function($resource) {
		var rootUrl = "../rest/service/";
		return {
			actualitesJour: function() {
				return $resource(rootUrl+'getActualiteJour/:code_specialite/:annee_specialite', {}, {
					get: {method:'GET',params:{code_specialite:'@code_specialite',annee_specialite:'@annee_specialite'}, isArray:true}
					
				});
			},

			

			actualitesSemaine: function() {
				return $resource(rootUrl+'getActualiteSemaine/:code_specialite/:annee_specialite', {}, {
					get: {method:'GET',params:{code_specialite:'@code_specialite',annee_specialite:'@annee_specialite'}, isArray:true}
				});
			},
			modules:function () {
				return $resource(rootUrl+'getModulesJSON/:code_specialite/:annee_specialite', {}, {
					query: {method:'GET',params:{code_specialite:'@code_specialite',annee_specialite:'@annee_specialite'}, isArray:true}
				});
			}
		}
});

