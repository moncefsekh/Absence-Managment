angular.module('responsableApp.StatistiquesFactory', [])
	.factory('StatistiquesModel', function($resource) {
		//var rootUrl = "http://localhost:8080/GSA_TLSI_Backend/rest/service/";
		var rootUrl = "../rest/service/";
		return {
			/*  Ajouter */
			absencesSpecialite: function() {
				return $resource(rootUrl+'getNbrAbsencesSemaine/:specialite', {}, {
					get: {method:'GET',params:{specialite:'@specialite'},isArray:true}
				});
			},
			absencesModules: function() {
				return $resource(rootUrl+'statistiques-modules-:specialite-:annee-:semestre.json', {}, {
					get: {method:'GET', params:{specialite:'@specialite',annee:'@annee',semestre:'@semestre'},isArray:true}
				});
			},
			etudiantsExclus:function () {
				return $resource(rootUrl+'getExclusModule/:specialite', {}, {
					get: {method:'GET', params:{specialite:'@specialite'},isArray:true}
				});
			}
			/*,
			absencesJour:function() {
				return $resource(rootUrl+'nombre-absences-:specialite-:annee-jour.json', {}, {
					get: {method:'GET', params:{specialite:'@specialite',annee:'@annee'}}
				});
			},
			modules: function() {
				return $resource(rootUrl+'modules-etudiants-:idEtudiant.json', {}, {
					get: {method:'GET', params:{idEtudiant:'@idEtudiant'},isArray:true}
				});
			},



			absencesJourSpecialite:function () {
				return $resource(rootUrl+'absences-specialite-jour-:specialite.json', {}, {
					get: {method:'GET', params:{specialite:'@specialite'}}
				});			
			},
			nombreTotalEtudiants:function () {
				return $resource(rootUrl+'nombre-total-:specialite.json', {}, {
					get: {method:'GET', params:{specialite:'@specialite'}}
				});			
			},
			etudiantsExclus:function () {
				return $resource(rootUrl+'etudiants-exclus-:specialite.json', {}, {
					get: {method:'GET', params:{specialite:'@specialite'},isArray:true}
				});
			}

			End Ajouter*/
		}
});

