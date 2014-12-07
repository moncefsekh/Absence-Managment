angular.module('enseignantApp.SeancesFactory', [])
	.factory('SeancesModel', function($resource) {
		var rootUrl = "http://localhost:8080/GSA_TLSI_Backend/service/";
		return {
			seancesJour: function() {
				return $resource(rootUrl+'getseanceJson/:id', {}, {
					get: {method:'GET', params: {id:"@id"}, isArray:true}
				});
			},
			seancesSemaine: function() {
				return $resource(rootUrl+'getseanceSemaineJson/:id', {}, {
					get: {method:'GET', params: {id:"@id"}, isArray:true}
				});
			},

			seances: function() {
				return $resource(rootUrl+'getAllseanceJson/:id', {}, {
					get: {method:'GET', params: {id:"@id"}, isArray:true}
				});
			},
		
			seance : function () {
				return $resource(rootUrl+'getDatesSeance/:id', {},{
					get :{method:'GET',params:{id:"@id"} , isArray:false}
				});
			},
			enseignements: function() {
				return $resource(rootUrl+'enseignements.json', {}, {
					get: {method:'GET', isArray:true}
				});
			}

				
				/*
				if(ensCode){
					return $resource(rootUrl+'seances_ens.json', {}, {
						get: {method:'GET', isArray:true}
					});
				}else if(groupeCode){
					return $resource(rootUrl+'seances_groupe.json', {}, {
						get: {method:'GET', isArray:true}
					});
				}else if(secCode){
					return $resource(rootUrl+'seances_sec.json', {}, {
						get: {method:'GET', isArray:true}
					});
				}else{
					return $resource(rootUrl+'seances.json', {}, {
						get: {method:'GET', isArray:true}
					});
				}
				*/
				
			}
		}
);

