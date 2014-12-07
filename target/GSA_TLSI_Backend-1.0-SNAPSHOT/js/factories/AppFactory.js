angular.module('App.AppFactory', ['ngResource'])
	.factory('AppModel', function($resource) {
		
		var rootUrl = "rest/service/";
		return {
			authentificate: function() {
				return $resource(rootUrl+':typeUser', {}, {
					post: {method:'POST',params:{post:true,
						typeUser:'@typeUser',
						nomUtilisateur:'@nomUtilisateur',
						motDePasse:'@motDePasse'}
					}
				})
			},

			InfoEtudiant: function() {
				return $resource(rootUrl+'getInfoEtudiant', {}, {
					get: {method:'GET', isArray:false}
				});
			},

			InfoEnseignant:function () {
				return $resource(rootUrl+'getInfoEnseignant', {}, {
					get: {method:'GET', isArray:false}
				});
			},

			InfoResponsable:function () {
				return $resource(rootUrl+'getInfoResponsable', {}, {
					get: {method:'GET', isArray:false}
				});
			}
		}
		
	});

