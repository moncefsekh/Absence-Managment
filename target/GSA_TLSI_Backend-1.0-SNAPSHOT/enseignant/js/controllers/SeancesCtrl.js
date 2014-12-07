angular.module('enseignantApp.SeancesCtrl', [])
	.controller('SeancesCtrl', function ($scope,$location,SectionsModel,GroupesModel,SeancesModel) {
		$scope.seances = SeancesModel.seancesJour().get({id :"1"});
		
		
		if($location.path() != "/"){
		$scope.seances = SeancesModel.seances().get({id :"1"});
		}
		

		
	});