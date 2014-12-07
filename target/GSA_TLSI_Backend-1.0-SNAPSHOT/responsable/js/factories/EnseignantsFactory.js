angular.module('responsableApp.EnseignantsFactory', [])
	.factory('EnseignantsModel', function($resource) {
		var rootUrl = "../rest/service/";
		//var rootUrl = "http://localhost:8080/GSA_TLSI_Backend/rest/service/";
		return {
			enseignants: function() {
				return $resource(rootUrl+'getEnseignantJSON/:specialite', {}, {
					get: {method:'GET',params:{specialite:'@specialite'}, isArray:true}
				});
			},
			allEnseignants: function() {
				return $resource(rootUrl+'getAllEnseignantJSON', {}, {
					get: {method:'GET',isArray:true}
				});
			},
			actuellement:function() {
				return $resource(rootUrl+'getEnseignantActuel', {}, {
					get: {method:'GET', isArray:true}
				});
			},
			modules: function() {
				return $resource(rootUrl+'getModulesEnseignant/:idEnseignant', {}, {
					get: {method:'GET', params:{idEnseignant:'@idEnseignant'},isArray:true}
				});
			},
			ajouterEnseignant:function  () {
				return $resource(rootUrl+'ajouterEnseignant', {}, {
					post: {method:'POST',params:{post:true,
						nom:'@nom',
						prenom:'@prenom',
						dateNaissance:'@dateNaissance',
						grade:'@grade'
						}
					}
				})
			},
			modifierEnseignant:function  () {
				return $resource(rootUrl+'modifierEnseignant', {}, {
					put: {method:'PUT',params:{
						idEnseignant:'@idEnseignant',
						nom:'@nom',
						prenom:'@prenom',
						dateNaissance:'@dateNaissance',
						grade:'@grade'
						}
					}
				})
			}
		}
	});

