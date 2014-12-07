angular.module('enseignantApp.EtudiantsFactory', [])
	.factory('EtudiantsModel', function($resource) {
		var rootUrl = "http://localhost:8080/GSA_TLSI_Backend/service/";
		return {
			etudiants: function() {
				return $resource(rootUrl+'getEtudiantGroupe/:code_spec/:annee/:code_sec/:code_gr/:idSeance', {}, {
					get: {method:'GET',params:{code_spec:'@code_spec',annee:'@annee',code_sec:'@code_sec',code_gr:'@code_gr',idSeance:'@idSeance'}, isArray:true}
				});
			},

			absents: function() {
				return $resource(rootUrl+'getAbsents/:idSeance/:idDate', {}, {
					get: {method:'GET',params:{idSeance:'@idSeance',idDate:'@idDate'}, isArray:true}
				});
			},

			absences:function () {
				return $resource(rootUrl+"postAbsences",{},{
					post:{method:'POST', params:{post:true , idSeance:'@idSeance',dateSeance:'@dateSeance',ids:'@ids',idDate:'@idDate'} }
				});
			}
		}
});

//post: {method:'POST',params:{post:true,reclamation:'@reclamation'}}
/*reclamation: function() {
				return $resource(rootUrl+'postReclamation', {}, {
					post: {method:'POST',params:{post:true,reclamation:'@reclamation'}}
				})
			},*/