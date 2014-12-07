angular.module('enseignantApp.StatistiquesCtrl', [])
	.controller('StatistiquesCtrl',function ($scope,EtudiantsModel) {
		$scope.allAbsences = EtudiantsModel.etudiants().get({cat:'absences.json'});
		$scope.getAbsSection = function (code) {
			$scope.allAbsences = EtudiantsModel.etudiants().get({cat :"absences.json"});
		};
		$scope.getAbsGroupe = function (code) {
			$scope.allAbsences = EtudiantsModel.etudiants().get({cat :"absGroupe.json"});
		};
		$scope.getAbsEns = function (code) {
			$scope.allAbsences = EtudiantsModel.etudiants().get({cat :"absEns.json"});
		};
	});