angular.module('responsableApp.AgentsFactory', [])
	.factory('AgentsModel', function($resource) {
		var rootUrl = "../rest/service/";
		return {
			agents: function() {
				return $resource(rootUrl+'getAgents', {}, {
					get: {method:'GET', isArray:true}
				});
			},
			creerAgent:function  () {
				return $resource(rootUrl+'ajouterAgent', {}, {
					post: {method:'POST',params:{post:true,
							nom:'@nom',
							prenom:'@prenom'
						}
					}
				})
			},
			modifierAgent:function  () {
				return $resource(rootUrl+'modifierAgent', {}, {
					put: {method:'PUT',params:{
							idAgent:'@idAgent',
							nom:'@nom',
							prenom:'@prenom'
						}
					}
				})
			}
		}
	});

