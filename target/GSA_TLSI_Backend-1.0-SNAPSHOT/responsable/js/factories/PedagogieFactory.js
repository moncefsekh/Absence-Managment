angular.module('responsableApp.PedagogieFactory', [])
	.factory('PedagogieModel', function($resource) {
		var rootUrl = "../rest/service/";
		//var rootUrl = "http://localhost:8080/GSA_TLSI_Backend/rest/service/";
		return {
			
				
			modules: function() {
				return $resource(rootUrl+'getModulesJSON/:specialite', {}, {
					get: {method:'GET',params:{specialite:'@specialite'}, isArray:true}
				});
			},
			groupes: function() {
				return $resource(rootUrl+'getGroupesJSON/:specialite', {}, {
					get: {method:'GET',params:{specialite:'@specialite'}, isArray:true}
				});
			},
			sections: function() {
				return $resource(rootUrl+'getSectionJSON/:specialite', {}, {
					get: {method:'GET',params:{specialite:'@specialite'}, isArray:true}
				});
			},

			/* a Ajouter */

			specialites:function () {
				return $resource(rootUrl+'getSpecialiteJson', {}, {
					get: {method:'GET',isArray:true}
				});
			},
			seances:function  () {
				return $resource(rootUrl+'getSeancesJson/:specialite', {}, {
					get: {method:'GET',params:{specialite:'@specialite'},isArray:true}
				});
			},
			annees:function () {
				return $resource(rootUrl+'getAnneeJson', {}, {
					get: {method:'GET',isArray:true}
				});
			},
			creerModule: function() {
				return $resource(rootUrl+'ajouterModule', {}, {
					post: {method:'POST',params:{post:true,
						code_module:'@code_module',
						nom_module:'@nom_module',
						nbrCredit:'@nbrCredit',
						semestre:'@semestre',
						idSpecialite:'@idSpecialite'}
					}
				})
			},
			modifierModule: function() {
				return $resource(rootUrl+'modifierModule', {}, {
					put: {method:'PUT',params:{put:true,
						id_Module:'@id_Module',
						code_module:'@code_module',
						nom_module:'@nom_module',
						nbrCredit:'@nbrCredit',
						semestre:'@semestre',
						idSpecialite:'@idSpecialite'}
					}
				})
			},
			
			creerGroupe: function() {
				return $resource(rootUrl+'ajouterGroupe', {}, {
					post: {method:'POST',params:{post:true,
						codeGroupe:'@codeGroupe',
						nomGroupe:'@nomGroupe',
						idSection:'@idSection'}
					}
				})
			},
			modifierGroupe: function() {
				return $resource(rootUrl+'modifierGroupe', {}, {
					put: {method:'PUT',params:{put:true,
						id_Groupe:'@id_Groupe',
						codeGroupe:'@codeGroupe',
						nomGroupe:'@nomGroupe',
						idSection:'@idSection'}
					}
				})
			},
			creerSection: function() {
				return $resource(rootUrl+'ajouterSection', {}, {
					post: {method:'POST',params:{post:true,
						codeSection:'@codeSection',
						nomSection:'@nomSection',
						idSpecialite:'@idSpecialite'}
					}
				})
			},
			modifierSection: function() {
				return $resource(rootUrl+'modifierSection', {}, {
					put: {method:'PUT',params:{put:true,
						idSection:'@idSection',
						codeSection:'@codeSection',
						nomSection:'@nomSection',
						idSpecialite:'@idSpecialite'}
					}
				})
			},
			creerSpecialite: function() {
				return $resource(rootUrl+'ajouterSpecialite', {}, {
					post: {method:'POST',params:{post:true,
						codeSpecialite:'@codeSpecialite',
						nomSpecialite:'@nomSpecialite',
						}
					}
				})
			},
			modifierSpecialite: function() {
				return $resource(rootUrl+'modifierSpecialite', {}, {
					put: {method:'PUT',params:{put:true,
						id_Specialite:'@id_Specialite',
						codeSpecialite:'@codeSpecialite',
						nomSpecialite:'@nomSpecialite'}
					}
				})
			},
				modifierSeance:function () {
				return $resource(rootUrl+'modifierSeance', {}, {
					put: {method:'PUT',params:{put:true,
						idSeance:'@idSeance',
						type:'@type',
						idModule:'@idModule',
						idEnseignant:'@idEnseignant',
						dateSeance:'@dateSeance',
						heureSeance:'@heureSeance'}
					}
				})
			},
			creerSeance:function () {
				return $resource(rootUrl+'ajouterSeance', {}, {
					post: {method:'POST',params:{post:true,
						idModule:'@idModule',
						type:'@type',
						idEnseignant:'@idEnseignant',
						dateSeance:'@dateSeance',
						heureSeance:'@heureSeance'}
					}
				})
			},
			changerSeance:function () {
				return $resource(rootUrl+'changerSeance', {}, {
					put: {method:'PUT',params:{put:true,
						idDates:'@id_Dates',
						dateChangement:'@dateChangement',
						heureChangement:'@heureChangement'}
					}
				})
			},

			/* end Ajouter */
		}
	});

