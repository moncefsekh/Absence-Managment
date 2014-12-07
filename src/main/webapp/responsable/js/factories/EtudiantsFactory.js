angular.module('responsableApp.EtudiantsFactory', [])
	.factory('EtudiantsModel', function($resource) {
		var rootUrl = "../rest/service/";
		//var rootUrl = "http://localhost:8080/GSA_TLSI_Backend/rest/service/";
		return {
			etudiants: function() {
				return $resource(rootUrl+'getEtudiantsJSON/:specialite/:annee', {}, {
					get: {method:'GET', params:{specialite:'@specialite',annee:'@annee'},isArray:true}
				});
			},
			absencesJour:function() {
				return $resource(rootUrl+'nombre-absences-:specialite-:annee-jour.json', {}, {
					get: {method:'GET', params:{specialite:'@specialite',annee:'@annee'}}
				});
			},
			modules: function() {
				return $resource(rootUrl+'getEtatExclusion/:idEtudiant', {}, {
					get: {method:'GET', params:{idEtudiant:'@idEtudiant'},isArray:true}
				});
			},


			/*  Ajouter */

			absencesJourSpecialite:function () {
				return $resource(rootUrl+'getNbrAbsents/:specialite', {}, {
					get: {method:'GET', params:{specialite:'@specialite'}}
				});			
			},
			nombreTotalEtudiants:function () {
				return $resource(rootUrl+'getNbrEtudiant/:specialite', {}, {
					get: {method:'GET', params:{specialite:'@specialite'}}
				});			
			}

			/*End Ajouter*/
		}
});

