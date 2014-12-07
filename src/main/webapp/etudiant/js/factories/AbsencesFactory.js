angular.module('etudiantApp.AbsencesFactory', [])
	.factory('AbsencesModel', function($resource) {
		var rootUrl = "../rest/service/";
		return {
			absences: function() {
				return $resource(rootUrl+'getAbsencesJSON/:id', {}, {
					get: {method:'GET',params:{id:'@id'}, isArray:true}
				})
			},

			etat_exclusion: function() {
				return $resource(rootUrl+'getEtatExclusion/:id', {}, {
					get: {method:'GET',params:{id:'@id'}, isArray:true}
				})
			},

			reclamation: function() {
				return $resource(rootUrl+'postReclamation', {}, {
					post: {method:'POST',params:{post:true,reclamation:'@reclamation'}}
				})
			},
			
			absencesSemaine: function() {
				return $resource(rootUrl+'getAbsenceSemaine/:id', {}, {
					get: {method:'GET',params:{id:'@id'}, isArray:true}
				});
			}
			
			

		}

});

