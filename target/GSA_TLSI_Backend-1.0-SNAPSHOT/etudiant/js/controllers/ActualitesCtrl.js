angular.module('etudiantApp.ActualitesCtrl', [])
	.controller('ActualitesCtrl', function ($scope,$location,ActualitesModel,EtudiantModel) {
		console.log(EtudiantModel.InfoEtudiant().get({id:1}));
		$scope.datas = "ActualitesContrl";
		$scope.actualites = ActualitesModel.actualitesJour().get({code_specialite:'GL',annee_specialite:'L2'});
		$scope.actualitesSemaine = ActualitesModel.actualitesSemaine().get({code_specialite:'GL',annee_specialite:'L2'});
		
		$scope.getModule = function (code) {
			$scope.titreListe = 'Les actualités du module '+code;
			$scope.actualites = ActualitesModel.actualitesJour().get({code_specialite:'GL',annee_specialite:'L2'});

		}

	});