angular.module('responsableApp.ParametresCtrl', [])
	.controller('ParametresCtrl', function ($scope,$rootScope,$routeParams,AgentsModel){
		//$scope.datas="ParametresCtrl";
		$rootScope.getAppParams.$promise.then(function(){
			$scope.parametres = $rootScope.AppParams;
			console.log($rootScope.AppParams.nbrAbsence);
			$rootScope.setLoadingPage(false);
		});

		$scope.refreshAgents = function () {
			AgentsModel.agents().get(function (agents) {
				$scope.agents = agents
			});
		};
		$scope.refreshAgents();



		/*ParametresModel.get(function (parametres) {
			console.log(parametres.parametres.nbrAbsence);
		});*/
	});